package org.jeecg.modules.salary.vo;

import java.util.List;
import org.jeecg.modules.salary.entity.InvoiceManagement;
import org.jeecg.modules.salary.entity.InvoiceManagementAttached;
import lombok.Data;
import org.jeecg.modules.salary.entity.InvoiceManagementMember;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 开票管理
 * @Author: jeecg-boot
 * @Date:   2020-08-31
 * @Version: V1.0
 */
@Data
@ApiModel(value="invoice_managementPage对象", description="开票管理")
public class InvoiceManagementPage {

	/**开票管理*/
	@ApiModelProperty(value = "开票管理")
	private String id;
	/**开票编号*/
	@Excel(name = "开票编号", width = 15)
	@ApiModelProperty(value = "开票编号")
	private String invoiceId;
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
	/**分包公司*/
	@Excel(name = "分包公司", width = 15)
	@ApiModelProperty(value = "分包公司")
	private String subcontractingCompany;
	/**总金额*/
	@Excel(name = "总金额", width = 15)
	@ApiModelProperty(value = "总金额")
	private java.math.BigDecimal totalAmount;
	/**状态*/
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

//	@ExcelCollection(name="附票")
	@ApiModelProperty(value = "附票")
	private List<InvoiceManagementAttached> invoiceManagementAttachedList;
	@ExcelCollection(name="对应人员")
	@ApiModelProperty(value = "对应人员")
	private List<InvoiceManagementMember> invoiceManagementMemberList;

}
