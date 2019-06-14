package com.runyee.agdhome.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

public class HttpClientUtil {
	public static byte[] get(String url){

        HttpClient hc = null;
        GetMethod getMethod = null;
        byte[] responseBody = new byte[0];
        try {
        	HttpClientParams params = new HttpClientParams();
        	params.setSoTimeout(5000);//设置读数据超时时间
        	params.setConnectionManagerTimeout(5000);//设置连接超时时间
        	hc = new HttpClient(params,new SimpleHttpConnectionManager(true));
        	getMethod = new GetMethod(url);
            int status = hc.executeMethod(getMethod);

			//System.out.println(url + "[" + status);
            if(status == HttpStatus.SC_OK) {
            	responseBody = getMethod.getResponseBody();
            } else {
            	responseBody = null;
            }
		} catch (Exception e) {
			responseBody = null;
			e.printStackTrace();
		}finally{
			if(getMethod != null){
				getMethod.releaseConnection();
			}
		}
        return responseBody;
	}
	
	//post 参数 json ， Map 
	public static byte[] post(String url,String json,Map<String,String> paramters){

        HttpClient hc = null;
		PostMethod mPost = null;
        byte[] responseBody = new byte[0];
        try {
        	HttpClientParams params = new HttpClientParams();
        	params.setSoTimeout(50000);//设置读数据超时时间
        	params.setConnectionManagerTimeout(50000);//设置连接超时时间
        	hc = new HttpClient(params,new SimpleHttpConnectionManager(true));
        	mPost = new PostMethod(url);
        	if(!ConvertUtil.isEmpty(json)){
        		RequestEntity requestEntity = new StringRequestEntity(json,"text/xml","UTF-8");
        		mPost.setRequestEntity(requestEntity);
        	}
        	if(paramters != null && paramters.size() > 0){
            	for(Entry<String, String> entry : paramters.entrySet()){
            		if(entry.getKey()!=null&&entry.getValue()!=null){
						mPost.addParameter(entry.getKey(), entry.getValue());
					}
            	}
        	}
        	mPost.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            int status = hc.executeMethod(mPost);
			System.out.println(url + "[" + status);
            if(status == HttpStatus.SC_OK) {
            	responseBody = mPost.getResponseBody();
            } else {
            	responseBody = null;
            }
		} catch (IOException e) {
			e.printStackTrace();
			responseBody = null;
		} finally{
			if(mPost != null){
				mPost.releaseConnection();
			}
		}
        
        return responseBody;
	}

