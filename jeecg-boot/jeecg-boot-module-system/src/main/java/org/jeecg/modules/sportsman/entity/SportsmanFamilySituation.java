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
 * @Description: 运动员家庭成员情况
 * @author： jeecg-boot
 * @date：   2019-06-20
 * @version： V1.0
 */
@Data
@TableName("sportsman_family_situation")
public class SportsmanFamilySituation implements Serializable {
    private static final long serialVersionUID = 1L;

	/**运动员家庭成员情况*/
	@TableId(type = IdType.UUID)
	private String id;
	/**运动员id*/
	private String sportsmanId;
	/**称谓*/
    @Excel(name = "称谓", width = 15)
	private String appellation;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
	private String name;
	/**年龄*/
	@Excel(name = "年龄", width = 15)
	private Integer age;
	/**身高*/
    @Excel(name = "身高", width = 15)
	private Double height;
	/**体重*/
	@Excel(name = "体重", width = 15)
	private Double weight;
	/**工作单位*/
	@Excel(name = "工作单位", width = 15)
	private String workUnit;
	/**电话*/
	@Excel(name = "电话", width = 15)
	private String phoneNo;
	/**家庭住址*/
    @Excel(name = "家庭住址", width = 15)
	private String homeAddress;
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
