package org.jeecg.modules.training.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.training.entity.WeekPlan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 一周训练内容
 * @Author: jeecg-boot
 * @Date:   2020-06-17
 * @Version: V1.0
 */
public interface WeekPlanMapper extends BaseMapper<WeekPlan> {

    public void insertTrainingScore(@Param("mainId") String mainId);

}
