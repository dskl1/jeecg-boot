package org.jeecg.modules.company.service.impl;

import org.jeecg.modules.company.entity.NewestEmployee;
import org.jeecg.modules.company.entity.NewestBillingReport;
import org.jeecg.modules.company.mapper.NewestBillingReportMapper;
import org.jeecg.modules.company.mapper.NewestEmployeeMapper;
import org.jeecg.modules.company.service.INewestEmployeeService;
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
 * @Date: 2020-04-02
 * @Version: V1.0
 */
@Service
public class NewestEmployeeServiceImpl extends ServiceImpl<NewestEmployeeMapper, NewestEmployee> implements INewestEmployeeService {

    @Autowired
    private NewestEmployeeMapper newestEmployeeMapper;
    @Autowired
    private NewestBillingReportMapper newestBillingReportMapper;

    @Override
    @Transactional
    public void saveMain(NewestEmployee newestEmployee, List<NewestBillingReport> newestBillingReportList) {
        newestEmployeeMapper.insert(newestEmployee);
        for (NewestBillingReport entity : newestBillingReportList) {
            //外键设置
            entity.setSportsmanId(newestEmployee.getId());
            newestBillingReportMapper.insert(entity);
        }
    }

    @Override
    @Transactional
    public void updateMain(NewestEmployee newestEmployee, List<NewestBillingReport> newestBillingReportList) {
        newestEmployeeMapper.updateById(newestEmployee);

        //1.先删除子表数据
        newestBillingReportMapper.deleteByMainId(newestEmployee.getId());

        //2.子表数据重新插入
        for (NewestBillingReport entity : newestBillingReportList) {
            //外键设置
            entity.setSportsmanId(newestEmployee.getId());
            newestBillingReportMapper.insert(entity);
        }
    }

    @Override
    @Transactional
    public void delMain(String id) {
        newestBillingReportMapper.deleteByMainId(id);
        newestEmployeeMapper.deleteById(id);
    }

    @Override
    @Transactional
    public void delBatchMain(Collection<? extends Serializable> idList) {
        for (Serializable id : idList) {
            newestBillingReportMapper.deleteByMainId(id.toString());
            newestEmployeeMapper.deleteById(id);
        }
    }

    @Override
    public void deleteAll() {
        this.newestEmployeeMapper.deleteAll();
    }

    @Override
    public void updateStatus(String id) {
        this.newestEmployeeMapper.updateStatus(id);
    }

}
