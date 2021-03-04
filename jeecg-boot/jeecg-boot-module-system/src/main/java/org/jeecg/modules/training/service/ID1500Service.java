package org.jeecg.modules.training.service;

import org.jeecg.modules.training.entity.D1500;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 1500
 * @Author: jeecg-boot
 * @Date:   2020-05-20
 * @Version: V1.0
 */
public interface ID1500Service extends IService<D1500> {

	public List<D1500> selectByMainId(String mainId);

	/**
	 * 把当天得训练计划插到1500对应的训练项目里
	 */
	public void insertD1500();
}
