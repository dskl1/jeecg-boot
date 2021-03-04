package org.jeecg.modules.salary.service;

import org.jeecg.modules.salary.entity.InvoiceManagementAttached;
import org.jeecg.modules.salary.entity.InvoiceManagement;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.salary.entity.InvoiceManagementMember;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 开票管理
 * @Author: jeecg-boot
 * @Date:   2020-08-31
 * @Version: V1.0
 */
public interface IInvoiceManagementService extends IService<InvoiceManagement> {

	/**
	 * 添加一对多
	 *
	 */
	public void saveMain(InvoiceManagement invoiceManagement,List<InvoiceManagementAttached> invoiceManagementAttachedList,List<InvoiceManagementMember> invoiceManagementMemberList) ;

	/**
	 * 修改一对多
	 *
	 */
	public void updateMain(InvoiceManagement invoiceManagement,List<InvoiceManagementAttached> invoiceManagementAttachedList,List<InvoiceManagementMember> invoiceManagementMemberList);

	/**
	 * 删除一对多
	 */
	public void delMain (String id);

	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);

	/**
	 * 确认
	 */
	public void stageUpdate(String id);

	/**
	 * 计算总金额
	 */
	List<InvoiceManagement> totalUpdate();

	/**
	 * 插入开票管理
	 */
	public void invoiceInsert();

	/**
	 * 删除子表外键不存在的数据
	 */
	public void memberDelete();

	/**
	 * 开票编号重复覆盖原始数据
	 */
	public void deleteId();

}
