package org.jeecg.modules.sportsman.service;

import org.jeecg.modules.sportsman.entity.SportsmanRank;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 运动员等级
 * @author： jeecg-boot
 * @date： 2019-06-20
 * @version： V1.0
 */
public interface ISportsmanRankService extends IService<SportsmanRank> {

    public List<SportsmanRank> selectByMainId(String mainId);

    public void rankDelete();

    public void rankInsert();

    public void cardUpdate();
}
