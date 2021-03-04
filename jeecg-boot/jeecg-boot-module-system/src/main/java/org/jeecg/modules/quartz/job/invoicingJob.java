package org.jeecg.modules.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.DateUtils;
import org.jeecg.modules.salary.service.IInvoicingStatisticsService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class invoicingJob implements Job {

    @Autowired
    private IInvoicingStatisticsService invoicingStatisticsService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        this.invoicingStatisticsService.isInsert(); //备份开票记录
        log.info("备份开票记录  时间:" + DateUtils.getTimestamp());
    }
}
