package org.jeecg.modules.bestscoreshow.service;

import org.jeecg.modules.bestscoreshow.entity.ItemShow;
import org.jeecg.modules.bestscoreshow.entity.NameShow;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 姓名展示
 * @Author: jeecg-boot
 * @Date:   2019-12-20
 * @Version: V1.0
 */
public interface INameShowService extends IService<NameShow> {

	/**
	 * 添加一对多
	 *
	 */
	public void saveMain(NameShow nameShow, List<ItemShow> itemShowList) ;

	/**
	 * 修改一对多
	 *
	 */
	public void updateMain(NameShow nameShow, List<ItemShow> itemShowList);

	/**
	 * 删除一对多
	 */
	public void delMain(String id);

	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);

}
