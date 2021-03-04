package org.jeecg.modules.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.DateUtils;
import org.jeecg.modules.salary.service.ISalaryStatisticsCopy1Service;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class SalaryJob implements Job {

    @Autowired
    private ISalaryStatisticsCopy1Service salaryStatisticsCopy1Service;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        this.salaryStatisticsCopy1Service.ssInsert(); //备份工资记录
        log.info(" 备份工资记录  时间:" + DateUtils.getTimestamp());
    }
}
