package org.jeecg.modules.match.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 比赛运动员连接
 * @Author: jeecg-boot
 * @Date:   2019-08-14
 * @Version: V1.0
 */
@Data
@TableName("match_member_connect")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="match_member_connect对象", description="比赛运动员连接")
public class MatchMemberConnect {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.UUID)
	private String id;

	/**
	 * 用户id
	 */
	private String matchId;

	/**
	 * 角色id
	 */
	private String sportsmanId;


	public MatchMemberConnect(String matchId, String sportsmanId) {
		this.matchId = matchId;
		this.sportsmanId = sportsmanId;
	}
}
