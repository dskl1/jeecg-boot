package org.jeecg.modules.sportsman.service.impl;

import org.jeecg.modules.sportsman.entity.DevelopSituation;
import org.jeecg.modules.sportsman.mapper.DevelopSituationMapper;
import org.jeecg.modules.sportsman.service.IDevelopSituationService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 历年机能发育情况
 * @author： jeecg-boot
 * @date：   2019-06-20
 * @version： V1.0
 */
@Service
public class DevelopSituationServiceImpl extends ServiceImpl<DevelopSituationMapper, DevelopSituation> implements IDevelopSituationService {

	@Autowired
	private DevelopSituationMapper developSituationMapper;

	@Override
	public List<DevelopSituation> selectByMainId(String mainId) {
		return developSituationMapper.selectByMainId(mainId);
	}
}
