package org.jeecg.modules.sportsman.service;

import org.jeecg.modules.sportsman.entity.BodyAbility;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 身体素质
 * @author： jeecg-boot
 * @date：   2019-06-20
 * @version： V1.0
 */
public interface IBodyAbilityService extends IService<BodyAbility> {

	public List<BodyAbility> selectByMainId(String mainId);
}
