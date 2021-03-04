package org.jeecg.modules.salary.service;

import org.jeecg.modules.salary.entity.HeadDisplay;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 首页展示
 * @Author: jeecg-boot
 * @Date:   2021-01-21
 * @Version: V1.0
 */
public interface IHeadDisplayService extends IService<HeadDisplay> {

    /**
     * 未开票更新
     */
    public void invoiceUpdate();

    /**
     * 呆账更新
     */
    public void longRepayUpdate();

    /**
     * 未回款更新
     */
    public void repayUpdate();

}
