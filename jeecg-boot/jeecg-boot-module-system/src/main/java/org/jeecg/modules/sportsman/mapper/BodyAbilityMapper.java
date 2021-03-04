package org.jeecg.modules.sportsman.mapper;

import java.util.List;
import org.jeecg.modules.sportsman.entity.BodyAbility;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 身体素质
 * @author： jeecg-boot
 * @date：   2019-06-20
 * @version： V1.0
 */
public interface BodyAbilityMapper extends BaseMapper<BodyAbility> {

	public boolean deleteByMainId(String mainId);

	public List<BodyAbility> selectByMainId(String mainId);
}
