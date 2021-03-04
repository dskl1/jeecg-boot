package org.jeecg.modules.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.company.entity.AttendanceTable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 考勤统计
 * @Author: jeecg-boot
 * @Date:   2020-07-20
 * @Version: V1.0
 */
public interface AttendanceTableMapper extends BaseMapper<AttendanceTable> {

    @Insert("insert ignore into attendance_table(id,name,create_by,create_time,update_by,update_time,sys_org_code)\n" +
            "(\n" +
            "select id,name,create_by,create_time,update_by,update_time,sys_org_code\n" +
            "from employee_list)")
    public void personAttendance();  //将人员插入考勤统计表

}
