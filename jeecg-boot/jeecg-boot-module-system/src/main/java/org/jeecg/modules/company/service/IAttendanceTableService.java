package org.jeecg.modules.company.service;

import org.jeecg.modules.company.entity.AttendanceTable;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 考勤统计
 * @Author: jeecg-boot
 * @Date: 2020-07-20
 * @Version: V1.0
 */
public interface IAttendanceTableService extends IService<AttendanceTable> {

    /**
     * 将人员插入考勤统计表
     */
    public void personAttendance();

}
