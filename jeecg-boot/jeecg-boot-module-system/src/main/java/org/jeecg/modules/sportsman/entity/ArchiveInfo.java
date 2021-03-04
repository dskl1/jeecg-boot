package org.jeecg.modules.sportsman.entity;

import java.io.Serializable;
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
 * @Description: 归档信息
 * @Author: jeecg-boot
 * @Date:   2020-01-16
 * @Version: V1.0
 */
@Data
@TableName("sportsman_registration_entry")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="sportsman_registration_entry对象", description="归档信息")
public class ArchiveInfo {

	/**入队后拟选专项*/
	@Excel(name = "入队后拟选专项", width = 15)
    @ApiModelProperty(value = "入队后拟选专项")
	private String afterSportItem;
	/**开始训练时间*/
	@Excel(name = "开始训练时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开始训练时间")
	private Date attendTrainTime;
	/**照片*/
	@Excel(name = "照片", width = 15)
    @ApiModelProperty(value = "照片")
	private String avatar;
	/**生物*/
	@Excel(name = "生物", width = 15)
    @ApiModelProperty(value = "生物")
	private Integer biology;
	/**出生日期*/
	@Excel(name = "出生日期", width = 15)
    @ApiModelProperty(value = "出生日期")
	private String birthday;
	/**出生地*/
	@Excel(name = "出生地", width = 15)
    @ApiModelProperty(value = "出生地")
	private String birthplace;
	/**身份证号码*/
	@Excel(name = "身份证号码", width = 15)
    @ApiModelProperty(value = "身份证号码")
	private String cardNumber;
	/**化学*/
	@Excel(name = "化学", width = 15)
    @ApiModelProperty(value = "化学")
	private Integer chemistry;
	/**教练*/
	@Excel(name = "教练", width = 15)
    @ApiModelProperty(value = "教练")
	private String coachName;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**距离*/
	@Excel(name = "距离", width = 15)
    @ApiModelProperty(value = "距离")
	private String distance;
	/**文化程度*/
	@Excel(name = "文化程度", width = 15)
    @ApiModelProperty(value = "文化程度")
	private String educationalLevel;
	/**英语*/
	@Excel(name = "英语", width = 15)
    @ApiModelProperty(value = "英语")
	private Integer english;
	/**入队(校)时间*/
	@Excel(name = "入队(校)时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "入队(校)时间")
	private Date entryTeamDate;
	/**父亲身高(CM)*/
	@Excel(name = "父亲身高(CM)", width = 15)
    @ApiModelProperty(value = "父亲身高(CM)")
	private Integer fatherHeight;
	/**原教练*/
	@Excel(name = "原教练", width = 15)
    @ApiModelProperty(value = "原教练")
	private String formerCoach;
	/**原学校*/
	@Excel(name = "原学校", width = 15)
    @ApiModelProperty(value = "原学校")
	private String formerSchool;
	/**入队前专项*/
	@Excel(name = "入队前专项", width = 15)
    @ApiModelProperty(value = "入队前专项")
	private String formerSportItem;
	/**地理*/
	@Excel(name = "地理", width = 15)
    @ApiModelProperty(value = "地理")
	private Integer geography;
	/**年级*/
	@Excel(name = "年级", width = 15)
    @ApiModelProperty(value = "年级")
	private String grade;
	/**组数*/
	@Excel(name = "组数", width = 15)
    @ApiModelProperty(value = "组数")
	private String groupNo;
	/**历史*/
	@Excel(name = "历史", width = 15)
    @ApiModelProperty(value = "历史")
	private Integer history;
	/**主键ID*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "主键ID")
	private String id;
	/**有何伤病史*/
	@Excel(name = "有何伤病史", width = 15)
    @ApiModelProperty(value = "有何伤病史")
	private String injuryHistory;
	/**语文*/
	@Excel(name = "语文", width = 15)
    @ApiModelProperty(value = "语文")
	private Integer language;
	/**直系亲属中有无遗传疾病*/
	@Excel(name = "直系亲属中有无遗传疾病", width = 15)
    @ApiModelProperty(value = "直系亲属中有无遗传疾病")
	private String linealRelativeGeneticDiseases;
	/**直系亲属中有无突出身高者*/
	@Excel(name = "直系亲属中有无突出身高者", width = 15)
    @ApiModelProperty(value = "直系亲属中有无突出身高者")
	private String linealRelativeHeightProminent;
	/**数学*/
	@Excel(name = "数学", width = 15)
    @ApiModelProperty(value = "数学")
	private Integer math;
	/**母亲身高(CM)*/
	@Excel(name = "母亲身高(CM)", width = 15)
    @ApiModelProperty(value = "母亲身高(CM)")
	private Integer motherHeight;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
	private String name;
	/**民族*/
	@Excel(name = "民族", width = 15)
    @ApiModelProperty(value = "民族")
	private String nation;
	/**籍贯*/
	@Excel(name = "籍贯", width = 15)
    @ApiModelProperty(value = "籍贯")
	private String nativePlace;
	/**系统时间*/
	@Excel(name = "系统时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "系统时间")
	private Date newTime;
	/**时间段*/
	@Excel(name = "时间段", width = 15)
    @ApiModelProperty(value = "时间段")
	private String period;
	/**物理*/
	@Excel(name = "物理", width = 15)
    @ApiModelProperty(value = "物理")
	private Integer physics;
	/**政治*/
	@Excel(name = "政治", width = 15)
    @ApiModelProperty(value = "政治")
	private Integer politics;
	/**政治面貌*/
	@Excel(name = "政治面貌", width = 15)
    @ApiModelProperty(value = "政治面貌")
	private String politicsStatus;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String retire;
	/**性别*/
	@Excel(name = "性别", width = 15)
    @ApiModelProperty(value = "性别")
	private String sex;
	/**运动项目*/
	@Excel(name = "运动项目", width = 15)
    @ApiModelProperty(value = "运动项目")
	private String sportItem;
	/**运动员id*/
	@Excel(name = "运动员id", width = 15)
    @ApiModelProperty(value = "运动员id")
	private String sportsmanId;
	/**阶段*/
	@Excel(name = "阶段", width = 15)
    @ApiModelProperty(value = "阶段")
	private String stage;
	/**辅助道具*/
	@Excel(name = "辅助道具", width = 15)
    @ApiModelProperty(value = "辅助道具")
	private String supportItem;
	/**所属部门编码*/
	@Excel(name = "所属部门编码", width = 15)
    @ApiModelProperty(value = "所属部门编码")
	private String sysOrgCode;
	/**学期*/
	@Excel(name = "学期", width = 15)
    @ApiModelProperty(value = "学期")
	private String term;
	/**训练日期*/
	@Excel(name = "训练日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "训练日期")
	private Date trainingDate;
	/**训练时间*/
	@Excel(name = "训练时间", width = 15)
    @ApiModelProperty(value = "训练时间")
	private String trainingDate1;
	/**训练小组*/
	@Excel(name = "训练小组", width = 15)
    @ApiModelProperty(value = "训练小组")
	private String trainingGroupId;
	/**训练项目*/
	@Excel(name = "训练项目", width = 15)
    @ApiModelProperty(value = "训练项目")
	private String trainingItem;
	/**训练序号*/
	@Excel(name = "训练序号", width = 15)
    @ApiModelProperty(value = "训练序号")
	private String trainingItemNo;
	/**成绩*/
	@Excel(name = "成绩", width = 15)
    @ApiModelProperty(value = "成绩")
	private String trainingScore;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;
}
