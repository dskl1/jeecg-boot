package org.jeecg.modules.salary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.jeecg.modules.company.entity.NewestEmployee;
import org.jeecg.modules.salary.entity.CostManagement;
import org.jeecg.modules.salary.entity.SalaryStatistics;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 工资统计
 * @Author: jeecg-boot
 * @Date: 2020-07-17
 * @Version: V1.0
 */
public interface SalaryStatisticsMapper extends BaseMapper<SalaryStatistics> {

    @Insert("insert ignore into salary_statistics(id,name,create_by,create_time,update_by,update_time,sys_org_code)\n" +
            "(\n" +
            "select id,name,create_by,create_time,update_by,update_time,sys_org_code\n" +
            "from employee_list)")
    public void personSalary();  //将人员插入工资统计表

    @Update("update salary_statistics s,newest_employee_list e\n" +
            "set s.basic_wage_standard = \n" +
            "case when e.level = '初级' and e.price <= 18000 and datediff(curdate(), e.entry_time) <=183 then 3500\n" +
            "when e.level = '中级' and e.price <= 18000 and datediff(curdate(), e.entry_time) <=183 then 3700\n" +
            "when e.level = '高级' and e.price <= 18000 and datediff(curdate(), e.entry_time) <=183 then 4000\n" +
            "when e.level = '初级' and e.price <= 18000 and datediff(curdate(), e.entry_time) >183 \n" +
            "and floor(datediff(curdate(), e.entry_time) / 183) % 2 = 0 \n" +
            "then 3500 + floor(datediff(curdate(), e.entry_time) / 183) / 2 * 300\n" +
            "when e.level = '中级' and e.price <= 18000 and datediff(curdate(), e.entry_time) >183 \n" +
            "and floor(datediff(curdate(), e.entry_time) / 183) % 2 = 0 \n" +
            "then 3700 + floor(datediff(curdate(), e.entry_time) / 183) / 2 * 300\n" +
            "when e.level = '高级' and e.price <= 18000 and datediff(curdate(), e.entry_time) >183 \n" +
            "and floor(datediff(curdate(), e.entry_time) / 183) % 2 = 0  \n" +
            "then 4000 + floor(datediff(curdate(), e.entry_time) / 183) / 2 * 300\n" +
            "when e.level = '初级' and e.price <= 18000 and datediff(curdate(), e.entry_time) >183 \n" +
            "and floor(datediff(curdate(), e.entry_time) / 183) % 2 = 1 \n" +
            "then 3500 + (floor(datediff(curdate(), e.entry_time) / 183) - 1) / 2 * 300 + 100\n" +
            "when e.level = '中级' and e.price <= 18000 and datediff(curdate(), e.entry_time) >183 \n" +
            "and floor(datediff(curdate(), e.entry_time) / 183) % 2 = 1 \n" +
            "then 3700 + (floor(datediff(curdate(), e.entry_time) / 183) - 1) / 2 * 300 + 100\n" +
            "when e.level = '高级' and e.price <= 18000 and datediff(curdate(), e.entry_time) >183 \n" +
            "and floor(datediff(curdate(), e.entry_time) / 183) % 2 = 1  \n" +
            "then 4000 + (floor(datediff(curdate(), e.entry_time) / 183) - 1) / 2 * 300 + 100\n" +
            "when e.level = '初级' and e.price > 18000 and e.price < 40000 and datediff(curdate(), e.entry_time) <=183 \n" +
            "then 3500 + floor((e.price-16000) /2000) *100\n" +
            "when e.level = '中级' and e.price > 18000 and e.price < 40000 and datediff(curdate(), e.entry_time) <=183 \n" +
            "then 3700 + floor((e.price-16000) /2000) *100\n" +
            "when e.level = '高级' and e.price > 18000 and e.price < 40000 and datediff(curdate(), e.entry_time) <=183 \n" +
            "then 4000 + floor((e.price-16000) /2000) *100\n" +
            "when e.level = '初级' and e.price > 18000 and e.price < 40000 and datediff(curdate(), e.entry_time) >183 \n" +
            "and floor(datediff(curdate(), e.entry_time) / 183) % 2 = 0 \n" +
            "then 3500 + floor((e.price-16000) /2000) *100 + floor(datediff(curdate(), e.entry_time) / 183) / 2 * 300\n" +
            "when e.level = '中级' and e.price > 18000 and e.price < 40000 and datediff(curdate(), e.entry_time) >183 \n" +
            "and floor(datediff(curdate(), e.entry_time) / 183) % 2 = 0 \n" +
            "then 3700 + floor((e.price-16000) /2000) *100 + floor(datediff(curdate(), e.entry_time) / 183) / 2 * 300\n" +
            "when e.level = '高级' and e.price > 18000 and e.price < 40000 and datediff(curdate(), e.entry_time) >183 \n" +
            "and floor(datediff(curdate(), e.entry_time) / 183) % 2 = 0 \n" +
            "then 4000 + floor((e.price-16000) /2000) *100 + floor(datediff(curdate(), e.entry_time) / 183) / 2 * 300\n" +
            "when e.level = '初级' and e.price > 18000 and e.price < 40000 and datediff(curdate(), e.entry_time) >183 \n" +
            "and floor(datediff(curdate(), e.entry_time) / 183) % 2 = 1 \n" +
            "then 3500 + floor((e.price-16000) /2000) *100 + (floor(datediff(curdate(), e.entry_time) / 183) - 1) / 2 * 300 + 100\n" +
            "when e.level = '中级' and e.price > 18000 and e.price < 40000 and datediff(curdate(), e.entry_time) >183 \n" +
            "and floor(datediff(curdate(), e.entry_time) / 183) % 2 = 1 \n" +
            "then 3700 + floor((e.price-16000) /2000) *100 + (floor(datediff(curdate(), e.entry_time) / 183) - 1) / 2 * 300 + 100\n" +
            "when e.level = '高级' and e.price > 18000 and e.price < 40000 and datediff(curdate(), e.entry_time) >183 \n" +
            "and floor(datediff(curdate(), e.entry_time) / 183) % 2 = 1 \n" +
            "then 4000 + floor((e.price-16000) /2000) *100 + (floor(datediff(curdate(), e.entry_time) / 183) - 1) / 2 * 300 + 100\n" +
            "when e.level = '初级' and e.price >= 40000 and datediff(curdate(), e.entry_time) <=183 \n" +
            "then 3500 + 1000\n" +
            "when e.level = '中级' and e.price >= 40000 and datediff(curdate(), e.entry_time) <=183 \n" +
            "then 3700 + 1000\n" +
            "when e.level = '高级' and e.price >= 40000 and datediff(curdate(), e.entry_time) <=183 \n" +
            "then 4000 + 1000\n" +
            "when e.level = '初级' and e.price >= 40000 and datediff(curdate(), e.entry_time) >183 \n" +
            "and floor(datediff(curdate(), e.entry_time) / 183) % 2 = 0 \n" +
            "then 3500 + 1000 + floor(datediff(curdate(), e.entry_time) / 183) / 2 * 300\n" +
            "when e.level = '中级' and e.price >= 40000 and datediff(curdate(), e.entry_time) >183 \n" +
            "and floor(datediff(curdate(), e.entry_time) / 183) % 2 = 0 \n" +
            "then 3700 + 1000 + floor(datediff(curdate(), e.entry_time) / 183) / 2 * 300\n" +
            "when e.level = '高级' and e.price >= 40000 and datediff(curdate(), e.entry_time) >183 \n" +
            "and floor(datediff(curdate(), e.entry_time) / 183) % 2 = 0 \n" +
            "then 4000 + 1000 + floor(datediff(curdate(), e.entry_time) / 183) / 2 * 300\n" +
            "when e.level = '初级' and e.price >= 40000 and datediff(curdate(), e.entry_time) >183 \n" +
            "and floor(datediff(curdate(), e.entry_time) / 183) % 2 = 1 \n" +
            "then 3500 + 1000 + (floor(datediff(curdate(), e.entry_time) / 183) - 1) / 2 * 300 + 100\n" +
            "when e.level = '中级' and e.price >= 40000 and datediff(curdate(), e.entry_time) >183 \n" +
            "and floor(datediff(curdate(), e.entry_time) / 183) % 2 = 1 \n" +
            "then 3700 + 1000 + (floor(datediff(curdate(), e.entry_time) / 183) - 1) / 2 * 300 + 100\n" +
            "when e.level = '高级' and e.price >= 40000 and datediff(curdate(), e.entry_time) >183 \n" +
            "and floor(datediff(curdate(), e.entry_time) / 183) % 2 = 1 \n" +
            "then 4000 + 1000 + (floor(datediff(curdate(), e.entry_time) / 183) - 1) / 2 * 300 + 100\n" +
            "else 0\n" +
            "end\n" +
            "where s.name = e.name")
    public void basicWageUpdate();  //计算基本工资标准

