package org.jeecg.modules.sportsman.service;

import org.jeecg.modules.sportsman.entity.BodyType;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 身体形态指标
 * @author： jeecg-boot
 * @date：   2019-06-20
 * @version： V1.0
 */
public interface IBodyTypeService extends IService<BodyType> {

	public List<BodyType> selectByMainId(String mainId);
}
