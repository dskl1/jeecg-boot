package org.jeecg.modules.match.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.jeecg.modules.match.entity.MatchMemberConnect;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 比赛运动员连接
 * @Author: jeecg-boot
 * @Date:   2019-08-14
 * @Version: V1.0
 */
public interface MatchMemberConnectMapper extends BaseMapper<MatchMemberConnect> {

    @Select("select name from member_match where id in (select sportsman_id from match_member_connect where match_id = (select id from match_plan where matchName=#{matchName}))")
    List<String> getSportsmanByMatchName(@Param("matchName") String matchName);

}
