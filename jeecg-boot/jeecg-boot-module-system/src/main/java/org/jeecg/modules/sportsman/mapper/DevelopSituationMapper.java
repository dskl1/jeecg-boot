package org.jeecg.modules.sportsman.mapper;

import java.util.List;
import org.jeecg.modules.sportsman.entity.DevelopSituation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 历年机能发育情况
 * @author： jeecg-boot
 * @date：   2019-06-20
 * @version： V1.0
 */
public interface DevelopSituationMapper extends BaseMapper<DevelopSituation> {

	public boolean deleteByMainId(String mainId);

	public List<DevelopSituation> selectByMainId(String mainId);
}
