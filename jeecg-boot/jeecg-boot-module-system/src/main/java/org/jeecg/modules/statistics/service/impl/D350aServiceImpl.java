package org.jeecg.modules.statistics.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.statistics.entity.D350a;
import org.jeecg.modules.statistics.mapper.D350aMapper;
import org.jeecg.modules.statistics.service.ID350aService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 350
 * @author： jeecg-boot
 * @date：   2019-07-15
 * @version： V1.0
 */
@Service
public class D350aServiceImpl extends ServiceImpl<D350aMapper, D350a> implements ID350aService {

	@Autowired
	private D350aMapper d350aMapper;

	@Override
	public List<D350a> selectByMainId(String mainId) {
		return d350aMapper.selectByMainId(mainId);
	}

	@Override
	public List<Map<String,Object>> findTrainingCount(Integer selectValue, String mainId, Date dayStart, Date dayEnd) {
		return d350aMapper.findTrainingCount(selectValue,mainId,dayStart, dayEnd);
	}
}
