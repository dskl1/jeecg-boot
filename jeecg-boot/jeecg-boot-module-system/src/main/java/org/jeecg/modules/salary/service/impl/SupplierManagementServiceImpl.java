package org.jeecg.modules.salary.service.impl;

import org.jeecg.modules.salary.entity.InvoiceManagement;
import org.jeecg.modules.salary.entity.SupplierManagement;
import org.jeecg.modules.salary.entity.SupplierManagementMember;
import org.jeecg.modules.salary.mapper.SupplierManagementMemberMapper;
import org.jeecg.modules.salary.mapper.SupplierManagementMapper;
import org.jeecg.modules.salary.service.ISupplierManagementService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 供应商管理
 * @Author: jeecg-boot
 * @Date:   2021-02-23
 * @Version: V1.0
 */
@Service
public class SupplierManagementServiceImpl extends ServiceImpl<SupplierManagementMapper, SupplierManagement> implements ISupplierManagementService {

	@Autowired
	private SupplierManagementMapper supplierManagementMapper;
	@Autowired
	private SupplierManagementMemberMapper supplierManagementMemberMapper;

	@Override
	@Transactional
	public void saveMain(SupplierManagement supplierManagement, List<SupplierManagementMember> supplierManagementMemberList) {
		supplierManagementMapper.insert(supplierManagement);
		if(supplierManagementMemberList!=null && supplierManagementMemberList.size()>0) {
			for(SupplierManagementMember entity:supplierManagementMemberList) {
				//外键设置
				entity.setSportsmanId(supplierManagement.getId());
				supplierManagementMemberMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(SupplierManagement supplierManagement,List<SupplierManagementMember> supplierManagementMemberList) {
		supplierManagementMapper.updateById(supplierManagement);

		//1.先删除子表数据
		supplierManagementMemberMapper.deleteByMainId(supplierManagement.getId());

		//2.子表数据重新插入
		if(supplierManagementMemberList!=null && supplierManagementMemberList.size()>0) {
			for(SupplierManagementMember entity:supplierManagementMemberList) {
				//外键设置
				entity.setSportsmanId(supplierManagement.getId());
				supplierManagementMemberMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		supplierManagementMemberMapper.deleteByMainId(id);
		supplierManagementMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			supplierManagementMemberMapper.deleteByMainId(id.toString());
			supplierManagementMapper.deleteById(id);
		}
	}

	@Override
	public void billingInsert() {
		this.supplierManagementMapper.billingInsert();
	}

	@Override
	public void stageUpdate(String id) {
		supplierManagementMapper.stageUpdate(id);
	}

	@Override
	public List<InvoiceManagement> totalUpdate() {
		return this.supplierManagementMapper.totalUpdate();
	}

	@Override
	public void memberDelete() {
		this.supplierManagementMapper.memberDelete();
	}

	@Override
	public void deleteId() {
		this.supplierManagementMapper.deleteId();
	}

}
