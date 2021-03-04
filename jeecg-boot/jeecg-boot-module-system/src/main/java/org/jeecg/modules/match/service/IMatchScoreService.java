package org.jeecg.modules.match.service;

import org.jeecg.modules.match.entity.MatchScore;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 比赛成绩
 * @Author: jeecg-boot
 * @Date:   2019-08-07
 * @Version: V1.0
 */
public interface IMatchScoreService extends IService<MatchScore> {

	public List<MatchScore> selectByMainId(String mainId);

	public void scoreDelete();
}
