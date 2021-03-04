package org.jeecg.modules.training.service;

import org.jeecg.modules.training.entity.D25;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 25
 * @Author: jeecg-boot
 * @Date:   2020-05-20
 * @Version: V1.0
 */
public interface ID25Service extends IService<D25> {

	public List<D25> selectByMainId(String mainId);

	/**
	 * 删除一对多
	 */
	public void delMain (String id);

	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);

	/**
	 * 把当天得训练计划插到25对应的训练项目里
	 */
	public void insertD25();
}
