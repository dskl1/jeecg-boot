package org.jeecg.modules.salary.service;

import org.jeecg.modules.salary.entity.CostManagement;
import org.jeecg.modules.salary.entity.SalaryStatistics;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.util.List;

/**
 * @Description: 工资统计
 * @Author: jeecg-boot
 * @Date: 2020-07-17
 * @Version: V1.0
 */
public interface ISalaryStatisticsService extends IService<SalaryStatistics> {

    /**
     * 将人员插入工资统计表
     */
    public void personSalary();

    /**
     * 计算基本工资标准
     */
    public void basicWageUpdate();

    /**
     * 计算外派补贴标准
     */
    public void dispatchSubsidyUpdate();

    /**
     * 计算员工基本工资,住房补贴
     */
    public void basicUpdate();

    /**
     * 计算员工加班工资
     */
    public void overtimeUpdate();

    /**
     * 计算员工外派补贴,工资总计,税前总计
     */
    public void dispatchUpdate();

    /**
     * 计算个税
     */
    public void taxUpdate();

    /**
     * 计算实际收入
     */
    public void incomeUpdate();

    /**
     * 计算总计支付工资
     */
    public void totalUpdate();

    /**
     * 更新年假
     */
    public void holidayUpdate(String id);

    /**
     * 年假减一
     */
    public void minisHoliday(String id);

    /**
     * 覆盖原始数据
     */
    public void deleteName();

    ResponseEntity<byte[]> exportLandAndClassIndex(String ids);

    public List<SalaryStatistics> queryAllClassIndex(List<SalaryStatistics> list);

    public List<SalaryStatistics> queryBankCard(String id);

}
