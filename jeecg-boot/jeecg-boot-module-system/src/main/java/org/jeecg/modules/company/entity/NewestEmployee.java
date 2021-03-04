package org.jeecg.modules.company.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 清单
 * @Author: jeecg-boot
 * @Date:   2020-04-02
 * @Version: V1.0
 */
@Data
@TableName("newest_employee_list")
public class NewestEmployee implements Serializable {
    private static final long serialVersionUID = 1L;

	/**比赛成绩*/
	@TableId(type = IdType.ID_WORKER_STR)
	private String id;
	/**姓名*/
	private String name;
	/**岗位*/
	private String job;
	/**级别*/
	private String level;
	/**薪资*/
	private java.math.BigDecimal salary;
	/**单价*/
	private java.math.BigDecimal price;
	/**调薪*/
	private java.math.BigDecimal raiseSalary;
	/**调薪日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date raiseDate;
	/**入职时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date entryTime;
	/**入场时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date enterTime;
	/**离场时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date awayTime;
	/**客户方*/
	private String client;
	/**是否租用电脑*/
	private String isRentalComputer;
	/**租用日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date rentalDate;
	/**订单号*/
	private String orderNumber;
	/**费用*/
	private java.math.BigDecimal cost;
	/**银行名称*/
	private String bankName;
	/**银行卡号*/
	private String bankCard;
	/**状态*/
	private String status;
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
	/**所属部门编码*/
	private String sysOrgCode;

	public String getBankCard() {
		return bankCard;
	}
}
