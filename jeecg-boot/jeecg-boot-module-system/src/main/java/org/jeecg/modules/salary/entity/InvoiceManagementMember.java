package org.jeecg.modules.salary.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 对应人员
 * @Author: jeecg-boot
 * @Date:   2020-09-03
 * @Version: V1.0
 */
@ApiModel(value="invoice_management对象", description="开票管理")
@Data
@TableName("invoice_management_member")
public class InvoiceManagementMember implements Serializable {
    private static final long serialVersionUID = 1L;

	/**对应人员*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "对应人员")
	private String id;
	/**外键*/
	@ApiModelProperty(value = "外键")
	private String sportsmanId;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
	@ApiModelProperty(value = "姓名")
	private String name;
	/**结算月份*/
	@Excel(name = "结算月份", width = 15)
	@ApiModelProperty(value = "结算月份")
	private String billingMonth;
	/**结算日期*/
	@Excel(name = "结算日期", width = 15)
	@ApiModelProperty(value = "结算日期")
	private String invoiceDate;
	/**开票额*/
	@Excel(name = "开票额", width = 15)
	@ApiModelProperty(value = "开票额")
	private java.math.BigDecimal invoiceAmount;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**修改人*/
	@ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "修改时间")
	private Date updateTime;
	/**所属部门编码*/
	@ApiModelProperty(value = "所属部门编码")
	private String sysOrgCode;
}
