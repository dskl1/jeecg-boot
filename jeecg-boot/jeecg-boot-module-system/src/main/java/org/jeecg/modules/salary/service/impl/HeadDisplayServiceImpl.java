package org.jeecg.modules.salary.service.impl;

import org.jeecg.modules.salary.entity.HeadDisplay;
import org.jeecg.modules.salary.mapper.HeadDisplayMapper;
import org.jeecg.modules.salary.service.IHeadDisplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 首页展示
 * @Author: jeecg-boot
 * @Date: 2021-01-21
 * @Version: V1.0
 */
@Service
public class HeadDisplayServiceImpl extends ServiceImpl<HeadDisplayMapper, HeadDisplay> implements IHeadDisplayService {

    @Autowired
    private HeadDisplayMapper headDisplayMapper;

    @Override
    public void invoiceUpdate() {
        this.headDisplayMapper.invoiceUpdate();
    }

    @Override
    public void repayUpdate() {
        this.headDisplayMapper.repayUpdate();
    }

    @Override
    public void longRepayUpdate() {
        this.headDisplayMapper.longRepayUpdate();
    }

}
