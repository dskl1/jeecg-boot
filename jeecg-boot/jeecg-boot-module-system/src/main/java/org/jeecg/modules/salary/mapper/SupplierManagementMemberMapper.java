package org.jeecg.modules.salary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.jeecg.modules.salary.entity.SupplierManagementMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 人员名单
 * @Author: jeecg-boot
 * @Date:   2021-02-23
 * @Version: V1.0
 */
public interface SupplierManagementMemberMapper extends BaseMapper<SupplierManagementMember> {

	public boolean deleteByMainId(@Param("mainId") String mainId);

	public List<SupplierManagementMember> selectByMainId(@Param("mainId") String mainId);

	@Insert("insert ignore into supplier_management_member\n" +
			"select UUID(),i.id,e.name,b.is_invoicing,b.invoicing_day,b.payment,\n" +
			"b.create_by,now(),null,null,b.sys_org_code\n" +
			"from newest_employee_list e,billing_report b,supplier_management i\n" +
			"where e.id = b.sportsman_id and i.invoice_id = b.billing_id")
	public void memberInsert2(); //插入开票人员(分包公司)
}
