package org.jeecg.modules.physicaltest.service.impl;

import org.jeecg.modules.physicaltest.entity.SportsmanTest;
import org.jeecg.modules.physicaltest.entity.BasicPhysicalTestScore;
import org.jeecg.modules.physicaltest.mapper.BasicPhysicalTestScoreMapper;
import org.jeecg.modules.physicaltest.mapper.SportsmanTestMapper;
import org.jeecg.modules.physicaltest.service.ISportsmanTestService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 人员表
 * @Author: jeecg-boot
 * @Date: 2020-05-12
 * @Version: V1.0
 */
@Service
public class SportsmanTestServiceImpl extends ServiceImpl<SportsmanTestMapper, SportsmanTest> implements ISportsmanTestService {

    @Autowired
    private SportsmanTestMapper sportsmanTestMapper;
    @Autowired
    private BasicPhysicalTestScoreMapper basicPhysicalTestScoreMapper;

    @Override
    @Transactional
    public void delMain(String id) {
        basicPhysicalTestScoreMapper.deleteByMainId(id);
        sportsmanTestMapper.deleteById(id);
    }

    @Override
    @Transactional
    public void delBatchMain(Collection<? extends Serializable> idList) {
        for(Serializable id:idList) {
            basicPhysicalTestScoreMapper.deleteByMainId(id.toString());
            sportsmanTestMapper.deleteById(id);
        }
    }

    @Override
    public void cardNumberToSex() {
        this.sportsmanTestMapper.cardNumberToSex();
    }

    @Override
    public void doubleSex() {
        this.sportsmanTestMapper.doubleSex();
    }

}
