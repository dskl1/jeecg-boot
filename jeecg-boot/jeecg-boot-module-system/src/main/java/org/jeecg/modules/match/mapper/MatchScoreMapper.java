package org.jeecg.modules.match.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.jeecg.modules.match.entity.MatchScore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 比赛成绩
 * @Author: jeecg-boot
 * @Date:   2019-08-07
 * @Version: V1.0
 */
public interface MatchScoreMapper extends BaseMapper<MatchScore> {

	public boolean deleteByMainId(String mainId);

	public List<MatchScore> selectByMainId(String mainId);

	@Delete("delete from match_score\n" +
			"where sportsman_id not in (select id\n" +
			"from match_total)")
	public void scoreDelete(); //删除子表外键不存在的数据
}
