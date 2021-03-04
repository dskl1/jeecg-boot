package org.jeecg.modules.company.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 账单
 * @Author: jeecg-boot
 * @Date: 2020-04-02
 * @Version: V1.0
 */
@Data
@TableName("billing_report")
public class NewestBillingReport implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 比赛成绩
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;
    /**
     * 运动员id
     */
    private String sportsmanId;
    /**
     * 岗位
     */
//    @Excel(name = "岗位", width = 15)
    private String job;
    /**
     * 开票编号
     */
    @Excel(name = "开票编号", width = 15)
    private String invoiceId;
    /**
     * 开票时间
     */
    @Excel(name = "开票时间", width = 20, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date invoiceTime;
    /**
     * 入场时间
     */
//    @Excel(name = "入场时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date enterTime;
    /**
     * 离场时间
     */
//    @Excel(name = "离场时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date awayTime;
    /**
     * 客户方
     */
//    @Excel(name = "客户方", width = 15)
    private String client;
    /**
     * (客户方)结算月份
     */
    @Excel(name = "(客户方)结算月份", width = 15)
    private String billingMonth;
    /**
     * 开票金额
     */
    @Excel(name = "开票金额", width = 15)
    private java.math.BigDecimal billingPrice;
    /**
     * 回款日期
     */
    @Excel(name = "回款日期", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date paymentMonth;
    /**
     * 是否分包
     */
    @Excel(name = "是否分包", width = 15)
    private String isSubcontract;
    /**
     * 分包单价
     */
    @Excel(name = "分包单价", width = 15)
    private java.math.BigDecimal subcontractPrice;
    /**
     * 分包公司
     */
    @Excel(name = "分包公司", width = 15)
    private String subcontractingCompany;
    /**
     * (分包公司)开票编号
     */
    @Excel(name = "(分包公司)开票编号", width = 15)
    private String billingId;
    /**
     * 开票日期(分包公司)
     */
    @Excel(name = "开票日期(分包公司)", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date invoicingDay;
    /**
     * 开票月份(分包公司)
     */
    @Excel(name = "结算月份(分包公司)", width = 15)
    private String isInvoicing;
    /**
     * 开票金额(分包公司)
     */
    @Excel(name = "开票金额(分包公司)", width = 15)
    private java.math.BigDecimal payment;
    /**
     * 回款日期(分包公司)
     */
    @Excel(name = "回款日期(分包公司)", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date billingDate;
    /**
     * 备注
     */
    @Excel(name = "备注", width = 15)
    private String note;
    /**
     * 创建人
     */
//    @Excel(name = "创建人", width = 15)
    private String createBy;
    /**
     * 创建时间
     */
//    @Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createTime;
    /**
     * 修改人
     */
//    @Excel(name = "修改人", width = 15)
    private String updateBy;
    /**
     * 修改时间
     */
//    @Excel(name = "修改时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date updateTime;
    /**
     * 所属部门编码
     */
//    @Excel(name = "所属部门编码", width = 15)
    private String sysOrgCode;
}
