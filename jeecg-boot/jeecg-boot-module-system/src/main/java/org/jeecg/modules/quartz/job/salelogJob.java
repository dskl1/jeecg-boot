package org.jeecg.modules.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.DateUtils;
import org.jeecg.modules.sale.service.ISaleLogService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class salelogJob implements Job {

    @Autowired
    private ISaleLogService saleLogService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        this.saleLogService.saleInsert();
        log.info("销售记录每月数据记录  时间:" + DateUtils.getTimestamp());
    }
}
