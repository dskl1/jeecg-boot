package org.jeecg.modules.training.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.jeecg.modules.training.entity.TrainingScore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 队员表
 * @Author: jeecg-boot
 * @Date: 2020-05-20
 * @Version: V1.0
 */
public interface TrainingScoreMapper extends BaseMapper<TrainingScore> {

    @Update("update training_score\n" +
            "set training_flag = 0\n" +
            "where date_format( update_time, '%Y-%c-%d' ) = curdate( ) or date_format( create_time, '%Y-%c-%d' ) = curdate( );\n")
    public void setFlagSee(); //设置training_score卡标记为0

    @Update("update training_score\n" +
            "set training_flag = 1\n" +
            "where (update_time is NULL and date_format( create_time, '%Y-%c-%d' ) != curdate( )) or (date_format( update_time, '%Y-%c-%d' ) != curdate( ))\n")
    public void setFlagNoSee(); //设置training_score卡标记为1

    @Insert("insert ignore into training_score(id,sportsman_id,name,create_by,create_time,update_by,update_time,sys_org_code)\n" +
            "select UUID(),w.id,name,r.create_by,r.create_time,r.update_by,r.update_time,r.sys_org_code\n" +
            "from rest_api_copy1 r,week_plan w\n" +
            "where w.training_date1 = dayofweek(curdate())-1 and status = 1003 and date_format( r.create_time, '%Y-%c-%d' ) = curdate()")
    public void insertName(); //打卡人员插到week_plan当天计划下

    @Update("update training_score_copy1\n" +
            "set training_flag = 0\n" +
            "where date_format( update_time, '%Y-%c-%d' ) = curdate( ) or date_format( create_time, '%Y-%c-%d' ) = curdate( );\n")
    public void setFlagSeeCopy(); //设置training_score_copy1卡标记为0

    @Update("update training_score_copy1\n" +
            "set training_flag = 1\n" +
            "where (update_time is NULL and date_format( create_time, '%Y-%c-%d' ) != curdate( )) or (date_format( update_time, '%Y-%c-%d' ) != curdate( ))\n")
    public void setFlagNoSeeCopy(); //设置training_score_copy1卡标记为1

    @Insert("insert ignore into training_score_copy1(\n" +
            "select *\n" +
            "from training_score\n" +
            "group by name\n" +
            "having count(name) = 1 )")
    public void insertNameCopy(); //count(name)=1必定是当天新增的数据，直接插入training_score_copy1表

    @Update("update training_score_copy1 t,(select name,create_time\n" +
            "from training_score\n" +
            "where name in (\n" +
            "select name\n" +
            "from training_score\n" +
            "group by name\n" +
            "having count(name) > 1 ) and date_format( create_time, '%Y-%c-%d' ) = curdate()) a\n" +
            "set t.update_time = a.create_time\n" +
            "where t.name = a.name")
    public void updateTimeCopy(); //count(name) > 1时，查找在training_score表中当天是否有新增数据，有就更新training_score_copy1对应的名字的update_time

}
