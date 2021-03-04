package org.jeecg.modules.training.service;

import org.jeecg.modules.training.entity.WeekTrainingScore;
import org.jeecg.modules.training.entity.WeekPlan;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 一周训练内容
 * @Author: jeecg-boot
 * @Date: 2020-06-17
 * @Version: V1.0
 */
public interface IWeekPlanService extends IService<WeekPlan> {

    /**
     * 删除一对多
     */
    public void delMain(String id);

    /**
     * 批量删除一对多
     */
    public void delBatchMain(Collection<? extends Serializable> idList);


    /**
     * 选择计划后，插入当天相同计划内容的各人对应成绩
     */
    public void insertTrainingScore(String mainId);
}
