package com.runyee.agdhome.entity;


import com.runyee.agdhome.util.DataUtil;

import java.util.List;

/**
 * 移动端请求后需要接受的JSON
 * 
 * @author
 * 
 */
public class AppJson {
	private String success = "00000000";// 是否成功
	private String msg = "操作成功";// 提示信息
	private Object obj = null;// 其他信息
	

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		if(msg.contains("#?")){
			List<String> msgs = DataUtil.getCurrent().getMsgs();
			if(msgs!=null&&msgs.size()>0){
				for(int i=0;i<msgs.size();i++){
					msg = msg.replace("#?"+i+"?#",msgs.get(i));
				}
			}
		}
		this.msg = msg;

	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	
}
