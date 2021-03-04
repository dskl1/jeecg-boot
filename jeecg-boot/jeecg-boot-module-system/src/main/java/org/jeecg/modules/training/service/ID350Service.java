package org.jeecg.modules.training.service;

import org.jeecg.modules.training.entity.D350;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 350
 * @Author: jeecg-boot
 * @Date:   2020-05-20
 * @Version: V1.0
 */
public interface ID350Service extends IService<D350> {

	public List<D350> selectByMainId(String mainId);

	/**
	 * 把当天得训练计划插到350对应的训练项目里
	 */
	public void insertD350();
}
