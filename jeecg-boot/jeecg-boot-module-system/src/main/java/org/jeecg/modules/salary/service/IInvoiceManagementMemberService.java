package org.jeecg.modules.salary.service;

import org.jeecg.modules.salary.entity.InvoiceManagementMember;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 对应人员
 * @Author: jeecg-boot
 * @Date: 2020-09-03
 * @Version: V1.0
 */
public interface IInvoiceManagementMemberService extends IService<InvoiceManagementMember> {

    public List<InvoiceManagementMember> selectByMainId(String mainId);

    /**
     * 插入开票人员
     */
    public void memberInsert();

}
