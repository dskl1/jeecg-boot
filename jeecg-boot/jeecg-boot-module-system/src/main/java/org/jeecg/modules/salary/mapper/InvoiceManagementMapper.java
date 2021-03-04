package org.jeecg.modules.salary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.jeecg.modules.salary.entity.InvoiceManagement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 开票管理
 * @Author: jeecg-boot
 * @Date:   2020-08-31
 * @Version: V1.0
 */
public interface InvoiceManagementMapper extends BaseMapper<InvoiceManagement> {

    @Update("update invoice_management\n" +
            "set stage = 1\n" +
            "where id = #{id}")
    public void stageUpdate(String id); //确认改变状态为1

    @Select("update invoice_management i\n" +
            "set total_amount = (\n" +
            "select sum(invoice_amount)\n" +
            "from invoice_management_member im\n" +
            "group by sportsman_id\n" +
            "having i.id = im.sportsman_id)")
    public List<InvoiceManagement> totalUpdate(); //计算发票总金额

    @Insert("insert ignore into invoice_management\n" +
            "select UUID(),b.invoice_id,b.invoice_time,0,e.client,b.subcontracting_company,'0',0,\n" +
            "b.create_by,b.create_time,b.update_by,b.update_time,b.sys_org_code\n" +
            "from newest_employee_list e,billing_report b\n" +
            "where e.id = b.sportsman_id and ISNULL(invoice_id) is false")
    public void invoiceInsert(); //插入开票管理

    @Delete("delete from invoice_management_member\n" +
            "where sportsman_id not in (select id\n" +
            "from invoice_management)")
    public void memberDelete(); //删除子表外键不存在的数据

    @Delete("DELETE from invoice_management \n" +
            "WHERE id in(\n" +
            "select a.id\n" +
            "from (\n" +
            "select *,min(create_time) \n" +
            "from invoice_management \n" +
            "group by invoice_id\n" +
            "having count(invoice_id) > 1)a)")
    public void deleteId(); //开票编号重复覆盖原始数据

}
