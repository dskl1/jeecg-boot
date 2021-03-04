package org.jeecg.modules.company.service;

import org.jeecg.modules.company.entity.TestWord;
import org.jeecg.modules.company.entity.BasicSituation;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 简历
 * @Author: jeecg-boot
 * @Date:   2020-04-21
 * @Version: V1.0
 */
public interface IBasicSituationService extends IService<BasicSituation> {

	/**
	 * 删除一对多
	 */
	public void delMain (String id);

	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);

	/**
	 *   首页：根据时间统计训练成绩（访问数量/ip数量）
	 * @param name
	 * @return
	 */
	public List<BasicSituation> selectUserInfoByNa(String name);
}
