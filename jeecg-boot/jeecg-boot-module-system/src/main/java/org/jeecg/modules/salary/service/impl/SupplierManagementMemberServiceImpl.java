package org.jeecg.modules.salary.service.impl;

import org.jeecg.modules.salary.entity.SupplierManagementMember;
import org.jeecg.modules.salary.mapper.SupplierManagementMemberMapper;
import org.jeecg.modules.salary.service.ISupplierManagementMemberService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 人员名单
 * @Author: jeecg-boot
 * @Date:   2021-02-23
 * @Version: V1.0
 */
@Service
public class SupplierManagementMemberServiceImpl extends ServiceImpl<SupplierManagementMemberMapper, SupplierManagementMember> implements ISupplierManagementMemberService {

	@Autowired
	private SupplierManagementMemberMapper supplierManagementMemberMapper;

	@Override
	public List<SupplierManagementMember> selectByMainId(String mainId) {
		return supplierManagementMemberMapper.selectByMainId(mainId);
	}

	@Override
	public void memberInsert2() {
		this.supplierManagementMemberMapper.memberInsert2();
	}
}
