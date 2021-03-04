package org.jeecg.modules.statistics.service.impl;

import org.jeecg.modules.statistics.entity.D200a;
import org.jeecg.modules.statistics.mapper.D200aMapper;
import org.jeecg.modules.statistics.service.ID200aService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 200
 * @author： jeecg-boot
 * @date：   2019-07-15
 * @version： V1.0
 */
@Service
public class D200aServiceImpl extends ServiceImpl<D200aMapper, D200a> implements ID200aService {

	@Autowired
	private D200aMapper d200aMapper;

	@Override
	public List<D200a> selectByMainId(String mainId) {
		return d200aMapper.selectByMainId(mainId);
	}

	@Override
	public List<Map<String,Object>> findTrainingCount(Integer selectValue, String mainId, Date dayStart, Date dayEnd) {
		return d200aMapper.findTrainingCount(selectValue,mainId,dayStart, dayEnd);
	}
}
