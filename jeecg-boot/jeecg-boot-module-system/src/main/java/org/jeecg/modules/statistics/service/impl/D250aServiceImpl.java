package org.jeecg.modules.statistics.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.statistics.entity.D250a;
import org.jeecg.modules.statistics.mapper.D250aMapper;
import org.jeecg.modules.statistics.service.ID250aService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 250
 * @author： jeecg-boot
 * @date：   2019-07-15
 * @version： V1.0
 */
@Service
public class D250aServiceImpl extends ServiceImpl<D250aMapper, D250a> implements ID250aService {

	@Autowired
	private D250aMapper d250aMapper;

	@Override
	public List<D250a> selectByMainId(String mainId) {
		return d250aMapper.selectByMainId(mainId);
	}

	@Override
	public List<Map<String,Object>> findTrainingCount(Integer selectValue, String mainId, Date dayStart, Date dayEnd) {
		return d250aMapper.findTrainingCount(selectValue,mainId,dayStart, dayEnd);
	}
}
