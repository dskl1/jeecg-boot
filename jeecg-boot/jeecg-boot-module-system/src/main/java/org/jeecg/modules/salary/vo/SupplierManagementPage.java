package org.jeecg.modules.salary.vo;

import java.util.List;
import org.jeecg.modules.salary.entity.SupplierManagement;
import org.jeecg.modules.salary.entity.SupplierManagementMember;
import lombok.Data;
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
 * @Description: 供应商管理
 * @Author: jeecg-boot
 * @Date:   2021-02-23
 * @Version: V1.0
 */
@Data
@ApiModel(value="supplier_managementPage对象", description="供应商管理")
public class SupplierManagementPage {

	/**供应商管理*/
	@ApiModelProperty(value = "供应商管理")
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

	@ExcelCollection(name="人员名单")
	@ApiModelProperty(value = "人员名单")
	private List<SupplierManagementMember> supplierManagementMemberList;

}
