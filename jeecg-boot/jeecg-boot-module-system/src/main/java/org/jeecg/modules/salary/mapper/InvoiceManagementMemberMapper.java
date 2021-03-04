package org.jeecg.modules.salary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.jeecg.modules.salary.entity.InvoiceManagementMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 对应人员
 * @Author: jeecg-boot
 * @Date: 2020-09-03
 * @Version: V1.0
 */
public interface InvoiceManagementMemberMapper extends BaseMapper<InvoiceManagementMember> {

    public boolean deleteByMainId(@Param("mainId") String mainId);

    public List<InvoiceManagementMember> selectByMainId(@Param("mainId") String mainId);

    @Insert("insert ignore into invoice_management_member\n" +
            "select UUID(),i.id,e.name,b.invoice_time,b.billing_month,b.billing_price,\n" +
            "b.create_by,now(),null,null,b.sys_org_code\n" +
            "from newest_employee_list e,billing_report b,invoice_management i\n" +
            "where e.id = b.sportsman_id and i.invoice_id = b.invoice_id")
    public void memberInsert(); //插入开票人员
}
