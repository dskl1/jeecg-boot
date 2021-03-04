package org.jeecg.modules.company.service.impl;

import org.jeecg.modules.company.entity.SalaryMan;
import org.jeecg.modules.company.mapper.SalaryManMapper;
import org.jeecg.modules.company.service.ISalaryManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 公司人员信息
 * @Author: jeecg-boot
 * @Date: 2020-09-08
 * @Version: V1.0
 */
@Service
public class SalaryManServiceImpl extends ServiceImpl<SalaryManMapper, SalaryMan> implements ISalaryManService {

    @Autowired
    SalaryManMapper salaryManMapper;

    @Override
    public void sexUpdate() {
        this.salaryManMapper.sexUpdate();
    }

    @Override
    public void birUpdate() {
        this.salaryManMapper.birUpdate();
    }

}
