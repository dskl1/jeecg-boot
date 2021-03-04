package org.jeecg.modules.sportsman.service;

import org.jeecg.modules.sportsman.entity.DevelopSituation;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 历年机能发育情况
 * @author： jeecg-boot
 * @date：   2019-06-20
 * @version： V1.0
 */
public interface IDevelopSituationService extends IService<DevelopSituation> {

	public List<DevelopSituation> selectByMainId(String mainId);
}
