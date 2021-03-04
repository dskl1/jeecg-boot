package org.jeecg.modules.match.service.impl;

import org.jeecg.modules.match.entity.MatchMember;
import org.jeecg.modules.match.entity.MatchTotal;
import org.jeecg.modules.match.mapper.MatchTotalMapper;
import org.jeecg.modules.match.mapper.MatchMemberMapper;
import org.jeecg.modules.match.service.IMatchMemberService;
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
 * @Date:   2019-08-07
 * @Version: V1.0
 */
@Service
public class MatchMemberServiceImpl extends ServiceImpl<MatchMemberMapper, MatchMember> implements IMatchMemberService {

	@Autowired
	private MatchMemberMapper matchMemberMapper;
	@Autowired
	private MatchTotalMapper matchTotalMapper;

	@Override
	@Transactional
	public void saveMain(MatchMember matchMember, List<MatchTotal> matchTotalList) {
		matchMemberMapper.insert(matchMember);
		for(MatchTotal entity:matchTotalList) {
			//外键设置
			entity.setSportsmanId(matchMember.getId());
			matchTotalMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(MatchMember matchMember,List<MatchTotal> matchTotalList) {
		matchMemberMapper.updateById(matchMember);

		//1.先删除子表数据
		matchTotalMapper.deleteByMainId(matchMember.getId());

		//2.子表数据重新插入
		for(MatchTotal entity:matchTotalList) {
			//外键设置
			entity.setSportsmanId(matchMember.getId());
			matchTotalMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		matchTotalMapper.deleteByMainId(id);
		matchMemberMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			matchTotalMapper.deleteByMainId(id.toString());
			matchMemberMapper.deleteById(id);
		}
	}

}
