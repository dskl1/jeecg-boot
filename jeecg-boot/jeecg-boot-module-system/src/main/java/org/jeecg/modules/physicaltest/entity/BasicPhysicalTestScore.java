package org.jeecg.modules.physicaltest.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.jeecg.common.aspect.annotation.Dict;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 基础体能评分标准
 * @Author: jeecg-boot
 * @Date:   2020-05-12
 * @Version: V1.0
 */
@Data
@TableName("basic_physical_test_score")
@ApiModel(value="sportsman_registration_entry对象", description="体能测试")
public class BasicPhysicalTestScore implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
	@ApiModelProperty(value = "id")
	private java.lang.String id;
	/**外键*/
	@ApiModelProperty(value = "外键")
	private java.lang.String sportsmanId;
	/**性别*/
	@Dict(dicCode = "sex")
	@ApiModelProperty(value = "性别")
	private java.lang.String sex;
	/**BM1*/
	@Excel(name = "BM1", width = 15)
	@ApiModelProperty(value = "BM1")
	private java.lang.Double bm1;
	/**坐位体前屈(cm)*/
	@Excel(name = "坐位体前屈(cm)", width = 15)
	@ApiModelProperty(value = "坐位体前屈(cm)")
	private java.lang.Integer sittingForward;
	/**30米冲刺(s)*/
	@Excel(name = "30米冲刺(s)", width = 15)
	@ApiModelProperty(value = "30米冲刺(s)")
	private java.lang.Double sprint;
	/**垂直纵跳(cm)*/
	@Excel(name = "垂直纵跳(cm)", width = 15)
	@ApiModelProperty(value = "垂直纵跳(cm)")
	private java.lang.Integer verticalJump;
	/**深蹲相对力量*/
	@Excel(name = "深蹲相对力量", width = 15)
	@ApiModelProperty(value = "深蹲相对力量")
	private java.lang.Double squatRelativeStrength;
	/**卧推相对力量*/
	@Excel(name = "卧推相对力量", width = 15)
	@ApiModelProperty(value = "卧推相对力量")
	private java.lang.Double benchPressRelativeStrength;
	/**引体向上(次)*/
	@Excel(name = "引体向上(次)", width = 15)
	@ApiModelProperty(value = "引体向上(次)")
	private java.lang.Integer pullUps;
	/**腹肌耐力(s)*/
	@Excel(name = "腹肌耐力(s)", width = 15)
	@ApiModelProperty(value = "腹肌耐力(s)")
	private java.lang.Integer absEndurance;
	/**背肌耐力(s)*/
	@Excel(name = "背肌耐力(s)", width = 15)
	@ApiModelProperty(value = "背肌耐力(s)")
	private java.lang.Integer dorsalMuscleEndurance;
	/**3000m(分:秒)*/
	@Excel(name = "3000m(分:秒)", width = 15)
	@ApiModelProperty(value = "3000m(分:秒)")
	private java.lang.Integer meter;
	/**总分*/
	@Excel(name = "总分", width = 15)
	@ApiModelProperty(value = "总分")
	private java.lang.Double counter;
	/**性别*/
	@Dict(dicCode = "sex")
	@ApiModelProperty(value = "性别")
	private java.lang.String sexs;
	/**测试时间*/
	@Excel(name = "测试时间", width = 20, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "测试时间")
	private java.util.Date testDate;
	/**BM1*/
	@ApiModelProperty(value = "BM1")
	private java.lang.Double bm1s;
	/**坐位体前屈(cm)*/
	@ApiModelProperty(value = "坐位体前屈(cm)")
	private java.lang.Integer sittingForwards;
	/**30米冲刺(s)*/
	@ApiModelProperty(value = "30米冲刺(s)")
	private java.lang.Double sprints;
	/**垂直纵跳(cm)*/
	@ApiModelProperty(value = "垂直纵跳(cm)")
	private java.lang.Integer verticalJumps;
	/**深蹲相对力量*/
	@ApiModelProperty(value = "深蹲相对力量")
	private java.lang.Double squatRelativeStrengths;
	/**卧推相对力量*/
	@ApiModelProperty(value = "卧推相对力量")
	private java.lang.Double benchPressRelativeStrengths;
	/**引体向上(次)*/
	@ApiModelProperty(value = "引体向上(次)")
	private java.lang.Integer pullUpss;
	/**腹肌耐力(s)*/
	@ApiModelProperty(value = "腹肌耐力(s)")
	private java.lang.Integer absEndurances;
	/**背肌耐力(s)*/
	@ApiModelProperty(value = "背肌耐力(s)")
	private java.lang.Integer dorsalMuscleEndurances;
	/**3000m(分:秒)*/
	@ApiModelProperty(value = "3000m(分:秒)")
	private java.lang.Integer meters;
	/**总分*/
	@ApiModelProperty(value = "总分")
	private java.lang.Double counters;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "创建日期")
	private java.util.Date createTime;
	/**更新人*/
	@ApiModelProperty(value = "更新人")
	private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "更新日期")
	private java.util.Date updateTime;
	/**所属部门*/
	@ApiModelProperty(value = "所属部门")
	private java.lang.String sysOrgCode;

    public String getSportsmanId() {
        return sportsmanId;
    }
	public String getSex() {
		return sex;
	}
	public Double getBm1() {
		return bm1;
	}
	public Integer getSittingForward() {
		return sittingForward;
	}
	public Double getSprint() {
		return sprint;
	}
	public Integer getVerticalJump() { return verticalJump;}
	public Double getSquatRelativeStrength() {
		return squatRelativeStrength;
	}
	public Double getBenchPressRelativeStrength() {
		return benchPressRelativeStrength;
	}
	public Integer getPullUps() {
		return pullUps;
	}
	public Integer getAbsEndurance() {
		return absEndurance;
	}
	public Integer getDorsalMuscleEndurance() {
		return dorsalMuscleEndurance;
	}
    public Integer getMeter() {
        return meter;
    }
    public Double getCounter() {
        return counter;
    }
}
