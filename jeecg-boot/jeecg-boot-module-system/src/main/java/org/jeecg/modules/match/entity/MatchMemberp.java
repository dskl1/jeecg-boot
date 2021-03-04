package org.jeecg.modules.match.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 运动员
 * @Author: jeecg-boot
 * @Date:   2019-08-19
 * @Version: V1.0
 */
@Data
@TableName("sportsman_registration_entry")
public class MatchMemberp implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键ID*/
	@TableId(type = IdType.UUID)
	private String id;
	/**运动员id*/
	private String sportsmanId;
	/**姓名*/
	private String name;
	/**性别*/
	private String sex;
	/**照片*/
	private String avatar;
	/**运动项目*/
	private String sportItem;
	/**父亲身高(CM)*/
	private Integer fatherHeight;
	/**母亲身高(CM)*/
	private Integer motherHeight;
	/**直系亲属中有无突出身高者*/
	private String linealRelativeHeightProminent;
	/**直系亲属中有无遗传疾病*/
	private String linealRelativeGeneticDiseases;
	/**年级*/
	private String grade;
	/**学期*/
	private String term;
	/**语文*/
	private Integer language;
	/**数学*/
	private Integer math;
	/**英语*/
	private Integer english;
	/**生物*/
	private Integer biology;
	/**地理*/
	private Integer geography;
	/**化学*/
	private Integer chemistry;
	/**物理*/
	private Integer physics;
	/**政治*/
	private Integer politics;
	/**历史*/
	private Integer history;
	/**民族*/
	private String nation;
	/**籍贯*/
	private String nativePlace;
	/**出生日期*/
	private String birthday;
	/**出生地*/
	private String birthplace;
	/**文化程度*/
	private String educationalLevel;
	/**政治面貌*/
	private String politicsStatus;
	/**原学校*/
	private String formerSchool;
	/**原教练*/
	private String formerCoach;
	/**入队(校)时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date entryTeamDate;
	/**开始训练时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date attendTrainTime;
	/**入队前专项*/
	private String formerSportItem;
	/**入队后拟选专项*/
	private String afterSportItem;
	/**有何伤病史*/
	private String injuryHistory;
	/**身份证号码*/
	private String cardNumber;
	/**状态*/
	private String retire;
	/**阶段*/
	private String stage;
	/**训练日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date trainingDate;
	/**训练时间*/
	private String trainingDate1;
	/**时间段*/
	private String period;
	/**训练小组*/
	private String trainingGroupId;
	/**训练序号*/
	private String trainingItemNo;
	/**训练项目*/
	private String trainingItem;
	/**距离*/
	private String distance;
	/**组数*/
	private String groupNo;
	/**教练*/
	private String coachName;
	/**成绩*/
	private String trainingScore;
	/**辅助道具*/
	private String supportItem;
	/**系统时间*/
	private Date newTime;
	/**创建人*/
	private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**修改人*/
	private String updateBy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
}
