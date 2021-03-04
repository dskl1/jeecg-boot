package org.jeecg.modules.salary.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 历史工资
 * @Author: jeecg-boot
 * @Date: 2020-08-18
 * @Version: V1.0
 */
@Data
@TableName("salary_statistics_copy1")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "salary_statistics_copy1对象", description = "历史工资")
public class SalaryStatisticsCopy1 implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 工资统计
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "工资统计")
    private java.lang.String id;
    /**
     * 姓名
     */
    @Excel(name = "姓名", width = 10)
    @ApiModelProperty(value = "姓名")
    private java.lang.String name;
    /**
     * 电子邮件
     */
    @Excel(name = "电子邮件", width = 10)
    @ApiModelProperty(value = "电子邮件")
    private java.lang.String email;
    /**
     * 基本工资标准
     */
    @Excel(name = "基本工资标准", width = 10)
    @ApiModelProperty(value = "基本工资标准")
    private java.math.BigDecimal basicWageStandard;
    /**
     * 外派补贴标准
     */
    @Excel(name = "外派补贴标准", width = 10)
    @ApiModelProperty(value = "外派补贴标准")
    private java.math.BigDecimal dispatchSubsidyStandard;
    /**
     * 应出勤天数
     */
    @Excel(name = "应出勤天数", width = 10)
    @ApiModelProperty(value = "应出勤天数")
    private java.math.BigDecimal totalDay;
    /**
     * 转正天数
     */
    @Excel(name = "转正天数", width = 10)
    @ApiModelProperty(value = "转正天数")
    private java.lang.Double positiveDay;
    /**
     * 转正外派天数
     */
    @Excel(name = "转正外派天数", width = 10)
    @ApiModelProperty(value = "转正外派天数")
    private java.lang.Double positiveDispatchDay;
    /**
     * 试用天数
     */
    @Excel(name = "试用天数", width = 10)
    @ApiModelProperty(value = "试用天数")
    private java.lang.Double trialDay;
    /**
     * 试用外派天数
     */
    @Excel(name = "试用外派天数", width = 10)
    @ApiModelProperty(value = "试用外派天数")
    private java.lang.Double trialDispatchDay;
    /**
     * 试用折扣
     */
    @Excel(name = "试用折扣", width = 10)
    @ApiModelProperty(value = "试用折扣")
    private java.lang.Double trialDiscount;
    /**
     * 请假折扣
     */
    @Excel(name = "请假折扣", width = 10)
    @ApiModelProperty(value = "请假折扣")
    private java.lang.Double absenceDiscount;
    /**
     * 请假理由
     */
    @Excel(name = "请假理由", width = 10)
    @ApiModelProperty(value = "请假理由")
    private java.lang.String absenceReason;
    /**
     * 加班时间
     */
    @Excel(name = "加班时间", width = 10)
    @ApiModelProperty(value = "加班时间")
    private java.lang.Double overtimeHours;
    /**
     * 基本工资
     */
    @Excel(name = "基本工资", width = 10, groupName = "工资明细")
    @ApiModelProperty(value = "基本工资")
    private java.math.BigDecimal basicWage;
    /**
     * 外派补贴
     */
    @Excel(name = "外派补贴", width = 10, groupName = "工资明细")
    @ApiModelProperty(value = "外派补贴")
    private java.math.BigDecimal dispatchSubsidy;
    /**
     * 年假
     */
    @Excel(name = "年假", width = 10)
    @ApiModelProperty(value = "年假")
    private java.math.BigDecimal yearHoliday;
    /**
     * 加班工资
     */
    @Excel(name = "加班工资", width = 10, groupName = "工资明细")
    @ApiModelProperty(value = "加班工资")
    private java.math.BigDecimal overtimeSalary;
    /**
     * 绩效
     */
    @Excel(name = "绩效", width = 10, groupName = "工资明细")
    @ApiModelProperty(value = "绩效")
    private java.math.BigDecimal performance;
    /**
     * 餐补
     */
    @Excel(name = "餐补", width = 10, groupName = "工资明细")
    @ApiModelProperty(value = "餐补")
    private java.math.BigDecimal mealSupplement;
    /**
     * 其他
     */
    @Excel(name = "其他", width = 10, groupName = "工资明细")
    @ApiModelProperty(value = "其他")
    private java.math.BigDecimal otherThing;
    /**
     * 电脑补贴
     */
    @Excel(name = "电脑补贴", width = 10, groupName = "工资明细")
    @ApiModelProperty(value = "电脑补贴")
    private java.math.BigDecimal computerSubsidy;
    /**
     * 预支工资
     */
    @Excel(name = "预支工资", width = 10, groupName = "扣除项")
    @ApiModelProperty(value = "预支工资")
    private java.math.BigDecimal advanceSalary;
    /**
     * 公积金
     */
    @Excel(name = "公积金", width = 10, groupName = "扣除项")
    @ApiModelProperty(value = "公积金")
    private java.math.BigDecimal providentFund;
    /**
     * 社保
     */
    @Excel(name = "社保", width = 10, groupName = "扣除项")
    @ApiModelProperty(value = "社保")
    private java.math.BigDecimal socialSecurityPerson;
    /**
     * 迟到
     */
    @Excel(name = "迟到", width = 10, groupName = "扣除项")
    @ApiModelProperty(value = "迟到")
    private java.math.BigDecimal late;
    /**
     * 社保(公司)
     */
    @ApiModelProperty(value = "社保(公司)")
    private java.math.BigDecimal socialSecurityCo;
    /**
     * 工资总计
     */
    @Excel(name = "工资总计", width = 10)
    @ApiModelProperty(value = "工资总计")
    private java.math.BigDecimal totalIncome;
    /**
     * 税前总计
     */
    @Excel(name = "税前总计", width = 10)
    @ApiModelProperty(value = "税前总计")
    private java.math.BigDecimal preTaxIncome;
    /**
     * 专项抵扣项目
     */
    @Excel(name = "专项抵扣项目", width = 10)
    @ApiModelProperty(value = "专项抵扣项目")
    private java.lang.String specialDeductionItems;
    /**
     * 专项抵扣金额
     */
    @Excel(name = "专项抵扣金额", width = 10)
    @ApiModelProperty(value = "专项抵扣金额")
    private java.math.BigDecimal specialDeductionAmount;
    /**
     * 申请时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "申请时间")
    private java.util.Date applicationDate;
    /**
     * 到期时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "到期时间")
    private java.util.Date expireDate;
    /**
     * 个人所得税
     */
    @Excel(name = "个人所得税", width = 10)
    @ApiModelProperty(value = "个人所得税")
    private java.math.BigDecimal personIncome;
    /**
     * 应发
     */
    @Excel(name = "应发", width = 10)
    @ApiModelProperty(value = "应发")
    private java.math.BigDecimal actualSalary;
    /**
     * 总计
     */
    @Excel(name = "总计", width = 10)
    @ApiModelProperty(value = "总计")
    private java.math.BigDecimal totalSalary;
    /**
     * 通知
     */
    @Excel(name = "通知", width = 20)
    @ApiModelProperty(value = "通知")
    private java.lang.String notice;
    /**
     * 图片路径
     */
    @Excel(name = "图片路径", width = 20)
    @ApiModelProperty(value = "图片路径")
    private java.lang.String imgPath;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private java.util.Date createTime;
    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private java.lang.String updateBy;
    /**
     * 修改时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
    private java.util.Date updateTime;
    /**
     * 所属部门编码
     */
    @ApiModelProperty(value = "所属部门编码")
    private java.lang.String sysOrgCode;
}
