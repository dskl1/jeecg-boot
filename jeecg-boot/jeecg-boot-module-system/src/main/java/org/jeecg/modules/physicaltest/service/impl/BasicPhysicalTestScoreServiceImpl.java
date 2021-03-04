package org.jeecg.modules.physicaltest.service.impl;

import org.jeecg.modules.physicaltest.entity.BasicPhysicalTestScore;
import org.jeecg.modules.physicaltest.mapper.BasicPhysicalTestScoreMapper;
import org.jeecg.modules.physicaltest.service.IBasicPhysicalTestScoreService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 基础体能评分标准
 * @Author: jeecg-boot
 * @Date:   2020-05-12
 * @Version: V1.0
 */
@Service
public class BasicPhysicalTestScoreServiceImpl extends ServiceImpl<BasicPhysicalTestScoreMapper, BasicPhysicalTestScore> implements IBasicPhysicalTestScoreService {

//	@Autowired
//	private BasicPhysicalTestScoreMapper basicPhysicalTestScoreMapper;
//
//	@Override
//	public List<BasicPhysicalTestScore> selectByMainId(String mainId) {
//		return basicPhysicalTestScoreMapper.selectByMainId(mainId);
//	}
}
