package org.jeecg.modules.match.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.jeecg.modules.match.entity.MatchScorep;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 项目
 * @Author: jeecg-boot
 * @Date:   2019-08-19
 * @Version: V1.0
 */
public interface MatchScorepMapper extends BaseMapper<MatchScorep> {

	public boolean deleteByMainId(String mainId);

	public List<MatchScorep> selectByMainId(String mainId);

	@Delete("delete from match_score_copy1\n" +
			"where sportsman_id not in (select id\n" +
			"from match_total)")
	public void scoreDelete(); //删除子表外键不存在的数据
}
