package org.jeecg.modules.training.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.jeecg.modules.training.entity.D300;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 300
 * @Author: jeecg-boot
 * @Date:   2020-05-20
 * @Version: V1.0
 */
public interface D300Mapper extends BaseMapper<D300> {

	public boolean deleteByMainId(@Param("mainId") String mainId);

	public List<D300> selectByMainId(@Param("mainId") String mainId);

	@Insert("INSERT ignore into\n" +
			"\t\td300(id,sportsman_id,training_date,period,training_group_id,training_item_no,training_item,group_no,coach_name,is_highland,bao_gan,tang_shu,support_item,create_time,create_by,update_time,update_by,sys_org_code)\n" +
			"        select UUID(),t.id,CURDATE(),w.period,w.training_group_id,w.training_item_no,w.training_item,w.group_no,w.coach_name,w.is_highland,w.bao_gan,w.tang_shu,w.support_item,w.create_time,w.create_by,w.update_time,w.update_by,w.sys_org_code\n" +
			"\t\t\t\tfrom week_plan w,training_score_copy1 t\n" +
			"\t\t\t\twhere w.training_date1 = dayofweek(curdate())-1 and w.distance= 300 and t.training_flag = 0")
	public void insertD300(); //把当天得训练计划插到300对应的训练项目里

}
