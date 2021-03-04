package org.jeecg.modules.company.service;

import org.jeecg.modules.company.entity.BillingReport;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 账单
 * @Author: jeecg-boot
 * @Date:   2020-03-17
 * @Version: V1.0
 */
public interface IBillingReportService extends IService<BillingReport> {

	public List<BillingReport> selectByMainId(String mainId);
}
