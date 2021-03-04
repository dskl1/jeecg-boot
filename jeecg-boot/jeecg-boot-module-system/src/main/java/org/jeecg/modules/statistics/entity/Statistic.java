package org.jeecg.modules.statistics.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 比赛报表
 * @Author: jeecg-boot
 * @Date:   2019-09-18
 * @Version: V1.0
 */
@Data
@TableName("sportsman_registration_entry")
public class Statistic implements Serializable {
    private static final long serialVersionUID = 1L;

	/**入队后拟选专项*/
	private String afterSportItem;
	/**开始训练时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date attendTrainTime;
	/**照片*/
	private String avatar;
	/**生物*/
	private Integer biology;
	/**出生日期*/
	private String birthday;
	/**出生地*/
	private String birthplace;
	/**身份证号码*/
	private String cardNumber;
	/**化学*/
	private Integer chemistry;
	/**教练*/
	private String coachName;
	/**创建人*/
	private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**距离*/
	private String distance;
	/**文化程度*/
	private String educationalLevel;
	/**英语*/
	private Integer english;
	/**入队(校)时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date entryTeamDate;
	/**父亲身高(CM)*/
	private Integer fatherHeight;
	/**原教练*/
	private String formerCoach;
	/**原学校*/
	private String formerSchool;
	/**入队前专项*/
	private String formerSportItem;
	/**地理*/
	private Integer geography;
	/**年级*/
	private String grade;
	/**组数*/
	private String groupNo;
	/**历史*/
	private Integer history;
	/**主键ID*/
	@TableId(type = IdType.UUID)
	private String id;
	/**有何伤病史*/
	private String injuryHistory;
	/**语文*/
	private Integer language;
	/**直系亲属中有无遗传疾病*/
	private String linealRelativeGeneticDiseases;
	/**直系亲属中有无突出身高者*/
	private String linealRelativeHeightProminent;
	/**数学*/
	private Integer math;
	/**母亲身高(CM)*/
	private Integer motherHeight;
	/**姓名*/
	private String name;
	/**民族*/
	private String nation;
	/**籍贯*/
	private String nativePlace;
	/**系统时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date newTime;
	/**时间段*/
	private String period;
	/**物理*/
	private Integer physics;
	/**政治*/
	private Integer politics;
	/**政治面貌*/
	private String politicsStatus;
	/**状态*/
	private String retire;
	/**性别*/
	private String sex;
	/**运动项目*/
	private String sportItem;
	/**运动员id*/
	private String sportsmanId;
	/**阶段*/
	private String stage;
	/**辅助道具*/
	private String supportItem;
	/**学期*/
	private String term;
	/**训练日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date trainingDate;
	/**训练时间*/
	private String trainingDate1;
	/**训练小组*/
	private String trainingGroupId;
	/**训练项目*/
	private String trainingItem;
	/**训练序号*/
	private String trainingItemNo;
	/**成绩*/
	private String trainingScore;
	/**修改人*/
	private String updateBy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	/**所属部门*/
	@ApiModelProperty(value = "所属部门")
	private String sysOrgCode;
}
