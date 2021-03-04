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
 * @Description: 开票统计
 * @Author: jeecg-boot
 * @Date: 2020-09-28
 * @Version: V1.0
 */
@Data
@TableName("invoicing_statistics")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "invoicing_statistics对象", description = "开票统计")
public class InvoicingStatistics implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 开票统计
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "开票统计")
    private String id;
    /**
     * 姓名
     */
    @Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
    private String name;
    /**
     * 入场时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "入场时间")
    private Date enterTime;
    /**
     * 出勤天数
     */
    @Excel(name = "出勤天数", width = 15)
    @ApiModelProperty(value = "出勤天数")
    private BigDecimal totalDay;
    /**
     * 单价
     */
    @Excel(name = "单价", width = 15, isStatistics = true)
    @ApiModelProperty(value = "单价")
    private BigDecimal price;
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
}
