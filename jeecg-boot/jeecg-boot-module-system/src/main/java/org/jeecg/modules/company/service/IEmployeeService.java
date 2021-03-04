package org.jeecg.modules.company.service;

import org.jeecg.modules.company.entity.BillingReport;
import org.jeecg.modules.company.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 清单
 * @Author: jeecg-boot
 * @Date: 2020-03-17
 * @Version: V1.0
 */
public interface IEmployeeService extends IService<Employee> {

    /**
     * 添加一对多
     */
    public void saveMain(Employee employee, List<BillingReport> billingReportList);

    /**
     * 修改一对多
     */
    public void updateMain(Employee employee, List<BillingReport> billingReportList);

    /**
     * 删除一对多
     */
    public void delMain(String id);

    /**
     * 批量删除一对多
     */
    public void delBatchMain(Collection<? extends Serializable> idList);

    /**
     * 删除表中所有数据
     */
    public void deleteAll();

    /**
     * 调薪加到工资里
     */
    public void updateRaise();

    /**
     * 删除子表外键不存在的数据
     */
    public void sportsmanDelete();

    /**
     * 名字重复覆盖原始数据
     */
    public void deleteName();

}
