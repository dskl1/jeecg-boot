package org.jeecg.modules.training.service;

import org.jeecg.modules.training.entity.D50;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 50
 * @Author: jeecg-boot
 * @Date:   2020-05-20
 * @Version: V1.0
 */
public interface ID50Service extends IService<D50> {

	public List<D50> selectByMainId(String mainId);

	/**
	 * 把当天得训练计划插到50对应的训练项目里
	 */
	public void insertD50();
}
