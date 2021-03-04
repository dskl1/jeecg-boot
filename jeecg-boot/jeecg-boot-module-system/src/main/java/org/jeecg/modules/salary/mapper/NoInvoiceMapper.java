package org.jeecg.modules.salary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.jeecg.modules.salary.entity.NoInvoice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 未开票
 * @Author: jeecg-boot
 * @Date:   2021-01-18
 * @Version: V1.0
 */
public interface NoInvoiceMapper extends BaseMapper<NoInvoice> {

    @Insert("insert ignore into no_invoice\n" +
            "select UUID(),s.name,CURDATE(),\n" +
            "\t(s.positive_day + s.trial_day) / s.total_day * e.price,\n" +
            "\tnull,null,'0',s.create_by,NOW(),null,null,s.sys_org_code\n" +
            "from salary_statistics s,newest_employee_list e\n" +
            "where s.name = e.name")
    public void invoiceInsert();

    @Update("update no_invoice set stage = 1\n" +
            "where id = #{id}")
    public void stageUpdate(String id); //是否开票

    @Insert("insert ignore into invoice_management\n" +
            "select UUID(),invoice_id,invoice_date,null,null,real_invoice_amount,'0',\n" +
            "\t\tcreate_by,create_time,update_by,update_time,sys_org_code\n" +
            "from no_invoice\n" +
            "where ISNULL(`invoice_id`) is false")
    public void manageInsert(); //插入开票管理

    @Insert("insert ignore into invoice_management_member\n" +
            "select UUID(),m.id,n.invoice_member,n.invoice_date,n.real_invoice_amount,\n" +
            "n.create_by,n.create_time,n.update_by,n.update_time,n.sys_org_code\n" +
            "from no_invoice n,invoice_management m\n" +
            "where n.invoice_id = m.invoice_id")
    public void membInsert(); //插入开票人员



}
