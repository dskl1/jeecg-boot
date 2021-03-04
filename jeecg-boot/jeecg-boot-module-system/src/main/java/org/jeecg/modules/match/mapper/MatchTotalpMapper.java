package org.jeecg.modules.match.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.match.entity.MatchTotalp;

import java.util.List;

/**
 * @Description: 比赛
 * @Author: jeecg-boot
 * @Date:   2019-08-19
 * @Version: V1.0
 */
public interface MatchTotalpMapper extends BaseMapper<MatchTotalp> {

	public boolean deleteByMainId(String mainId);

	public List<MatchTotalp> selectByMainId(String mainId);

}
