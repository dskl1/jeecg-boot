package org.jeecg.modules.salary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.jeecg.modules.salary.entity.InvoicingStatistics;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.salary.entity.SalaryStatisticsCopy1;

/**
 * @Description: 开票统计
 * @Author: jeecg-boot
 * @Date:   2020-09-28
 * @Version: V1.0
 */
public interface InvoicingStatisticsMapper extends BaseMapper<InvoicingStatistics> {

    @Select("select *\n" +
            "from invoicing_statistics\n" +
            "where DATE_FORMAT(create_time,\"%Y-%m\") = #{format}")
    List<InvoicingStatistics> selectByMonth(String format);

    @Insert("insert into invoicing_statistics\n" +
            "SELECT\n" +
            "\tUUID() AS `id`,\n" +
            "\t`e`.`name` AS `name`,\n" +
            "\t`e`.`enter_time` AS `enter_time`,\n" +
            "\t`s`.`total_day` AS `total_day`,\n" +
            "\t`e`.`price` AS `price`,\n" +
            "\t`s`.`create_by` AS `create_by`,\n" +
            "\tnow() AS `create_time`,\n" +
            "\t`s`.`update_by` AS `update_by`,\n" +
            "\t`s`.`update_time` AS `update_time`,\n" +
            "\t`s`.`sys_org_code` AS `sys_org_code` \n" +
            "FROM\n" +
            "\t( `newest_employee_list` `e` JOIN `salary_statistics` `s` ) \n" +
            "WHERE\n" +
            "\t( `e`.`name` = `s`.`name` ) UNION ALL\n" +
            "SELECT\n" +
            "\tUUID() AS `Name_exp_11`,\n" +
            "\t'合计' AS `合计`,\n" +
            "\t'1970-01-01' AS `Name_exp_13`,\n" +
            "\t'0' AS `Name_exp_14`,\n" +
            "\tsum( `newest_employee_list`.`price` ) AS `sum(price)`,\n" +
            "\t'' AS `Name_exp_16`,\n" +
            "\tnow() AS `Name_exp_17`,\n" +
            "\t'' AS `Name_exp_18`,\n" +
            "\t'1970-01-01' AS `Name_exp_19`,\n" +
            "\tsys_org_code AS `Name_exp_20` \n" +
            "FROM\n" +
            "\t`newest_employee_list`")
    public void isInsert(); //插入开票记录

}
