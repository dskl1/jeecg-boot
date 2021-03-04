package org.jeecg.modules.match.service;

import org.jeecg.modules.match.entity.MatchMemberConnect;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 比赛运动员连接
 * @Author: jeecg-boot
 * @Date:   2019-08-14
 * @Version: V1.0
 */
public interface IMatchMemberConnectService extends IService<MatchMemberConnect> {

    /**
     * 查询所有的比赛运动员信息
     * @return
     */
    Map<String,String> queryMatchMember();

}
