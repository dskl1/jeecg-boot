package org.jeecg.modules.match.service.impl;

import org.jeecg.modules.match.entity.MatchScorep;
import org.jeecg.modules.match.mapper.MatchScorepMapper;
import org.jeecg.modules.match.service.IMatchScorepService;
import org.springframework.stereotype.Service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 项目
 * @Author: jeecg-boot
 * @Date: 2019-08-19
 * @Version: V1.0
 */
@Service
public class MatchScorepServiceImpl extends ServiceImpl<MatchScorepMapper, MatchScorep> implements IMatchScorepService {

    @Autowired
    private MatchScorepMapper matchScorepMapper;

    @Override
    public List<MatchScorep> selectByMainId(String mainId) {
        return matchScorepMapper.selectByMainId(mainId);
    }

    @Override
    public void scoreDelete() {
        this.matchScorepMapper.scoreDelete();
    }
}
