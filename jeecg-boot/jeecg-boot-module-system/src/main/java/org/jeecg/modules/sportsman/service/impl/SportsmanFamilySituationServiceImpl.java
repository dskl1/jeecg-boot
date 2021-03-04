package org.jeecg.modules.sportsman.service.impl;

import org.jeecg.modules.sportsman.entity.SportsmanFamilySituation;
import org.jeecg.modules.sportsman.mapper.SportsmanFamilySituationMapper;
import org.jeecg.modules.sportsman.service.ISportsmanFamilySituationService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 运动员家庭成员情况
 * @author： jeecg-boot
 * @date：   2019-06-20
 * @version： V1.0
 */
@Service
public class SportsmanFamilySituationServiceImpl extends ServiceImpl<SportsmanFamilySituationMapper, SportsmanFamilySituation> implements ISportsmanFamilySituationService {

	@Autowired
	private SportsmanFamilySituationMapper sportsmanFamilySituationMapper;

	@Override
	public List<SportsmanFamilySituation> selectByMainId(String mainId) {
		return sportsmanFamilySituationMapper.selectByMainId(mainId);
	}
}
