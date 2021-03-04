package org.jeecg.modules.training.service;

import org.jeecg.modules.training.entity.D1000;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 1000
 * @Author: jeecg-boot
 * @Date:   2020-05-20
 * @Version: V1.0
 */
public interface ID1000Service extends IService<D1000> {

	public List<D1000> selectByMainId(String mainId);

	/**
	 * 把当天得训练计划插到1000对应的训练项目里
	 */
	public void insertD1000();
}
