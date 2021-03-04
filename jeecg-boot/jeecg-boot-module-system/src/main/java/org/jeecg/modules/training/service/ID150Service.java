package org.jeecg.modules.training.service;

import org.jeecg.modules.training.entity.D150;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 150
 * @Author: jeecg-boot
 * @Date:   2020-05-20
 * @Version: V1.0
 */
public interface ID150Service extends IService<D150> {

	public List<D150> selectByMainId(String mainId);

	/**
	 * 把当天得训练计划插到150对应的训练项目里
	 */
	public void insertD150();
}
