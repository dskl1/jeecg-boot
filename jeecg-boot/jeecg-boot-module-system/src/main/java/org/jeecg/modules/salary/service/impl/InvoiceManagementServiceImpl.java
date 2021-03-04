package org.jeecg.modules.salary.service.impl;

import org.jeecg.modules.salary.entity.InvoiceManagement;
import org.jeecg.modules.salary.entity.InvoiceManagementAttached;
import org.jeecg.modules.salary.entity.InvoiceManagementMember;
import org.jeecg.modules.salary.mapper.InvoiceManagementAttachedMapper;
import org.jeecg.modules.salary.mapper.InvoiceManagementMapper;
import org.jeecg.modules.salary.mapper.InvoiceManagementMemberMapper;
import org.jeecg.modules.salary.service.IInvoiceManagementService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 开票管理
 * @Author: jeecg-boot
 * @Date: 2020-08-31
 * @Version: V1.0
 */
@Service
public class InvoiceManagementServiceImpl extends ServiceImpl<InvoiceManagementMapper, InvoiceManagement> implements IInvoiceManagementService {

    @Autowired
    private InvoiceManagementMapper invoiceManagementMapper;
    @Autowired
    private InvoiceManagementAttachedMapper invoiceManagementAttachedMapper;
    @Autowired
    private InvoiceManagementMemberMapper invoiceManagementMemberMapper;

    @Override
    @Transactional
    public void saveMain(InvoiceManagement invoiceManagement, List<InvoiceManagementAttached> invoiceManagementAttachedList, List<InvoiceManagementMember> invoiceManagementMemberList) {
        invoiceManagementMapper.insert(invoiceManagement);
        if (invoiceManagementAttachedList != null && invoiceManagementAttachedList.size() > 0) {
            for (InvoiceManagementAttached entity : invoiceManagementAttachedList) {
                //外键设置
                entity.setSportsmanId(invoiceManagement.getId());
                invoiceManagementAttachedMapper.insert(entity);
            }
        }
        if (invoiceManagementMemberList != null && invoiceManagementMemberList.size() > 0) {
            for (InvoiceManagementMember entity : invoiceManagementMemberList) {
                //外键设置
                entity.setSportsmanId(invoiceManagement.getId());
                invoiceManagementMemberMapper.insert(entity);
            }
        }
    }

    @Override
    @Transactional
    public void updateMain(InvoiceManagement invoiceManagement, List<InvoiceManagementAttached> invoiceManagementAttachedList, List<InvoiceManagementMember> invoiceManagementMemberList) {
        invoiceManagementMapper.updateById(invoiceManagement);

        //1.先删除子表数据
        invoiceManagementAttachedMapper.deleteByMainId(invoiceManagement.getId());
        invoiceManagementMemberMapper.deleteByMainId(invoiceManagement.getId());

        //2.子表数据重新插入
        if (invoiceManagementAttachedList != null && invoiceManagementAttachedList.size() > 0) {
            for (InvoiceManagementAttached entity : invoiceManagementAttachedList) {
                //外键设置
                entity.setSportsmanId(invoiceManagement.getId());
                invoiceManagementAttachedMapper.insert(entity);
            }
        }
        if (invoiceManagementMemberList != null && invoiceManagementMemberList.size() > 0) {
            for (InvoiceManagementMember entity : invoiceManagementMemberList) {
                //外键设置
                entity.setSportsmanId(invoiceManagement.getId());
                invoiceManagementMemberMapper.insert(entity);
            }
        }
    }

    @Override
    @Transactional
    public void delMain(String id) {
        invoiceManagementAttachedMapper.deleteByMainId(id);
        invoiceManagementMemberMapper.deleteByMainId(id);
        invoiceManagementMapper.deleteById(id);
    }

    @Override
    @Transactional
    public void delBatchMain(Collection<? extends Serializable> idList) {
        for (Serializable id : idList) {
            invoiceManagementAttachedMapper.deleteByMainId(id.toString());
            invoiceManagementMemberMapper.deleteByMainId(id.toString());
            invoiceManagementMapper.deleteById(id);
        }
    }

    @Override
    public void stageUpdate(String id) {
        invoiceManagementMapper.stageUpdate(id);
    }

    @Override
    public List<InvoiceManagement> totalUpdate() {
        return this.invoiceManagementMapper.totalUpdate();
    }

    @Override
    public void invoiceInsert() {
        this.invoiceManagementMapper.invoiceInsert();
    }

    @Override
    public void memberDelete() {
        this.invoiceManagementMapper.memberDelete();
    }

    @Override
    public void deleteId() {
        this.invoiceManagementMapper.deleteId();
    }

}
