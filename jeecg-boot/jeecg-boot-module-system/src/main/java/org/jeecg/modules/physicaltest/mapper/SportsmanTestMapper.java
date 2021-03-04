package org.jeecg.modules.physicaltest.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.jeecg.modules.physicaltest.entity.SportsmanTest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 人员表
 * @Author: jeecg-boot
 * @Date: 2020-05-12
 * @Version: V1.0
 */
public interface SportsmanTestMapper extends BaseMapper<SportsmanTest> {

    /**
     * 判断身份证号的第17位为奇偶数，给性别赋值
     */
    @Update("update sportsman_registration_entry \n" +
            "set sex = substring(card_number,17,1)%2 ")
    public void cardNumberToSex();

    /**
     * 将队员表的性别字段值赋给基础体能表
     */
    @Update("update basic_physical_test_score\n" +
            "set sex = (\n" +
            "select sex\n" +
            "from sportsman_registration_entry\n" +
            "where sportsman_registration_entry.id = basic_physical_test_score.sportsman_id)")
    public void doubleSex();
}
