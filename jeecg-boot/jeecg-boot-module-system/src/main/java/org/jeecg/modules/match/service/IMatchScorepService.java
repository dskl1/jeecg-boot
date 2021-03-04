package org.jeecg.modules.match.service;

import org.jeecg.modules.match.entity.MatchScorep;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 项目
 * @Author: jeecg-boot
 * @Date: 2019-08-19
 * @Version: V1.0
 */
public interface IMatchScorepService extends IService<MatchScorep> {

    public List<MatchScorep> selectByMainId(String mainId);

    public void scoreDelete();
}
