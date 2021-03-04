package org.jeecg.modules.company.mapper;

import java.util.List;
import org.jeecg.modules.company.entity.BillingReport;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 账单
 * @Author: jeecg-boot
 * @Date:   2020-03-17
 * @Version: V1.0
 */
public interface BillingReportMapper extends BaseMapper<BillingReport> {

	public boolean deleteByMainId(String mainId);

	public List<BillingReport> selectByMainId(String mainId);
}
