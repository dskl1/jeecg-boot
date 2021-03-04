package org.jeecg.modules.training.service;

import org.jeecg.modules.training.entity.D250;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 250
 * @Author: jeecg-boot
 * @Date:   2020-05-20
 * @Version: V1.0
 */
public interface ID250Service extends IService<D250> {

	public List<D250> selectByMainId(String mainId);

	/**
	 * 把当天得训练计划插到250对应的训练项目里
	 */
	public void insertD250();
}
