package org.jeecg.modules.statistics.vo;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import org.jeecg.modules.statistics.entity.Statistics;
import org.jeecg.modules.statistics.entity.D25a;
import org.jeecg.modules.statistics.entity.D50a;
import org.jeecg.modules.statistics.entity.D100a;
import org.jeecg.modules.statistics.entity.D150a;
import org.jeecg.modules.statistics.entity.D200a;
import org.jeecg.modules.statistics.entity.D250a;
import org.jeecg.modules.statistics.entity.D300a;
import org.jeecg.modules.statistics.entity.D350a;
import org.jeecg.modules.statistics.entity.D400a;
import org.jeecg.modules.statistics.entity.D800a;
import org.jeecg.modules.statistics.entity.D1000a;
import org.jeecg.modules.statistics.entity.D1500a;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class StatisticsPage {

	/**入队后拟选专项*/
  	@Excel(name = "入队后拟选专项", width = 15)
	private String afterSportItem;
	/**开始训练时间*/
  	@Excel(name = "开始训练时间", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date attendTrainTime;
	/**照片*/
  	@Excel(name = "照片", width = 15)
	private String avatar;
	/**生物*/
  	@Excel(name = "生物", width = 15)
	private Integer biology;
	/**出生日期*/
  	@Excel(name = "出生日期", width = 15)
	private String birthday;
	/**出生地*/
  	@Excel(name = "出生地", width = 15)
	private String birthplace;
	/**身份证号码*/
  	@Excel(name = "身份证号码", width = 15)
	private String cardNumber;
	/**化学*/
  	@Excel(name = "化学", width = 15)
	private Integer chemistry;
	/**教练*/
  	@Excel(name = "教练", width = 15)
	private String coachName;
	/**创建人*/
  	@Excel(name = "创建人", width = 15)
	private String createBy;
	/**创建时间*/
  	@Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**距离*/
  	@Excel(name = "距离", width = 15)
	private String distance;
	/**文化程度*/
  	@Excel(name = "文化程度", width = 15)
	private String educationalLevel;
	/**英语*/
  	@Excel(name = "英语", width = 15)
	private Integer english;
	/**入队(校)时间*/
  	@Excel(name = "入队(校)时间", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date entryTeamDate;
	/**父亲身高(CM)*/
  	@Excel(name = "父亲身高(CM)", width = 15)
	private Integer fatherHeight;
	/**原教练*/
  	@Excel(name = "原教练", width = 15)
	private String formerCoach;
	/**原学校*/
  	@Excel(name = "原学校", width = 15)
	private String formerSchool;
	/**入队前专项*/
  	@Excel(name = "入队前专项", width = 15)
	private String formerSportItem;
	/**地理*/
  	@Excel(name = "地理", width = 15)
	private Integer geography;
	/**年级*/
  	@Excel(name = "年级", width = 15)
	private String grade;
	/**组数*/
  	@Excel(name = "组数", width = 15)
	private String groupNo;
	/**历史*/
  	@Excel(name = "历史", width = 15)
	private Integer history;
	/**主键ID*/
	private String id;
	/**有何伤病史*/
  	@Excel(name = "有何伤病史", width = 15)
	private String injuryHistory;
	/**语文*/
  	@Excel(name = "语文", width = 15)
	private Integer language;
	/**直系亲属中有无遗传疾病*/
  	@Excel(name = "直系亲属中有无遗传疾病", width = 15)
	private String linealRelativeGeneticDiseases;
	/**直系亲属中有无突出身高者*/
  	@Excel(name = "直系亲属中有无突出身高者", width = 15)
	private String linealRelativeHeightProminent;
	/**数学*/
  	@Excel(name = "数学", width = 15)
	private Integer math;
	/**母亲身高(CM)*/
  	@Excel(name = "母亲身高(CM)", width = 15)
	private Integer motherHeight;
	/**姓名*/
  	@Excel(name = "姓名", width = 15)
	private String name;
	/**民族*/
  	@Excel(name = "民族", width = 15)
	private String nation;
	/**籍贯*/
  	@Excel(name = "籍贯", width = 15)
	private String nativePlace;
	/**系统时间*/
	private java.util.Date newTime;
	/**时间段*/
  	@Excel(name = "时间段", width = 15)
	private String period;
	/**物理*/
  	@Excel(name = "物理", width = 15)
	private Integer physics;
	/**政治*/
  	@Excel(name = "政治", width = 15)
	private Integer politics;
	/**政治面貌*/
  	@Excel(name = "政治面貌", width = 15)
	private String politicsStatus;
	/**状态*/
  	@Excel(name = "状态", width = 15)
	private String retire;
	/**性别*/
  	@Excel(name = "性别", width = 15)
	private String sex;
	/**运动项目*/
  	@Excel(name = "运动项目", width = 15)
	private String sportItem;
	/**运动员id*/
  	@Excel(name = "运动员id", width = 15)
	private String sportsmanId;
	/**阶段*/
  	@Excel(name = "阶段", width = 15)
	private String stage;
	/**辅助道具*/
  	@Excel(name = "辅助道具", width = 15)
	private String supportItem;
	/**学期*/
  	@Excel(name = "学期", width = 15)
	private String term;
	/**训练日期*/
  	@Excel(name = "训练日期", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date trainingDate;
	/**训练时间*/
  	@Excel(name = "训练时间", width = 15)
	private String trainingDate1;
	/**训练小组*/
  	@Excel(name = "训练小组", width = 15)
	private String trainingGroupId;
	/**训练项目*/
  	@Excel(name = "训练项目", width = 15)
	private String trainingItem;
	/**训练序号*/
  	@Excel(name = "训练序号", width = 15)
	private String trainingItemNo;
	/**成绩*/
  	@Excel(name = "成绩", width = 15)
	private String trainingScore;
	/**修改人*/
  	@Excel(name = "修改人", width = 15)
	private String updateBy;
	/**修改时间*/
  	@Excel(name = "修改时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;
	/**所属部门*/
	@ApiModelProperty(value = "所属部门")
	private String sysOrgCode;

	@ExcelCollection(name="25")
	private List<D25a> d25aList;
	@ExcelCollection(name="50")
	private List<D50a> d50aList;
	@ExcelCollection(name="100")
	private List<D100a> d100aList;
	@ExcelCollection(name="150")
	private List<D150a> d150aList;
	@ExcelCollection(name="200")
	private List<D200a> d200aList;
	@ExcelCollection(name="250")
	private List<D250a> d250aList;
	@ExcelCollection(name="300")
	private List<D300a> d300aList;
	@ExcelCollection(name="350")
	private List<D350a> d350aList;
	@ExcelCollection(name="400")
	private List<D400a> d400aList;
	@ExcelCollection(name="800")
	private List<D800a> d800aList;
	@ExcelCollection(name="1000")
	private List<D1000a> d1000aList;
	@ExcelCollection(name="1500")
	private List<D1500a> d1500aList;

}
