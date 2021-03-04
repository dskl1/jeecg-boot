package org.jeecg.modules.training.service;

import org.jeecg.modules.training.entity.D100;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 100
 * @Author: jeecg-boot
 * @Date:   2020-05-20
 * @Version: V1.0
 */
public interface ID100Service extends IService<D100> {

	public List<D100> selectByMainId(String mainId);

	/**
	 * 把当天得训练计划插到100对应的训练项目里
	 */
	public void insertD100();
}
