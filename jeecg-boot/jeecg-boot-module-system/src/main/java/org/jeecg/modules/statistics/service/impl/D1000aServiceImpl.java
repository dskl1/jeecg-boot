package org.jeecg.modules.statistics.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.statistics.entity.D1000a;
import org.jeecg.modules.statistics.mapper.D1000aMapper;
import org.jeecg.modules.statistics.service.ID1000aService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 1000
 * @author： jeecg-boot
 * @date：   2019-07-15
 * @version： V1.0
 */
@Service
public class D1000aServiceImpl extends ServiceImpl<D1000aMapper, D1000a> implements ID1000aService {

	@Autowired
	private D1000aMapper d1000aMapper;

	@Override
	public List<D1000a> selectByMainId(String mainId) {
		return d1000aMapper.selectByMainId(mainId);
	}

	@Override
	public List<Map<String,Object>> findTrainingCount(Integer selectValue, String mainId, Date dayStart, Date dayEnd) {
		return d1000aMapper.findTrainingCount(selectValue,mainId,dayStart, dayEnd);
	}
}
