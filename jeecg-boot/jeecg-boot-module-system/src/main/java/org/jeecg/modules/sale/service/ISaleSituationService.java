package org.jeecg.modules.sale.service;

import org.jeecg.modules.sale.entity.SaleSituation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 销售情况
 * @Author: jeecg-boot
 * @Date: 2020-01-14
 * @Version: V1.0
 */
public interface ISaleSituationService extends IService<SaleSituation> {

    /**
     * 计算剩余数量
     */
    public void remainUpdate();

    /**
     * 计算利润
     */
    public void profitUpdate();

}
