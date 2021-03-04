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
 * @Description: 首页展示
 * @Author: jeecg-boot
 * @Date:   2021-01-21
 * @Version: V1.0
 */
@Data
@TableName("head_display")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="head_display对象", description="首页展示")
public class HeadDisplay implements Serializable {
    private static final long serialVersionUID = 1L;

	/**首页展示*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "首页展示")
    private String id;
	/**未回款*/
	@Excel(name = "未回款", width = 15)
    @ApiModelProperty(value = "未回款")
    private BigDecimal noRepay;
	/**未开票*/
	@Excel(name = "未开票", width = 15)
    @ApiModelProperty(value = "未开票")
    private BigDecimal noInvoice;
	/**呆账*/
	@Excel(name = "呆账", width = 15)
    @ApiModelProperty(value = "呆账")
    private BigDecimal longNoRepay;
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
}
