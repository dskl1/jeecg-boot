package org.jeecg.modules.training.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 一周训练内容
 * @Author: jeecg-boot
 * @Date:   2020-06-17
 * @Version: V1.0
 */
@Data
@TableName("week_plan")
@ApiModel(value="week_plan对象", description="一周训练内容")
public class WeekPlan implements Serializable {
    private static final long serialVersionUID = 1L;

	/**一周计划*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "一周计划")
    private String id;
	/**用户id*/
    @Excel(name = "用户id", width = 15)
    @ApiModelProperty(value = "用户id")
    private String userId;
	/**计划id*/
    @Excel(name = "计划id", width = 15)
    @ApiModelProperty(value = "计划id")
    private String sportsmanId;
	/**训练日期*/
    @Excel(name = "训练日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "训练日期")
    private Date trainingDate;
	/**训练时间*/
    @Excel(name = "训练时间", width = 15)
    @ApiModelProperty(value = "训练时间")
    @Dict(dicCode = "training_date")
    private String trainingDate1;
	/**时间段*/
    @Excel(name = "时间段", width = 15)
    @ApiModelProperty(value = "时间段")
    private String period;
	/**训练小组*/
    @Excel(name = "训练小组", width = 15)
    @ApiModelProperty(value = "训练小组")
    private String trainingGroupId;
	/**训练序号*/
    @Excel(name = "训练序号", width = 15)
    @ApiModelProperty(value = "训练序号")
    private String trainingItemNo;
	/**训练项目*/
    @Excel(name = "训练项目", width = 15)
    @ApiModelProperty(value = "训练项目")
    private String trainingItem;
	/**距离*/
    @Excel(name = "距离", width = 15)
    @ApiModelProperty(value = "距离")
    private String distance;
	/**组数*/
    @Excel(name = "组数", width = 15)
    @ApiModelProperty(value = "组数")
    private String groupNo;
	/**是否高原*/
    @Excel(name = "是否高原", width = 15)
    @ApiModelProperty(value = "是否高原")
    private String isHighland;
	/**教练*/
    @Excel(name = "教练", width = 15)
    @ApiModelProperty(value = "教练")
    private String coachName;
	/**包干时间*/
    @Excel(name = "包干时间", width = 15)
    @ApiModelProperty(value = "包干时间")
    private String baoGan;
	/**趟数*/
    @Excel(name = "趟数", width = 15)
    @ApiModelProperty(value = "趟数")
    private String tangShu;
	/**辅助道具*/
    @Excel(name = "辅助道具", width = 15)
    @ApiModelProperty(value = "辅助道具")
    private String supportItem;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd  HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd  HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
	/**修改人*/
    @ApiModelProperty(value = "修改人")
    private String updateBy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd  HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd  HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
	/**所属部门编码*/
    @ApiModelProperty(value = "所属部门编码")
    private String sysOrgCode;
}
