package org.jeecg.modules.statistics.service;

import org.jeecg.modules.statistics.entity.D25a;
import org.jeecg.modules.statistics.entity.D50a;
import org.jeecg.modules.statistics.entity.D100a;
import org.jeecg.modules.statistics.entity.D150a;
import org.jeecg.modules.statistics.entity.D200a;
import org.jeecg.modules.statistics.entity.D250a;
import org.jeecg.modules.statistics.entity.D300a;
import org.jeecg.modules.statistics.entity.D350a;
import org.jeecg.modules.statistics.entity.D400a;
import org.jeecg.modules.statistics.entity.D800a;
import org.jeecg.modules.statistics.entity.D1000a;
import org.jeecg.modules.statistics.entity.D1500a;
import org.jeecg.modules.statistics.entity.Statistics;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 训练报表
 * @author： jeecg-boot
 * @date：   2019-07-15
 * @version： V1.0
 */
public interface IStatisticsService extends IService<Statistics> {

	/**
	 * 添加一对多
	 *
	 */
	public void saveMain(Statistics statistics, List<D25a> d25aList, List<D50a> d50aList, List<D100a> d100aList, List<D150a> d150aList, List<D200a> d200aList, List<D250a> d250aList, List<D300a> d300aList, List<D350a> d350aList, List<D400a> d400aList, List<D800a> d800aList, List<D1000a> d1000aList, List<D1500a> d1500aList) ;

	/**
	 * 修改一对多
	 *
	 */
	public void updateMain(Statistics statistics, List<D25a> d25aList, List<D50a> d50aList, List<D100a> d100aList, List<D150a> d150aList, List<D200a> d200aList, List<D250a> d250aList, List<D300a> d300aList, List<D350a> d350aList, List<D400a> d400aList, List<D800a> d800aList, List<D1000a> d1000aList, List<D1500a> d1500aList);

	/**
	 * 删除一对多
	 */
	public void delMain(String id);

	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);

}
