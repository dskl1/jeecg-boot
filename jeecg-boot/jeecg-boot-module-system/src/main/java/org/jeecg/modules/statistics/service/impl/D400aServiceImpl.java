package org.jeecg.modules.statistics.service.impl;

import org.jeecg.modules.statistics.entity.D400a;
import org.jeecg.modules.statistics.mapper.D400aMapper;
import org.jeecg.modules.statistics.service.ID400aService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 400
 * @author： jeecg-boot
 * @date：   2019-07-15
 * @version： V1.0
 */
@Service
public class D400aServiceImpl extends ServiceImpl<D400aMapper, D400a> implements ID400aService {

	@Autowired
	private D400aMapper d400aMapper;

	@Override
	public List<D400a> selectByMainId(String mainId) {
		return d400aMapper.selectByMainId(mainId);
	}

	@Override
	public List<Map<String,Object>> findTrainingCount(Integer selectValue, String mainId, Date dayStart, Date dayEnd) {
		return d400aMapper.findTrainingCount(selectValue,mainId,dayStart, dayEnd);
	}
}
