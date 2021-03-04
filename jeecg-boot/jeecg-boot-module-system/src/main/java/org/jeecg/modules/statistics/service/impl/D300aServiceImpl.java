package org.jeecg.modules.statistics.service.impl;

import org.jeecg.modules.statistics.entity.D300a;
import org.jeecg.modules.statistics.mapper.D300aMapper;
import org.jeecg.modules.statistics.service.ID300aService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 300
 * @author： jeecg-boot
 * @date：   2019-07-15
 * @version： V1.0
 */
@Service
public class D300aServiceImpl extends ServiceImpl<D300aMapper, D300a> implements ID300aService {

	@Autowired
	private D300aMapper d300aMapper;

	@Override
	public List<D300a> selectByMainId(String mainId) {
		return d300aMapper.selectByMainId(mainId);
	}

	@Override
	public List<Map<String,Object>> findTrainingCount(Integer selectValue, String mainId, Date dayStart, Date dayEnd) {
		return d300aMapper.findTrainingCount(selectValue,mainId,dayStart, dayEnd);
	}
}
