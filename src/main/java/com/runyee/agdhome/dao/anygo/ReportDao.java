package com.runyee.agdhome.dao.anygo;

import com.runyee.agdhome.entity.db.anygo.RyReport;
import com.runyee.agdhome.entity.db.anygo.RyReportHandle;
import com.runyee.agdhome.entity.ex.ReportBean;
import com.runyee.agdhome.entity.ex.ReportInfoBean;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;


@Mapper
public interface ReportDao {


    /**
     * 新增 report handle
     */
    @Insert("insert into ry_report_handle( rrh_id, rrh_report, rrh_user, rrh_status, rrh_content, rrh_val, rrh_del, create_date, update_date ) " +
            "                   values( #{rrh_id}, #{rrh_report}, #{rrh_user}, #{rrh_status}, #{rrh_content}, #{rrh_val}, #{rrh_del}, #{create_date}, #{update_date} )")
    @SelectKey(before=true,keyProperty="rrh_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertReportHandle(RyReportHandle reportHandle);


    /**
     *  删除 report 信息
     */
    @Update("update ry_report set  " +
            "   rr_del= #{rr_del} " +
            "   where rr_id = #{rr_id} ")
    void updateReportDel(RyReport rr_del);


    /**
     * report
     */
    @Select("select rr.*," +
            //举报人信息
            "   if((@name:='')='' and (@phone:='')='' and   (@sex:=0)=0 " +
            "       and(select concat(@name:=ifnull(rv_name,rv_num)," +
            "                    @phone:=ifnull(rv_phone,''), " +
            "                    @sex:=ifnull(rv_sex,0)) " +
            "               from ry_visitor rv " +
            "               where ifnull(rv_del,0)=0 and rv.rv_id = rr.rr_visitor " +
            "               order by  create_date desc limit 1 )is not null,@name,'') name," +
            "   @phone phone," +
            "   @sex sex," +
            /*//被举报者信息
            "   if(rr_type=0," +
            "      (select concat(ifnull(rv.rv_name,rv.rv_num),'+',rv.rv_phone) " +
            "           from ry_visitor rv " +
            "           where  rv.rv_id = rr.rr_obj " +
            "           order by  create_date desc limit 1  )," +
            "      if(rr_type=1," +
            "         (select ifnull(rg.rg_name,rg.rg_num) " +
            "               from ry_group rg " +
            "               where   rg.rg_id = rr.rr_obj " +
            "               order by  create_date desc limit 1 )," +
            "         (select rtn.rtn_content " +
            "               from ry_travel_note rtn " +
            "               where  rtn.rtn_id = rr.rr_obj " +
            "               order by  create_date desc limit 1 ) " +
            "      )" +
            "   )reported_info," +*/
            //举报原因
            "   (select group_concat(rrt.rrt_val)" +
            "           from ry_report_tag rrt " +
            /*"           left join ry_common_val rcv on rcv.rcv_id = rrt.rrt_val " +*/
            "           where ifnull(rrt.rrt_del,0)=0 " +
            "           and rrt.rrt_report = rr.rr_id )tags,    " +
            //处理进度
            "   if((@handle_schedule:=0)=0 and (@handle_user:='')='' " +
            "       and (@handle_results:='')='' and (@date_handle:=null)is null " +
            "       and (select concat(@handle_schedule:=ifnull(rrh_status,0), " +
            "                   @handle_user:=ifnull(rrh_user,''), " +
            "                   @handle_results:=ifnull(rrh_val,''), " +
            "                   @date_handle:=rrh.create_date) " +
            "               from ry_report_handle rrh " +
            "               where  ifnull(rrh.rrh_del,0)=0 and rrh.rrh_report = rr.rr_id  " +
            "               order by create_date desc limit 1 ) is not null, @handle_schedule,@handle_schedule:=0) handle_schedule," +
            "   @handle_user handle_user, " +
            "   @handle_results handle_results, " +
            "   @date_handle date_handle " +
            /*"   ifnull((select rcv.rcv_name " +
            "               from ry_common_val rcv " +
            "               where ifnull(rcv.rcv_del,0)=0 and  rcv.rcv_group = 12 " +
            "               and ifnull(rcv.rcv_val,'')= @handle_schedule " +
            "               order by create_date desc limit 1 ),'') schedule_name, " +
            "   ifnull((select rcv.rcv_name " +
            "               from ry_common_val rcv " +
            "               where ifnull(rcv.rcv_del,0)=0 and  rcv.rcv_group = 13 " +
            "               and ifnull(rcv.rcv_val,'')= @handle_results " +
            "               order by create_date desc limit 1 ),'') results_name " +*/

            "   from ry_report rr " +
            "   where ifnull(rr_del,0)=0  and  rr_id =#{id} " +
            "   order by create_date desc limit 1  ")
    ReportInfoBean getReportOneKey(@Param("id") String id);

    /**
     * report handle
     */
    @Select("select rrh.* " +
            "   from ry_report_handle rrh " +
            "   where ifnull(rrh_del,0)=0  and  rrh_report =#{report} " +
            "   order by create_date desc limit 1  ")
    RyReportHandle getReportHandleOneKey(@Param("report") String report);

    /**
     * 更新 report handle信息
     */
    @Update("update ry_report_handle set  " +
            "   rrh_status= #{rrh_status}, " +
            "   rrh_val= #{rrh_val}, " +
            "   rrh_content= #{rrh_content}, " +
            "   rrh_user= #{rrh_user} " +
            "   where rrh_id = #{rrh_id} ")
    void updateReportHandle(RyReportHandle reportHandle);


    /**
     * report  Bean
     */
    @Select("select rr.* " +

            "   from ry_report rr " +
            "   where ifnull(rr_del,0)=0  and  rr_id =#{id} " +
            "   order by create_date desc limit 1 ")
    RyReport getReportBeanOneKey(@Param("id") String id);

    /**
     * report 列表
     */
    @Select("select rr.*," +
            //举报人信息
            "   if((@name:='')='' and (@phone:='')='' and   (@sex:=0)=0 " +
            "       and(select concat(@name:=ifnull(rv_name,rv_num)," +
            "                    @phone:=ifnull(rv_phone,''), " +
            "                    @sex:=ifnull(rv_sex,0)) " +
            "               from ry_visitor rv " +
            "               where ifnull(rv_del,0)=0 and rv.rv_id = rr.rr_visitor " +
            "               order by  create_date desc limit 1 )is not null,@name,'') name," +
            "   @phone phone," +
            "   @sex sex," +
            /*//被举报者信息
            "   if(rr_type=0," +
            "      (select concat(ifnull(rv.rv_name,rv.rv_num),'+',rv.rv_phone) " +
            "           from ry_visitor rv " +
            "           where  rv.rv_id = rr.rr_obj " +
            "           order by  create_date desc limit 1  )," +
            "      if(rr_type=1," +
            "         (select ifnull(rg.rg_name,rg.rg_num) " +
            "               from ry_group rg " +
            "               where   rg.rg_id = rr.rr_obj " +
            "               order by  create_date desc limit 1 )," +
            "         (select rtn.rtn_content " +
            "               from ry_travel_note rtn " +
            "               where  rtn.rtn_id = rr.rr_obj " +
            "               order by  create_date desc limit 1 ) " +
            "      )" +
            "   )reported_info," +*/
            //举报原因
            "   (select group_concat(rrt.rrt_val)" +
            "           from ry_report_tag rrt " +
            "           where ifnull(rrt.rrt_del,0)=0 " +
            "           and rrt.rrt_report = rr.rr_id )tags,    " +
            //处理进度
            "   if((@handle_schedule:=0)=0 and (@handle_user:='')='' " +
            "       and (@handle_results:='')='' and (@date_handle:=null)is null " +
            "       and (select concat(@handle_schedule:=(ifnull(rrh_status,0)+1), " +
            "                   @handle_user:=ifnull(rrh_user,''), " +
            "                   @handle_results:=ifnull(rrh_val,''), " +
            "                   @date_handle:=rrh.create_date) " +
            "               from ry_report_handle rrh " +
            "               where  ifnull(rrh.rrh_del,0)=0 and rrh.rrh_report = rr.rr_id  " +
            "               order by create_date desc limit 1 ) is not null, @handle_schedule,@handle_schedule:=0) handle_schedule," +
            "   @handle_user handle_user, " +
            "   @handle_results handle_results, " +
            "   @date_handle date_handle " +
            /*"   ifnull((select rcv.rcv_name " +
            "               from ry_common_val rcv " +
            "               where ifnull(rcv.rcv_del,0)=0 and  rcv.rcv_group = 12 " +
            "               and ifnull(rcv.rcv_val,'')= @handle_schedule " +
            "               order by create_date desc limit 1 ),'') schedule_name, " +
            "   ifnull((select rcv.rcv_name " +
            "               from ry_common_val rcv " +
            "               where ifnull(rcv.rcv_del,0)=0 and  rcv.rcv_group = 13 " +
            "               and ifnull(rcv.rcv_val,'')= @handle_results " +
            "               order by create_date desc limit 1 ),'') results_name " +*/

            "   from ry_report rr " +
            "   where ifnull(rr_del,0)=0  " +
            "   ${filter} " +
            "   ${order} " +
            "   limit #{page},#{rows}  ")
    List<ReportBean> getReportBeanList(@Param("filter") String filter,
                                       @Param("order") String order,
                                       @Param("page") int page,
                                       @Param("rows") int rows);

    /**
     * 总数
     */
    @Select("select count(1) " +
            "   from ry_report rr " +
            "   where ifnull(rr_del,0)=0  " +
            "   ${filter} ")
    int getReportCount(@Param("filter") String filter);
}
