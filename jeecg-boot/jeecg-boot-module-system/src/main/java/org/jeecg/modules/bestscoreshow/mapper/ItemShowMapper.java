package org.jeecg.modules.bestscoreshow.mapper;

import java.util.List;
import org.jeecg.modules.bestscoreshow.entity.ItemShow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 项目展示
 * @Author: jeecg-boot
 * @Date:   2019-12-20
 * @Version: V1.0
 */
public interface ItemShowMapper extends BaseMapper<ItemShow> {

	public boolean deleteByMainId(String mainId);

	public List<ItemShow> selectByMainId(String mainId);
}
