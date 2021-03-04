package org.jeecg.modules.statistics.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.statistics.entity.D50a;
import org.jeecg.modules.statistics.mapper.D50aMapper;
import org.jeecg.modules.statistics.service.ID50aService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 50
 * @author： jeecg-boot
 * @date：   2019-07-15
 * @version： V1.0
 */
@Service
public class D50aServiceImpl extends ServiceImpl<D50aMapper, D50a> implements ID50aService {

	@Autowired
	private D50aMapper d50aMapper;

	@Override
	public List<D50a> selectByMainId(String mainId) {
		return d50aMapper.selectByMainId(mainId);
	}

	@Override
	public List<Map<String,Object>> findTrainingCount(Integer selectValue,String mainId, Date dayStart, Date dayEnd) {
		return d50aMapper.findTrainingCount(selectValue,mainId,dayStart, dayEnd);
	}
}
