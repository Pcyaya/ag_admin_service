package com.runyee.agdhome.constant;

import java.util.HashMap;
import java.util.Map;

/**
* 项目名称：anygo
* 类名称：ServiceCode
* 类描述：  全局变量定义
*
 */
public final class ServiceCode {
	public static int valid_hour = 24*30;//token 有效时间
	public static String news_source="ATG_0000000";

	public static Map<String,String> failMap = new HashMap();//需转换失败编码
	public static Map<String,String> successMap = new HashMap();//需转换成功编码
	/**
	 * 基本接口返回
	 * */
	public static final String SUCCESS = APPCode.SUCCESS;//基本接口请求成功
	public static final String ERRO = APPCode.ERRO;//基本接口请求失败

	/**************************************************************************************************************************
	 * success Code
	 * */
	public static final String success_app_download_url = "success_000000";
	public static final String success_contact = "success_000001";
	public static final String success_apply = "success_000002";
	public static final String success_freeback = "success_000003";
	public static final String success_agent_apply = "success_000004";
	public static final String success_depart_user_deled= "success_00000000";


	static{
		//处理成功消息
		successMap.put(success_app_download_url,"下载连接获取成功");
		successMap.put(success_contact,"提交成功，我们会尽快回复您");
		successMap.put(success_apply,"提交成功，我们会尽快联系您");
		successMap.put(success_freeback,"感谢您提出的宝贵意见，我们会尽快解决");
		successMap.put(success_agent_apply,"申请成功，我们会尽快审核并联系您");
		successMap.put(success_depart_user_deled,"此管理人员员已从此部门移除");


	}









	/**************************************************************************************************************************
	 * fail Code
	 * */

	public static final String EXCEPTION = "00000002";//基本接口请求异常
	public static final String fail_param_error= "fail_000000";
	public static final String fail_contact_necessary= "fail_000001";
	public static final String fail_apply_necessary= "fail_000002";
	public static final String fail_apply_noexists= "fail_000003";
	public static final String fail_freeback_empty= "fail_000004";
	public static final String fail_agentapply_empty= "fail_000005";

	public static final String fail_contact_day= "fail_000006";
	public static final String fail_contact_minute = "fail_000007";
	public static final String fail_contact_second = "fail_000008";

	public static final String fail_freeback_limit = "fail_000009";

	public static final String fail_freeback_day= "fail_000010";
	public static final String fail_freeback_minute = "fail_000011";
	public static final String fail_freeback_second = "fail_000012";

	public static final String fail_card_type = "fail_000013";

	public static final String fail_agentapply_day= "fail_000014";
	public static final String fail_agentapply_minute = "fail_000015";
	public static final String fail_agentapply_second = "fail_000016";

	public static final String fail_order_day= "fail_000017";
	public static final String fail_order_minute = "fail_000018";
	public static final String fail_order_second = "fail_000019";

	public static final String fail_recruit_apply_day= "fail_000020";
	public static final String fail_recruit_apply_minute = "fail_000021";
	public static final String fail_recruit_apply_second = "fail_000022";

	public static final String fail_man_login_empty= "fail_000023";
	public static final String fail_man_login_unfind= "fail_000024";
	public static final String fail_man_login_pwderror= "fail_000025";

	public static final String fail_man_news_del= "fail_000026";
	public static final String fail_man_news_edit_source= "fail_000027";
	public static final String fail_man_news_empty= "fail_000028";

	public static final String fail_man_recruit_del= "fail_000029";
	public static final String fail_man_recruit_empty= "fail_000030";
	public static final String fail_man_recruit_exper= "fail_000030";

	public static final String fail_man_pwd_empty= "fail_000031";
	public static final String fail_man_pwd_unfind= "fail_000032";
	public static final String fail_man_pwd_pwderror= "fail_000033";
	public static final String fail_man_pwd_equally= "fail_000034";
	public static final String fail_man_pwd_unlikeliness= "fail_000035";

	public static final String fail_man_sos_del= "fail_000036";
	public static final String fail_man_sos_informed= "fail_000037";
	public static final String fail_man_sos_unhandle= "fail_000038";
	public static final String fail_man_sos_reinform= "fail_000039";
	public static final String fail_man_sos_confirm= "fail_000040";
	public static final String fail_man_sos_operate= "fail_000041";

	public static final String fail_man_report_del= "fail_000042";
	public static final String fail_man_report_handled= "fail_000043";


	public static final String fail_man_valid_del= "fail_000044";
	public static final String fail_man_valid_handled= "fail_000045";

	public static final String fail_coin_unadequate="fail_000046";

	public static final String fail_menu_noright = "fail_00000000";
	public static final String fail_menu_name_empty = "fail_00000001";
	public static final String fail_menu_firsted = "fail_00000002";
	public static final String fail_menu_lasted = "fail_00000003";
	public static final String fail_menu_max_level = "fail_00000004";
	public static final String fail_menu_parent_self = "fail_00000005";

	public static final String fail_group_traveling = "fail_000053";

