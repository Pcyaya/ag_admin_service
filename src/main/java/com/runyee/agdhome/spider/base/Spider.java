package com.runyee.agdhome.spider.base;

import com.runyee.agdhome.entity.db.ag_home.AhNewsSource;
import com.runyee.agdhome.entity.page.NewsExPage;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Date;
import java.util.List;

/**
 * Created by cheri on 2018/8/10.
 */
public interface Spider {
    /**
     * 获取当前页面的title
     * */
    String getHtmlTitle(Document doc);
    /**
     * 获取当前新闻的title
     * */
    String getTitle(Document doc);
    /**
     * 获取当前新闻的time
     * */
    Date getTime(Document doc);

    /**
     * 获取当前新闻的title
     * */
    String getTitle(Element element);

    /**
     * 获取当前新闻的content
     * */
    String getContent(Element element);
    /**
     * 获取当前新闻的time
     * */
    Date getTime(Element element);
    /**
     * 获取当前新闻的url
     * */
    String getUrl(Element element);

    /**
     * 获取当前新闻的url
     * */
    String getResourceUrl(Element element);

    Elements  getElements(Document doc,String cssQuery);

    void getNewsContents(NewsExPage news);

    List<NewsExPage> getNews(Document doc);

}
