package org.jeecg.modules.training.service.impl;

import org.jeecg.modules.training.entity.TrainingScoree;
import org.jeecg.modules.training.mapper.TrainingScoreeMapper;
import org.jeecg.modules.training.service.ITrainingScoreeService;
import org.springframework.stereotype.Service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

/**
 * @Description: 成绩
 * @Author: jeecg-boot
 * @Date: 2020-05-20
 * @Version: V1.0
 */
@Service
public class TrainingScoreeServiceImpl extends ServiceImpl<TrainingScoreeMapper, TrainingScoree> implements ITrainingScoreeService {

    @Autowired
    private TrainingScoreeMapper trainingScoreeMapper;

    @Override
    public List<TrainingScoree> selectByMainId(String mainId) {
        return trainingScoreeMapper.selectByMainId(mainId);
    }

    @Override
    public void insertScore(String mainId) {
        this.trainingScoreeMapper.insertScore(mainId);
    }
}