	public static final String fail_man_tmp_del= "fail_00000100";
	public static final String fail_man_tmpbackup_undel= "fail_00000101";
	public static final String fail_man_tmp_den= "fail_00000102";
	public static final String fail_man_tmp_interval= "fail_00000103";
	public static final String fail_man_tmp_unit= "fail_00000104";
	public static final String fail_man_tmp_type= "fail_00000105";

	public static final String fail_depart_user_added= "fail_00000200";
	public static final String fail_depart_user_deled= "fail_00000201";
	public static final String fail_depart_noexists= "fail_00000202";

	public static final String fail_man_user_name_empty = "fail_00000301";
	public static final String fail_man_user_name_repeat = "fail_00000302";

	public static final String fail_user_noexists= "fail_00000401";
	public static final String fail_user_max_noexists= "fail_00000402";

	public static final String fail_appversion_empty= "fail_00000500";

	public static final String fail_survival_empty= "fail_00000600";
	public static final String fail_survival_exists= "fail_00000601";
	public static final String fail_urgent_empty= "fail_00000602";
	public static final String fail_urgent_exists= "fail_00000603";
	public static final String fail_uphone_empty= "fail_00000604";
	public static final String fail_uphone_exists= "fail_00000605";
	public static final String fail_uphone_urgent= "fail_00000606";
	public static final String fail_uphone_urgentdel= "fail_00000607";

	public static final String fail_uhcategory_empty= "fail_00000700";
	public static final String fail_uhcategory_exists= "fail_00000701";
	public static final String fail_usehelp_empty= "fail_00000702";
	public static final String fail_usehelp_exists= "fail_00000703";
	public static final String fail_uhcategory_firsted = "fail_00000705";
	public static final String fail_uhcategory_lasted = "fail_00000706";

	public static final String fail_man_commodity_tmp_del= "fail_00000800";
	public static final String fail_man_commodity_tmp_online= "fail_00000801";
	public static final String fail_man_commodity_tmp_empty= "fail_00000802";
	public static final String fail_man_commodity_tmp_unsupport= "fail_00000803";
	public static final String fail_man_commodity_tmp_amount= "fail_00000804";
	public static final String fail_man_commodity_tmp_sure= "fail_00000805";
	public static final String fail_man_commodity_tmp_coupon= "fail_00000806";
	public static final String fail_man_commodity_tmp_coupon_del= "fail_00000807";
	public static final String fail_man_commodity_tmp_reonline= "fail_00000808";




