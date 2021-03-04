package org.jeecg.modules.company.service.impl;

import org.jeecg.modules.company.entity.AttendanceTable;
import org.jeecg.modules.company.mapper.AttendanceTableMapper;
import org.jeecg.modules.company.service.IAttendanceTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 考勤统计
 * @Author: jeecg-boot
 * @Date: 2020-07-20
 * @Version: V1.0
 */
@Service
public class AttendanceTableServiceImpl extends ServiceImpl<AttendanceTableMapper, AttendanceTable> implements IAttendanceTableService {

    @Autowired
    private AttendanceTableMapper attendanceTableMapper;

    @Override
    public void personAttendance() {
        this.attendanceTableMapper.personAttendance();
    }

}
