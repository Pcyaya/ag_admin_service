package com.runyee.agdhome.timer.handle;

import ch.qos.logback.core.db.DataSourceConnectionSource;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.runyee.agdhome.constant.AppUrl;
import com.runyee.agdhome.dao.anygo.AgSpkSosDao;
import com.runyee.agdhome.dao.anygo.AgVisitorDao;
import com.runyee.agdhome.entity.db.ag_home.AhNews;
import com.runyee.agdhome.entity.db.ag_home.AhNewsBusiness;
import com.runyee.agdhome.entity.db.ag_home.AhNewsDetail;
import com.runyee.agdhome.entity.db.ag_home.AhNewsSource;
import com.runyee.agdhome.entity.db.anygo.RySpkSos;
import com.runyee.agdhome.entity.db.anygo.RyVisitor;
import com.runyee.agdhome.entity.ex.SpkSosBean;
import com.runyee.agdhome.entity.page.NewsDetailPage;
import com.runyee.agdhome.entity.page.NewsExPage;
import com.runyee.agdhome.service.NewsService;
import com.runyee.agdhome.spider.XHNetSpider;
import com.runyee.agdhome.spider.XHTrlNetSpider;
import com.runyee.agdhome.util.ConvertUtil;
import com.runyee.agdhome.util.DataUtil;
import com.runyee.agdhome.util.DateUtils;
import com.runyee.agdhome.util.SendSOSInfoUtil;
import com.runyee.config.db.datasource.DataSourceConfig;
import com.runyee.config.db.dynamic.DynamicDataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by cheri on 2017/12/12.
 */
@Component
public class Cronhandle {
    @Autowired
    private NewsService newsService;

    @Autowired
    private AgSpkSosDao sosDao;

    @Autowired
    private AgVisitorDao visitorDao;

    private static String name = "行业新闻";
    /**
     *
     * */
    public void spider(){
        List<AhNewsSource> sources = newsService.getNewsSourceAll();
        if(sources!=null&&sources.size()>0){
            String business = "";//行业新闻
            AhNewsBusiness news_business = newsService.getNewsBusinessOneName(name);
            business = news_business.getAnb_id();
            if(!ConvertUtil.isEmpty(business)){
                for(AhNewsSource source:sources){
                    if(source!=null){
                        List<NewsExPage> news_list = null;
                        String key = source.getAns_id();//来源
                        switch (key){
                            //中国旅游新闻网
                            case "ans_000000":

                                break;
                            //新华网
                            case "ans_000001":
                                news_list = XHNetSpider.getNews(source);
                                break;
                            //新华旅游
                            case "ans_000002":
                                news_list = XHTrlNetSpider.getNews(source);
                                break;
                            default:
                        }

                        if(!ConvertUtil.isEmpty(key)&&news_list!=null){
                            for(NewsExPage news :news_list){
                                String title = news.getTitle();
                                Date date = news.getDate();
                                if(date!=null&&!ConvertUtil.isEmpty(title)){
                                    List<NewsDetailPage> details = news.getDetails();
                                    if(details!=null && details.size() > 0){
                                        AhNews ahnews =  newsService.getNewsBusinessOneTitleDate(title, DateUtils.gettimestamp(date).toString());
                                        if(ahnews==null || ConvertUtil.isEmpty(ahnews.getAn_id())){
                                            AhNews origin = newsService.getNewsBusinessOneOrigin(news.getOrigin());
                                            if(origin==null || ConvertUtil.isEmpty(origin.getAn_id())){
                                                ahnews = new AhNews();
                                                ahnews.setAn_business(business);
                                                ahnews.setAn_title(title);
                                                ahnews.setAn_content(news.getContent());
                                                ahnews.setAn_source(key);
                                                ahnews.setAn_origin(news.getOrigin());
                                                ahnews.setAn_url(news.getResource());
                                                ahnews.setAn_audit(1);
                                                ahnews.setDel(0);
                                                ahnews.setCreate_date(date);


                                                newsService.insertNews(ahnews);

                                                int sort = 0;
                                                for(NewsDetailPage detailPage:details){
                                                    if(!ConvertUtil.isEmpty(detailPage.getTitle())||
                                                            !ConvertUtil.isEmpty(detailPage.getContent())||
                                                            !ConvertUtil.isEmpty(detailPage.getUrl())){
                                                        AhNewsDetail detail = new AhNewsDetail();
                                                        detail.setAnd_news(ahnews.getAn_id());
                                                        detail.setAnd_title(detailPage.getTitle());
                                                        detail.setAnd_content(detailPage.getContent());
                                                        detail.setAnd_type(detailPage.getType());
                                                        detail.setAnd_url(detailPage.getUrl());
                                                        detail.setAnd_align("l");
                                                        if(detail.getAnd_type()!=0){
                                                            detail.setAnd_align("c");
                                                        }
                                                        if(!ConvertUtil.isEmpty(detail.getAnd_title())){
                                                            detail.setAnd_align("c");
                                                        }
                                                        detail.setAnd_sort(sort);
                                                        detail.setDel(0);
                                                        detail.setCreate_date(date);
                                                        newsService.insertNewsDetail(detail);
                                                        sort++;
                                                    }
                                                }
                                                //清除重复新闻
                                                newsService.deleteRepeatNews();

                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    /**
     * 定时任务每30分钟处理一次
     * 后台未处理的SOS请求
     */
    public void executeSOS(){
        //先扫描测试库，然后再扫描正式库,此处设置扫描测试库
        DataUtil.getCurrent().setDb(DynamicDataSourceConfig.anygo);
        this.sendMsg();
        //扫描生产库
        DataUtil.getCurrent().setDb(DynamicDataSourceConfig.aetos_go);
        this.sendMsg();
    }

    /**
     * 发送消息方法
     */
    public void sendMsg(){
        //定时任务触发，捞取数据库中未处理的SOS请求
        List<RySpkSos> sosList = sosDao.getnotexecuteSOSInfoList();
        if(sosList!=null&&sosList.size()>0){
            //获取求助人的电话号码
            StringBuilder sos_ids = new StringBuilder("\'\'");
            for (RySpkSos sos : sosList){
                sos_ids.append(",\'"+sos.getRss_visitor()+"\'");
            }
            List<RyVisitor> visitors = visitorDao.getVisitorsKeys(sos_ids.toString());
            Map<String,String> map = new HashMap<>();
            if(visitors!=null&&visitors.size()>0){
                for (RyVisitor vi : visitors){
                    map.put(vi.getRv_id(),vi.getRv_phone());
                }
            }
            for (RySpkSos sos : sosList){
                if(!ConvertUtil.isEmpty(sos.getRss_phone()) &&
                        !ConvertUtil.isEmpty(sos.getRss_id()) &&
                        sos.getRss_phone().length()>7){
                    //请求参数
                    String name = sos.getRss_name();//紧急联系人名称
                    String alias = sos.getRss_toalias();//紧急联系人对求助人的称呼
                    //求助人的电话
                    String phone = null;
                    String vi_phone = map.get(sos.getRss_visitor());
                    if(!ConvertUtil.isEmpty(vi_phone)){
                        phone = vi_phone;
                    }
                    String addr = AppUrl.domain + AppUrl.sos_addr_url + sos.getRss_id();
                    //组装请求json
                    String reqJson = "{\"name\":\""+name+"\",\"alias\":\""+alias+"\",\"phone\":\""+phone+"\",\"addr\":\""+addr+"\"}";
                    //发消息
                    SendSOSInfoUtil.sendMsg(sos.getRss_phone(),reqJson);
                }
            }
        }
    }

}
