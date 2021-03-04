package org.jeecg.modules.match.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 比赛成绩
 * @Author: jeecg-boot
 * @Date:   2019-08-07
 * @Version: V1.0
 */
@Data
@TableName("match_score_copy1")
public class MatchScore implements Serializable {
    private static final long serialVersionUID = 1L;

	/**比赛成绩*/
	@TableId(type = IdType.UUID)
	private String id;
	/**运动员id*/
	private String sportsmanId;
	/**姓名*/
    @Excel(name = "姓名", width = 15)
	private String name;
	/**比赛名称*/
    @Excel(name = "比赛名称", width = 15)
	private String matchName;
    /**比赛日期*/
    @Excel(name = "比赛日期", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date matchDate;
	/**距离*/
    @Excel(name = "距离", width = 15)
	private String distance;
	/**比赛项目*/
    @Excel(name = "比赛项目", width = 15)
	private String matchItem;
	/**成绩*/
    @Excel(name = "成绩", width = 15)
	private java.math.BigDecimal matchScore;
	/**场次*/
    @Excel(name = "场次", width = 15)
	private Integer changCi;
	/**项次*/
    @Excel(name = "项次", width = 15)
	private Integer xiangCi;
	/**道次*/
    @Excel(name = "道次", width = 15)
	private Integer daoCi;
	/**名次*/
    @Excel(name = "名次", width = 15)
	private Integer mingCi;
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
	@Excel(name="部门编码",width=25)
	@ApiModelProperty(value = "部门编码")
	private java.lang.String sysOrgCode;
}