    @Update("update salary_statistics s,newest_employee_list e\n" +
            "set s.dispatch_subsidy_standard = e.salary - s.basic_wage_standard\n" +
            "where s.name = e.name")
    public void dispatchSubsidyUpdate(); //计算外派补贴标准

    @Update("update salary_statistics \n" +
            "set basic_wage = basic_wage_standard / total_day * positive_day + \n" +
            "basic_wage_standard / total_day * trial_day * trial_discount")
    public void basicUpdate();  //计算员工基本工资

    @Update("update newest_employee_list e,salary_statistics s set s.overtime_salary = case\n" +
            "\twhen e.client like '%太平%' and trial_discount = 0 then e.salary / 22 * s.overtime_hours\n" +
            "\twhen e.client like '%招行卡' and trial_discount = 0 then 50 * s.overtime_hours\n" +
            "\twhen e.client like '%招行' and trial_discount = 0 then 60 * s.overtime_hours\n" +
            "\twhen e.client like '%重庆小雨点%' and trial_discount = 0 then e.salary / s.total_day / 8 * s.overtime_hours *0.92\n" +
            "\twhen e.client like '%太平%' and trial_discount != 0 then e.salary / 22 * s.overtime_hours\n" +
            "\twhen e.client like '%招行卡' and trial_discount != 0 then 50 * s.overtime_hours\n" +
            "\twhen e.client like '%招行' and trial_discount != 0 then 60 * s.overtime_hours\n" +
            "\twhen e.client like '%重庆小雨点%' and trial_discount != 0 then e.salary * trial_discount / s.total_day / 8 * s.overtime_hours *0.92\n" +
            "\telse 0\n" +
            "\tend\n" +
            "\twhere s.name = e.name")
    public void overtimeUpdate(); //计算员工加班工资

