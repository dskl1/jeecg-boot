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
 * @Description: 历年机能发育情况
 * @author： jeecg-boot
 * @date：   2019-06-20
 * @version： V1.0
 */
@Data
@TableName("develop_situation")
public class DevelopSituation implements Serializable {
    private static final long serialVersionUID = 1L;

	/**运动员历年机能发育情况*/
	@TableId(type = IdType.UUID)
	private String id;
	/**运动员id*/
	private String sportsmanId;
	/**日期*/
	@Excel(name = "日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date testDate;
	/**晨脉*/
    @Excel(name = "晨脉", width = 15)
	private String chenMai;
	/**尿蛋白*/
	@Excel(name = "尿蛋白", width = 15)
	private String niaoDanBai;
	/**血压*/
	@Excel(name = "血压", width = 15)
	private String xueYa;
	/**血色素*/
	@Excel(name = "血色素", width = 15)
	private String xueSeSu;
	/**肺活量*/
	@Excel(name = "肺活量", width = 15)
	private String feiHuoLiang;
	/**背力*/
	@Excel(name = "背力", width = 15)
	private String beiLi;
	/**台阶实验*/
	@Excel(name = "台阶实验", width = 15)
	private String taiJieShiYan;
	/**反应时*/
	@Excel(name = "反应时", width = 15)
	private String fanYingShi;
	/**骨龄*/
	@Excel(name = "骨龄", width = 15)
	private String guLing;
	/**预测身高*/
	@Excel(name = "预测身高", width = 15)
	private String forecastHeight;
	/**心理1*/
    @Excel(name = "心理1", width = 15)
	private String psychology1;
	/**心理2*/
    @Excel(name = "心理2", width = 15)
	private String psychology2;
	/**心理3*/
    @Excel(name = "心理3", width = 15)
	private String psychology3;
	/**心理4*/
    @Excel(name = "心理4", width = 15)
	private String psychology4;
	/**发育类型*/
	@Excel(name = "发育类型", width = 15)
	private String faYuType;
	/**月经初潮*/
	@Excel(name = "月经初潮", width = 15)
	private String yueJingChuChao;
	/**乳房*/
	@Excel(name = "乳房", width = 15)
	private String ruFang;
	/**阴毛*/
	@Excel(name = "阴毛", width = 15)
	private String yinMao;
	/**睾丸*/
	@Excel(name = "睾丸", width = 15)
	private String gaoWan;
	/**视力*/
    @Excel(name = "视力", width = 15)
	private String shiLi;
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
