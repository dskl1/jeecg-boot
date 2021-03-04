package org.jeecg.modules.match.service;

import org.jeecg.modules.match.entity.MatchTotal;
import org.jeecg.modules.match.entity.MatchMember;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 运动员
 * @Author: jeecg-boot
 * @Date:   2019-08-07
 * @Version: V1.0
 */
public interface IMatchMemberService extends IService<MatchMember> {

	/**
	 * 添加一对多
	 *
	 */
	public void saveMain(MatchMember matchMember, List<MatchTotal> matchTotalList) ;

	/**
	 * 修改一对多
	 *
	 */
	public void updateMain(MatchMember matchMember, List<MatchTotal> matchTotalList);

	/**
	 * 删除一对多
	 */
	public void delMain(String id);

	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);

}
