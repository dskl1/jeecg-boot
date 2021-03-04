package org.jeecg.modules.sportsman.service;

import org.jeecg.modules.sportsman.entity.SportsmanFamilySituation;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 运动员家庭成员情况
 * @author： jeecg-boot
 * @date：   2019-06-20
 * @version： V1.0
 */
public interface ISportsmanFamilySituationService extends IService<SportsmanFamilySituation> {

	public List<SportsmanFamilySituation> selectByMainId(String mainId);
}
