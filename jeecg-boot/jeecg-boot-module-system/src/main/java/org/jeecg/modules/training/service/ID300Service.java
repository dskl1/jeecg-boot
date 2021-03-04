package org.jeecg.modules.training.service;

import org.jeecg.modules.training.entity.D300;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 300
 * @Author: jeecg-boot
 * @Date:   2020-05-20
 * @Version: V1.0
 */
public interface ID300Service extends IService<D300> {

	public List<D300> selectByMainId(String mainId);

	/**
	 * 把当天得训练计划插到300对应的训练项目里
	 */
	public void insertD300();
}
