package org.jeecg.modules.match.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.match.entity.MatchPlan;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * @Description: 比赛计划
 * @Author: jeecg-boot
 * @Date:   2019-08-06
 * @Version: V1.0
 */
public interface IMatchPlanService extends IService<MatchPlan> {

    /**
     * 根据运动员Id查询
     * @param
     * @return
     */
    public IPage<MatchPlan> getMatchBySportsmanId(Page<MatchPlan> page, String sportsmanId, String matchName);

    /**
     * 添加比赛和比赛运动员关系
     * @param match
     * @param sportsmans
     */
    public void addMatchWithSportsman(MatchPlan match, String sportsmans);

    /**
     * 修改用户和用户角色关系
     * @param match
     * @param sportsmans
     */
    public void editMatchWithSportsman(MatchPlan match,String sportsmans);

    /**
     * 获取比赛的授权运动员
     * @param matchName
     * @return
     */
    public List<String> getSportsman(String matchName);

    /**
     * 通过用户名获取用户角色集合
     *
     * @param matchName 比赛名
     * @return 运动员集合
     */
    Set<String> getMatchSportsmansSet(String matchName);
}
