package org.jeecg.modules.training.service.impl;

import org.jeecg.modules.training.entity.WeekPlan;
import org.jeecg.modules.training.entity.WeekTrainingScore;
import org.jeecg.modules.training.mapper.WeekTrainingScoreMapper;
import org.jeecg.modules.training.mapper.WeekPlanMapper;
import org.jeecg.modules.training.service.IWeekPlanService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 一周训练内容
 * @Author: jeecg-boot
 * @Date: 2020-06-17
 * @Version: V1.0
 */
@Service
public class WeekPlanServiceImpl extends ServiceImpl<WeekPlanMapper, WeekPlan> implements IWeekPlanService {

    @Autowired
    private WeekPlanMapper weekPlanMapper;
    @Autowired
    private WeekTrainingScoreMapper weekTrainingScoreMapper;

    @Override
    @Transactional
    public void delMain(String id) {
        weekTrainingScoreMapper.deleteByMainId(id);
        weekPlanMapper.deleteById(id);
    }

    @Override
    @Transactional
    public void delBatchMain(Collection<? extends Serializable> idList) {
        for (Serializable id : idList) {
            weekTrainingScoreMapper.deleteByMainId(id.toString());
            weekPlanMapper.deleteById(id);
        }
    }

    @Override
    @Transactional
    public void insertTrainingScore(String mainId) {
        this.weekPlanMapper.insertTrainingScore(mainId);
    }

}
