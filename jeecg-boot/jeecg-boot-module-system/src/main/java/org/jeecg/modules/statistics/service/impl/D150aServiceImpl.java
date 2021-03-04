package org.jeecg.modules.statistics.service.impl;

import org.jeecg.modules.statistics.entity.D150a;
import org.jeecg.modules.statistics.mapper.D150aMapper;
import org.jeecg.modules.statistics.service.ID150aService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 150
 * @author： jeecg-boot
 * @date：   2019-07-15
 * @version： V1.0
 */
@Service
public class D150aServiceImpl extends ServiceImpl<D150aMapper, D150a> implements ID150aService {

	@Autowired
	private D150aMapper d150aMapper;

	@Override
	public List<D150a> selectByMainId(String mainId) {
		return d150aMapper.selectByMainId(mainId);
	}

	@Override
	public List<Map<String,Object>> findTrainingCount(Integer selectValue, String mainId, Date dayStart, Date dayEnd) {
		return d150aMapper.findTrainingCount(selectValue,mainId,dayStart, dayEnd);
	}
}
