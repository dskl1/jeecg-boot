package org.jeecg.modules.sportsman.mapper;

import java.util.List;
import org.jeecg.modules.sportsman.entity.BodyType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 身体形态指标
 * @author： jeecg-boot
 * @date：   2019-06-20
 * @version： V1.0
 */
public interface BodyTypeMapper extends BaseMapper<BodyType> {

	public boolean deleteByMainId(String mainId);

	public List<BodyType> selectByMainId(String mainId);
}
