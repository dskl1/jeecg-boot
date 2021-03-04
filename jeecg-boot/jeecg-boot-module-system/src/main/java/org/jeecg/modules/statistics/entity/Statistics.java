package org.jeecg.modules.statistics.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 训练报表
 * @author： jeecg-boot
 * @date：   2019-07-15
 * @version： V1.0
 */
@Data
@TableName("training_score_copy1")
@ApiModel(value="training_score对象", description="队员表")
public class Statistics implements Serializable {
	private static final long serialVersionUID = 1L;

	/**训练成绩*/
	@TableId(type = IdType.ID_WORKER_STR)
	@ApiModelProperty(value = "训练成绩")
	private java.lang.String id;
	/**运动员id*/
	@ApiModelProperty(value = "运动员id")
	private java.lang.String sportsmanId;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
	@ApiModelProperty(value = "姓名")
	private java.lang.String name;
	/**训练日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "训练日期")
	private java.util.Date trainingDate;
	/**时间段*/
	@ApiModelProperty(value = "时间段")
	private java.lang.String period;
	/**训练小组*/
	@ApiModelProperty(value = "训练小组")
	private java.lang.String trainingGroupId;
	/**训练序号*/
	@ApiModelProperty(value = "训练序号")
	private java.lang.String trainingItemNo;
	/**训练项目*/
	@ApiModelProperty(value = "训练项目")
	private java.lang.String trainingItem;
	/**距离*/
	@ApiModelProperty(value = "距离")
	private java.lang.String distance;
	/**组数*/
	@ApiModelProperty(value = "组数")
	private java.lang.String groupNo;
	/**教练*/
	@ApiModelProperty(value = "教练")
	private java.lang.String coachName;
	/**成绩*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "成绩")
	private java.util.Date trainingScore;
	/**辅助道具*/
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
	/**秒*/
	@ApiModelProperty(value = "秒")
	private java.lang.Integer second;
	/**毫秒*/
	@ApiModelProperty(value = "毫秒")
	private java.lang.Integer miniSecond;
	/**所属部门*/
	@ApiModelProperty(value = "所属部门")
	private java.lang.String sysOrgCode;
}
