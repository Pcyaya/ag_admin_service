package com.runyee.agdhome.service;

import com.runyee.agdhome.constant.AppUrl;
import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.dao.ag_home.NewsDao;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.entity.db.ag_home.*;
import com.runyee.agdhome.entity.ex.NewsBean;
import com.runyee.agdhome.entity.form.NewsDetailForm;
import com.runyee.agdhome.entity.form.NewsForm;
import com.runyee.agdhome.entity.page.*;
import com.runyee.agdhome.entity.pagebean.ManNewsPageBean;
import com.runyee.agdhome.entity.pagebean.NewsPageBean;
import com.runyee.agdhome.entity.pagebean.RelevantNewsPageBean;
import com.runyee.agdhome.util.ConvertUtil;
import com.runyee.agdhome.util.DataUtil;
import com.runyee.agdhome.util.DateUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Created by cheri on 2018/2/24.
 */
@Service
public class NewsService {
    @Autowired
    private NewsDao dao;

    public String center(AppJson app){
        Date date = new Date();
        String service_code = ServiceCode.ERRO;
        NewsCenterPage page = new NewsCenterPage();
        AhNewsIndex index = dao.getNewsIndexOneLast();
        String filter = "";
        filter += " and an_audit = 1 ";
        NewsBean news = null;
        if(index==null || ConvertUtil.isEmpty(index.getAni_id())){
            //最新新闻
            filter += " and ifnull(an_title,'')<> '' and ifnull(an_content,'')<> '' ";
            filter += " and ( exists  (select nd.and_url " +
                    "       from ah_news_detail nd  " +
                    "       where ifnull(nd.del,0)=0 and nd.and_news=an.an_id " +
                    "       and ifnull(nd.and_type,0)=1  )  or ifnull(an_url,'')<>'' )";
            news = dao.getNewsOneFilter(filter);
            if(news!=null && !ConvertUtil.isEmpty(news.getAn_id())){
                //添加最新新闻
                index = new AhNewsIndex();
                index.setAni_news(news.getAn_id());
                index.setDel(0);
                index.setCreate_date(date);
                dao.insertNewsIndex(index);
            }
        }else{
            news = dao.getNewsBeanOneKey(index.getAni_news());
        }

        if(news!=null && !ConvertUtil.isEmpty(news.getAn_id())){
            NewsPage newspage = new NewsPage();
            newspage.setId(news.getAn_id());
            newspage.setBusiness(news.getAn_business());
            newspage.setTitle(news.getAn_title());
            newspage.setContent(news.getAn_content());
            newspage.setOrigin(news.getAn_origin());
            newspage.setSource(news.getAn_source());
            newspage.setSource_name(news.getSource_name());
            if(!ConvertUtil.isEmpty(news.getAn_url())){
                newspage.setImg(news.getAn_url());
            }else{
                newspage.setImg(news.getImg());
            }

            if(!ConvertUtil.isEmpty(newspage.getImg())){
                if(newspage.getImg().startsWith("http://")||newspage.getImg().startsWith("https://")){

                }else{
                    newspage.setImg(AppUrl.news_url+newspage.getImg());
                }
            }
            newspage.setDate(news.getCreate_date());
            page.setNews(newspage);
        }

        //新闻列表
        filter = "";
        filter += " and an_audit = 1 ";
        List<AhNews> list = dao.getNewsList(filter,0,4);
        if(list!=null && list.size()> 0){
            List<NewsPage> pages = new ArrayList<>();//新闻列表
            for(AhNews ahnews:list){
                NewsPage newspage = new NewsPage();

                newspage.setId(ahnews.getAn_id());
                newspage.setBusiness(ahnews.getAn_business());
                newspage.setTitle(ahnews.getAn_title());
                newspage.setContent(ahnews.getAn_content());
                newspage.setImg(ahnews.getAn_url());
                newspage.setDate(ahnews.getCreate_date());

                pages.add(newspage);
            }
            page.setList(pages);
        }

        app.setObj(page);
        service_code  = ServiceCode.SUCCESS;
        return service_code;
    }


