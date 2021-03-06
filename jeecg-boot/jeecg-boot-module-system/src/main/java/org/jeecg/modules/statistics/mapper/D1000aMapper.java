package org.jeecg.modules.statistics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.statistics.entity.D1000a;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 1000
 * @author： jeecg-boot
 * @date：   2019-07-15
 * @version： V1.0
 */
public interface D1000aMapper extends BaseMapper<D1000a> {

	public boolean deleteByMainId(String mainId);

	public List<D1000a> selectByMainId(String mainId);

	/**
	 *   首页：根据时间统计成绩（访问数量/ip数量）
	 * @param dayStart
	 * @param dayEnd
	 * @param mainId
	 * @param selectValue
	 * @return
	 */
	List<Map<String,Object>> findTrainingCount(@Param("selectValue") Integer selectValue, @Param("mainId") String mainId, @Param("dayStart") Date dayStart, @Param("dayEnd") Date dayEnd);
}
