package org.jeecg.modules.statistics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.statistics.entity.MatchTotala;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 比赛报表
 * @Author: jeecg-boot
 * @Date:   2019-09-18
 * @Version: V1.0
 */
public interface MatchTotalaMapper extends BaseMapper<MatchTotala> {

	public boolean deleteByMainId(String mainId);

	public List<MatchTotala> selectByMainId(String mainId);

	/**
	 *   首页：根据时间统计成绩（访问数量/ip数量）
	 * @param dayStart
	 * @param dayEnd
	 * @param mainId
	 * @return
	 */
	List<Map<String,Object>> findTrainingCount(@Param("selectValue") Integer selectValue,@Param("mainId") String mainId, @Param("dayStart") Date dayStart, @Param("dayEnd") Date dayEnd);
}
