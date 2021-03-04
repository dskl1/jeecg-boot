package org.jeecg.modules.company.service.impl;

import org.jeecg.modules.company.entity.BasicSituation;
import org.jeecg.modules.company.entity.TestWord;
import org.jeecg.modules.company.mapper.TestWordMapper;
import org.jeecg.modules.company.mapper.BasicSituationMapper;
import org.jeecg.modules.company.service.IBasicSituationService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 简历
 * @Author: jeecg-boot
 * @Date:   2020-04-21
 * @Version: V1.0
 */
@Service
public class BasicSituationServiceImpl extends ServiceImpl<BasicSituationMapper, BasicSituation> implements IBasicSituationService {

	@Autowired
	private BasicSituationMapper basicSituationMapper;
	@Autowired
	private TestWordMapper testWordMapper;

	@Override
	@Transactional
	public void delMain(String id) {
		testWordMapper.deleteByMainId(id);
		basicSituationMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			testWordMapper.deleteByMainId(id.toString());
			basicSituationMapper.deleteById(id);
		}
	}

	@Override
	public List<BasicSituation> selectUserInfoByNa(String name) {
		return basicSituationMapper.selectUserInfoByNa(name);
	}

}
