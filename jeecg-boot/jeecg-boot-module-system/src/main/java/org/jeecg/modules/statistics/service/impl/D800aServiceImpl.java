package org.jeecg.modules.statistics.service.impl;

import org.jeecg.modules.statistics.entity.D800a;
import org.jeecg.modules.statistics.mapper.D800aMapper;
import org.jeecg.modules.statistics.service.ID800aService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 800
 * @author： jeecg-boot
 * @date：   2019-07-15
 * @version： V1.0
 */
@Service
public class D800aServiceImpl extends ServiceImpl<D800aMapper, D800a> implements ID800aService {

	@Autowired
	private D800aMapper d800aMapper;

	@Override
	public List<D800a> selectByMainId(String mainId) {
		return d800aMapper.selectByMainId(mainId);
	}

	@Override
	public List<Map<String,Object>> findTrainingCount(Integer selectValue, String mainId, Date dayStart, Date dayEnd) {
		return d800aMapper.findTrainingCount(selectValue,mainId,dayStart, dayEnd);
	}
}
