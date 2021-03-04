package org.jeecg.modules.salary.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 成本管理
 * @Author: jeecg-boot
 * @Date: 2020-08-13
 * @Version: V1.0
 */
@Data
@TableName("cost_management")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "cost_management对象", description = "成本管理")
public class CostManagement implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 成本管理
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "成本管理")
    private String id;
    /**
     * 姓名
     */
    @Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
    private String name;
    /**
     * 开票日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "开票日期")
    private Date invoiceDate;
    /**
     * 开票额
     */
    @Excel(name = "开票额", width = 15)
    @ApiModelProperty(value = "开票额")
    private BigDecimal invoiceAmount;
    /**
     * 个人所得税
     */
    @Excel(name = "个人所得税", width = 15)
    @ApiModelProperty(value = "个人所得税")
    private BigDecimal personIncome;
    /**
     * 税前工资
     */
    @Excel(name = "税前工资", width = 15)
    @ApiModelProperty(value = "税前工资")
    private BigDecimal preTaxIncome;
    /**
     * 社保(公司)
     */
    @Excel(name = "社保(公司)", width = 15)
    @ApiModelProperty(value = "社保(公司)")
    private BigDecimal socialSecurityCo;
    /**
     * 公积金
     */
    @Excel(name = "公积金", width = 15)
    @ApiModelProperty(value = "公积金")
    private BigDecimal providentFund;
    /**
     * 资金成本
     */
    @Excel(name = "资金成本", width = 15)
    @ApiModelProperty(value = "资金成本")
    private BigDecimal capitalCost;
    /**
     * 管理成本
     */
    @Excel(name = "管理成本", width = 15)
    @ApiModelProperty(value = "管理成本")
    private BigDecimal managementCost;
    /**
     * 报销
     */
    @Excel(name = "报销", width = 15)
    @ApiModelProperty(value = "报销")
    private BigDecimal reimbursement;
    /**
     * 其他
     */
    @Excel(name = "其他", width = 15)
    @ApiModelProperty(value = "其他")
    private BigDecimal otherThing;
    /**
     * 利润
     */
    @Excel(name = "利润", width = 15)
    @ApiModelProperty(value = "利润")
    private BigDecimal profit;
    /**
     * 全部报销
     */
    @Excel(name = "全部报销", width = 15)
    @ApiModelProperty(value = "全部报销")
    private BigDecimal totalReimbursement;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createBy;
    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private String updateBy;
    /**
     * 修改时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
    /**
     * 所属部门编码
     */
    @ApiModelProperty(value = "所属部门编码")
    private String sysOrgCode;

    public String getName() {
        return name;
    }

    public BigDecimal getManagementCost() {
        return managementCost;
    }

    public BigDecimal getReimbursement() {
        return reimbursement;
    }

    public BigDecimal getTotalReimbursement() {
        return totalReimbursement;
    }

    public String getId() {
        return id;
    }
}
