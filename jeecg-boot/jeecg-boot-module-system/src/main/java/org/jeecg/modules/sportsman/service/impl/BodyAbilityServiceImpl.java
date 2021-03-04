package org.jeecg.modules.sportsman.service.impl;

import org.jeecg.modules.sportsman.entity.BodyAbility;
import org.jeecg.modules.sportsman.mapper.BodyAbilityMapper;
import org.jeecg.modules.sportsman.service.IBodyAbilityService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 身体素质
 * @author： jeecg-boot
 * @date：   2019-06-20
 * @version： V1.0
 */
@Service
public class BodyAbilityServiceImpl extends ServiceImpl<BodyAbilityMapper, BodyAbility> implements IBodyAbilityService {

	@Autowired
	private BodyAbilityMapper bodyAbilityMapper;

	@Override
	public List<BodyAbility> selectByMainId(String mainId) {
		return bodyAbilityMapper.selectByMainId(mainId);
	}
}
