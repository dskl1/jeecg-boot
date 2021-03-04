package org.jeecg.modules.match.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.match.entity.MatchTotal;

import java.util.List;

/**
 * @Description: 比赛名称
 * @Author: jeecg-boot
 * @Date:   2019-08-07
 * @Version: V1.0
 */
public interface MatchTotalMapper extends BaseMapper<MatchTotal> {

	public boolean deleteByMainId(String mainId);

	public List<MatchTotal> selectByMainId(String mainId);

	public boolean updateByMainId(String mainId);
}
