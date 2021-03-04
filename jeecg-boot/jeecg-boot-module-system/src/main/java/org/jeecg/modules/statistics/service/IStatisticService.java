package org.jeecg.modules.statistics.service;

import org.jeecg.modules.statistics.entity.MatchTotala;
import org.jeecg.modules.statistics.entity.Statistic;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 比赛报表
 * @Author: jeecg-boot
 * @Date:   2019-09-18
 * @Version: V1.0
 */
public interface IStatisticService extends IService<Statistic> {

	/**
	 * 添加一对多
	 *
	 */
	public void saveMain(Statistic statistic, List<MatchTotala> matchTotalaList) ;

	/**
	 * 修改一对多
	 *
	 */
	public void updateMain(Statistic statistic, List<MatchTotala> matchTotalaList);

	/**
	 * 删除一对多
	 */
	public void delMain(String id);

	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);

}