    public String list(NewsPageBean pageBean, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(pageBean!=null){
            //业务类型
            String business = pageBean.getBusiness();
            if(ConvertUtil.isEmpty(business)){
                AhNewsBusiness ahNewsBusiness = this.getNewsBusinessOneName("公司动态");
                business = ahNewsBusiness.getAnb_id();
            }
            List<AhNewsBusiness> blist = dao.getNewsBusinessAllList(0,5);
            if(blist!=null && blist.size()>0){
                List<NewsBusinessPage> blist_pages = new ArrayList<>();
                for(AhNewsBusiness busi:blist){
                    NewsBusinessPage b_page = new NewsBusinessPage();
                    b_page.setId(busi.getAnb_id());
                    b_page.setName(busi.getAnb_name());
                    if(business.equals(b_page.getId())){
                        b_page.setShow_flg(1);
                    }
                    blist_pages.add(b_page);

                }
                pageBean.setBlist(blist_pages);
            }
            //新闻列表

            String filter = " and an_business = '"+business+"' ";
            filter += " and an_audit = 1 ";
            //总数
            int total = dao.getNewsPagesTotal(filter);
            pageBean.setTotal(total);

            //List<AhNews> list = dao.getNewsList(filter,pageBean.getCurrPage(),pageBean.getRows());
            List<NewsBean> list = dao.getNewsBeanList(filter,pageBean.getCurrPage(),pageBean.getRows());

            if(list!=null && list.size()> 0){
                List<NewsPage> pages = new ArrayList<>();//新闻列表
                for(NewsBean ahnews:list){
                    NewsPage newspage = new NewsPage();


                    newspage.setId(ahnews.getAn_id());
                    newspage.setBusiness(ahnews.getAn_business());
                    newspage.setTitle(ahnews.getAn_title());
                    newspage.setContent(ahnews.getAn_content());
                    newspage.setDate(ahnews.getCreate_date());

                    newspage.setSource_name(ahnews.getSource_name());
                    newspage.setSource(ahnews.getAn_source());
                    if(!ConvertUtil.isEmpty(ahnews.getAn_url())){
                        newspage.setImg(ahnews.getAn_url());
                    }else{
                        newspage.setImg(ahnews.getImg());
                    }
                    if(!ConvertUtil.isEmpty(newspage.getImg())){
                        if(newspage.getImg().startsWith("http://")||newspage.getImg().startsWith("https://")){

                        }else{
                            newspage.setImg(AppUrl.news_url+newspage.getImg());
                        }
                    }
                    newspage.setOrigin(ahnews.getAn_origin());

                    pages.add(newspage);
                }
                pageBean.setData(pages);
            }

            app.setObj(pageBean);
            service_code  = ServiceCode.SUCCESS;
        }

        return service_code;
    }


