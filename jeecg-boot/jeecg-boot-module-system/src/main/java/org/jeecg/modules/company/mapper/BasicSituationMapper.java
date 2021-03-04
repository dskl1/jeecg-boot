package org.jeecg.modules.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.company.entity.BasicSituation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 简历
 * @Author: jeecg-boot
 * @Date:   2020-04-21
 * @Version: V1.0
 */
public interface BasicSituationMapper extends BaseMapper<BasicSituation> {
    /**
     *   首页：根据时间统计成绩（访问数量/ip数量）
     * @param name
     * @return
     */
    List<BasicSituation> selectUserInfoByNa(@Param("name") String name);
}
