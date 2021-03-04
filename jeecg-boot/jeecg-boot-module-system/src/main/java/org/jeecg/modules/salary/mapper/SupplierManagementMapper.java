package org.jeecg.modules.salary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.jeecg.modules.salary.entity.InvoiceManagement;
import org.jeecg.modules.salary.entity.SupplierManagement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 供应商管理
 * @Author: jeecg-boot
 * @Date:   2021-02-23
 * @Version: V1.0
 */
public interface SupplierManagementMapper extends BaseMapper<SupplierManagement> {

    @Insert("insert ignore into supplier_management\n" +
            "select UUID(),b.billing_id,b.invoicing_day,0,e.client,b.subcontracting_company,0,0,\n" +
            "b.create_by,now(),null,null,b.sys_org_code\n" +
            "from billing_report b,newest_employee_list e\n" +
            "where ISNULL(b.billing_id) is false and e.id = b.sportsman_id")
    public void billingInsert(); //插入开票管理（分包公司）

    @Update("update supplier_management\n" +
            "set stage = 1\n" +
            "where id = #{id}")
    public void stageUpdate(String id); //确认改变状态为1

    @Select("update supplier_management i\n" +
            "set total_amount = (\n" +
            "select sum(invoice_amount)\n" +
            "from supplier_management_member im\n" +
            "group by sportsman_id\n" +
            "having i.id = im.sportsman_id)")
    public List<InvoiceManagement> totalUpdate(); //计算发票总金额

    @Delete("delete from supplier_management_member\n" +
            "where sportsman_id not in (select id\n" +
            "from supplier_management)")
    public void memberDelete(); //删除子表外键不存在的数据

    @Delete("DELETE from supplier_management \n" +
            "WHERE id in(\n" +
            "select a.id\n" +
            "from (\n" +
            "select *,min(create_time) \n" +
            "from supplier_management \n" +
            "group by invoice_id\n" +
            "having count(invoice_id) > 1)a)")
    public void deleteId(); //开票编号重复覆盖原始数据

}
