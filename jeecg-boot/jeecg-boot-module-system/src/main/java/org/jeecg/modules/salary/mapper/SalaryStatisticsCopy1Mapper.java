package org.jeecg.modules.salary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.jeecg.modules.salary.entity.SalaryStatisticsCopy1;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.sportsman.entity.SportsmanData;

/**
 * @Description: 历史工资
 * @Author: jeecg-boot
 * @Date:   2020-08-18
 * @Version: V1.0
 */
public interface SalaryStatisticsCopy1Mapper extends BaseMapper<SalaryStatisticsCopy1> {


    @Select("select *\n" +
            "            from salary_statistics_copy1\n" +
            "            where DATE_FORMAT(create_time,\"%Y-%m\") = #{format}")
    List<SalaryStatisticsCopy1> selectByMonth(String format);

    @Insert("INSERT IGNORE INTO salary_statistics_copy1 (\n" +
            "id,\n" +
            "NAME,\n" +
            "email,\n" +
            "basic_wage_standard,\n" +
            "dispatch_subsidy_standard,\n" +
            "total_day,\n" +
            "positive_day,\n" +
            "positive_dispatch_day,\n" +
            "trial_day,\n" +
            "trial_dispatch_day,\n" +
            "trial_discount,\n" +
            "absence_discount,\n" +
            "absence_reason,\n" +
            "overtime_hours,\n" +
            "basic_wage,\n" +
            "dispatch_subsidy,\n" +
            "year_holiday,\n" +
            "overtime_salary,\n" +
            "performance,\n" +
            "meal_supplement,\n" +
            "other_thing,\n" +
            "computer_subsidy,\n" +
            "advance_salary,\n" +
            "provident_fund,\n" +
            "social_security_person,\n" +
            "late,\n" +
            "social_security_co,\n" +
            "total_income,\n" +
            "pre_tax_income,\n" +
            "special_deduction_items,\n" +
            "special_deduction_amount,\n" +
            "application_date,\n" +
            "expire_date,\n" +
            "person_income,\n" +
            "actual_salary,\n" +
            "notice,\n" +
            "img_path,\n" +
            "create_by,\n" +
            "create_time \n" +
            ") SELECT\n" +
            "UUID(),\n" +
            "NAME,\n" +
            "email,\n" +
            "basic_wage_standard,\n" +
            "dispatch_subsidy_standard,\n" +
            "total_day,\n" +
            "positive_day,\n" +
            "positive_dispatch_day,\n" +
            "trial_day,\n" +
            "trial_dispatch_day,\n" +
            "trial_discount,\n" +
            "absence_discount,\n" +
            "absence_reason,\n" +
            "overtime_hours,\n" +
            "basic_wage,\n" +
            "dispatch_subsidy,\n" +
            "year_holiday,\n" +
            "overtime_salary,\n" +
            "performance,\n" +
            "meal_supplement,\n" +
            "other_thing,\n" +
            "computer_subsidy,\n" +
            "advance_salary,\n" +
            "provident_fund,\n" +
            "social_security_person,\n" +
            "late,\n" +
            "social_security_co,\n" +
            "total_income,\n" +
            "pre_tax_income,\n" +
            "special_deduction_items,\n" +
            "special_deduction_amount,\n" +
            "application_date,\n" +
            "expire_date,\n" +
            "person_income,\n" +
            "actual_salary,\n" +
            "notice,\n" +
            "img_path,\n" +
            "create_by,\n" +
            "NOW() \n" +
            "FROM\n" +
            "salary_statistics")
    public void ssInsert(); //插入工资记录

}
