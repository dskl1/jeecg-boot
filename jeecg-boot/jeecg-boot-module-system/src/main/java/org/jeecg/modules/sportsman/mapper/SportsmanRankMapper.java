package org.jeecg.modules.sportsman.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.jeecg.modules.sportsman.entity.SportsmanRank;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 运动员等级
 * @author： jeecg-boot
 * @date：   2019-06-20
 * @version： V1.0
 */
public interface SportsmanRankMapper extends BaseMapper<SportsmanRank> {

	public boolean deleteByMainId(String mainId);

	public List<SportsmanRank> selectByMainId(String mainId);

	@Delete("delete from sportsman_rank\n" +
			"where sportsman_id not in (select id\n" +
			"from sportsman_registration_entry)")
	public void rankDelete(); //删除子表外键不存在的数据

	@Insert("insert ignore into sportsman_rank\n" +
			"select UUID(),s.id,t.match_name,null,t.match_date,c.match_item,c.distance,min(c.match_score),'否',null,\n" +
			"c.create_by,c.create_time,null,null\n" +
			"from match_total t,match_score_copy1 c,sportsman_registration_entry s\n" +
			"where t.sportsman_id = s.id and t.id = c.sportsman_id\n" +
			"group by c.match_item,s.id")
	public void rankInsert(); //比赛成绩达到某一等级的插入sports_rank表

	@Update("update sportsman_rank\n" +
			"set sport_grade =\n" +
			"(select card_number\n" +
			"from sportsman_registration_entry s\n" +
			"where sportsman_rank.sportsman_id = s.id)")
	public void cardUpdate(); //插入身份证号码
}
