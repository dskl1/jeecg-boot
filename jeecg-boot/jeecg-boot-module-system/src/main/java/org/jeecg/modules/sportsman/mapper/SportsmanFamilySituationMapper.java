package org.jeecg.modules.sportsman.mapper;

import java.util.List;
import org.jeecg.modules.sportsman.entity.SportsmanFamilySituation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 运动员家庭成员情况
 * @author： jeecg-boot
 * @date：   2019-06-20
 * @version： V1.0
 */
public interface SportsmanFamilySituationMapper extends BaseMapper<SportsmanFamilySituation> {

	public boolean deleteByMainId(String mainId);

	public List<SportsmanFamilySituation> selectByMainId(String mainId);
}
