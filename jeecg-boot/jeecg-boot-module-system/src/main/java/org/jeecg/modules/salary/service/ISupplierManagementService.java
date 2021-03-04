package org.jeecg.modules.salary.service;

import org.jeecg.modules.salary.entity.InvoiceManagement;
import org.jeecg.modules.salary.entity.SupplierManagementMember;
import org.jeecg.modules.salary.entity.SupplierManagement;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 供应商管理
 * @Author: jeecg-boot
 * @Date:   2021-02-23
 * @Version: V1.0
 */
public interface ISupplierManagementService extends IService<SupplierManagement> {

	/**
	 * 添加一对多
	 *
	 */
	public void saveMain(SupplierManagement supplierManagement,List<SupplierManagementMember> supplierManagementMemberList) ;

	/**
	 * 修改一对多
	 *
	 */
	public void updateMain(SupplierManagement supplierManagement,List<SupplierManagementMember> supplierManagementMemberList);

	/**
	 * 删除一对多
	 */
	public void delMain (String id);

	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);

	/**
	 * 插入开票管理（分包公司）
	 */
	public void billingInsert();

	/**
	 * 确认
	 */
	public void stageUpdate(String id);

	/**
	 * 计算总金额
	 */
	List<InvoiceManagement> totalUpdate();

	/**
	 * 删除子表外键不存在的数据
	 */
	public void memberDelete();

	/**
	 * 开票编号重复覆盖原始数据
	 */
	public void deleteId();

}
