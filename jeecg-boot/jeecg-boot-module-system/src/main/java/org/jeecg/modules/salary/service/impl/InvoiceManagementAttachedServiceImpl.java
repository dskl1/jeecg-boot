package org.jeecg.modules.salary.service.impl;

import org.jeecg.modules.salary.entity.InvoiceManagementAttached;
import org.jeecg.modules.salary.mapper.InvoiceManagementAttachedMapper;
import org.jeecg.modules.salary.service.IInvoiceManagementAttachedService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 附票
 * @Author: jeecg-boot
 * @Date:   2020-08-31
 * @Version: V1.0
 */
@Service
public class InvoiceManagementAttachedServiceImpl extends ServiceImpl<InvoiceManagementAttachedMapper, InvoiceManagementAttached> implements IInvoiceManagementAttachedService {

	@Autowired
	private InvoiceManagementAttachedMapper invoiceManagementAttachedMapper;

	@Override
	public List<InvoiceManagementAttached> selectByMainId(String mainId) {
		return invoiceManagementAttachedMapper.selectByMainId(mainId);
	}

	@Override
	public void stageUpdate(String ids){
		this.invoiceManagementAttachedMapper.stageUpdate(ids);
	}
}
