package org.jeecg.modules.mayanfan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: ma
 * @Author: jeecg-boot
 * @Date:   2019-08-27
 * @Version: V1.0
 */
@Data
@TableName("training_score")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="training_score对象", description="ma")
public class Ma {

	/**训练成绩*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "训练成绩")
	private java.lang.String id;
	/**运动员id*/
	@Excel(name = "运动员id", width = 15)
    @ApiModelProperty(value = "运动员id")
	private java.lang.String sportsmanId;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
	private java.lang.String name;
	/**训练日期*/
	@Excel(name = "训练日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
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
	/**距离*/
	@Excel(name = "距离", width = 15)
    @ApiModelProperty(value = "距离")
	private java.lang.String distance;
	/**组数*/
	@Excel(name = "组数", width = 15)
    @ApiModelProperty(value = "组数")
	private java.lang.String groupNo;
	/**教练*/
	@Excel(name = "教练", width = 15)
    @ApiModelProperty(value = "教练")
	private java.lang.String coachName;
	/**成绩*/
	@Excel(name = "成绩", width = 15)
    @ApiModelProperty(value = "成绩")
	private java.math.BigDecimal trainingScore;
	/**分*/
	@Excel(name = "分", width = 15)
    @ApiModelProperty(value = "分")
	private java.lang.Integer minute;
	/**秒*/
	@Excel(name = "秒", width = 15)
    @ApiModelProperty(value = "秒")
	private java.lang.Integer second;
	/**毫秒*/
	@Excel(name = "毫秒", width = 15)
    @ApiModelProperty(value = "毫秒")
	private java.lang.Integer miniSecond;
	/**辅助道具*/
	@Excel(name = "辅助道具", width = 15)
    @ApiModelProperty(value = "辅助道具")
	private java.lang.String supportItem;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
	private java.util.Date createTime;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private java.lang.String updateBy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
	private java.util.Date updateTime;
}
