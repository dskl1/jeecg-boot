package org.jeecg.modules.sportsman.service.impl;

import org.jeecg.modules.sportsman.entity.BodyType;
import org.jeecg.modules.sportsman.mapper.BodyTypeMapper;
import org.jeecg.modules.sportsman.service.IBodyTypeService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 身体形态指标
 * @author： jeecg-boot
 * @date：   2019-06-20
 * @version： V1.0
 */
@Service
public class BodyTypeServiceImpl extends ServiceImpl<BodyTypeMapper, BodyType> implements IBodyTypeService {

	@Autowired
	private BodyTypeMapper bodyTypeMapper;

	@Override
	public List<BodyType> selectByMainId(String mainId) {
		return bodyTypeMapper.selectByMainId(mainId);
	}
}
