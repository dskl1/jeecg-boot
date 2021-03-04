package org.jeecg.modules.statistics.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.statistics.entity.D100a;
import org.jeecg.modules.statistics.mapper.D100aMapper;
import org.jeecg.modules.statistics.service.ID100aService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 100
 * @author： jeecg-boot
 * @date：   2019-07-15
 * @version： V1.0
 */
@Service
public class D100aServiceImpl extends ServiceImpl<D100aMapper, D100a> implements ID100aService {

	@Autowired
	private D100aMapper d100aMapper;

	@Override
	public List<D100a> selectByMainId(String mainId) {
		return d100aMapper.selectByMainId(mainId);
	}

	@Override
	public List<Map<String,Object>> findTrainingCount(Integer selectValue, String mainId, Date dayStart, Date dayEnd) {
		return d100aMapper.findTrainingCount(selectValue,mainId,dayStart, dayEnd);
	}
}
