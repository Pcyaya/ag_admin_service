package com.runyee.agdhome.constant;

import java.util.HashMap;
import java.util.Map;

/**
* 项目名称：anygo
* 类名称：APPCode   
* 类描述：  全局变量定义
*
 */
public final class APPCode {
	public static Map<String,String> codeMap = new HashMap();//需要处理操作的code
	/**
	 * 基本接口返回
	 * */
	public static final String SUCCESS = "00000000";//基本接口请求成功
	public static final String ERRO = "00000001";//基本接口请求失败

	/**
	 * access_token
	 * */
	public static final String ACCESS_TOKEN_SUCCESS = "00100000";//ACCESS_TOKEN获取成功

	public static final String ACCESS_TOKEN_EXPIRE = "00100001";//ACCESS_TOKEN 过期
	public static final String ACCESS_TOKEN_ERROR = "00100002";// 无效的ACCESS_TOKEN
	public static final String ACCESS_TOKEN_INVALID = "00100003";//ACCESS_TOKEN 已失效(单点登陆被挤掉或注销)
	public static final String ACCESS_TOKEN_LOGIN = "00100004";//需登陆







	//###########################################################################################################

	static{
		//接口返回（原编码返回无需转换）
		codeMap.put(SUCCESS,"请求成功");
		codeMap.put(ERRO,"请求失败");
		codeMap.put(ACCESS_TOKEN_SUCCESS,"TOKEN获取成功");
		codeMap.put(ACCESS_TOKEN_EXPIRE,"TOKEN 过期");
		codeMap.put(ACCESS_TOKEN_ERROR,"无效的TOKEN");
		codeMap.put(ACCESS_TOKEN_INVALID,"TOKEN 已失效");
		codeMap.put(ACCESS_TOKEN_LOGIN,"需登陆");

	}

	/**
	 *  系统通用消息:: 更改导游认证状态
	 * 		prefix = "SKC";//前缀
	 * 		code = "sk_000019";// 操作码
	 * 		plat;// 其他参数前端自定义(后台原样转发)
	 *
	 *  请求参数::不可请求
	 *  转发后::
	 *		status;//认证状态 -2.未认证 -1.认证中 0.验证中 1.认证通过 2.认证失败 3.认证失效
	 *		content;//认证失败原因
	 *
	 *  回复参数::无需回复
	 *  读回复参数::无需回复
	 * */
	public static final  String sk_guide_valid = "sk_000019";

	/**
	 *  系统通用消息:: 账号冻结
	 *  	prefix = "SKC";//前缀
	 *  	code = "sk_000020";// 操作码
	 *		plat;// 其他参数前端自定义(后台原样转发)
	 *  请求参数:: 不可请求
	 *
	 *  转发后::
	 *		opt;//0.解冻1.冻结
	 *		content;//冻结原因
	 *  回复参数:: 无需回复
	 *
	 * */
	public static final  String sk_visitor_freeze = "sk_000020";


}
