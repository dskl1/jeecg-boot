package org.jeecg.modules.sportsman.vo;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import org.jeecg.modules.sportsman.entity.SportsmanPersonalArchives;
import org.jeecg.modules.sportsman.entity.SportsmanRank;
import org.jeecg.modules.sportsman.entity.SportsmanResume;
import org.jeecg.modules.sportsman.entity.SportsmanFamilySituation;
import org.jeecg.modules.sportsman.entity.SportsmanData;
import org.jeecg.modules.sportsman.entity.DevelopSituation;
import org.jeecg.modules.sportsman.entity.BodyType;
import org.jeecg.modules.sportsman.entity.BodyAbility;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class SportsmanPersonalArchivesPage {

	/**主键ID*/
	private String id;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
	private String name;
	/**运动项目*/
	@Excel(name = "运动项目", width = 15)
	private String sportItem;
	/**父亲身高(CM)*/
	@Excel(name = "父亲身高(CM)", width = 15)
	private Integer fatherHeight;
	/**母亲身高(CM)*/
	@Excel(name = "母亲身高(CM)", width = 15)
	private Integer motherHeight;
	/**直系亲属中有无遗传疾病*/
	@Excel(name = "直系亲属中有无遗传疾病", width = 15)
	private String linealRelativeGeneticDiseases;
	/**直系亲属中有无突出身高者*/
	@Excel(name = "直系亲属中有无突出身高者", width = 15)
	private String linealRelativeHeightProminent;
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
	private java.util.Date entryTeamDate;
	/**开始训练时间*/
  	@Excel(name = "开始训练时间", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date attendTrainTime;
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
	/**退队日期*/
	@Excel(name = "退队日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date trainingDate;
	/**系统时间*/
	@Excel(name = "系统时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date newTime;
	/**创建人*/
  	@Excel(name = "创建人", width = 15)
	private String createBy;
	/**创建时间*/
  	@Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**修改人*/
  	@Excel(name = "修改人", width = 15)
	private String updateBy;
	/**修改时间*/
  	@Excel(name = "修改时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;
	/** 部门编码 */
	@ApiModelProperty(value = "部门编码")
	private java.lang.String sysOrgCode;

	@ExcelCollection(name="运动员等级")
	private List<SportsmanRank> sportsmanRankList;
	@ExcelCollection(name="运动员训练简历")
	private List<SportsmanResume> sportsmanResumeList;
	@ExcelCollection(name="运动员家庭成员情况")
	private List<SportsmanFamilySituation> sportsmanFamilySituationList;
	@ExcelCollection(name="个人资料")
	private List<SportsmanData> sportsmanDataList;
	@ExcelCollection(name="历年机能发育情况")
	private List<DevelopSituation> developSituationList;
	@ExcelCollection(name="身体形态指标")
	private List<BodyType> bodyTypeList;
	@ExcelCollection(name="身体素质")
	private List<BodyAbility> bodyAbilityList;

}
