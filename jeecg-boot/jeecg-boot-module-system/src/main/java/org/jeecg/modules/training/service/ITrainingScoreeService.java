package org.jeecg.modules.training.service;

import org.jeecg.modules.training.entity.TrainingScoree;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 成绩
 * @Author: jeecg-boot
 * @Date:   2020-05-20
 * @Version: V1.0
 */
public interface ITrainingScoreeService extends IService<TrainingScoree> {

	public List<TrainingScoree> selectByMainId(String mainId);

	public void insertScore(String mainId);
}
