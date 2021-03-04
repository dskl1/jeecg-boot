package org.jeecg.modules.bestscoreshow.service;

import org.jeecg.modules.bestscoreshow.entity.ItemShow;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 项目展示
 * @Author: jeecg-boot
 * @Date:   2019-12-20
 * @Version: V1.0
 */
public interface IItemShowService extends IService<ItemShow> {

	public List<ItemShow> selectByMainId(String mainId);
}
