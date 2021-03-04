package org.jeecg.modules.match.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.match.entity.MatchMemberp;
import org.jeecg.modules.match.entity.MatchPlan;
import org.jeecg.modules.match.entity.MatchTotalp;
import org.jeecg.modules.match.mapper.MatchTotalpMapper;
import org.jeecg.modules.match.mapper.MatchMemberpMapper;
import org.jeecg.modules.match.service.IMatchMemberpService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 运动员
 * @Author: jeecg-boot
 * @Date:   2019-08-19
 * @Version: V1.0
 */
@Service
public class MatchMemberpServiceImpl extends ServiceImpl<MatchMemberpMapper, MatchMemberp> implements IMatchMemberpService {

	@Autowired
	private MatchMemberpMapper matchMemberpMapper;
	@Autowired
	private MatchTotalpMapper matchTotalpMapper;

	@Override
	@Transactional
	public void saveMain(MatchMemberp matchMemberp, List<MatchTotalp> matchTotalpList) {
		matchMemberpMapper.insert(matchMemberp);
		for(MatchTotalp entity:matchTotalpList) {
			//外键设置
			entity.setSportsmanId(matchMemberp.getId());
			matchTotalpMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(MatchMemberp matchMemberp,List<MatchTotalp> matchTotalpList) {
		matchMemberpMapper.updateById(matchMemberp);

		//1.先删除子表数据
		matchTotalpMapper.deleteByMainId(matchMemberp.getId());

		//2.子表数据重新插入
		for(MatchTotalp entity:matchTotalpList) {
			//外键设置
			entity.setSportsmanId(matchMemberp.getId());
			matchTotalpMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		matchTotalpMapper.deleteByMainId(id);
		matchMemberpMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			matchTotalpMapper.deleteByMainId(id.toString());
			matchMemberpMapper.deleteById(id);
		}
	}

	// 根据运动员Id查询
	@Override
	public IPage<MatchMemberp> selectByDivide() {
		return matchMemberpMapper.selectByDivide();
	}

}
