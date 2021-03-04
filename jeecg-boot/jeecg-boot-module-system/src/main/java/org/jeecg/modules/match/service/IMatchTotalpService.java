package org.jeecg.modules.match.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.match.entity.MatchScorep;
import org.jeecg.modules.match.entity.MatchTotalp;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 比赛
 * @Author: jeecg-boot
 * @Date:   2019-08-19
 * @Version: V1.0
 */
public interface IMatchTotalpService extends IService<MatchTotalp> {

	public List<MatchTotalp> selectByMainId(String mainId);

	/**
	 * 添加一对多
	 *
	 */
	public void saveMain(MatchTotalp matchTotalp,List<MatchScorep> matchScorepList) ;

	/**
	 * 修改一对多
	 *
	 */
	public void updateMain(MatchTotalp matchTotalp,List<MatchScorep> matchScorepList);

	/**
	 * 删除一对多
	 */
	public void delMain (String id);

	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);

}
