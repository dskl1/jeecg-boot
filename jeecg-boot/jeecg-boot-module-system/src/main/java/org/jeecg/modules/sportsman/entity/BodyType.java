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
 * @Description: 身体形态指标
 * @author： jeecg-boot
 * @date：   2019-06-20
 * @version： V1.0
 */
@Data
@TableName("body_type")
public class BodyType implements Serializable {
    private static final long serialVersionUID = 1L;

	/**运动员身体形态指标*/
	@TableId(type = IdType.UUID)
	private String id;
	/**运动员id*/
	private String sportsmanId;
	/**测试日期*/
	@Excel(name = "测试日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date testDate;
	/**身高(cm)*/
	@Excel(name = "身高(cm)", width = 15)
	private Double height;
	/**体重(kg)*/
    @Excel(name = "体重(kg)", width = 15)
	private Double bodyWeight;
	/**坐高(cm)*/
	@Excel(name = "坐高(cm)", width = 15)
	private Double sitHeight;
	/**指距(cm)*/
	@Excel(name = "指距(cm)", width = 15)
	private Double zhiJu;
	/**胸围(cm)*/
	@Excel(name = "胸围(cm)", width = 15)
	private Double xiongWei;
	/**肩宽(cm)*/
	@Excel(name = "肩宽(cm)", width = 15)
	private Double jianKuan;
	/**骨盆宽(cm)*/
	@Excel(name = "骨盆宽(cm)", width = 15)
	private Double guPenKuan;
	/**上肢长(cm)*/
	@Excel(name = "上肢长(cm)", width = 15)
	private Double shangZhiChang;
	/**下肢长(cm)*/
	@Excel(name = "下肢长(cm)", width = 15)
	private Double xiaZhiChang;
	/**小腿围(cm)*/
    @Excel(name = "小腿围(cm)", width = 15)
	private Double smallLeg;
	/**大腿围(cm)*/
	@Excel(name = "大腿围(cm)", width = 15)
	private Double bigLeg;
	/**腰围(cm)*/
	@Excel(name = "腰围(cm)", width = 15)
	private Double butt;
	/**小腿长(cm)*/
	@Excel(name = "小腿长(cm)", width = 15)
	private Double xiaoTuiChang;
	/**腿腱长(cm)*/
    @Excel(name = "腿腱长(cm)", width = 15)
	private Double tuiJianChang;
	/**踝围(cm)*/
	@Excel(name = "踝围(cm)", width = 15)
	private Double huaiWei;
	/**背*/
	@Excel(name = "背", width = 15)
	private String back;
	/**腹*/
	@Excel(name = "腹", width = 15)
	private String belly;
	/**臂*/
	@Excel(name = "臂", width = 15)
	private String arm;
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
