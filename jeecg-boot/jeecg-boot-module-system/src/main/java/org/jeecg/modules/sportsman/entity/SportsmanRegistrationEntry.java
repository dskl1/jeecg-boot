package org.jeecg.modules.sportsman.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 队员登记
 * @author： jeecg-boot
 * @date：   2019-06-04
 * @version： V1.0
 */
@Data
@TableName("sportsman_registration_entry")
public class SportsmanRegistrationEntry implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键ID*/
	@TableId(type = IdType.UUID)
	private String id;
	/***/
	private String sportsmanId;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
	private String name;
	/**头像*/
	@Excel(name = "照片", width = 15)
	private String avatar;
	/**运动项目*/
	@Excel(name = "运动项目", width = 15)
	private String sportItem;
	/**父亲身高(CM)*/
	@Excel(name = "父亲身高(CM)", width = 15)
	private Integer fatherHeight;
	/**母亲身高(CM)*/
	@Excel(name = "母亲身高(CM)", width = 15)
	private Integer motherHeight;
	/**直系亲属中有无突出身高者*/
	@Excel(name = "直系亲属中有无突出身高者", width = 15)
	private String linealRelativeHeightProminent;
	/**直系亲属中有无遗传疾病*/
	@Excel(name = "直系亲属中有无遗传疾病", width = 15)
	private String linealRelativeGeneticDiseases;
	/**民族*/
	@Excel(name = "民族", width = 15)
	private String nation;
	/**籍贯*/
	@Excel(name = "籍贯", width = 15)
	private String nativePlace;
	/**出生地*/
	@Excel(name = "出生地", width = 15)
	private String birthplace;
	/**文化程度*/
	@Excel(name = "文化程度", width = 15)
	private String educationalLevel;
	/**政治面貌*/
	@Excel(name = "政治面貌", width = 15)
	private String politicsStatus;
	/**原学校*/
	@Excel(name = "原学校", width = 15)
	private String formerSchool;
	/**原教练*/
	@Excel(name = "原教练", width = 15)
	private String formerCoach;
	/**入队(校)时间*/
	@Excel(name = "入队(校)时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date entryTeamDate;
	/**开始训练时间*/
	@Excel(name = "开始训练时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date attendTrainTime;
	/**入队前专项*/
	@Excel(name = "入队前专项", width = 15)
	private String formerSportItem;
	/**入队后拟选专项*/
	@Excel(name = "入队后拟选专项", width = 15)
	private String afterSportItem;
	/**有何伤病史*/
	@Excel(name = "有何伤病史", width = 15)
	private String injuryHistory;
	/**身份证号码*/
	@Excel(name = "身份证号码", width = 15)
	private String cardNumber;
	/**状态*/
	@Excel(name = "状态", width = 15)
	private String retire;
	/**训练小组*/
	@Excel(name = "训练小组", width = 15)
	private String trainingGroupId;
	/**辅助道具*/
	private String supportItem;
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
	/** 部门编码 */
	@ApiModelProperty(value = "部门编码")
	private java.lang.String sysOrgCode;
}
