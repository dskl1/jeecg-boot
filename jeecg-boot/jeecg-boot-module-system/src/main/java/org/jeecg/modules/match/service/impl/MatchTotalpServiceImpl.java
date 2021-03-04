package org.jeecg.modules.match.service.impl;

import org.jeecg.modules.match.entity.*;
import org.jeecg.modules.match.mapper.MatchScorepMapper;
import org.jeecg.modules.match.mapper.MatchTotalpMapper;
import org.jeecg.modules.match.service.IMatchTotalpService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.Collection;

/**
 * @Description: 比赛
 * @Author: jeecg-boot
 * @Date:   2019-08-19
 * @Version: V1.0
 */
@Service
public class MatchTotalpServiceImpl extends ServiceImpl<MatchTotalpMapper, MatchTotalp> implements IMatchTotalpService {

	@Autowired
	private MatchTotalpMapper matchTotalpMapper;
	@Autowired
	private MatchScorepMapper matchScorepMapper;

	@Override
	public List<MatchTotalp> selectByMainId(String mainId) {
		return matchTotalpMapper.selectByMainId(mainId);
	}

	@Override
	@Transactional
	public void saveMain(MatchTotalp matchTotalp, List<MatchScorep> matchScorepList) {
		matchTotalpMapper.insert(matchTotalp);
		for(MatchScorep entity:matchScorepList) {
			//外键设置
			entity.setSportsmanId(matchTotalp.getId());
			matchScorepMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(MatchTotalp matchTotalp,List<MatchScorep> matchScorepList) {
		matchTotalpMapper.updateById(matchTotalp);

		//1.先删除子表数据
		matchScorepMapper.deleteByMainId(matchTotalp.getId());

		//2.子表数据重新插入
		for(MatchScorep entity:matchScorepList) {
			//外键设置
			entity.setSportsmanId(matchTotalp.getId());
			matchScorepMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		matchScorepMapper.deleteByMainId(id);
		matchTotalpMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			matchScorepMapper.deleteByMainId(id.toString());
			matchTotalpMapper.deleteById(id);
		}
	}

}
