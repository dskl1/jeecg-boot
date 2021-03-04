package org.jeecg.modules.match.service.impl;

import org.jeecg.modules.match.entity.MatchMember;
import org.jeecg.modules.match.entity.MatchMemberConnect;
import org.jeecg.modules.match.entity.MatchPlan;
import org.jeecg.modules.match.mapper.MatchMemberConnectMapper;
import org.jeecg.modules.match.service.IMatchMemberConnectService;
import org.jeecg.modules.match.service.IMatchMemberService;
import org.jeecg.modules.match.service.IMatchPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 比赛运动员连接
 * @Author: jeecg-boot
 * @Date:   2019-08-14
 * @Version: V1.0
 */
@Service
public class MatchMemberConnectServiceImpl extends ServiceImpl<MatchMemberConnectMapper, MatchMemberConnect> implements IMatchMemberConnectService {

    @Autowired
    private IMatchPlanService matchPlanService;
    @Autowired
    private IMatchMemberService matchMemberService;
    /**
     * 查询所有比赛对应的运动员信息
     */
    @Override
    public Map<String,String> queryMatchMember() {
        List<MatchMemberConnect> uSportsmanList = this.list();
        List<MatchPlan> matchList = matchPlanService.list();
        List<MatchMember> sportsmanList = matchMemberService.list();
        Map<String,String> map = new IdentityHashMap<>();
        String matchId = "";
        String sportsmanId = "";
        String name = "";
        if(uSportsmanList != null && uSportsmanList.size() > 0) {
            for(MatchMemberConnect uSportsman : uSportsmanList) {
                sportsmanId = uSportsman.getSportsmanId();
                for(MatchPlan match : matchList) {
                    matchId = match.getId();
                    if(uSportsman.getMatchId().equals(matchId)) {
                        name = this.searchBySportsmanId(sportsmanList,sportsmanId);
                        map.put(matchId, name);
                    }
                }
            }
            return map;
        }
        return map;
    }

    /**
     * queryMatchMember调用的方法
     * @param sportsmanList
     * @param sportsmanId
     * @return
     */
    private String searchBySportsmanId(List<MatchMember> sportsmanList, String sportsmanId) {
        while(true) {
            for(MatchMember sportsman : sportsmanList) {
                if(sportsmanId.equals(sportsman.getId())) {
                    return sportsman.getName();
                }
            }
        }
    }

}