    public String  content(String id, String business,AppJson app){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            String filter = "";
            filter += " and an2.an_audit = 1 ";
            if(!ConvertUtil.isEmpty(business)){
                filter += " and an2.an_business = '"+business+"' ";
            }
            Map<String,String> pages =  dao.getNewsPage(id,filter);
            if(pages!=null){
                NewsContentPage page = new NewsContentPage();
                //上一条
                String last = pages.get("last");

                if(!ConvertUtil.isEmpty(last)){
                    AhNews last_news = dao.getNewsOneKey(last);
                    if(last_news!=null&&!ConvertUtil.isEmpty(last_news.getAn_id())){
                        NewsPage last_page = new NewsPage();
                        last_page.setId(last_news.getAn_id());
                        last_page.setTitle(last_news.getAn_title());
                        last_page.setDate(last_news.getCreate_date());
                        page.setLast(last_page);
                    }
                }

                //当前
                String current = pages.get("current");

                if(!ConvertUtil.isEmpty(current)){
                    NewsBean curr_news = dao.getNewsBeanOneKey(current);
                    if(curr_news!=null&&!ConvertUtil.isEmpty(curr_news.getAn_id())){
                        NewsPage curr_page = new NewsPage();
                        curr_page.setId(curr_news.getAn_id());
                        curr_page.setTitle(curr_news.getAn_title());
                        curr_page.setContent(curr_news.getAn_content());
                        curr_page.setDate(curr_news.getCreate_date());

                        curr_page.setSource(curr_news.getAn_source());
                        curr_page.setSource_name(curr_news.getSource_name());
                        curr_page.setOrigin(curr_news.getAn_origin());

                        page.setCurrent(curr_page);
                        List<AhNewsDetail> contents = dao.getNewsDetailList(current);
                        if(contents!=null&&contents.size()>0){
                            List<NewsDetailPage> details = new ArrayList<>();
                            for(AhNewsDetail content:contents){
                                NewsDetailPage detail = new NewsDetailPage();
                                detail.setTitle(content.getAnd_title());
                                detail.setContent(content.getAnd_content());
                                detail.setType(content.getAnd_type());
                                detail.setUrl(content.getAnd_url());
                                if(!ConvertUtil.isEmpty(detail.getUrl())){
                                    if(detail.getUrl().startsWith("http://")||detail.getUrl().startsWith("https://")){

                                    }else{
                                        detail.setUrl(AppUrl.news_url+detail.getUrl());
                                    }
                                }
                                detail.setAlign(content.getAnd_align());

                                details.add(detail);
                            }
                            page.setDetails(details);
                        }

                        //类型名称
                        AhNewsBusiness newsBusiness  = dao.getNewsBusinessOneKey(curr_news.getAn_business());
                        if(newsBusiness!=null){
                            NewsBusinessPage businessPage = new NewsBusinessPage();
                            businessPage.setId(newsBusiness.getAnb_id());
                            businessPage.setName(newsBusiness.getAnb_name());
                            businessPage.setShow_flg(1);
                            page.setBusiness(businessPage);
                        }
                    }
                }


                //下一条
                String next = pages.get("next");
                if(!ConvertUtil.isEmpty(next)){
                    AhNews next_news = dao.getNewsOneKey(next);
                    if(next_news!=null&&!ConvertUtil.isEmpty(next_news.getAn_id())){
                        NewsPage next_page = new NewsPage();
                        next_page.setId(next_news.getAn_id());
                        next_page.setTitle(next_news.getAn_title());
                        next_page.setDate(next_news.getCreate_date());
                        page.setNext(next_page);
                    }
                }

                //相关新闻

                app.setObj(page);
                service_code = ServiceCode.SUCCESS;
            }

        }else{
            service_code = ServiceCode.fail_param_error;
        }

