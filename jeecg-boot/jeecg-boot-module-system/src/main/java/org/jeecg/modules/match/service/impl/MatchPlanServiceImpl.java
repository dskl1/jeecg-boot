package org.jeecg.modules.match.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.common.constant.CacheConstant;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.match.entity.MatchMemberConnect;
import org.jeecg.modules.match.entity.MatchPlan;
import org.jeecg.modules.match.mapper.MatchMemberConnectMapper;
import org.jeecg.modules.match.mapper.MatchPlanMapper;
import org.jeecg.modules.match.service.IMatchPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description: 比赛计划
 * @Author: jeecg-boot
 * @Date:   2019-08-06
 * @Version: V1.0
 */
@Service
public class MatchPlanServiceImpl extends ServiceImpl<MatchPlanMapper, MatchPlan> implements IMatchPlanService {

    @Autowired
    private MatchMemberConnectMapper matchMemberConnectMapper;
    @Autowired
    private MatchPlanMapper matchPlanMapper;

    @Override
    @Transactional
    public void addMatchWithSportsman(MatchPlan match, String sportsmans) {
        this.save(match);
        if(oConvertUtils.isNotEmpty(sportsmans)) {
            String[] arr = sportsmans.split(",");
            for (String sportsmanId : arr) {
                MatchMemberConnect matchMember = new MatchMemberConnect(match.getId(), sportsmanId);
                matchMemberConnectMapper.insert(matchMember);
            }
        }
    }

    @Override
    @CacheEvict(value= CacheConstant.SYS_USERS_CACHE, allEntries=true)
    @Transactional
    public void editMatchWithSportsman(MatchPlan match, String sportsmans) {
        this.updateById(match);
        //先删后加
        matchMemberConnectMapper.delete(new QueryWrapper<MatchMemberConnect>().lambda().eq(MatchMemberConnect::getMatchId, match.getId()));
        if(oConvertUtils.isNotEmpty(sportsmans)) {
            String[] arr = sportsmans.split(",");
            for (String sportsmanId : arr) {
                MatchMemberConnect matchMember = new MatchMemberConnect(match.getId(), sportsmanId);
                matchMemberConnectMapper.insert(matchMember);
            }
        }
    }

    @Override
    public List<String> getSportsman(String matchName) {
        return matchMemberConnectMapper.getSportsmanByMatchName(matchName);
    }

    /**
     * 通过比赛名称获取比赛运动员集合
     * @param matchName 比赛名称
     * @return 运动员集合
     */
    @Override
    @Cacheable(value = CacheConstant.SYS_USERS_CACHE,key = "'Sportsmans_'+#matchName")
    public Set<String> getMatchSportsmansSet(String matchName) {
        // 查询比赛拥有的运动员集合
        List<String> sportsmans = matchMemberConnectMapper.getSportsmanByMatchName(matchName);
        //log.info("-------通过数据库读取比赛拥有的运动员Rules------matchName： " + matchName + ",Sportsmans size: " + (sportsmans == null ? 0 : sportsmans.size()));
        return new HashSet<>(sportsmans);
    }

    // 根据运动员Id查询
    @Override
    public IPage<MatchPlan> getMatchBySportsmanId(Page<MatchPlan> page, String SportsmanId, String matchName) {
        return matchPlanMapper.getMatchBySportsmanId(page,SportsmanId,matchName);
    }



}
