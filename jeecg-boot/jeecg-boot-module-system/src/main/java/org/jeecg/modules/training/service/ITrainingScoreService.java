package org.jeecg.modules.training.service;

import org.jeecg.modules.training.entity.D25;
import org.jeecg.modules.training.entity.D50;
import org.jeecg.modules.training.entity.D150;
import org.jeecg.modules.training.entity.D200;
import org.jeecg.modules.training.entity.D250;
import org.jeecg.modules.training.entity.D300;
import org.jeecg.modules.training.entity.D350;
import org.jeecg.modules.training.entity.D400;
import org.jeecg.modules.training.entity.D800;
import org.jeecg.modules.training.entity.D1000;
import org.jeecg.modules.training.entity.D1500;
import org.jeecg.modules.training.entity.TrainingScore;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: training_score
 * @Author: jeecg-boot
 * @Date:   2020-05-20
 * @Version: V1.0
 */
public interface ITrainingScoreService extends IService<TrainingScore> {

	/**
	 * 删除一对多
	 */
	public void delMain (String id);

	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);

	/**
	 * 设置training_score卡标记为0
	 */
	public void setFlagSee();

	/**
	 * 设置training_score卡标记为1
	 */
	public void setFlagNoSee();

	/**
	 * 打卡人员插到当天计划下
	 */
	public void insertName();

	/**
	 * 设置training_score_copy1卡标记为0
	 */
	public void setFlagSeeCopy();

	/**
	 * 设置training_score_copy1卡标记为1
	 */
	public void setFlagNoSeeCopy();

	/**
	 * count(name)=1必定是当天新增的数据，直接插入training_score_copy1表
	 */
	public void insertNameCopy();

	/**
	 * count(name) > 1时，查找在training_score表中当天是否有新增数据，有就更新training_score_copy1对应的名字的update_time
	 */
	public void updateTimeCopy();
}
