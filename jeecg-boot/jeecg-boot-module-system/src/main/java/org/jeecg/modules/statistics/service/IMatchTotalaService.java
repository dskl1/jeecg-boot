package org.jeecg.modules.statistics.service;

import org.jeecg.modules.statistics.entity.MatchTotala;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 比赛报表
 * @Author: jeecg-boot
 * @Date:   2019-09-18
 * @Version: V1.0
 */
public interface IMatchTotalaService extends IService<MatchTotala> {

	public List<MatchTotala> selectByMainId(String mainId);

	/**
	 *   首页：根据时间统计训练成绩（访问数量/ip数量）
	 * @param dayStart
	 * @param mainId
	 * @param dayEnd
	 * @return
	 */
	List<Map<String,Object>> findTrainingCount(Integer selectValue,String mainId, Date dayStart, Date dayEnd);
}
