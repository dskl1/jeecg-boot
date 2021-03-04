package org.jeecg.modules.restapi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: api
 * @Author: jeecg-boot
 * @Date:   2019-11-27
 * @Version: V1.0
 */
@Data
@TableName("rest_api")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="rest_api对象", description="api")
public class RestApi {

	/**主键*/
	@TableId(type = IdType.UUID)
	private String id;
	/**服务id*/
	@Excel(name = "服务id", width = 15)
    @ApiModelProperty(value = "服务id")
	private String deviceId;
	/**机器id*/
	@Excel(name = "机器id", width = 15)
	@ApiModelProperty(value = "机器id")
	private String nfcId;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String status;
	/**用时*/
	@Excel(name = "用时", width = 15)
	@ApiModelProperty(value = "用时")
	private String subSecond;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**修改人*/
	@ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "修改时间")
	private Date updateTime;
	/**所属部门*/
	@ApiModelProperty(value = "所属部门")
	private java.lang.String sysOrgCode;
}
