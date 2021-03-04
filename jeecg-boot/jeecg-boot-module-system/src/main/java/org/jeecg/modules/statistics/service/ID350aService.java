package org.jeecg.modules.statistics.service;

import org.jeecg.modules.statistics.entity.D350a;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 350
 * @author： jeecg-boot
 * @date：   2019-07-15
 * @version： V1.0
 */
public interface ID350aService extends IService<D350a> {

	public List<D350a> selectByMainId(String mainId);

	/**
	 *   首页：根据时间统计训练成绩（访问数量/ip数量）
	 * @param dayStart
	 * @param mainId
	 * @param dayEnd
	 * @param selectValue
	 * @return
	 */
	List<Map<String,Object>> findTrainingCount(Integer selectValue, String mainId, Date dayStart, Date dayEnd);
}
