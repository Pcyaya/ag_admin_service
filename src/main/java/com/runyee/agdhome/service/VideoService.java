package com.runyee.agdhome.service;

import com.runyee.agdhome.constant.AppUrl;
import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.dao.ag_home.ContactDao;
import com.runyee.agdhome.dao.ag_home.VideoDao;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.entity.db.ag_home.AhContact;
import com.runyee.agdhome.entity.db.ag_home.AhVideo;
import com.runyee.agdhome.entity.form.ContactForm;
import com.runyee.agdhome.entity.page.VideoPage;
import com.runyee.agdhome.entity.pagebean.VideoPageBean;
import com.runyee.agdhome.util.ConvertUtil;
import com.runyee.agdhome.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by cheri on 2018/2/24.
 */
@Service
public class VideoService {
    @Autowired
    private VideoDao dao;

    public String list(VideoPageBean pageBean,AppJson app){
        String service_code = ServiceCode.ERRO;
        if(pageBean!=null){
            if(!ConvertUtil.isEmpty(pageBean.getBusiness())){
                int business = ConvertUtil.convertStrToInt(pageBean.getBusiness());
                List<AhVideo> videos = dao.getVideosBusiness(business,pageBean.getCurrPage(),pageBean.getRows());
                if(videos!=null &&  videos.size() > 0){
                    List<VideoPage> pages = new ArrayList<>();
                    for(AhVideo video:videos){
                        VideoPage page = new VideoPage();
                        page.setBusiness(video.getAv_business());
                        page.setTitle(video.getAv_title());
                        page.setContent(video.getAv_content());
                        String url = video.getAv_url();
                        if(!ConvertUtil.isEmpty(url)){
                            url = AppUrl.video_url+url;
                            page.setUrl(url);
                            if(!ConvertUtil.isEmpty(page.getUrl())){
                                page.setSnapshot(page.getUrl()+"?x-oss-process=video/snapshot,t_27000,f_jpg");
                            }
                        }
                        pages.add(page);
                    }
                    pageBean.setData(pages);
                }
                app.setObj(pageBean);
                service_code  = ServiceCode.SUCCESS;
            }else{
                service_code = ServiceCode.fail_param_error;
            }
        }
        return service_code;
    }


}
