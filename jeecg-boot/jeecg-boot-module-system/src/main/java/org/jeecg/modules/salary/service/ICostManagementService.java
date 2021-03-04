package org.jeecg.modules.salary.service;

import org.jeecg.modules.salary.entity.CostManagement;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @Description: 成本管理
 * @Author: jeecg-boot
 * @Date: 2020-08-13
 * @Version: V1.0
 */
public interface ICostManagementService extends IService<CostManagement> {

    /**
     * 成本管理插入姓名
     */
    public void costInsert();

    /**
     * 更新资金成本
     */
    public void costUpdate();

    /**
     * 更新利润
     */
    public void profitUpdate();

    /**
     * 报销取整
     */
    public void intReimbursement();

    /**
     * @param ids
     * @return
     */
    ResponseEntity<byte[]> exportLand2ndClassIndex(String ids);

    public List<CostManagement> queryAllClassIndex(List<CostManagement> list);

    public List<CostManagement> queryBankCard(String id);

}
