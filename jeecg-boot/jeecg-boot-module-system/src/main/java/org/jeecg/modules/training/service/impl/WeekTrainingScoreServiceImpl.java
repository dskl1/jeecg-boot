package org.jeecg.modules.training.service.impl;

import org.jeecg.modules.training.entity.WeekTrainingScore;
import org.jeecg.modules.training.mapper.WeekTrainingScoreMapper;
import org.jeecg.modules.training.service.IWeekTrainingScoreService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 训练人员名单
 * @Author: jeecg-boot
 * @Date:   2020-06-17
 * @Version: V1.0
 */
@Service
public class WeekTrainingScoreServiceImpl extends ServiceImpl<WeekTrainingScoreMapper, WeekTrainingScore> implements IWeekTrainingScoreService {
	
	@Autowired
	private WeekTrainingScoreMapper weekTrainingScoreMapper;
	
	@Override
	public List<WeekTrainingScore> selectByMainId(String mainId) {
		return weekTrainingScoreMapper.selectByMainId(mainId);
	}
}
