package org.jeecg.modules.salary.service.impl;

import org.jeecg.modules.salary.entity.InvoicingStatistics;
import org.jeecg.modules.salary.mapper.InvoicingStatisticsMapper;
import org.jeecg.modules.salary.service.IInvoicingStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 开票统计
 * @Author: jeecg-boot
 * @Date: 2020-09-28
 * @Version: V1.0
 */
@Service
public class InvoicingStatisticsServiceImpl extends ServiceImpl<InvoicingStatisticsMapper, InvoicingStatistics> implements IInvoicingStatisticsService {

    @Autowired
    private InvoicingStatisticsMapper invoicingStatisticsMapper;

    @Override
    public List<InvoicingStatistics> selectByMonth(String format) {
        return invoicingStatisticsMapper.selectByMonth(format);
    }

    @Override
    public void isInsert() {
        this.invoicingStatisticsMapper.isInsert();
    }

}
