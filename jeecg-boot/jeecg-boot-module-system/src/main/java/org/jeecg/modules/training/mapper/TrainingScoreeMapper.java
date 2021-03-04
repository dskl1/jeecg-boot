package org.jeecg.modules.training.mapper;

import java.util.List;
import org.jeecg.modules.training.entity.TrainingScoree;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 成绩
 * @Author: jeecg-boot
 * @Date:   2020-05-20
 * @Version: V1.0
 */
public interface TrainingScoreeMapper extends BaseMapper<TrainingScoree> {

	public boolean deleteByMainId(@Param("mainId") String mainId);

	public List<TrainingScoree> selectByMainId(@Param("mainId") String mainId);

	public void insertScore(@Param("mainId") String mainId);

}