	static{
		//处理失败消息
		//failMap.put(ERRO,"请稍后重试");
		failMap.put(EXCEPTION,"服务器错误,请稍后重试");
		failMap.put(fail_param_error,"参数错误");
		failMap.put(fail_contact_necessary,"#?0?# 不能为空");
		failMap.put(fail_apply_necessary,"#?0?# 不能为空");
		failMap.put(fail_apply_noexists,"您申请的职位不存在或已被删除");
		failMap.put(fail_freeback_empty,"#?0?# 不能为空");

		failMap.put(fail_contact_day,"每天最多联系我们三次");
		failMap.put(fail_contact_minute,"您已联系过我们，请稍后重试");
		failMap.put(fail_contact_second,"操作过于频繁，请稍后重试");

		failMap.put(fail_freeback_limit,"最多上传#?0?##?1?#");

		failMap.put(fail_freeback_day,"每天最多投诉建议我们三次");
		failMap.put(fail_freeback_minute,"您已投诉建议过了，请稍后重试");
		failMap.put(fail_freeback_second,"操作过于频繁，请稍后重试");

		failMap.put(fail_card_type,"不支持此类型");

		failMap.put(fail_agentapply_day,"同一手机号每天最多只能提交一次,不可重复提交");
		failMap.put(fail_agentapply_minute,"您已申请过了，请稍后重试");
		failMap.put(fail_agentapply_second,"操作过于频繁，请稍后重试");

		failMap.put(fail_order_day,"同一手机号每天最多只能提交一次,不可重复提交");
		failMap.put(fail_order_minute,"您已申请过了，请稍后重试");
		failMap.put(fail_order_second,"操作过于频繁，请稍后重试");

		failMap.put(fail_recruit_apply_day,"同一手机号每天最多只能提交一次,不可重复提交");
		failMap.put(fail_recruit_apply_minute,"您已申请过了，请稍后重试");
		failMap.put(fail_recruit_apply_second,"操作过于频繁，请稍后重试");

		failMap.put(fail_man_login_empty,"#?0?#不能为空");
		failMap.put(fail_man_login_unfind,"此账户不存在，请联系管理员添加");
		failMap.put(fail_man_login_pwderror,"密码错误，请重试");

		failMap.put(fail_man_news_del,"新闻已被删除!");
		failMap.put(fail_man_news_edit_source,"编辑新闻必须确定来源");
		failMap.put(fail_man_news_empty,"#?0?#不能为空");

		failMap.put(fail_man_recruit_del,"招聘信息已被删除!");
		failMap.put(fail_man_recruit_empty,"#?0?#不能为空");
		failMap.put(fail_man_recruit_exper,"请输入正确的工作经验");

		failMap.put(fail_man_pwd_empty,"#?0?#不能为空");
		failMap.put(fail_man_pwd_unfind,"此账户不存在，请联系管理员");
		failMap.put(fail_man_pwd_pwderror,"请输入正确的旧密码");
		failMap.put(fail_man_pwd_equally,"新旧密码不能一样");
		failMap.put(fail_man_pwd_unlikeliness,"两次密码不一样，请重试");

		failMap.put(fail_man_sos_del,"sos信息已被删除!");
		failMap.put(fail_man_sos_informed,"sos信息已通知紧急联系人,不能重新确认!");
		failMap.put(fail_man_sos_unhandle,"sos信息已被他人处理!");
		failMap.put(fail_man_sos_reinform,"sos信息已通知紧急联系人,不能重复通知!");
		failMap.put(fail_man_sos_confirm,"sos信息还未确认,不能通知紧急联系人!");
		failMap.put(fail_man_sos_operate,"sos信息为误操作,不能通知紧急联系人!");

		failMap.put(fail_man_report_del,"举报信息已被删除!");
		failMap.put(fail_man_report_handled,"举报信息已处理,不能更改!");

		failMap.put(fail_man_valid_del,"认证信息已被删除!");
		failMap.put(fail_man_valid_handled,"认证信息已处理,不能更改!");

		failMap.put(fail_coin_unadequate,"游币余额不足,交易失败");

		failMap.put(fail_menu_noright,"你没有访问权限,请联系管理员");
		failMap.put(fail_menu_name_empty,"菜单名称不能为空");

		failMap.put(fail_menu_firsted,"菜单已经是首位");
		failMap.put(fail_menu_lasted,"菜单已经是末位");
		failMap.put(fail_menu_max_level,"菜单最多三个级别,请重新选则父菜单");
		failMap.put(fail_menu_parent_self,"不能选则自身为父菜单");

		failMap.put(fail_group_traveling,"旅行团#?0?#正在进行中,请联系导游结束旅行");

		failMap.put(fail_man_tmp_del,"此优惠券模板不存在或已被删除");
		failMap.put(fail_man_tmpbackup_undel,"备份模板不能删除或更改");
		failMap.put(fail_man_tmp_den,"请输入正确的优惠券面额");
		failMap.put(fail_man_tmp_interval,"请输入正确的优惠券时效");
		failMap.put(fail_man_tmp_unit,"请输入正确的优惠券单位");
		failMap.put(fail_man_tmp_type,"请输入正确的优惠券类型");

		failMap.put(fail_depart_user_added,"此管理人员员已是此部门员工");
		failMap.put(fail_depart_user_deled,"此管理人员员已从此部门移除");
		failMap.put(fail_depart_noexists,"部门不存在或已被删除");

		failMap.put(fail_man_user_name_empty,"管理人员名称不能为空");
		failMap.put(fail_man_user_name_repeat,"管理人员已存在");

		failMap.put(fail_user_noexists,"管理人员不存在或已被删除");
		failMap.put(fail_user_max_noexists,"超级管理人员不可删除");


		failMap.put(fail_appversion_empty,"#?0?#不能为空");

		failMap.put(fail_survival_empty,"#?0?#不能为空");
		failMap.put(fail_survival_exists,"#?0?#已存在");
		failMap.put(fail_urgent_empty,"#?0?#不能为空");
		failMap.put(fail_urgent_exists,"#?0?#已存在");
		failMap.put(fail_uphone_empty,"#?0?#不能为空");
		failMap.put(fail_uphone_exists,"#?0?#已存在");
		failMap.put(fail_uphone_urgent,"国家未指定");
		failMap.put(fail_uphone_urgentdel,"国家不存在或已删除");

		failMap.put(fail_uhcategory_empty,"#?0?#不能为空");
		failMap.put(fail_uhcategory_exists,"#?0?#已存在");
		failMap.put(fail_usehelp_empty,"#?0?#不能为空");
		failMap.put(fail_usehelp_exists,"#?0?#已存在");
		failMap.put(fail_uhcategory_firsted,"类别已经是首位");
		failMap.put(fail_uhcategory_lasted,"类别已经是末位");

		failMap.put(fail_man_commodity_tmp_del,"此商品不存在或已被删除");
		failMap.put(fail_man_commodity_tmp_online,"此商品已上架不能被更改或删除");
		failMap.put(fail_man_commodity_tmp_empty,"#?0?#不能为空");
		failMap.put(fail_man_commodity_tmp_unsupport,"此商品类型暂不支持,请联系管理员");
		failMap.put(fail_man_commodity_tmp_amount,"商品的预售数量不能小于已售数量");
		failMap.put(fail_man_commodity_tmp_sure,"请输入正确的#?0?#");
		failMap.put(fail_man_commodity_tmp_coupon,"请选则正确的优惠券");
		failMap.put(fail_man_commodity_tmp_coupon_del,"您所选的优惠券不存在或已被删除");
		failMap.put(fail_man_commodity_tmp_reonline,"不能重复上下线商品");


	}



}
