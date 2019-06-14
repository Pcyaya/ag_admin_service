package com.runyee.agdhome.spider;

import com.runyee.agdhome.entity.db.ag_home.AhNewsSource;
import com.runyee.agdhome.entity.page.NewsDetailPage;
import com.runyee.agdhome.entity.page.NewsExPage;
import com.runyee.agdhome.spider.base.Spider;
import com.runyee.agdhome.util.ConvertUtil;
import com.runyee.agdhome.util.DateUtils;
import com.runyee.agdhome.util.SpiderUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XHNetSpider implements Spider{

	@Override
	public String getHtmlTitle(Document doc) {
		return SpiderUtil.getHtmlTitle(doc);
	}

	@Override
	public String getTitle(Document doc) {
		return null;
	}

	@Override
	public Date getTime(Document doc) {
		Date date = null;
		Elements element=doc.select("div.source span.time, div.h-news span.h-time");//此处是根据百度新闻的网页形式解析的新闻时间
		String rex="^(((20\\d{2})年(\\d{2})月(\\d{2})日)) ((\\d{2}):(\\d{2}):(\\d{2}))$";//正则表达式用于匹配时间
		String rex2="^(((20\\d{2})-(\\d{2})-(\\d{2}))) ((\\d{2}):(\\d{2}):(\\d{2}))$";//正则表达式用于匹配时间
		Pattern pattern=Pattern.compile(rex);
		Pattern pattern2=Pattern.compile(rex2);
		for(Element el: element){
			String content = el.text();
			Matcher matcher=pattern.matcher(content);
			if(matcher.matches()){
				try {
					date= DateUtils.date_sdf_wz_hhmmss.parse(content);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else{
				matcher=pattern2.matcher(content);
				if(matcher.matches()){
					try {
						date= DateUtils.datetimeFormat.parse(content);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}

			}
		}
		return date;
	}




	@Override
	public String getTitle(Element element) {
		String title = "";
		title = element.select("h3 a").text();
		return title;
	}

	@Override
	public String getContent(Element element) {
		String content = "";
		content = element.select("p a").text();
		return content;
	}

	@Override
	public Date getTime(Element element) {
		return null;
	}

	@Override
	public String getUrl(Element element) {
		String url = "";
		//.attr("abs:href");//获取网页的绝对地址
		url = element.select("h3 a").attr("abs:href");
		return url;
	}

	@Override
	public String getResourceUrl(Element element) {
		return null;
	}


	@Override
	public Elements getElements(Document doc,String cssQuery) {
		Elements elements = doc.select(cssQuery);
		return elements;
	}

	@Override
	public void getNewsContents(NewsExPage news) {
		if(news!=null&& !ConvertUtil.isEmpty(news.getOrigin()) && !ConvertUtil.isEmpty(news.getTitle())){
			Connection connect= Jsoup.connect(news.getOrigin());
			try {
				Document doc = connect.get();
				Date date = this.getTime(doc);
				if(date!=null){
					news.setDate(date);
					String cssQuery = "div.article p, div#p-detail p";// div#p-detail div.video-url";
					//String cssQuery = "iframe";
					Elements article_p = this.getElements(doc,cssQuery);
					if(article_p!=null && article_p.size()>0){
						List<NewsDetailPage> details = new ArrayList<>();//详情
						for(Element element :article_p){
							NewsDetailPage detail =  new NewsDetailPage();
							String content = "";
							String title = "";
							String url = "";
							String align = "";
							int type = 0;//类型0.文本，1.图片，2音频，3.视频
							align = element.attr("align");
							if(element.is("p")){
								if(element.hasText()){
									if(element.is("[align=center]")){
										title = element.text();
									}else{
										content = element.text();
									}
								}
								Elements spans = element.select("span");
								if(spans!=null&&spans.size()>0){
									for(Element span:spans){
										//段落
										/*if(span.hasText()){
											content += span.text();
										}*/
										//图片
										Element img = span.selectFirst("img[src]");
										if(img!=null){
											type = 1;
											url = img.attr("abs:src");
										}

									}
								}
								Element img = element.selectFirst("img");
								if(img!=null){
									type = 1;
									url = img.attr("abs:src");
								}
							}/*else if(element.is("div.video-url")){
								String src = element.text();//element.attr("abs:src");
								if(!ConvertUtil.isEmpty(src)){
									Connection iframe = Jsoup.connect(src);
									Document ifdoc = iframe.get();
									Element video = ifdoc.selectFirst("div.video_box #PlayerCtrl");
									if(video!=null){
										//视频
										type = 2;
										url = video.selectFirst("param[name=movie]").attr("value");
									}
								}
							}*/

							detail.setTitle(title);
							detail.setContent(content);
							detail.setUrl(url);
							detail.setType(type);
							detail.setAlign(align);

							details.add(detail);
						}
						news.setDetails(details);
					}
				}


			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}



	@Override
	public List<NewsExPage> getNews(Document doc) {
		List<NewsExPage> news_list = null;
		String html_title= this.getHtmlTitle(doc);//获取新闻标题
		//System.out. println("网站标题:"+html_title);
		String cssQuery = "ul.picList li";
		Elements elements = this.getElements(doc,cssQuery);
		if(elements!=null&&elements.size()>0 && elements.size()< 1000){
			news_list = new ArrayList<>();
			for(Element element:elements){
				if(element!=null){
					NewsExPage news = new NewsExPage();
					news.setTitle(this.getTitle(element));
					news.setContent(this.getContent(element));
					news.setResource(this.getResourceUrl(element));
					news.setDate(this.getTime(element));
					news.setOrigin(this.getUrl(element));
					if(!ConvertUtil.isEmpty(news.getOrigin()) && !ConvertUtil.isEmpty(news.getTitle())){
						this.getNewsContents(news);
						List<NewsDetailPage> details =  news.getDetails();
						if(news.getDate()!=null && details!=null && details.size()>0){
							news_list.add(news);
						}
					}
				}
			}
		}

		return news_list;
	}


	public static List<NewsExPage> getNews(AhNewsSource source) {
		List<NewsExPage> news_list = null;
		String url=source.getAns_url();
		Connection connect= Jsoup.connect(url);
		Document doc = null;
		try {
			doc = connect.get();
			Spider spider =  new XHNetSpider();
			news_list = spider.getNews(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return news_list;
	}


	public static void main(String[] args) throws IOException {
		String url="http://zgly.xinhuanet.com/";
		Connection connect= Jsoup.connect(url);
		Document doc = connect.get();
		Spider spider =  new XHNetSpider();
		spider.getNews(doc);
	}
}
