package org.jeecg.modules.match.service.impl;

import org.jeecg.modules.match.entity.MatchTotal;
import org.jeecg.modules.match.entity.MatchScore;
import org.jeecg.modules.match.mapper.MatchScoreMapper;
import org.jeecg.modules.match.mapper.MatchTotalMapper;
import org.jeecg.modules.match.service.IMatchTotalService;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.Collection;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 比赛名称
 * @Author: jeecg-boot
 * @Date:   2019-08-07
 * @Version: V1.0
 */
@Service
public class MatchTotalServiceImpl extends ServiceImpl<MatchTotalMapper, MatchTotal> implements IMatchTotalService {

	@Autowired
	private MatchTotalMapper matchTotalMapper;
	@Autowired
	private MatchScoreMapper matchScoreMapper;

	@Override
	public List<MatchTotal> selectByMainId(String mainId) {
		return matchTotalMapper.selectByMainId(mainId);
	}

	@Override
	@Transactional
	public void saveMain(MatchTotal matchTotal, List<MatchScore> matchScoreList) {
		matchTotalMapper.insert(matchTotal);
		for(MatchScore entity:matchScoreList) {
			//外键设置
			entity.setSportsmanId(matchTotal.getId());
			matchScoreMapper.insert(entity);
			matchTotalMapper.updateByMainId(matchTotal.getId());
		}
	}

	@Override
	@Transactional
	public void updateMain(MatchTotal matchTotal,List<MatchScore> matchScoreList) {
		matchTotalMapper.updateById(matchTotal);

		//1.先删除子表数据
		matchScoreMapper.deleteByMainId(matchTotal.getId());
		matchTotalMapper.updateByMainId(matchTotal.getId());

		//2.子表数据重新插入
		for(MatchScore entity:matchScoreList) {
			//外键设置
			entity.setSportsmanId(matchTotal.getId());
			matchScoreMapper.insert(entity);
			matchTotalMapper.updateByMainId(matchTotal.getId());
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		matchScoreMapper.deleteByMainId(id);
		matchTotalMapper.deleteById(id);
		matchTotalMapper.updateByMainId(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			matchScoreMapper.deleteByMainId(id.toString());
			matchTotalMapper.deleteById(id);
		}
	}

	@Override
	public List<MatchTotal> updateByMainId(String mainId) {

		return null;
	}
}
