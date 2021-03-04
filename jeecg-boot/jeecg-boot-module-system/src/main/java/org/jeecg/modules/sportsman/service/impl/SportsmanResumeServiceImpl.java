package org.jeecg.modules.sportsman.service.impl;

import org.jeecg.modules.sportsman.entity.SportsmanResume;
import org.jeecg.modules.sportsman.mapper.SportsmanResumeMapper;
import org.jeecg.modules.sportsman.service.ISportsmanResumeService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 运动员训练简历
 * @author： jeecg-boot
 * @date：   2019-06-20
 * @version： V1.0
 */
@Service
public class SportsmanResumeServiceImpl extends ServiceImpl<SportsmanResumeMapper, SportsmanResume> implements ISportsmanResumeService {

	@Autowired
	private SportsmanResumeMapper sportsmanResumeMapper;

	@Override
	public List<SportsmanResume> selectByMainId(String mainId) {
		return sportsmanResumeMapper.selectByMainId(mainId);
	}
}
