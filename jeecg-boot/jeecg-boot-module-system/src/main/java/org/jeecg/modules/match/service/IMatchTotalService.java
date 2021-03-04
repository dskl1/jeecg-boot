package org.jeecg.modules.match.service;

import org.jeecg.modules.match.entity.MatchScore;
import org.jeecg.modules.match.entity.MatchTotal;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 比赛名称
 * @Author: jeecg-boot
 * @Date:   2019-08-07
 * @Version: V1.0
 */
public interface IMatchTotalService extends IService<MatchTotal> {

	public List<MatchTotal> selectByMainId(String mainId);

	/**
	 * 添加一对多
	 *
	 */
	public void saveMain(MatchTotal matchTotal,List<MatchScore> matchScoreList) ;

	/**
	 * 修改一对多
	 *
	 */
	public void updateMain(MatchTotal matchTotal,List<MatchScore> matchScoreList);

	/**
	 * 删除一对多
	 */
	public void delMain (String id);

	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);

	public List<MatchTotal> updateByMainId(String mainId);
}
