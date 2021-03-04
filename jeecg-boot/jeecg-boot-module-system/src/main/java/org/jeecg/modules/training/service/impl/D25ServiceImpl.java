package org.jeecg.modules.training.service.impl;

import org.jeecg.modules.training.entity.D25;
import org.jeecg.modules.training.mapper.D25Mapper;
import org.jeecg.modules.training.mapper.TrainingScoreeMapper;
import org.jeecg.modules.training.service.ID25Service;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

/**
 * @Description: 25
 * @Author: jeecg-boot
 * @Date: 2020-05-20
 * @Version: V1.0
 */
@Service
public class D25ServiceImpl extends ServiceImpl<D25Mapper, D25> implements ID25Service {

    @Autowired
    private D25Mapper d25Mapper;
    @Autowired
    private TrainingScoreeMapper trainingScoreeMapper;

    @Override
    public List<D25> selectByMainId(String mainId) {
        return d25Mapper.selectByMainId(mainId);
    }

    @Override
    @Transactional
    public void delMain(String id) {
        trainingScoreeMapper.deleteByMainId(id);
        d25Mapper.deleteById(id);
    }

    @Override
    @Transactional
    public void delBatchMain(Collection<? extends Serializable> idList) {
        for (Serializable id : idList) {
            trainingScoreeMapper.deleteByMainId(id.toString());
            d25Mapper.deleteById(id);
        }
    }

    @Override
    @Transactional
    public void insertD25() {
        this.d25Mapper.insertD25();
    }
}
