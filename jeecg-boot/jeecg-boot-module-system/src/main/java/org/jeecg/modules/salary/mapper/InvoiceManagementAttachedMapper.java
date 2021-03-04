package org.jeecg.modules.salary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Update;
import org.jeecg.modules.salary.entity.InvoiceManagementAttached;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 附票
 * @Author: jeecg-boot
 * @Date:   2020-08-31
 * @Version: V1.0
 */
public interface InvoiceManagementAttachedMapper extends BaseMapper<InvoiceManagementAttached> {

	public boolean deleteByMainId(@Param("mainId") String mainId);

	public List<InvoiceManagementAttached> selectByMainId(@Param("mainId") String mainId);

	@Update("update invoice_management_attached set stage = 1\n" +
			"where id = #{id}")
	public void stageUpdate(String id); //确认改变状态为1
}