    @Update("update salary_statistics set dispatch_subsidy = dispatch_subsidy_standard / total_day * positive_dispatch_day +\n" +
            "dispatch_subsidy_standard / total_day * trial_dispatch_day * trial_discount,\n" +
            "total_income = basic_wage + dispatch_subsidy + overtime_salary + performance + meal_supplement + other_thing + computer_subsidy,\n" +
            "pre_tax_income = total_income - advance_salary - provident_fund - social_security_person - late")
    public void dispatchUpdate(); //计算员工外派补贴,工资总计,税前总计

    @Update("UPDATE salary_statistics\n" +
            "SET person_income = \n" +
            "\tcase when pre_tax_income between 1 and 5000 then 0\n" +
            "\t\twhen pre_tax_income between 5001 and 8000 and (pre_tax_income - 5000 - special_deduction_amount) * 0.03 >=0\n" +
            "\t\tthen (pre_tax_income - 5000 - special_deduction_amount) * 0.03\n" +
            "\t\twhen pre_tax_income between 8001 and 17000 and (pre_tax_income - 5000 - special_deduction_amount) * 0.1 - 210>=0\n" +
            "\t\tthen (pre_tax_income - 5000 - special_deduction_amount) * 0.1 - 210\n" +
            "\t\twhen pre_tax_income between 17001 and 30000 and (pre_tax_income - 5000 - special_deduction_amount) * 0.2 - 1410>=0\n" +
            "\t\tthen (pre_tax_income - 5000 - special_deduction_amount) * 0.2 - 1410\n" +
            "\t\twhen pre_tax_income between 5001 and 8000 and (pre_tax_income - 5000 - special_deduction_amount) * 0.03 <0\n" +
            "\t\tthen 0\n" +
            "\t\twhen pre_tax_income between 8001 and 17000 and (pre_tax_income - 5000 - special_deduction_amount) * 0.1 - 210<0\n" +
            "\t\tand pre_tax_income - 5000 - special_deduction_amount > 0\n" +
            "\t\tthen (pre_tax_income - 5000 - special_deduction_amount) * 0.03\n" +
            "\t\twhen pre_tax_income between 17001 and 30000 and (pre_tax_income - 5000 - special_deduction_amount) * 0.2 - 1410<0\n" +
            "\t\tand pre_tax_income - 5000 - special_deduction_amount > 0\n" +
            "\t\tthen (pre_tax_income - 5000 - special_deduction_amount) * 0.1 - 210\n" +
            "\t\twhen pre_tax_income between 8001 and 17000 and pre_tax_income - 5000 - special_deduction_amount < 0\n" +
            "\t\tthen 0\n" +
            "\t\telse 0\n" +
            "\tend")
    public void taxUpdate(); //计算个税

