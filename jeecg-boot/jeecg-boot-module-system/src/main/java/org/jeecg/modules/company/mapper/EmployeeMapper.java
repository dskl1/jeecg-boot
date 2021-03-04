package org.jeecg.modules.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.jeecg.modules.company.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 清单
 * @Author: jeecg-boot
 * @Date: 2020-03-17
 * @Version: V1.0
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    @Delete("delete from employee_list")
    public void deleteAll(); //删除表中所有数据

    @Update("update employee_list\n" +
            "set salary = salary + raise_salary\n" +
            "where raise_date = now()")
    public void updateRaise(); //调薪加到工资里

    @Delete("delete from billing_report\n" +
            "where sportsman_id not in (select id\n" +
            "from employee_list)")
    public void sportsmanDelete();  //删除子表外键不存在的数据

    @Delete("DELETE from employee_list \n" +
            "WHERE id in(\n" +
            "select a.id\n" +
            "from (\n" +
            "select *,min(create_time) \n" +
            "from employee_list \n" +
            "group by name\n" +
            "having count(name) > 1)a)")
    public void deleteName(); //名字重复覆盖原始数据

}
