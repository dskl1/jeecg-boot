package org.jeecg.modules.training.service;

import org.jeecg.modules.training.entity.D400;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 400
 * @Author: jeecg-boot
 * @Date:   2020-05-20
 * @Version: V1.0
 */
public interface ID400Service extends IService<D400> {

	public List<D400> selectByMainId(String mainId);

	/**
	 * 把当天得训练计划插到400对应的训练项目里
	 */
	public void insertD400();
}
