package org.jeecg.modules.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Update;
import org.jeecg.modules.company.entity.NewestBillingReport;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 账单
 * @Author: jeecg-boot
 * @Date:   2020-04-02
 * @Version: V1.0
 */
public interface NewestBillingReportMapper extends BaseMapper<NewestBillingReport> {

	public boolean deleteByMainId(String mainId);

	public List<NewestBillingReport> selectByMainId(String mainId);
}
