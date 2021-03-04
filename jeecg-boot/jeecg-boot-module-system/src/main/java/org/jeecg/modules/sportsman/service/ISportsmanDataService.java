package org.jeecg.modules.sportsman.service;

import org.jeecg.modules.sportsman.entity.SportsmanData;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 个人资料
 * @author： jeecg-boot
 * @date：   2019-06-28
 * @version： V1.0
 */
public interface ISportsmanDataService extends IService<SportsmanData> {

	public List<SportsmanData> selectByMainId(String mainId);
}
