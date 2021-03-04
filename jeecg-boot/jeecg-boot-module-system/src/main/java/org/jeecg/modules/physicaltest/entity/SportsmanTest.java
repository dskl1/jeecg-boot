package org.jeecg.modules.physicaltest.entity;

import java.io.Serializable;
import java.util.Date;
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

/**
 * @Description: 体能测试
 * @Author: jeecg-boot
 * @Date:   2020-05-12
 * @Version: V1.0
 */
@Data
@TableName("sportsman_registration_entry")
@ApiModel(value="sportsman_registration_entry对象", description="体能测试")
public class SportsmanTest implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键ID*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "主键ID")
    private java.lang.String id;
	/**运动员id*/
    @ApiModelProperty(value = "运动员id")
    private java.lang.String sportsmanId;
	/**姓名*/
    @Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
    private java.lang.String name;
    /**
     * 性别（1：男 0：女）
     */
    @Dict(dicCode = "sex")
    @Excel(name = "性别", width = 15)
    @ApiModelProperty(value = "性别")
    private java.lang.String sex;
	/**照片*/
    @ApiModelProperty(value = "照片")
    private java.lang.String avatar;
	/**运动项目*/
    @ApiModelProperty(value = "运动项目")
    private java.lang.String sportItem;
	/**父亲身高(CM)*/
    @ApiModelProperty(value = "父亲身高(CM)")
    private java.lang.Integer fatherHeight;
	/**母亲身高(CM)*/
    @ApiModelProperty(value = "母亲身高(CM)")
    private java.lang.Integer motherHeight;
	/**直系亲属中有无突出身高者*/
    @ApiModelProperty(value = "直系亲属中有无突出身高者")
    private java.lang.String linealRelativeHeightProminent;
	/**直系亲属中有无遗传疾病*/
    @ApiModelProperty(value = "直系亲属中有无遗传疾病")
    private java.lang.String linealRelativeGeneticDiseases;
	/**年级*/
    @ApiModelProperty(value = "年级")
    private java.lang.String grade;
	/**学期*/
    @ApiModelProperty(value = "学期")
    private java.lang.String term;
	/**语文*/
    @ApiModelProperty(value = "语文")
    private java.lang.Integer language;
	/**数学*/
    @ApiModelProperty(value = "数学")
    private java.lang.Integer math;
	/**英语*/
    @ApiModelProperty(value = "英语")
    private java.lang.Integer english;
	/**生物*/
    @ApiModelProperty(value = "生物")
    private java.lang.Integer biology;
	/**地理*/
    @ApiModelProperty(value = "地理")
    private java.lang.Integer geography;
	/**化学*/
    @ApiModelProperty(value = "化学")
    private java.lang.Integer chemistry;
	/**物理*/
    @ApiModelProperty(value = "物理")
    private java.lang.Integer physics;
	/**政治*/
    @ApiModelProperty(value = "政治")
    private java.lang.Integer politics;
	/**历史*/
    @ApiModelProperty(value = "历史")
    private java.lang.Integer history;
	/**民族*/
    @ApiModelProperty(value = "民族")
    private java.lang.String nation;
	/**籍贯*/
    @ApiModelProperty(value = "籍贯")
    private java.lang.String nativePlace;
	/**出生日期*/
    @ApiModelProperty(value = "出生日期")
    private java.lang.String birthday;
	/**出生地*/
    @ApiModelProperty(value = "出生地")
    private java.lang.String birthplace;
	/**文化程度*/
    @ApiModelProperty(value = "文化程度")
    private java.lang.String educationalLevel;
	/**政治面貌*/
    @ApiModelProperty(value = "政治面貌")
    private java.lang.String politicsStatus;
	/**原学校*/
    @ApiModelProperty(value = "原学校")
    private java.lang.String formerSchool;
	/**原教练*/
    @ApiModelProperty(value = "原教练")
    private java.lang.String formerCoach;
	/**入队(校)时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "入队(校)时间")
    private java.util.Date entryTeamDate;
	/**开始训练时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开始训练时间")
    private java.util.Date attendTrainTime;
	/**入队前专项*/
    @ApiModelProperty(value = "入队前专项")
    private java.lang.String formerSportItem;
	/**入队后拟选专项*/
    @ApiModelProperty(value = "入队后拟选专项")
    private java.lang.String afterSportItem;
	/**有何伤病史*/
    @ApiModelProperty(value = "有何伤病史")
    private java.lang.String injuryHistory;
	/**身份证号码*/
    @ApiModelProperty(value = "身份证号码")
    private java.lang.String cardNumber;
	/**状态*/
    @ApiModelProperty(value = "状态")
    private java.lang.String retire;
	/**阶段*/
    @ApiModelProperty(value = "阶段")
    private java.lang.String stage;
	/**训练日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "训练日期")
    private java.util.Date trainingDate;
	/**训练时间*/
    @ApiModelProperty(value = "训练时间")
    private java.lang.String trainingDate1;
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
    @ApiModelProperty(value = "成绩")
    private java.lang.String trainingScore;
	/**辅助道具*/
    @ApiModelProperty(value = "辅助道具")
    private java.lang.String supportItem;
	/**系统时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "系统时间")
    private java.util.Date newTime;
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
