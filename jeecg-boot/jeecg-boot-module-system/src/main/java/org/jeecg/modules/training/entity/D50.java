package org.jeecg.modules.training.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.jeecg.common.aspect.annotation.Dict;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 50
 * @Author: jeecg-boot
 * @Date:   2020-05-20
 * @Version: V1.0
 */
@Data
@TableName("d50")
@ApiModel(value="training_score对象", description="training_score")
public class D50 implements Serializable {
    private static final long serialVersionUID = 1L;

	/**50*/
	@TableId(type = IdType.ID_WORKER_STR)
	@ApiModelProperty(value = "50")
	private java.lang.String id;
	/**运动员id*/
	@ApiModelProperty(value = "运动员id")
	private java.lang.String sportsmanId;
	/**训练日期*/
	@Excel(name = "训练日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "训练日期")
	private java.util.Date trainingDate;
	/**时间段*/
	@Excel(name = "时间段", width = 15)
	@ApiModelProperty(value = "时间段")
	private java.lang.String period;
	/**训练小组*/
	@Excel(name = "训练小组", width = 15)
	@ApiModelProperty(value = "训练小组")
	private java.lang.String trainingGroupId;
	/**训练序号*/
	@Excel(name = "训练序号", width = 15)
	@ApiModelProperty(value = "训练序号")
	private java.lang.String trainingItemNo;
	/**训练项目*/
	@Excel(name = "训练项目", width = 15)
	@ApiModelProperty(value = "训练项目")
	private java.lang.String trainingItem;
	/**组数*/
	@Excel(name = "组数", width = 15)
	@ApiModelProperty(value = "组数")
	private java.lang.String groupNo;
	/**教练*/
	@Excel(name = "教练", width = 15)
	@ApiModelProperty(value = "教练")
	private java.lang.String coachName;
	/**是否高原*/
	@Excel(name = "是否高原", width = 15)
	@ApiModelProperty(value = "是否高原")
	private java.lang.String isHighland;
	/**包干时间*/
	@Excel(name = "包干时间", width = 15)
	@ApiModelProperty(value = "包干时间")
	private java.lang.String baoGan;
	/**趟数*/
	@Excel(name = "趟数", width = 15)
	@ApiModelProperty(value = "趟数")
	private java.lang.String tangShu;
	/**辅助道具*/
	@Excel(name = "辅助道具", width = 15)
	@ApiModelProperty(value = "辅助道具")
	private java.lang.String supportItem;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "创建时间")
	private java.util.Date createTime;
	/**修改人*/
	@ApiModelProperty(value = "修改人")
	private java.lang.String updateBy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "修改时间")
	private java.util.Date updateTime;
	/**所属部门编码*/
	@ApiModelProperty(value = "所属部门编码")
	private java.lang.String sysOrgCode;
}
