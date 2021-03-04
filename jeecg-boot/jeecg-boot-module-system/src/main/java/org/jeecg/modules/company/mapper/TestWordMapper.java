package org.jeecg.modules.company.mapper;

import java.util.List;
import org.jeecg.modules.company.entity.TestWord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 子表
 * @Author: jeecg-boot
 * @Date:   2020-04-21
 * @Version: V1.0
 */
public interface TestWordMapper extends BaseMapper<TestWord> {

	public boolean deleteByMainId(@Param("mainId") String mainId);

	public List<TestWord> selectByMainId(@Param("mainId") String mainId);

}
