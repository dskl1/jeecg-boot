package org.jeecg.modules.salary.service.impl;

import org.jeecg.modules.salary.entity.NoInvoice;
import org.jeecg.modules.salary.mapper.NoInvoiceMapper;
import org.jeecg.modules.salary.service.INoInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 未开票
 * @Author: jeecg-boot
 * @Date: 2021-01-18
 * @Version: V1.0
 */
@Service
public class NoInvoiceServiceImpl extends ServiceImpl<NoInvoiceMapper, NoInvoice> implements INoInvoiceService {

    @Autowired
    private NoInvoiceMapper noInvoiceMapper;

    /**
     * 是否开票
     *
     * @param id
     */
    @Override
    public void stageUpdate(String id) {
        this.noInvoiceMapper.stageUpdate(id);
    }

    @Override
    public void invoiceInsert() {
        this.noInvoiceMapper.invoiceInsert();
    }

    @Override
    public void manageInsert() {
        this.noInvoiceMapper.manageInsert();
    }

    @Override
    public void membInsert() {
        this.noInvoiceMapper.membInsert();
    }

}
