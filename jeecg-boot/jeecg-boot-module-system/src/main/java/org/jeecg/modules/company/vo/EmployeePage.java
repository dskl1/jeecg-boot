package org.jeecg.modules.company.vo;

import java.util.List;
import org.jeecg.modules.company.entity.Employee;
import org.jeecg.modules.company.entity.BillingReport;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 清单
 * @Author: jeecg-boot
 * @Date:   2020-03-17
 * @Version: V1.0
 */
@Data
public class EmployeePage {

	/**比赛成绩*/
	private String id;
	/**姓名*/
  	@Excel(name = "姓名", width = 10)
	private String name;
	/**岗位*/
	@Excel(name = "岗位", width = 10)
	private String job;
	/**级别*/
	@Excel(name = "级别", width = 10)
	private String level;
	/**薪资*/
	@Excel(name = "薪资", width = 10)
	private java.math.BigDecimal salary;
	/**单价*/
	@Excel(name = "单价", width = 10)
	private java.math.BigDecimal price;
	/**调薪*/
	@Excel(name = "调薪", width = 10)
	private java.math.BigDecimal raiseSalary;
	/**调薪日期*/
	@Excel(name = "调薪日期", width = 10, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date raiseDate;
	/**入职时间*/
	@Excel(name = "入职时间", width = 10, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date entryTime;
	/**入场时间*/
	@Excel(name = "入场时间", width = 10, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date enterTime;
	/**离场时间*/
	@Excel(name = "离场时间", width = 10, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date awayTime;
	/**客户方*/
	@Excel(name = "客户方", width = 10)
	private String client;
	/**是否租用电脑*/
	@Excel(name = "是否租用电脑", width = 10)
	private String isRentalComputer;
	/**租用日期*/
	@Excel(name = "租用日期", width = 10, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date rentalDate;
	/**订单号*/
	@Excel(name = "订单号", width = 10)
	private String orderNumber;
	/**费用*/
	@Excel(name = "费用", width = 10)
	private java.math.BigDecimal cost;
	/**银行名称*/
	@Excel(name = "银行名称", width = 10)
	private String bankName;
	/**银行卡号*/
	@Excel(name = "银行卡号", width = 10)
	private String bankCard;
	/**状态*/
//	@Excel(name = "状态", width = 10)
	private String status;
	/**创建人*/
//  	@Excel(name = "创建人", width = 15)
	private String createBy;
	/**创建时间*/
//  	@Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**修改人*/
//  	@Excel(name = "修改人", width = 15)
	private String updateBy;
	/**修改时间*/
//  	@Excel(name = "修改时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;
	/**所属部门编码*/
//  	@Excel(name = "所属部门编码", width = 15)
	private String sysOrgCode;

	@ExcelCollection(name="账单")
	private List<BillingReport> billingReportList;

}