    @Update("update salary_statistics \n" +
            "set actual_salary = pre_tax_income - person_income")
    public void incomeUpdate();  //计算实际收入

    @Update("update salary_statistics\n" +
            "set total_salary = floor(actual_salary)")
    public void totalUpdate(); //应发工资取整

    @Update("update salary_statistics s,newest_employee_list e\n" +
            "set s.year_holiday = \n" +
            "(select \n" +
            "case when floor(DATEDIFF(date_format(now(),'%Y-12-31'),e.entry_time) /365  * 5) < 5\n" +
            "\t\t\tthen floor(DATEDIFF(date_format(now(),'%Y-12-31'),e.entry_time) /365  * 5)\n" +
            "\t\t\twhen floor(DATEDIFF(date_format(now(),'%Y-12-31'),e.entry_time) /365  * 5) >= 5\n" +
            "\t\t\tthen 5\n" +
            "\t\t\telse 0\n" +
            "\t\t\tend\n" +
            "where s.name = e.name)\n" +
            "where s.name = e.name and s.id =#{id}")
    public void holidayUpdate(String id);  //更新年假

    @Update("update salary_statistics\n" +
            "set year_holiday = year_holiday - 0.5\n" +
            "where id = #{id}")
    public void minisHoliday(String id); //年假减0.5

    @Delete("DELETE from salary_statistics")
    public void deleteName(); //覆盖原始数据

    @Select("select *\n" +
            "from newest_employee_list\n" +
            "where name = (\n" +
            "select name\n" +
            "from salary_statistics\n" +
            "where id = #{id})")
    public List<NewestEmployee> queryBankCard(String id); //根据工资对应人名查找人员的id

    @Select("select s.name,s.total_salary,e.bank_card\n" +
            "from salary_statistics s,newest_employee_list e\n" +
            "where s.name = e.name and s.id = #{list}")
    public List<SalaryStatistics>  queryAllClassIndex(List<SalaryStatistics> list);

}
