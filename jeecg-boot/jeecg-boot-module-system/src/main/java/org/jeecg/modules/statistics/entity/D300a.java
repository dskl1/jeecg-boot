package org.jeecg.modules.statistics.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 300
 * @author： jeecg-boot
 * @date：   2019-07-15
 * @version： V1.0
 */
@Data
@TableName("d300")
public class D300a implements Serializable {
    private static final long serialVersionUID = 1L;

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
	/**组数*/
    @Excel(name = "组数", width = 15)
	private String groupNo;
	/**300*/
	@TableId(type = IdType.UUID)
	private String id;
	/**时间段*/
    @Excel(name = "时间段", width = 15)
	private String period;
	/**运动员id*/
	private String sportsmanId;
	/**训练日期*/
	@Excel(name = "训练日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date trainingDate;
	/**训练小组*/
    @Excel(name = "训练小组", width = 15)
	private String trainingGroupId;
	/**训练项目*/
    @Excel(name = "训练项目", width = 15)
	private String trainingItem;
	/**训练序号*/
    @Excel(name = "训练序号", width = 15)
	private String trainingItemNo;
	/**修改人*/
    @Excel(name = "修改人", width = 15)
	private String updateBy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;
}
