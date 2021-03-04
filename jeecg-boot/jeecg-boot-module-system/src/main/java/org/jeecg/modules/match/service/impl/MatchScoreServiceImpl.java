package org.jeecg.modules.match.service.impl;

import org.jeecg.modules.match.entity.MatchScore;
import org.jeecg.modules.match.mapper.MatchScoreMapper;
import org.jeecg.modules.match.service.IMatchScoreService;
import org.springframework.stereotype.Service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 比赛成绩
 * @Author: jeecg-boot
 * @Date: 2019-08-07
 * @Version: V1.0
 */
@Service
public class MatchScoreServiceImpl extends ServiceImpl<MatchScoreMapper, MatchScore> implements IMatchScoreService {

    @Autowired
    private MatchScoreMapper matchScoreMapper;

    @Override
    public List<MatchScore> selectByMainId(String mainId) {
        return matchScoreMapper.selectByMainId(mainId);
    }

    @Override
    public void scoreDelete() {
        this.matchScoreMapper.scoreDelete();
    }
}
