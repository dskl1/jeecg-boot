package org.jeecg.modules.training.service.impl;

import org.jeecg.modules.training.entity.TrainingScore;
import org.jeecg.modules.training.entity.D25;
import org.jeecg.modules.training.entity.D50;
import org.jeecg.modules.training.entity.D150;
import org.jeecg.modules.training.entity.D200;
import org.jeecg.modules.training.entity.D250;
import org.jeecg.modules.training.entity.D300;
import org.jeecg.modules.training.entity.D350;
import org.jeecg.modules.training.entity.D400;
import org.jeecg.modules.training.entity.D800;
import org.jeecg.modules.training.entity.D1000;
import org.jeecg.modules.training.entity.D1500;
import org.jeecg.modules.training.mapper.D25Mapper;
import org.jeecg.modules.training.mapper.D50Mapper;
import org.jeecg.modules.training.mapper.D150Mapper;
import org.jeecg.modules.training.mapper.D200Mapper;
import org.jeecg.modules.training.mapper.D250Mapper;
import org.jeecg.modules.training.mapper.D300Mapper;
import org.jeecg.modules.training.mapper.D350Mapper;
import org.jeecg.modules.training.mapper.D400Mapper;
import org.jeecg.modules.training.mapper.D800Mapper;
import org.jeecg.modules.training.mapper.D1000Mapper;
import org.jeecg.modules.training.mapper.D1500Mapper;
import org.jeecg.modules.training.mapper.TrainingScoreMapper;
import org.jeecg.modules.training.service.ITrainingScoreService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: training_score
 * @Author: jeecg-boot
 * @Date: 2020-05-20
 * @Version: V1.0
 */
@Service
public class TrainingScoreServiceImpl extends ServiceImpl<TrainingScoreMapper, TrainingScore> implements ITrainingScoreService {

    @Autowired
    private TrainingScoreMapper trainingScoreMapper;
    @Autowired
    private D25Mapper d25Mapper;
    @Autowired
    private D50Mapper d50Mapper;
    @Autowired
    private D150Mapper d150Mapper;
    @Autowired
    private D200Mapper d200Mapper;
    @Autowired
    private D250Mapper d250Mapper;
    @Autowired
    private D300Mapper d300Mapper;
    @Autowired
    private D350Mapper d350Mapper;
    @Autowired
    private D400Mapper d400Mapper;
    @Autowired
    private D800Mapper d800Mapper;
    @Autowired
    private D1000Mapper d1000Mapper;
    @Autowired
    private D1500Mapper d1500Mapper;

    @Override
    @Transactional
    public void delMain(String id) {
        d25Mapper.deleteByMainId(id);
        d50Mapper.deleteByMainId(id);
        d150Mapper.deleteByMainId(id);
        d200Mapper.deleteByMainId(id);
        d250Mapper.deleteByMainId(id);
        d300Mapper.deleteByMainId(id);
        d350Mapper.deleteByMainId(id);
        d400Mapper.deleteByMainId(id);
        d800Mapper.deleteByMainId(id);
        d1000Mapper.deleteByMainId(id);
        d1500Mapper.deleteByMainId(id);
        trainingScoreMapper.deleteById(id);
    }

    @Override
    @Transactional
    public void delBatchMain(Collection<? extends Serializable> idList) {
        for (Serializable id : idList) {
            d25Mapper.deleteByMainId(id.toString());
            d50Mapper.deleteByMainId(id.toString());
            d150Mapper.deleteByMainId(id.toString());
            d200Mapper.deleteByMainId(id.toString());
            d250Mapper.deleteByMainId(id.toString());
            d300Mapper.deleteByMainId(id.toString());
            d350Mapper.deleteByMainId(id.toString());
            d400Mapper.deleteByMainId(id.toString());
            d800Mapper.deleteByMainId(id.toString());
            d1000Mapper.deleteByMainId(id.toString());
            d1500Mapper.deleteByMainId(id.toString());
            trainingScoreMapper.deleteById(id);
        }
    }

    @Override
    public void setFlagSee() {
        this.trainingScoreMapper.setFlagSee();
    }

    @Override
    public void setFlagNoSee() {
        this.trainingScoreMapper.setFlagNoSee();
    }

    @Override
    public void insertName() {
        this.trainingScoreMapper.insertName();
    }

    @Override
    public void setFlagSeeCopy() {
        this.trainingScoreMapper.setFlagSeeCopy();
    }

    @Override
    public void setFlagNoSeeCopy() {
        this.trainingScoreMapper.setFlagNoSeeCopy();
    }

    @Override
    public void insertNameCopy() {
        this.trainingScoreMapper.insertNameCopy();
    }

    @Override
    public void updateTimeCopy() {
        this.trainingScoreMapper.updateTimeCopy();
    }
}
