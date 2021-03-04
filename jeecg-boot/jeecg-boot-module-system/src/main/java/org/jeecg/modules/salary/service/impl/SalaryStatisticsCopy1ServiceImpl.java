package org.jeecg.modules.salary.service.impl;

import org.jeecg.modules.salary.entity.SalaryStatisticsCopy1;
import org.jeecg.modules.salary.mapper.SalaryStatisticsCopy1Mapper;
import org.jeecg.modules.salary.service.ISalaryStatisticsCopy1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 历史工资
 * @Author: jeecg-boot
 * @Date: 2020-08-18
 * @Version: V1.0
 */
@Service
public class SalaryStatisticsCopy1ServiceImpl extends ServiceImpl<SalaryStatisticsCopy1Mapper, SalaryStatisticsCopy1> implements ISalaryStatisticsCopy1Service {

    @Autowired
    private SalaryStatisticsCopy1Mapper salaryStatisticsCopy1Mapper;

    @Override
    public List<SalaryStatisticsCopy1> selectByMonth(String format) {
        return salaryStatisticsCopy1Mapper.selectByMonth(format);
    }

    @Override
    public void ssInsert() {
        this.salaryStatisticsCopy1Mapper.ssInsert();
    }

}
