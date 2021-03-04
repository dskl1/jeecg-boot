package org.jeecg.modules.salary.service;

import org.jeecg.modules.salary.entity.InvoicingStatistics;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 开票统计
 * @Author: jeecg-boot
 * @Date:   2020-09-28
 * @Version: V1.0
 */
public interface IInvoicingStatisticsService extends IService<InvoicingStatistics> {

    List<InvoicingStatistics> selectByMonth(String format);

    /**
     * 插入开票统计
     */
    public void isInsert();
}
