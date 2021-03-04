package org.jeecg.modules.sportsman.mapper;

import java.util.List;
import org.jeecg.modules.sportsman.entity.SportsmanData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 个人资料
 * @author： jeecg-boot
 * @date：   2019-06-28
 * @version： V1.0
 */
public interface SportsmanDataMapper extends BaseMapper<SportsmanData> {

	public boolean deleteByMainId(String mainId);

	public List<SportsmanData> selectByMainId(String mainId);
}
