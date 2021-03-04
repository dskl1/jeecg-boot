package org.jeecg.modules.statistics.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.statistics.entity.MatchTotala;
import org.jeecg.modules.statistics.mapper.MatchTotalaMapper;
import org.jeecg.modules.statistics.service.IMatchTotalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 比赛报表
 * @Author: jeecg-boot
 * @Date:   2019-09-18
 * @Version: V1.0
 */
@Service
public class MatchTotalaServiceImpl extends ServiceImpl<MatchTotalaMapper, MatchTotala> implements IMatchTotalaService {

	@Autowired
	private MatchTotalaMapper matchTotalaMapper;

	@Override
	public List<MatchTotala> selectByMainId(String mainId) {
		return matchTotalaMapper.selectByMainId(mainId);
	}

	@Override
	public List<Map<String,Object>> findTrainingCount(Integer selectValue,String mainId, Date dayStart, Date dayEnd) {
		return matchTotalaMapper.findTrainingCount(selectValue,mainId,dayStart, dayEnd);
	}
}
