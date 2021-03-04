package org.jeecg.modules.sportsman.service.impl;

import org.jeecg.modules.sportsman.entity.SportsmanRank;
import org.jeecg.modules.sportsman.mapper.SportsmanRankMapper;
import org.jeecg.modules.sportsman.service.ISportsmanRankService;
import org.springframework.stereotype.Service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 运动员等级
 * @author： jeecg-boot
 * @date： 2019-06-20
 * @version： V1.0
 */
@Service
public class SportsmanRankServiceImpl extends ServiceImpl<SportsmanRankMapper, SportsmanRank> implements ISportsmanRankService {

    @Autowired
    private SportsmanRankMapper sportsmanRankMapper;

    @Override
    public List<SportsmanRank> selectByMainId(String mainId) {
        return sportsmanRankMapper.selectByMainId(mainId);
    }

    @Override
    public void rankDelete() {
        this.sportsmanRankMapper.rankDelete();
    }

    @Override
    public void rankInsert() {
        this.sportsmanRankMapper.rankInsert();
    }

    @Override
    public void cardUpdate() {
        this.sportsmanRankMapper.cardUpdate();
    }
}
