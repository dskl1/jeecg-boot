package org.jeecg.modules.match.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.jeecg.modules.match.entity.MatchMemberp;

/**
 * @Description: 运动员
 * @Author: jeecg-boot
 * @Date:   2019-08-19
 * @Version: V1.0
 */
public interface MatchMemberpMapper extends BaseMapper<MatchMemberp> {
    /**
     * 根据运动员Id查询用户信息
     * @param
     * @param
     * @return
     */
    IPage<MatchMemberp> selectByDivide();



}
