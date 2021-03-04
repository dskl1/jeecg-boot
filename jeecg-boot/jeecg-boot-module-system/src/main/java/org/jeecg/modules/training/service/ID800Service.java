package org.jeecg.modules.training.service;

import org.jeecg.modules.training.entity.D800;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 800
 * @Author: jeecg-boot
 * @Date:   2020-05-20
 * @Version: V1.0
 */
public interface ID800Service extends IService<D800> {

	public List<D800> selectByMainId(String mainId);

	/**
	 * 把当天得训练计划插到800对应的训练项目里
	 */
	public void insertD800();
}