        return service_code;
    }

    public  List<AhNewsSource> getNewsSourceAll(){
        List<AhNewsSource> sources = null;
        sources = dao.getNewsSourceAll();
        return sources;
    }

    public  AhNewsBusiness getNewsBusinessOneName(String name){
        AhNewsBusiness business = null;
        business = dao.getNewsBusinessOneName(name);
        if(business==null|| ConvertUtil.isEmpty(business.getAnb_id())){
            Date date = new Date();
            business = new AhNewsBusiness();
            business.setAnb_name(name);
            business.setAnb_sort(0);
            business.setDel(0);
            business.setCreate_date(date);
            dao.insertNewsBusiness(business);
        }
        return business;
    }

    public  AhNews getNewsBusinessOneTitleDate(String title,String date){
        AhNews news = null;
        news = dao.getNewsBusinessOneTitleDate(title,date);
        return news;
    }

    public  AhNews getNewsBusinessOneOrigin(String origin){
        AhNews news = null;
        news = dao.getNewsBusinessOneOrigin(origin);
        return news;
    }

    public  void insertNews(AhNews news){
        if(news!=null){
            dao.insertNews(news);
        }
    }

    public  void insertNewsDetail(AhNewsDetail detail){
        if(detail!=null){
            dao.insertNewsDetail(detail);
        }
    }

    public  void deleteRepeatNews(){
        String filter = " and exists (select * " +
                "             from ah_news an2 " +
                "             where ifnull(an2.del,0)=0 and an2.an_title = an.an_title " +
                "             and an2.create_date = an.create_date and an2.an_id<>an.an_id " +
                "             order by an2.create_date desc,an2.an_id desc ) ";
        List<AhNews> list = dao.getNewsListFilter(filter);
        if(list!=null && list.size()>0){
            Map<String,AhNews> news_map = new HashMap<>();
            for(AhNews news:list){
                if(news.getCreate_date()!=null){
                    String key = news.getAn_title()+ DateUtils.gettimestamp(news.getCreate_date()).toString();
                    if(news_map.containsKey(key)){
                        this.deleteNews(news);
                    }else{
                        news_map.put(key,news);
                    }
                }else{
                    this.deleteNews(news);
                }
            }
        }

    }

    public  void deleteNews(AhNews news){
        if(news!=null){
            dao.deleteNews(news);
        }
    }

    public String  relevants(RelevantNewsPageBean pageBean, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(pageBean!=null){
            String id = pageBean.getId();
            String business = pageBean.getBusiness();
            if(!ConvertUtil.isEmpty(id)){
                String search = "";
                String filter = " and an.an_audit = 1 ";
                 filter += " and concat(an.create_date ,an.an_id) < " +
                        "               ifnull((select concat(an2.create_date ,an2.an_id) " +
                        "                           from ah_news  an2 " +
                        "                           where an2.an_id = '"+id+"' ),'') ";
                String order = " order by concat(an.create_date, an.an_id) desc ";
                if(!ConvertUtil.isEmpty(business)){
                    search = " and an.an_business = '"+business+"' ";
                }
                filter+=search;
                int limit = 4;
                List<AhNews> lasts =  dao.getNewsRelevants(id,filter,order,limit);
                //相关新闻
                List<NewsPage> relevants = new ArrayList<>();
                if(lasts!=null){
                    for(int i=0;i<lasts.size();i++){
                        AhNews last_news = lasts.get(i);
                        if(last_news!=null&&!ConvertUtil.isEmpty(last_news.getAn_id())){
                            NewsPage last_page = new NewsPage();
                            last_page.setId(last_news.getAn_id());
                            last_page.setTitle(last_news.getAn_title());
                            last_page.setDate(last_news.getCreate_date());
                            relevants.add(last_page);
                            limit--;
                            if(limit<=(limit/2)){
                                break;
                            }
                        }
                    }
                }

                filter = "";
                filter+=search;
                filter += " and an.an_audit = 1 ";
                filter+= " and concat(an.create_date ,an.an_id) > " +
                        "               ifnull((select concat(an2.create_date ,an2.an_id) " +
                        "                           from ah_news  an2 " +
                        "                           where an2.an_id = '"+id+"' ),'') ";
                order = " order by concat(an.create_date, an.an_id) asc ";
                List<AhNews> nexts =  dao.getNewsRelevants(id,filter,order,limit);
                if(nexts!=null){
                    for(int i=0;i<nexts.size();i++){
                        AhNews next_news = nexts.get(i);
                        if(next_news!=null&&!ConvertUtil.isEmpty(next_news.getAn_id())){
                            NewsPage last_page = new NewsPage();
                            last_page.setId(next_news.getAn_id());
                            last_page.setTitle(next_news.getAn_title());
                            last_page.setDate(next_news.getCreate_date());
                            relevants.add(last_page);
                            limit--;
                            if(limit<=0){
                                break;
                            }
                        }
                    }
                }
                pageBean.setData(relevants);
                app.setObj(pageBean);
                service_code = ServiceCode.SUCCESS;
            }else{
                service_code = ServiceCode.fail_param_error;
            }
        }

        return service_code;
    }

    public String man_business_list(AppJson app){
        String service_code = ServiceCode.ERRO;
        List<AhNewsBusiness> blist = dao.getNewsBusinessAllList(0,5);
        List<NewsBusinessPage> blist_pages = new ArrayList<>();
        if(blist!=null && blist.size()>0){
            for(AhNewsBusiness busi:blist){
                NewsBusinessPage b_page = new NewsBusinessPage();
                b_page.setId(busi.getAnb_id());
                b_page.setName(busi.getAnb_name());
                blist_pages.add(b_page);
            }
        }
        app.setObj(blist_pages);
        service_code  = ServiceCode.SUCCESS;
        return service_code;
    }

    public String man_source_list(String search,AppJson app){
        String service_code = ServiceCode.ERRO;
        String filter = "";
        if(!ConvertUtil.isEmpty(search)){
            filter = " and ans_name like '%"+search+"%' ";
        }
        List<AhNewsSource> sources = dao.getNewsSourceAllList(filter,0,10);
        List<SourcePage> source_pages = new ArrayList<>();
        if(sources!=null && sources.size()>0){
            for(AhNewsSource source:sources){
                SourcePage b_page = new SourcePage();
                b_page.setId(source.getAns_id());
                b_page.setName(source.getAns_name());
                source_pages.add(b_page);
            }
        }
        service_code  = ServiceCode.SUCCESS;
        app.setObj(source_pages);
        return service_code;
    }

    public String man_news_list(ManNewsPageBean pageBean, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(pageBean!=null){
            //新闻列表
            String filter = " ";
            //类型
            if(!ConvertUtil.isEmpty(pageBean.getBusiness())){
                filter += " and an_business = '"+pageBean.getBusiness()+"' ";
            }
            if(!ConvertUtil.isEmpty(pageBean.getTitle())){
                filter += " and an_title = '"+pageBean.getTitle()+"' ";
            }
            if(!ConvertUtil.isEmpty(pageBean.getSource())){
                filter += " and an_source = '"+pageBean.getSource()+"' ";
            }

            if(pageBean.getAudit()>=0){
                filter += " and an_audit = '"+pageBean.getAudit()+"' ";
            }
            //是否在app资讯中显示
            if(pageBean.getFlg()>0){
                //是
                filter += " and  an.an_id in (select *  " +
                        "                       from (select anti.anti_news " +
                        "                                       from ah_news_travel_info anti " +
                        "                                       where ifnull(anti.del,0)=0 " +
                        "                                       order by anti.create_date desc limit 5 ) as app ) ";
            }else if(pageBean.getFlg()==0){
                //否
                filter += " and  an.an_id not in (select *  " +
                        "                       from (select anti.anti_news " +
                        "                                       from ah_news_travel_info anti " +
                        "                                       where ifnull(anti.del,0)=0 " +
                        "                                       order by anti.create_date desc limit 5 ) as app ) ";
            }
            //发布日期
            if(!ConvertUtil.isEmpty(pageBean.getStart())){
                filter += " and date_format(create_date,'%Y-%m-%d') = '"+pageBean.getStart()+"' ";
            }
            //总数
            int total = dao.getNewsPagesTotal(filter);
            pageBean.setTotal(total);
            List<NewsBean> list = dao.getManNewsBeanList(filter,pageBean.getCurrPage(),pageBean.getRows());

            if(list!=null && list.size()> 0){
                List<NewsPage> pages = new ArrayList<>();//新闻列表
                for(NewsBean ahnews:list){
                    NewsPage newspage = new NewsPage();


                    newspage.setId(ahnews.getAn_id());
                    newspage.setBusiness(ahnews.getAn_business());
                    newspage.setTitle(ahnews.getAn_title());
                    newspage.setContent(ahnews.getAn_content());
                    newspage.setDate(ahnews.getCreate_date());

                    newspage.setBusiness_name(ahnews.getBusiness_name());
                    newspage.setApp_flg(ahnews.getApp_flg());
                    newspage.setIndex_flg(ahnews.getIndex_flg());
                    newspage.setAudit(ahnews.getAn_audit());

                    newspage.setSource_name(ahnews.getSource_name());
                    newspage.setSource(ahnews.getAn_source());
                    if(!ConvertUtil.isEmpty(ahnews.getAn_url())){
                        newspage.setImg(ahnews.getAn_url());
                    }else{
                        newspage.setImg(ahnews.getImg());
                    }
                    if(!ConvertUtil.isEmpty(newspage.getImg())){
                        if(newspage.getImg().startsWith("http://")||newspage.getImg().startsWith("https://")){

                        }else{
                            newspage.setImg(AppUrl.news_url+newspage.getImg());
                        }

                    }
                    newspage.setOrigin(ahnews.getAn_origin());

                    pages.add(newspage);
                }
                pageBean.setData(pages);
            }

            app.setObj(pageBean);
            service_code  = ServiceCode.SUCCESS;
        }

        return service_code;
    }

    public String man_del(String id,AppJson app){
        String service_code = ServiceCode.ERRO;
        AhNews news = dao.getNewsOneKey(id);
        if(news!=null && !ConvertUtil.isEmpty(news.getAn_id())){
            news.setDel(1);
            dao.updateDelNews(news);
            service_code  = ServiceCode.SUCCESS;
        }else{
            service_code  = ServiceCode.fail_man_news_del;
        }
        return service_code;
    }


    public String  man_content(String id,AppJson app){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            NewsBean news = dao.getManNewsBeanOneKey(id);
            if(news!=null&&!ConvertUtil.isEmpty(news.getAn_id())){
                NewsExPage news_page = new NewsExPage();
                news_page.setId(news.getAn_id());
                news_page.setTitle(news.getAn_title());
                news_page.setContent(news.getAn_content());
                news_page.setDate(news.getCreate_date());

                news_page.setSource(news.getAn_source());
                news_page.setSource_name(news.getSource_name());
                news_page.setOrigin(news.getAn_origin());

                news_page.setBusiness_name(news.getAn_business());
                news_page.setBusiness_name(news.getBusiness_name());
                news_page.setApp_flg(news.getApp_flg());
                news_page.setIndex_flg(news.getIndex_flg());
                news_page.setAudit(news.getAn_audit());

                if(!ConvertUtil.isEmpty(news.getAn_url())){
                    news_page.setImg(news.getAn_url());
                }else{
                    news_page.setImg(news.getImg());
                }
                /*if(!ConvertUtil.isEmpty(news_page.getImg())&&"ATG_0000000".equals(news_page.getSource())){
                    news_page.setImg(AppUrl.news_url+news_page.getImg());
                }*/
                if(news_page.getImg().startsWith("http://")||news_page.getImg().startsWith("https://")){

                }else{
                    news_page.setImg(AppUrl.news_url+news_page.getImg());
                }

                List<AhNewsDetail> contents = dao.getNewsDetailList(id);
                if(contents!=null&&contents.size()>0){
                    List<NewsDetailPage> details = new ArrayList<>();
                    for(AhNewsDetail content:contents){
                        NewsDetailPage detail = new NewsDetailPage();
                        detail.setTitle(content.getAnd_title());
                        detail.setContent(content.getAnd_content());
                        detail.setType(content.getAnd_type());
                        detail.setUrl(content.getAnd_url());

                        if(!ConvertUtil.isEmpty(detail.getUrl())){
                            if(detail.getUrl().startsWith("http://")||detail.getUrl().startsWith("https://")){

                            }else{
                                detail.setUrl(AppUrl.news_url+detail.getUrl());
                            }

                        }
                        detail.setAlign(content.getAnd_align());

                        details.add(detail);
                    }
                    news_page.setDetails(details);
                }
                app.setObj(news_page);
                service_code = ServiceCode.SUCCESS;
            }
        }else{
            service_code = ServiceCode.fail_param_error;
        }
        return service_code;
    }

    public String man_add_edit(NewsForm form, AppJson app){
        String service_code = ServiceCode.ERRO;
        Date date = new Date();
        if(form!=null){
            if(ConvertUtil.isEmpty(form.getId())){
                if(!ConvertUtil.isEmpty(form.getBusiness())&&
                        !ConvertUtil.isEmpty(form.getTitle())&&
                        !ConvertUtil.isEmpty(form.getContent())&&
                        !ConvertUtil.isEmpty(form.getImg())&&
                        !ConvertUtil.isEmpty(form.getDetails())){
                    //验证detail
                    List<NewsDetailForm> details = new ArrayList<>();

                    JSONArray details_json =  JSONArray.fromObject(form.getDetails());
                    if(details_json!=null){
                        details =  JSONArray.toList(details_json,NewsDetailForm.class);
                    }

                    if(details!=null && details.size()>0){
                        service_code = ServiceCode.SUCCESS;
                        //if(ServiceCode.SUCCESS.equals(service_code)){
                            //新增
                            NewsBean news = new NewsBean();
                            news.setAn_business(form.getBusiness());
                            news.setAn_title(form.getTitle());
                            news.setAn_content(form.getContent());
                            news.setAn_source(ServiceCode.news_source);//艾侗游
                            String img = form.getImg();
                            if(!ConvertUtil.isEmpty(img)){
                                img = img.replaceAll(AppUrl.news_url,"");
                            }
                            news.setAn_url(img);
                            news.setAn_origin("");
                            //news.setAn_audit(form.getAudit());
                            news.setAn_audit(0);
                            news.setDel(0);
                            news.setCreate_date(date);

                            dao.insertNews(news);
                            int i =0;
                            for(NewsDetailForm detail:details){
                                AhNewsDetail newsDetail = new AhNewsDetail();
                                newsDetail.setAnd_news(news.getAn_id());
                                newsDetail.setAnd_title(detail.getTitle());
                                newsDetail.setAnd_content(detail.getContent());
                                newsDetail.setAnd_type(detail.getType());
                                String url = detail.getUrl();
                                if(!ConvertUtil.isEmpty(url)){
                                    url = url.replaceAll(AppUrl.news_url,"");
                                }
                                newsDetail.setAnd_url(url);
                                newsDetail.setAnd_align(detail.getAlign());
                                newsDetail.setAnd_sort(i);
                                newsDetail.setDel(0);
                                newsDetail.setCreate_date(date);

                                dao.insertNewsDetail(newsDetail);
                                i++;
                            }
                            //app_flg 处理
                            this.sync_app_flg(form.getApp_flg(),news);
                            //index_flg处理
                            this.sync_index_flg(form.getIndex_flg(),news);
                        //}
                    }else{
                        service_code = ServiceCode.fail_man_news_empty;
                        List<String> msgs = new ArrayList<>();
                        String msg = "新闻详情";
                        msgs.add(msg);
                        DataUtil.getCurrent().setMsgs(msgs);
                    }
                }else{
                    service_code = ServiceCode.fail_man_news_empty;
                    List<String> msgs = new ArrayList<>();
                    String msg = "新闻类型";
                    if(ConvertUtil.isEmpty(form.getTitle())){
                        msg = "标题";
                    }else if(ConvertUtil.isEmpty(form.getContent())){
                        msg = "展示内容";
                    }else if(ConvertUtil.isEmpty(form.getImg())){
                        msg = "展示图片";
                    }else if(ConvertUtil.isEmpty(form.getDetails())){
                        msg = "新闻详情";
                    }
                    msgs.add(msg);
                    DataUtil.getCurrent().setMsgs(msgs);
                }
            }else{
                //更新
                NewsBean news = dao.getManNewsBeanOneKey(form.getId());
                if(news!=null && !ConvertUtil.isEmpty(news.getAn_id())){
                    if(!ConvertUtil.isEmpty(form.getBusiness())&&!form.getBusiness().equals(news.getAn_business())){
                        service_code = ServiceCode.SUCCESS;
                        news.setAn_business(form.getBusiness());
                    }
                    if(!ConvertUtil.isEmpty(form.getTitle())&&!form.getTitle().equals(news.getAn_title())){
                        service_code = ServiceCode.SUCCESS;
                        news.setAn_title(form.getTitle());
                    }
                    if(!ConvertUtil.isEmpty(form.getContent())&&!form.getContent().equals(news.getAn_content())){
                        service_code = ServiceCode.SUCCESS;
                        news.setAn_content(form.getContent());
                    }
                    String img = form.getImg();
                    if(!ConvertUtil.isEmpty(img)){
                        img = img.replaceAll(AppUrl.news_url,"");
                        if(!img.equals(news.getAn_url())){
                            service_code = ServiceCode.SUCCESS;
                            news.setAn_url(img);
                        }
                    }
                    /*if(form.getAudit()>=0){
                        service_code = ServiceCode.SUCCESS;
                        news.setAn_audit(form.getAudit());
                    }*/

                    if(ServiceCode.SUCCESS.equals(service_code)){
                        //更新新闻
                        dao.updateNews(news);
                    }
                    service_code = ServiceCode.SUCCESS;
                    if(!ConvertUtil.isEmpty(form.getDetails())){
                        List<NewsDetailForm> details = new ArrayList<>();
                        JSONArray details_json =  JSONArray.fromObject(form.getDetails());
                        if(details_json!=null){
                            details =  JSONArray.toList(details_json,NewsDetailForm.class);
                        }
                        if(details!=null && details.size()>0){
                            //删除之前所有详情
                            dao.updateDelNewsDetail(news.getAn_id());
                            int i =0;
                            for(NewsDetailForm detail:details){
                                AhNewsDetail newsDetail = new AhNewsDetail();
                                newsDetail.setAnd_news(news.getAn_id());
                                newsDetail.setAnd_title(detail.getTitle());
                                newsDetail.setAnd_content(detail.getContent());
                                newsDetail.setAnd_type(detail.getType());
                                String url = detail.getUrl();
                                if(!ConvertUtil.isEmpty(url)){
                                    url = url.replaceAll(AppUrl.news_url,"");
                                }
                                newsDetail.setAnd_url(url);
                                newsDetail.setAnd_align(detail.getAlign());
                                newsDetail.setAnd_sort(i);
                                newsDetail.setDel(0);
                                newsDetail.setCreate_date(date);

                                dao.insertNewsDetail(newsDetail);
                                i++;
                            }
                        }

                        //app_flg 处理
                        this.sync_app_flg(form.getApp_flg(),news);
                        //index_flg处理
                        this.sync_index_flg(form.getIndex_flg(),news);
                    }
                }else{
                    service_code = ServiceCode.fail_man_news_del;
                }
            }


        }
        return service_code;
    }

    public String sync_app_flg(int app_flg,NewsBean news){
        String service_code = ServiceCode.SUCCESS;
        //app_flg;//app新闻资讯显示 0.否1.是
        if(app_flg!=news.getApp_flg()){
            if(app_flg==0){
                dao.updateDelNewsTravelInfo(1,news.getAn_id());
            }else if(app_flg==1){
                AhNewsTravelInfo info =  dao.getNewsTravelInfoOneNewsDel(news.getAn_id());
                Date date = new Date();
                if(info!=null && !ConvertUtil.isEmpty(info.getAnti_id()) ){
                    info.setDel(0);
                    info.setCreate_date(date);
                    dao.updateNewsTravelInfo(info);
                }else{
                    info = new AhNewsTravelInfo();
                    info.setAnti_news(news.getAn_id());
                    info.setDel(0);
                    info.setCreate_date(date);
                    dao.insertNewsTravelInfo(info);
                }
            }
        }
        return service_code;
    }

    public String sync_index_flg(int index_flg,NewsBean news){
        String service_code = ServiceCode.SUCCESS;
        //index_flg;//官网首页显示 0.否1.是
        if(index_flg!=news.getIndex_flg()){
            if(index_flg==0){
                dao.updateDelNewsIndex(1,news.getAn_id());
            }else if(index_flg==1){
                AhNewsIndex index = dao.getNewsIndexOneNewsDel(news.getAn_id());
                Date date = new Date();
                if(index!=null && !ConvertUtil.isEmpty(index.getAni_id()) ){
                    index.setDel(0);
                    index.setCreate_date(date);
                    dao.updateNewsIndex(index);
                }else{
                    index = new AhNewsIndex();
                    index.setAni_news(news.getAn_id());
                    index.setDel(0);
                    index.setCreate_date(date);
                    dao.insertNewsIndex(index);
                }
            }
        }
        return service_code;
    }


    public String man_audit(String id,AppJson app){
        String service_code = ServiceCode.ERRO;
        AhNews news = dao.getNewsOneKey(id);
        if(news!=null && !ConvertUtil.isEmpty(news.getAn_id())){
            if(news.getAn_audit()==0){
                news.setAn_audit(1);
            }else{
                news.setAn_audit(0);
            }
            dao.updateAuditNews(news);
            service_code  = ServiceCode.SUCCESS;
        }else{
            service_code  = ServiceCode.fail_man_news_del;
        }
        return service_code;
    }
}
