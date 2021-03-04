package org.jeecg.modules.salary.service;

import org.jeecg.modules.salary.entity.SalaryStatisticsCopy1;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 历史工资
 * @Author: jeecg-boot
 * @Date:   2020-08-18
 * @Version: V1.0
 */
public interface ISalaryStatisticsCopy1Service extends IService<SalaryStatisticsCopy1> {

    /**
     * 按月份查询历史工资发放
     */
    List<SalaryStatisticsCopy1> selectByMonth(String format);

    /**
     * 插入工资记录
     */
    public void ssInsert();

}
