package org.jeecg.modules.training.service;

import org.jeecg.modules.training.entity.WeekTrainingScore;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 训练人员名单
 * @Author: jeecg-boot
 * @Date:   2020-06-17
 * @Version: V1.0
 */
public interface IWeekTrainingScoreService extends IService<WeekTrainingScore> {

	public List<WeekTrainingScore> selectByMainId(String mainId);
}
