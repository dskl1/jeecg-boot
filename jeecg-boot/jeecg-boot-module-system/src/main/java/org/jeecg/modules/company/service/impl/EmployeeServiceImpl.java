package org.jeecg.modules.company.service.impl;

import org.jeecg.modules.company.entity.Employee;
import org.jeecg.modules.company.entity.BillingReport;
import org.jeecg.modules.company.mapper.BillingReportMapper;
import org.jeecg.modules.company.mapper.EmployeeMapper;
import org.jeecg.modules.company.service.IEmployeeService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 清单
 * @Author: jeecg-boot
 * @Date: 2020-03-17
 * @Version: V1.0
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private BillingReportMapper billingReportMapper;

    @Override
    @Transactional
    public void saveMain(Employee employee, List<BillingReport> billingReportList) {
        employeeMapper.insert(employee);
        for (BillingReport entity : billingReportList) {
            //外键设置
            entity.setSportsmanId(employee.getId());
            billingReportMapper.insert(entity);
        }
    }

    @Override
    @Transactional
    public void updateMain(Employee employee, List<BillingReport> billingReportList) {
        employeeMapper.updateById(employee);

        //1.先删除子表数据
        billingReportMapper.deleteByMainId(employee.getId());

        //2.子表数据重新插入
        for (BillingReport entity : billingReportList) {
            //外键设置
            entity.setSportsmanId(employee.getId());
            billingReportMapper.insert(entity);
        }
    }

    @Override
    @Transactional
    public void delMain(String id) {
        billingReportMapper.deleteByMainId(id);
        employeeMapper.deleteById(id);
    }

    @Override
    @Transactional
    public void delBatchMain(Collection<? extends Serializable> idList) {
        for (Serializable id : idList) {
            billingReportMapper.deleteByMainId(id.toString());
            employeeMapper.deleteById(id);
        }
    }

    @Override
    public void deleteAll() {
        this.employeeMapper.deleteAll();
    }

    @Override
    public void updateRaise() {
        this.employeeMapper.updateRaise();
    }

    @Override
    public void sportsmanDelete() {
        this.employeeMapper.sportsmanDelete();
    }

    @Override
    public void deleteName() {
        this.employeeMapper.deleteName();
    }

}
