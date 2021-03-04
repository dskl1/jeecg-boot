package org.jeecg.modules.sale.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 销售情况
 * @Author: jeecg-boot
 * @Date: 2020-01-14
 * @Version: V1.0
 */
@Data
@TableName("sale_situation")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "sale_situation对象", description = "销售情况")
public class SaleSituation {

    /**
     * 主键ID
     */
    @TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "主键ID")
    private String id;
    /**
     * 商品
     */
    @Excel(name = "商品", width = 15)
    @ApiModelProperty(value = "商品")
    private String commodity;
    /**
     * 购入价格
     */
    @Excel(name = "购入价格", width = 15)
    @ApiModelProperty(value = "购入价格")
    private BigDecimal buyPrice;
    /**
     * 购入数量
     */
    @Excel(name = "购入数量", width = 15)
    @ApiModelProperty(value = "购入数量")
    private Integer sellNumber;
    /**
     * 卖出数量
     */
    @Excel(name = "卖出数量", width = 15)
    @ApiModelProperty(value = "卖出数量")
    private Integer buyNumber;
    /**
     * 卖出价格
     */
    @Excel(name = "卖出价格", width = 15)
    @ApiModelProperty(value = "卖出价格")
    private BigDecimal sellPrice;
    /**
     * 剩余数量
     */
    @Excel(name = "剩余数量", width = 15)
    @ApiModelProperty(value = "剩余数量")
    private Integer remainNumber;
    /**
     * 收益
     */
    @Excel(name = "收益", width = 15)
    @ApiModelProperty(value = "收益")
    private BigDecimal profit;
    /**
     * 创建人
     */
    @Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
    private String createBy;
    /**
     * 创建时间
     */
    @Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 修改人
     */
    @Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
    private String updateBy;
    /**
     * 修改时间
     */
    @Excel(name = "修改时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
    /**
     * 所属部门编码
     */
    @Excel(name = "所属部门编码", width = 15)
    @ApiModelProperty(value = "所属部门编码")
    private String sysOrgCode;
}
