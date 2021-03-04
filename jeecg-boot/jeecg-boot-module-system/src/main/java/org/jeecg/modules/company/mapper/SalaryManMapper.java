package org.jeecg.modules.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.jeecg.modules.company.entity.SalaryMan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 公司人员信息
 * @Author: jeecg-boot
 * @Date:   2020-09-08
 * @Version: V1.0
 */
public interface SalaryManMapper extends BaseMapper<SalaryMan> {

    @Update("update salary_man\n" +
            "set sex = \n" +
            "case when SUBSTRING(card_number, 17, 1)%2 =1 then '男'\n" +
            "\t   when SUBSTRING(card_number, 17, 1)%2 =0 then '女'\n" +
            "\t\t else sex='未知' \n" +
            "\t\t end")
    public void sexUpdate(); //从身份证取出性别信息

    @Update("update salary_man\n" +
            "set birthday = \n" +
            "(select concat(substring(card_number,7,4),'-',\n" +
            "substring(card_number,11,2),'-',substring(card_number,13,2))\n" +
            ")")
    public void birUpdate();//从身份证取出出生日期

}
