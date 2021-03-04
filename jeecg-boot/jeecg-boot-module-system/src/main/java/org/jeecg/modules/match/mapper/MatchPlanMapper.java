package org.jeecg.modules.match.mapper;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.match.entity.MatchPlan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 比赛计划
 * @Author: jeecg-boot
 * @Date:   2019-08-06
 * @Version: V1.0
 */
public interface MatchPlanMapper extends BaseMapper<MatchPlan> {

    /**
     * 根据运动员Id查询用户信息
     * @param page
     * @param
     * @return
     */
    IPage<MatchPlan> getMatchBySportsmanId(Page page, @Param("sportsmanId") String sportsmanId, @Param("matchName") String matchName);


}
