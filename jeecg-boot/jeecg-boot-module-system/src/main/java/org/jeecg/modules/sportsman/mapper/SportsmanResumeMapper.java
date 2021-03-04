package org.jeecg.modules.sportsman.mapper;

import java.util.List;
import org.jeecg.modules.sportsman.entity.SportsmanResume;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 运动员训练简历
 * @author： jeecg-boot
 * @date：   2019-06-20
 * @version： V1.0
 */
public interface SportsmanResumeMapper extends BaseMapper<SportsmanResume> {

	public boolean deleteByMainId(String mainId);

	public List<SportsmanResume> selectByMainId(String mainId);
}
