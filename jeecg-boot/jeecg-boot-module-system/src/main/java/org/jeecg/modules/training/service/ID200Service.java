package org.jeecg.modules.training.service;

import org.jeecg.modules.training.entity.D200;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 200
 * @Author: jeecg-boot
 * @Date:   2020-05-20
 * @Version: V1.0
 */
public interface ID200Service extends IService<D200> {

	public List<D200> selectByMainId(String mainId);

	/**
	 * 把当天得训练计划插到200对应的训练项目里
	 */
	public void insertD200();
}
