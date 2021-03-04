package org.jeecg.modules.sportsman.service.impl;

import org.jeecg.modules.sportsman.entity.SportsmanData;
import org.jeecg.modules.sportsman.mapper.SportsmanDataMapper;
import org.jeecg.modules.sportsman.service.ISportsmanDataService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 个人资料
 * @author： jeecg-boot
 * @date：   2019-06-28
 * @version： V1.0
 */
@Service
public class SportsmanDataServiceImpl extends ServiceImpl<SportsmanDataMapper, SportsmanData> implements ISportsmanDataService {

	@Autowired
	private SportsmanDataMapper sportsmanDataMapper;

	@Override
	public List<SportsmanData> selectByMainId(String mainId) {
		return sportsmanDataMapper.selectByMainId(mainId);
	}
}
