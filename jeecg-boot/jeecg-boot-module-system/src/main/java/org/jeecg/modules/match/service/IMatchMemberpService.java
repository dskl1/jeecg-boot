package org.jeecg.modules.match.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.match.entity.MatchMemberp;
import org.jeecg.modules.match.entity.MatchTotalp;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 运动员
 * @Author: jeecg-boot
 * @Date:   2019-08-19
 * @Version: V1.0
 */
public interface IMatchMemberpService extends IService<MatchMemberp> {

	/**
	 * 添加一对多
	 *
	 */
	public void saveMain(MatchMemberp matchMemberp, List<MatchTotalp> matchTotalpList) ;

	/**
	 * 修改一对多
	 *
	 */
	public void updateMain(MatchMemberp matchMemberp, List<MatchTotalp> matchTotalpList);

	/**
	 * 删除一对多
	 */
	public void delMain(String id);

	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);

	/**
	 * 根据运动员Id查询
	 * @param
	 * @return
	 */
	public IPage<MatchMemberp> selectByDivide();

}
