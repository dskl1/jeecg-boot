package org.jeecg.modules.salary.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.jeecg.modules.company.entity.NewestEmployee;
import org.jeecg.modules.salary.entity.CostManagement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Description: 成本管理
 * @Author: jeecg-boot
 * @Date: 2020-08-13
 * @Version: V1.0
 */
public interface CostManagementMapper extends BaseMapper<CostManagement> {

    @Insert("insert ignore into cost_management(id,name,person_income,pre_tax_income,provident_fund,\n" +
            "create_by,create_time,update_by,update_time,sys_org_code)\n" +
            "(\n" +
            "select id,name,person_income,pre_tax_income,provident_fund,\n" +
            "create_by,create_time,update_by,update_time,sys_org_code\n" +
            "from salary_statistics a\n" +
            "where create_time = (\n" +
            "select max(create_time)\n" +
            "from salary_statistics b\n" +
            "where a.name = b.name))")
    public void costInsert();  //成本管理插入姓名

    @Update("update cost_management\n" +
            "set capital_cost = (person_income + pre_tax_income + social_security_co \n" +
            "+ provident_fund + management_cost + reimbursement + other_thing) * 0.1")
    public void costUpdate(); //更新资金成本

    @Update("update cost_management set profit = \n" +
            "(invoice_amount - person_income - pre_tax_income - \n" +
            "social_security_co - provident_fund - capital_cost -\n" +
            "management_cost - reimbursement - other_thing) *0.9")
    public void profitUpdate(); //更新利润

    @Select("select c.name,c.reimbursement,e.bank_card\n" +
            "from cost_management c,newest_employee_list e\n" +
            "where c.name = e.name and c.id = #{list}")
    public List<CostManagement>  queryAllClassIndex(List<CostManagement> list);

    @Select("select *\n" +
            "from newest_employee_list\n" +
            "where name = (\n" +
            "select name\n" +
            "from cost_management\n" +
            "where id = #{id})")
    public List<NewestEmployee> queryBankCard(String id); //根据成本对应人名查找人员的id

    @Select("select sum(c.reimbursement) sum\n" +
            "from cost_management c,newest_employee_list e\n" +
            "where c.name = e.name;\n" +
            "select c.name,c.reimbursement,e.bank_card\n" +
            "from cost_management c,newest_employee_list e\n" +
            "where c.name = e.name")
    public void countReimbursement(); //选择的人的报销总计

    @Update("update cost_management\n" +
            "set reimbursement = floor(reimbursement)")
    public void intReimbursement(); //报销取整
}
