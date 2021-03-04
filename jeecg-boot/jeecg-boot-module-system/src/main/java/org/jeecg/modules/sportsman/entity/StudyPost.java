package org.jeecg.modules.sportsman.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 科目
 * @Author: jeecg-boot
 * @Date:   2020-11-06
 * @Version: V1.0
 */
@ApiModel(value="sportsman_registration_entry对象", description="学习成绩")
@Data
@TableName("study_score")
public class StudyPost implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "id")
	private String id;
	/**运动员id*/
	@ApiModelProperty(value = "运动员id")
	private String sportsmanId;
	/**年级*/
	@Excel(name = "年级", width = 15)
	@ApiModelProperty(value = "年级")
	private String grade;
	/**学期*/
	@Excel(name = "学期", width = 15)
	@ApiModelProperty(value = "学期")
	private String term;
	/**语文*/
	@Excel(name = "语文", width = 15)
	@ApiModelProperty(value = "语文")
	private String chinese;
	/**数学*/
	@Excel(name = "数学", width = 15)
	@ApiModelProperty(value = "数学")
	private String math;
	/**英语*/
	@Excel(name = "英语", width = 15)
	@ApiModelProperty(value = "英语")
	private String english;
	/**生物*/
	@Excel(name = "生物", width = 15)
	@ApiModelProperty(value = "生物")
	private String biology;
	/**地理*/
	@Excel(name = "地理", width = 15)
	@ApiModelProperty(value = "地理")
	private String geography;
	/**化学*/
	@Excel(name = "化学", width = 15)
	@ApiModelProperty(value = "化学")
	private String chemistry;
	/**物理*/
	@Excel(name = "物理", width = 15)
	@ApiModelProperty(value = "物理")
	private String physics;
	/**政治*/
	@Excel(name = "政治", width = 15)
	@ApiModelProperty(value = "政治")
	private String politics;
	/**历史*/
	@Excel(name = "历史", width = 15)
	@ApiModelProperty(value = "历史")
	private String history;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**修改人*/
	@ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "修改时间")
	private Date updateTime;
	/**所属部门编码*/
	@ApiModelProperty(value = "所属部门编码")
	private String sysOrgCode;
}
