package org.jeecg.modules.sportsman.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 运动员等级
 * @author： jeecg-boot
 * @date：   2019-06-20
 * @version： V1.0
 */
@Data
@TableName("sportsman_rank")
public class SportsmanRank implements Serializable {
    private static final long serialVersionUID = 1L;

	/**运动员等级*/
	@TableId(type = IdType.UUID)
	private String id;
	/**运动员id*/
	private String sportsmanId;
	/**比赛名称*/
	@Excel(name = "比赛名称", width = 15)
	private String matchName;
	/**运动等级*/
	@Excel(name = "运动等级", width = 15)
	private String sportGrade;
	/**项目*/
	@Excel(name = "项目", width = 15)
	private String matchItem;
	/**距离*/
	@Excel(name = "距离", width = 15)
	private String distance;
	/**成绩*/
	@Excel(name = "成绩", width = 15)
	private String matchScore;
	/**达成日期*/
	@Excel(name = "达成日期", width = 15)
	private String dataItemScore;
	/**是否申请证书*/
	@Excel(name = "是否申请证书", width = 15)
	private String certificate;
	/**批准日期*/
	@Excel(name = "批准日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date approvalDate;
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
}
