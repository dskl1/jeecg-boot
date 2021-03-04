package org.jeecg.modules.salary.service.impl;

import org.jeecg.modules.salary.entity.InvoiceManagementMember;
import org.jeecg.modules.salary.mapper.InvoiceManagementMemberMapper;
import org.jeecg.modules.salary.service.IInvoiceManagementMemberService;
import org.springframework.stereotype.Service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 对应人员
 * @Author: jeecg-boot
 * @Date: 2020-09-03
 * @Version: V1.0
 */
@Service
public class InvoiceManagementMemberServiceImpl extends ServiceImpl<InvoiceManagementMemberMapper, InvoiceManagementMember> implements IInvoiceManagementMemberService {

    @Autowired
    private InvoiceManagementMemberMapper invoiceManagementMemberMapper;

    @Override
    public List<InvoiceManagementMember> selectByMainId(String mainId) {
        return invoiceManagementMemberMapper.selectByMainId(mainId);
    }

    @Override
    public void memberInsert() {
        this.invoiceManagementMemberMapper.memberInsert();
    }

}
