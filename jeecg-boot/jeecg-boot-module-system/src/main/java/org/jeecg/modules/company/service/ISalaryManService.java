package org.jeecg.modules.company.service;

import org.jeecg.modules.company.entity.SalaryMan;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 公司人员信息
 * @Author: jeecg-boot
 * @Date:   2020-09-08
 * @Version: V1.0
 */
public interface ISalaryManService extends IService<SalaryMan> {

    public void sexUpdate();

    public void birUpdate();

}
