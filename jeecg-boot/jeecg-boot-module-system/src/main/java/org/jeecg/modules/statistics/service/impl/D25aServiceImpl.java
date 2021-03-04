package org.jeecg.modules.statistics.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.statistics.entity.D25a;
import org.jeecg.modules.statistics.mapper.D25aMapper;
import org.jeecg.modules.statistics.service.ID25aService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 25
 * @author： jeecg-boot
 * @date：   2019-07-15
 * @version： V1.0
 */
@Service
public class D25aServiceImpl extends ServiceImpl<D25aMapper, D25a> implements ID25aService {

	@Autowired
	private D25aMapper d25aMapper;

	@Override
	public List<D25a> selectByMainId(String mainId) {
		return d25aMapper.selectByMainId(mainId);
	}

	@Override
	public List<Map<String,Object>> findTrainingCount(Integer selectValue,String mainId, Date dayStart, Date dayEnd) {
		return d25aMapper.findTrainingCount(selectValue,mainId,dayStart, dayEnd);
	}

}
