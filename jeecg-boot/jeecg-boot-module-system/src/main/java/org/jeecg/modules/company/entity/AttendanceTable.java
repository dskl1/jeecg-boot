package org.jeecg.modules.company.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 考勤统计
 * @Author: jeecg-boot
 * @Date:   2020-07-20
 * @Version: V1.0
 */
@Data
@TableName("attendance_table")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="attendance_table对象", description="考勤统计")
public class AttendanceTable implements Serializable {
    private static final long serialVersionUID = 1L;

	/**考勤管理*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "考勤管理")
    private String id;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
    private String name;
	/**应到天数*/
	@Excel(name = "应到天数", width = 15)
    @ApiModelProperty(value = "应到天数")
    private Integer totalDay;
	/**实到天数*/
	@Excel(name = "实到天数", width = 15)
    @ApiModelProperty(value = "实到天数")
    private Integer actualDay;
	/**请假理由*/
	@Excel(name = "请假理由", width = 15)
    @ApiModelProperty(value = "请假理由")
    private String leaveForReason;
	/**出勤率*/
	@Excel(name = "出勤率", width = 15)
    @ApiModelProperty(value = "出勤率")
    private Double attendanceRate;
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
