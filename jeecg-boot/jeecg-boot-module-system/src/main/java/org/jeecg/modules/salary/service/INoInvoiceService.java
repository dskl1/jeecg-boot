package org.jeecg.modules.salary.service;

import org.jeecg.modules.salary.entity.NoInvoice;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.salary.mapper.NoInvoiceMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 未开票
 * @Author: jeecg-boot
 * @Date: 2021-01-18
 * @Version: V1.0
 */
public interface INoInvoiceService extends IService<NoInvoice> {


    /**
     * 插入未开票
     */
    public void invoiceInsert();

    /**
     * 是否开票
     *
     * @param id
     */
    public void stageUpdate(String id);

    /**
     * 插入开票管理
     */
    public void manageInsert();

    /**
     * 插入开票人员
     */
    public void membInsert();

}
