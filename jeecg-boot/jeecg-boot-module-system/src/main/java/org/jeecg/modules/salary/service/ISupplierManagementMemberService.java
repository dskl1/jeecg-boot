package org.jeecg.modules.salary.service;

import org.jeecg.modules.salary.entity.SupplierManagementMember;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 人员名单
 * @Author: jeecg-boot
 * @Date:   2021-02-23
 * @Version: V1.0
 */
public interface ISupplierManagementMemberService extends IService<SupplierManagementMember> {

	public List<SupplierManagementMember> selectByMainId(String mainId);

	/**
	 * 插入开票人员(分包公司)
	 */
	public void memberInsert2();
}
