package org.jeecg.modules.company.service;

import org.apache.ibatis.annotations.Delete;
import org.jeecg.modules.company.entity.NewestBillingReport;
import org.jeecg.modules.company.entity.NewestEmployee;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 清单
 * @Author: jeecg-boot
 * @Date:   2020-04-02
 * @Version: V1.0
 */
public interface INewestEmployeeService extends IService<NewestEmployee> {

	/**
	 * 添加一对多
	 *
	 */
	public void saveMain(NewestEmployee newestEmployee, List<NewestBillingReport> newestBillingReportList) ;

	/**
	 * 修改一对多
	 *
	 */
	public void updateMain(NewestEmployee newestEmployee, List<NewestBillingReport> newestBillingReportList);

	/**
	 * 删除一对多
	 */
	public void delMain(String id);

	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);

	/**
	 * 删除表中所有数据
	 */
	public void deleteAll();

	public void updateStatus(String id);

}
