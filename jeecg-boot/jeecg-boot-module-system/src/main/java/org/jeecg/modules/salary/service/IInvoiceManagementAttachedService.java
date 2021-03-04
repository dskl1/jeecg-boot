package org.jeecg.modules.salary.service;

import org.jeecg.modules.salary.entity.InvoiceManagementAttached;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 附票
 * @Author: jeecg-boot
 * @Date:   2020-08-31
 * @Version: V1.0
 */
public interface IInvoiceManagementAttachedService extends IService<InvoiceManagementAttached> {

	public List<InvoiceManagementAttached> selectByMainId(String mainId);

	public void stageUpdate(String ids);
}
