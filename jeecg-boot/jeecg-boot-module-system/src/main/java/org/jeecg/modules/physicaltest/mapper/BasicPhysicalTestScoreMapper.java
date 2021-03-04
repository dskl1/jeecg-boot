package org.jeecg.modules.physicaltest.mapper;

import java.util.List;
import org.jeecg.modules.physicaltest.entity.BasicPhysicalTestScore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 基础体能评分标准
 * @Author: jeecg-boot
 * @Date:   2020-05-12
 * @Version: V1.0
 */
public interface BasicPhysicalTestScoreMapper extends BaseMapper<BasicPhysicalTestScore> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<BasicPhysicalTestScore> selectByMainId(@Param("mainId") String mainId);

}
