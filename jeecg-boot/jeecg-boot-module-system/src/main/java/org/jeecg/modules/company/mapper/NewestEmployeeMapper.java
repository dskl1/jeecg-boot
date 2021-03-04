package org.jeecg.modules.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.jeecg.modules.company.entity.NewestEmployee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.data.repository.query.Param;

/**
 * @Description: 清单
 * @Author: jeecg-boot
 * @Date:   2020-04-02
 * @Version: V1.0
 */
public interface NewestEmployeeMapper extends BaseMapper<NewestEmployee> {

    @Delete("delete from employee_list")
    public void deleteAll(); //删除表中所有数据

    @Update("update employee_list\n" +
            "set status = 1\n" +
            "where id =#{id}")
    public void updateStatus(@Param("id") String id); //逻辑删除人员名单，状态变为1

}
