package org.jeecg.modules.company.service.impl;

import org.jeecg.modules.company.entity.NewestBillingReport;
import org.jeecg.modules.company.mapper.NewestBillingReportMapper;
import org.jeecg.modules.company.service.INewestBillingReportService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 账单
 * @Author: jeecg-boot
 * @Date:   2020-04-02
 * @Version: V1.0
 */
@Service
public class NewestBillingReportServiceImpl extends ServiceImpl<NewestBillingReportMapper, NewestBillingReport> implements INewestBillingReportService {

	@Autowired
	private NewestBillingReportMapper newestBillingReportMapper;

	@Override
	public List<NewestBillingReport> selectByMainId(String mainId) {
		return newestBillingReportMapper.selectByMainId(mainId);
	}
}
