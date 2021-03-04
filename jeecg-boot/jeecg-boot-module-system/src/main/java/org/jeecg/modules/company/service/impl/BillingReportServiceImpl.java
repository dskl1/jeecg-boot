package org.jeecg.modules.company.service.impl;

import org.jeecg.modules.company.entity.BillingReport;
import org.jeecg.modules.company.mapper.BillingReportMapper;
import org.jeecg.modules.company.service.IBillingReportService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 账单
 * @Author: jeecg-boot
 * @Date:   2020-03-17
 * @Version: V1.0
 */
@Service
public class BillingReportServiceImpl extends ServiceImpl<BillingReportMapper, BillingReport> implements IBillingReportService {

	@Autowired
	private BillingReportMapper billingReportMapper;

	@Override
	public List<BillingReport> selectByMainId(String mainId) {
		return billingReportMapper.selectByMainId(mainId);
	}
}
