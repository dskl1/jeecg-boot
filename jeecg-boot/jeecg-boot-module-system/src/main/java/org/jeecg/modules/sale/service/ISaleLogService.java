package org.jeecg.modules.sale.service;

import org.jeecg.modules.sale.entity.SaleLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 销售记录
 * @Author: jeecg-boot
 * @Date:   2020-01-15
 * @Version: V1.0
 */
public interface ISaleLogService extends IService<SaleLog> {

    /**
     * 插入销售记录
     */
    public void saleInsert();

}
