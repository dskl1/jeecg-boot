package org.jeecg.modules.statistics.service.impl;

import org.jeecg.modules.statistics.entity.Statistic;
import org.jeecg.modules.statistics.entity.MatchTotala;
import org.jeecg.modules.statistics.mapper.MatchTotalaMapper;
import org.jeecg.modules.statistics.mapper.StatisticMapper;
import org.jeecg.modules.statistics.service.IStatisticService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 比赛报表
 * @Author: jeecg-boot
 * @Date:   2019-09-18
 * @Version: V1.0
 */
@Service
public class StatisticServiceImpl extends ServiceImpl<StatisticMapper, Statistic> implements IStatisticService {

	@Autowired
	private StatisticMapper statisticMapper;
	@Autowired
	private MatchTotalaMapper matchTotalaMapper;

	@Override
	@Transactional
	public void saveMain(Statistic statistic, List<MatchTotala> matchTotalaList) {
		statisticMapper.insert(statistic);
		for(MatchTotala entity:matchTotalaList) {
			//外键设置
			entity.setSportsmanId(statistic.getId());
			matchTotalaMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(Statistic statistic,List<MatchTotala> matchTotalaList) {
		statisticMapper.updateById(statistic);

		//1.先删除子表数据
		matchTotalaMapper.deleteByMainId(statistic.getId());

		//2.子表数据重新插入
		for(MatchTotala entity:matchTotalaList) {
			//外键设置
			entity.setSportsmanId(statistic.getId());
			matchTotalaMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		matchTotalaMapper.deleteByMainId(id);
		statisticMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			matchTotalaMapper.deleteByMainId(id.toString());
			statisticMapper.deleteById(id);
		}
	}

}
