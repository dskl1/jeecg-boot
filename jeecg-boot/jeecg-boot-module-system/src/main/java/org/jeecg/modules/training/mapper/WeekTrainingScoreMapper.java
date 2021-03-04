package org.jeecg.modules.training.mapper;

import java.util.List;
import org.jeecg.modules.training.entity.WeekTrainingScore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 训练人员名单
 * @Author: jeecg-boot
 * @Date:   2020-06-17
 * @Version: V1.0
 */
public interface WeekTrainingScoreMapper extends BaseMapper<WeekTrainingScore> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<WeekTrainingScore> selectByMainId(@Param("mainId") String mainId);

}
