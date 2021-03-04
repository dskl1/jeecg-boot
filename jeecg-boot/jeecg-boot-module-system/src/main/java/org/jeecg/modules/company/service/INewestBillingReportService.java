package org.jeecg.modules.company.service;

import org.jeecg.modules.company.entity.NewestBillingReport;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 账单
 * @Author: jeecg-boot
 * @Date:   2020-04-02
 * @Version: V1.0
 */
public interface INewestBillingReportService extends IService<NewestBillingReport> {

	public List<NewestBillingReport> selectByMainId(String mainId);
}
