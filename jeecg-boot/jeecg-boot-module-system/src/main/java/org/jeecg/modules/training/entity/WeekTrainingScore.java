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
 * @Description: 训练人员名单
 * @Author: jeecg-boot
 * @Date:   2020-06-17
 * @Version: V1.0
 */
@Data
@TableName("training_score")
@ApiModel(value="week_plan对象", description="一周训练内容")
public class WeekTrainingScore implements Serializable {
    private static final long serialVersionUID = 1L;

	/**训练成绩*/
	@TableId(type = IdType.ID_WORKER_STR)
	@ApiModelProperty(value = "训练成绩")
	private String id;
	/**运动员id*/
	@ApiModelProperty(value = "运动员id")
	private String sportsmanId;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
	@ApiModelProperty(value = "姓名")
	private String name;
	/**训练日期*/
	@Excel(name = "训练日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "训练日期")
	private Date trainingDate;
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
	/**教练*/
	@Excel(name = "教练", width = 15)
	@ApiModelProperty(value = "教练")
	private String coachName;
	/**训练标记*/
	@ApiModelProperty(value = "训练标记")
	private Integer trainingFlag;
	/**辅助道具*/
	@Excel(name = "辅助道具", width = 15)
	@ApiModelProperty(value = "辅助道具")
	private String supportItem;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
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
	/**秒*/
	@Excel(name = "秒", width = 15)
	@ApiModelProperty(value = "秒")
	private Integer second;
	/**毫秒*/
	@Excel(name = "毫秒", width = 15)
	@ApiModelProperty(value = "毫秒")
	private Integer miniSecond;
	/**所属部门*/
	@ApiModelProperty(value = "所属部门")
	private String sysOrgCode;
}
