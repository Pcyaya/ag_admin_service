package com.runyee.agdhome.util;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpiderUtil {
	public static LinkedList ImgUrls=new LinkedList();//用于存放图片URL
	public static LinkedList linkurls=new LinkedList();//用于存放url链接

	public static void main(String[] args) throws IOException {
		String url="http://world.huanqiu.com/article/2016-01/8412590.html?from=bdwz";
		Connection connect= Jsoup.connect(url);
		Document doc = connect.get();
		//SpiderUtil.downloadPage(url);//下载网页
		String html_title=SpiderUtil.getHtmlTitle(doc);//获取新闻标题
		String time=SpiderUtil.getTime(doc);//获取新闻发布时间
		String text=SpiderUtil.getNewtext(doc);//获取新闻内容
		System.out.println("网站Url:"+url);
		System.out.println("网站标题:"+html_title);
		System.out.println("新闻时间"+time);
		System.out.println("新闻内容:"+text);
		System.out.println("******************************************************************");
		SpiderUtil.getImgurl(doc);//获取图片链接
		SpiderUtil.getlinkurl(doc);//获取网页链接
		System.out.println("图片url链接");
		for (Object IU : SpiderUtil.ImgUrls) {
			System.out.println(IU);
		}
		System.out.println("*****************************************************************");
		System.out.println("url链接");
		for (Object LU : SpiderUtil.linkurls) {
			System.out.println(LU);
		}
	}

	/**
	 * 网页的html title
	 * */
	public static String getHtmlTitle(Document doc){
		//获取网页的标题
		String title=doc.title();
		return title;
	}

	public static LinkedList getlinkurl(Document doc) throws IOException{
		//获取网页中的所有url地址，并保存在linkurls链表中
		Elements href = doc.select("a[href]");
		for (Element hre : href) {
			String linkUrl = hre.attr("abs:href");//获取网页的绝对地址
			if(!linkurls.contains(linkUrl)){
				linkurls.add(linkUrl);
				System.out.println(linkUrl);
			}
		}
		return linkurls;
	}
	public static  LinkedList getImgurl(Document doc) throws IOException{
		//获取图片地址，并保存在ImgUrls链表中
		int count=0;
		Elements pngs = doc.select("img[src]");
		for (Element img : pngs) {
			count++;
			String imgUrl = img.attr("abs:src");//获取图片的绝对路径
			// imgUrl = img.absUrl("src");
			ImgUrls.add(imgUrl);
			//System.out.println(imgUrl);
			//downloadImg(imgUrl,count);
		}
		return ImgUrls;
	}
	public static void  downloadImg(String ImgUrl,int count) throws IOException{
		//下载图片
		String str="F:\\MyEclipse\\Img\\";//保存下载图片文件夹
		String ss=str+count+".png";//保存图片路径
		URL url=new URL(ImgUrl);  // 构造URL
		URLConnection uc=url.openConnection(); // 打开连接
		InputStream is=uc.getInputStream(); // 输入流
		File file=new File(ss); //创建文件
		FileOutputStream out=new FileOutputStream(file);  // 输出的文件流
		byte[] bs = new byte[1024];
		// 读取到的数据长度
		int len;
		// 开始读取
		while ((len = is.read(bs)) != -1) {
			out.write(bs, 0, len);
		}
		// 完毕，关闭所有链接
		out.close();
		is.close();
	}

	public static String getNewtext(Document doc){
		//获取新闻内容
		String text = doc.select("div.text").text();
		text=text.replace(Jsoup.parse("   ").text(), "");
		return text;
	}

	public static String getTime(Document doc){
		//获取新闻发布的时间
		String time=null;
		Elements element=doc.select("strong.timeSummary");//此处是根据百度新闻的网页形式解析的新闻时间
		String rex="^(((20\\d{2})-(\\d{2})-(\\d{2}))) ((\\d{2}):(\\d{2}):(\\d{2}))$";//正则表达式用于匹配时间
		Pattern pattern=Pattern.compile(rex);
		for(Element el: element){
			String content = el.text();
			Matcher matcher=pattern.matcher(content);
			if(matcher.matches()){
				time=content;
			}
		}
		return time;
	}

	public static void downloadPage(String str){
		//下载网页
		String filestr="F:/test";//设置网页的保存地址
		try {
			URL pageUrl = new URL(str);
			pageUrl.getContent();
			URLConnection uc =pageUrl.openConnection(); // 打开连接
			String path = pageUrl.getPath();//获取网页的相对路径
			//  System.out.println(path);
			if (path.length() == 0) {//对网页的相对路径进行处理
				path = "index.html";
			} else {
				if (path.endsWith("/")) {
					path = path + "index.html";
				} else {
					int lastSlash = path.lastIndexOf("/");
					int lastPoint = path.lastIndexOf(".");
					if (lastPoint < lastSlash) {
						path = path + ".html";
					}
				}
			}
			filestr=filestr+path;//网页保存路径
			System.out.println("网页保存路径："+filestr);
			//设置网页保存目录，当保存网页的文件夹不存在时，需要先创建文件夹，然后在保存网页
			int lastSlash2 = filestr.lastIndexOf("/");
			String filestrr=filestr.substring(0,lastSlash2);
			// System.out.println(filestrr);
			File file2=new File(filestrr); //创建文件目录
			file2.mkdirs();
			File file=new File(filestr); //创建文件
			InputStream is=uc.getInputStream(); // 输入流
			FileOutputStream out=new FileOutputStream(file);  // 输出的文件流
			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;
			// 开始读取
			while ((len = is.read(bs)) != -1) {
				out.write(bs, 0, len);
			}
			// 完毕，关闭所有链接
			out.close();
			is.close();
		} catch (Exception ex) {
		}
	}

	/*********************************************************************************************/
	/**
	 * 请求超时时间,默认20000ms
	 */
	private static int timeout = 20000;
	/**
	 * 等待异步JS执行时间,默认20000ms
	 */
	private static int waitForBackgroundJavaScript = 20000;

	public int getWaitForBackgroundJavaScript() {
		return waitForBackgroundJavaScript;
	}

	/**
	 * 设置获取完整HTML页面时等待异步JS执行的时间
	 *
	 * @param waitForBackgroundJavaScript
	 */
	public void setWaitForBackgroundJavaScript(int waitForBackgroundJavaScript) {
		this.waitForBackgroundJavaScript = waitForBackgroundJavaScript;
	}

	/**
	 * 将网页返回为解析后的文档格式
	 *
	 * @param html
	 * @return
	 * @throws Exception
	 */
	public static Document parseHtmlToDoc(String html) throws Exception {
		return removeHtmlSpace(html);
	}

	private static Document removeHtmlSpace(String str) {
		Document doc = Jsoup.parse(str);
		String result = doc.html().replace("&nbsp;", "");
		return Jsoup.parse(result);
	}

	/**
	 * 获取页面文档字串(等待异步JS执行)
	 *
	 * @param url 页面URL
	 * @return
	 * @throws Exception
	 */
	public static String getHtmlPageResponse(String url) throws Exception {
		String result = "";

		final WebClient webClient = new WebClient(BrowserVersion.CHROME);

		webClient.getOptions().setThrowExceptionOnScriptError(false);//当JS执行出错的时候是否抛出异常
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常
		webClient.getOptions().setActiveXNative(false);
		webClient.getOptions().setCssEnabled(false);//是否启用CSS
		webClient.getOptions().setJavaScriptEnabled(true); //很重要，启用JS
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());//很重要，设置支持AJAX

		webClient.getOptions().setTimeout(timeout);//设置“浏览器”的请求超时时间
		webClient.setJavaScriptTimeout(timeout);//设置JS执行的超时时间

		HtmlPage page;
		try {
			page = webClient.getPage(url);
		} catch (Exception e) {
			webClient.close();
			throw e;
		}
		webClient.waitForBackgroundJavaScript(waitForBackgroundJavaScript);//该方法阻塞线程

		result = page.asXml();
		webClient.close();

		return result;
	}

	/**
	 * 获取页面文档Document对象(等待异步JS执行)
	 *
	 * @param url 页面URL
	 * @return
	 * @throws Exception
	 */
	public static Document getHtmlPageResponseAsDocument(String url) throws Exception {
		return parseHtmlToDoc(getHtmlPageResponse(url));
	}
}
