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
 * @Description: 未开票
 * @Author: jeecg-boot
 * @Date:   2021-01-18
 * @Version: V1.0
 */
@Data
@TableName("no_invoice")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="no_invoice对象", description="未开票")
public class NoInvoice implements Serializable {
    private static final long serialVersionUID = 1L;

	/**未开票*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "未开票")
    private String id;
	/**人员*/
	@Excel(name = "人员", width = 15)
    @ApiModelProperty(value = "人员")
    private String invoiceMember;
	/**开票日期*/
	@Excel(name = "开票日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开票日期")
    private Date invoiceDate;
	/**预计开票额*/
	@Excel(name = "预计开票额", width = 15)
    @ApiModelProperty(value = "预计开票额")
    private BigDecimal forecastInvoiceAmount;
    /**开票编号*/
    @Excel(name = "开票编号", width = 15)
    @ApiModelProperty(value = "开票编号")
    private String invoiceId;
    /**实际开票额*/
    @Excel(name = "实际开票额", width = 15)
    @ApiModelProperty(value = "实际开票额")
    private BigDecimal realInvoiceAmount;
	/**状态*/
    @Excel(name = "状态", width = 15,replace={"已开票_1","未开票_0"})
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
