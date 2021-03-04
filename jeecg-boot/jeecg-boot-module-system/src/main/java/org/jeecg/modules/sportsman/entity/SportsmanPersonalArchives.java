package org.jeecg.modules.sportsman.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 档案管理
 * @author： jeecg-boot
 * @date：   2019-06-20
 * @version： V1.0
 */
@Data
@TableName("sportsman_registration_entry")
public class SportsmanPersonalArchives implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键ID*/
	@TableId(type = IdType.UUID)
	private String id;
	/**姓名*/
	private String name;
	/**性别*/
	private String sex;
	/**出生日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	/**入队后拟选专项*/
	private String afterSportItem;
	/**开始训练时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date attendTrainTime;
	/**出生地*/
	private String birthplace;
	/**身份证号码*/
	private String cardNumber;
	/**教练*/
	private String coachName;
	/**创建人*/
	private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**文化程度*/
	private String educationalLevel;
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
	/**年级*/
	private String grade;
	/**有何伤病史*/
	private String injuryHistory;
	/**直系亲属中有无遗传疾病*/
	private String linealRelativeGeneticDiseases;
	/**直系亲属中有无突出身高者*/
	private String linealRelativeHeightProminent;
	/**母亲身高(CM)*/
	private Integer motherHeight;
	/**民族*/
	private String nation;
	/**籍贯*/
	private String nativePlace;
	/**时间段*/
	private String period;
	/**政治面貌*/
	private String politicsStatus;
	/**状态*/
	private String retire;
	/**运动项目*/
	private String sportItem;
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
	/**修改人*/
	private String updateBy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
}
