package org.jeecg.modules.sale.service.impl;

import org.jeecg.modules.sale.entity.SaleSituation;
import org.jeecg.modules.sale.mapper.SaleSituationMapper;
import org.jeecg.modules.sale.service.ISaleSituationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 销售情况
 * @Author: jeecg-boot
 * @Date: 2020-01-14
 * @Version: V1.0
 */
@Service
public class SaleSituationServiceImpl extends ServiceImpl<SaleSituationMapper, SaleSituation> implements ISaleSituationService {

    @Autowired
    private SaleSituationMapper saleSituationMapper;

    @Override
    public void remainUpdate() {
        this.saleSituationMapper.remainUpdate();
    }

    @Override
    public void profitUpdate() {
        this.saleSituationMapper.profitUpdate();
    }

}
