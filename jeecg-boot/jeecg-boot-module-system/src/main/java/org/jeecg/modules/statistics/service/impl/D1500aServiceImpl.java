package org.jeecg.modules.statistics.service.impl;

import org.jeecg.modules.statistics.entity.D1500a;
import org.jeecg.modules.statistics.mapper.D1500aMapper;
import org.jeecg.modules.statistics.service.ID1500aService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 1500
 * @author： jeecg-boot
 * @date：   2019-07-15
 * @version： V1.0
 */
@Service
public class D1500aServiceImpl extends ServiceImpl<D1500aMapper, D1500a> implements ID1500aService {

	@Autowired
	private D1500aMapper d1500aMapper;

	@Override
	public List<D1500a> selectByMainId(String mainId) {
		return d1500aMapper.selectByMainId(mainId);
	}

	@Override
	public List<Map<String,Object>> findTrainingCount(Integer selectValue, String mainId, Date dayStart, Date dayEnd) {
		return d1500aMapper.findTrainingCount(selectValue,mainId,dayStart, dayEnd);
	}
}
