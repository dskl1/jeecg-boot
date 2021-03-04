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
 * @Description: 附票
 * @Author: jeecg-boot
 * @Date:   2020-08-31
 * @Version: V1.0
 */
@ApiModel(value="invoice_management对象", description="开票管理")
@Data
@TableName("invoice_management_attached")
public class InvoiceManagementAttached implements Serializable {
    private static final long serialVersionUID = 1L;

	/**开票管理*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "开票管理")
	private String id;
	/**外键*/
	@ApiModelProperty(value = "外键")
	private String sportsmanId;
	/**开票编号*/
	@Excel(name = "开票编号", width = 15)
	@ApiModelProperty(value = "开票编号")
	private Integer invoiceId;
	/**开票日期*/
	@Excel(name = "开票日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "开票日期")
	private Date invoiceDate;
	/**开票额*/
	@Excel(name = "开票额", width = 15)
	@ApiModelProperty(value = "开票额")
	private java.math.BigDecimal invoiceAmount;
	/**客户方*/
	@Excel(name = "客户方", width = 15)
	@ApiModelProperty(value = "客户方")
	private String client;
	/**状态*/
	@Excel(name = "状态", width = 15)
	@ApiModelProperty(value = "状态")
	private String stage;
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

	public String getId() {
		return id;
	}
}
