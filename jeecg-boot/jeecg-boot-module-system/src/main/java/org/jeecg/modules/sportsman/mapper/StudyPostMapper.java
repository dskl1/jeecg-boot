package org.jeecg.modules.sportsman.mapper;

import java.util.List;
import org.jeecg.modules.sportsman.entity.StudyPost;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 科目
 * @Author: jeecg-boot
 * @Date:   2020-11-06
 * @Version: V1.0
 */
public interface StudyPostMapper extends BaseMapper<StudyPost> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<StudyPost> selectByMainId(@Param("mainId") String mainId);
}