	//post 参数 json
	public static byte[] post(String url,String json){

		HttpClient hc = null;
		PostMethod mPost = null;
		byte[] responseBody = new byte[0];
		try {
			HttpClientParams params = new HttpClientParams();
			params.setSoTimeout(50000);//设置读数据超时时间
			params.setConnectionManagerTimeout(50000);//设置连接超时时间
			hc = new HttpClient(params,new SimpleHttpConnectionManager(true));
			mPost = new PostMethod(url);
			if(!ConvertUtil.isEmpty(json)) {
				RequestEntity requestEntity = new StringRequestEntity(json, "text/xml", "UTF-8");
				mPost.setRequestEntity(requestEntity);
			}

			mPost.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			int status = hc.executeMethod(mPost);
			System.out.println(url + "[" + status);
			if(status == HttpStatus.SC_OK) {
				responseBody = mPost.getResponseBody();
			} else {
				responseBody = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			responseBody = null;
		} finally{
			if(mPost != null){
				mPost.releaseConnection();
			}
		}

		return responseBody;
	}
	//post 参数  Map
	public static byte[] post(String url,Map<String,String> paramters){

		HttpClient hc = null;
		PostMethod mPost = null;
		byte[] responseBody = new byte[0];
		try {
			HttpClientParams params = new HttpClientParams();
			params.setSoTimeout(50000);//设置读数据超时时间
			params.setConnectionManagerTimeout(50000);//设置连接超时时间
			hc = new HttpClient(params,new SimpleHttpConnectionManager(true));
			mPost = new PostMethod(url);
			if(paramters != null && paramters.size() > 0){
				for(Entry<String, String> entry : paramters.entrySet()){
					if(entry.getKey()!=null&&entry.getValue()!=null){
						mPost.addParameter(entry.getKey(), entry.getValue());
					}
				}
			}
			mPost.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			int status = hc.executeMethod(mPost);
			System.out.println(url + "[" + status);
			if(status == HttpStatus.SC_OK) {
				responseBody = mPost.getResponseBody();
			} else {
				responseBody = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			responseBody = null;
		} finally{
			if(mPost != null){
				mPost.releaseConnection();
			}
		}

		return responseBody;
	}

	//post 参数  Map
	public static byte[] post(String url,Map<String,String> paramters,Map<String,String> headers){

		HttpClient hc = null;
		PostMethod mPost = null;
		byte[] responseBody = new byte[0];
		try {
			HttpClientParams params = new HttpClientParams();
			params.setSoTimeout(50000);//设置读数据超时时间
			params.setConnectionManagerTimeout(50000);//设置连接超时时间
			hc = new HttpClient(params,new SimpleHttpConnectionManager(true));
			mPost = new PostMethod(url);
			if(paramters != null && paramters.size() > 0){
				for(Entry<String, String> entry : paramters.entrySet()){
					if(entry.getKey()!=null&&entry.getValue()!=null){
						mPost.addParameter(entry.getKey(), entry.getValue());
					}
				}
			}
			mPost.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			if(headers!=null && headers.size()>0){
				for(Entry<String, String> entry : headers.entrySet()){
					if(entry.getKey()!=null && entry.getValue()!=null){
						mPost.addRequestHeader(entry.getKey(), entry.getValue());
					}
				}
			}
			int status = hc.executeMethod(mPost);
			System.out.println(url + "[" + status);
			if(status == HttpStatus.SC_OK) {
				responseBody = mPost.getResponseBody();
			} else {
				responseBody = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			responseBody = null;
		} finally{
			if(mPost != null){
				mPost.releaseConnection();
			}
		}

		return responseBody;
	}

	//post 参数  Map
	public static byte[] post(String url){

		HttpClient hc = null;
		PostMethod mPost = null;
		byte[] responseBody = new byte[0];
		try {
			HttpClientParams params = new HttpClientParams();
			params.setSoTimeout(50000);//设置读数据超时时间
			params.setConnectionManagerTimeout(50000);//设置连接超时时间
			hc = new HttpClient(params,new SimpleHttpConnectionManager(true));
			mPost = new PostMethod(url);
			mPost.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			int status = hc.executeMethod(mPost);
			System.out.println(url + "[" + status);
			if(status == HttpStatus.SC_OK) {
				responseBody = mPost.getResponseBody();
			} else {
				responseBody = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			responseBody = null;
		} finally{
			if(mPost != null){
				mPost.releaseConnection();
			}
		}

		return responseBody;
	}

	//post 参数 json ， Map
	public static byte[] post(String url,String json,Map<String,String> paramters, int connectTimeout, int readTimeout){

		HttpClient hc = null;
		PostMethod mPost = null;
		byte[] responseBody = new byte[0];
		try {
			HttpClientParams params = new HttpClientParams();
			params.setSoTimeout(readTimeout);//设置读数据超时时间
			params.setConnectionManagerTimeout(connectTimeout);//设置连接超时时间
			hc = new HttpClient(params,new SimpleHttpConnectionManager(true));
			mPost = new PostMethod(url);
			if(!ConvertUtil.isEmpty(json)){
				RequestEntity requestEntity = new StringRequestEntity(json,"text/xml","UTF-8");
				mPost.setRequestEntity(requestEntity);
			}
			if(paramters != null && paramters.size() > 0){
				for(Entry<String, String> entry : paramters.entrySet()){
					if(entry.getKey()!=null&&entry.getValue()!=null){
						mPost.addParameter(entry.getKey(), entry.getValue());
					}
				}
			}
			mPost.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			int status = hc.executeMethod(mPost);
			System.out.println(url + "[" + status);
			if(status == HttpStatus.SC_OK) {
				responseBody = mPost.getResponseBody();
			} else {
				responseBody = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			responseBody = null;
		} finally{
			if(mPost != null){
				mPost.releaseConnection();
			}
		}

		return responseBody;
	}

}
