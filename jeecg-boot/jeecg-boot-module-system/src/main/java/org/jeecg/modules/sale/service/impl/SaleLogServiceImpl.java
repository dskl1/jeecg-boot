package org.jeecg.modules.sale.service.impl;

import org.jeecg.modules.sale.entity.SaleLog;
import org.jeecg.modules.sale.mapper.SaleLogMapper;
import org.jeecg.modules.sale.mapper.SaleSituationMapper;
import org.jeecg.modules.sale.service.ISaleLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 销售记录
 * @Author: jeecg-boot
 * @Date: 2020-01-15
 * @Version: V1.0
 */
@Service
public class SaleLogServiceImpl extends ServiceImpl<SaleLogMapper, SaleLog> implements ISaleLogService {

    @Autowired
    private SaleLogMapper saleLogMapper;

    @Override
    public void saleInsert() {
        this.saleLogMapper.saleInsert();
    }
}
