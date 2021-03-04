package org.jeecg.modules.company.service;

import org.jeecg.modules.company.entity.TestWord;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 子表
 * @Author: jeecg-boot
 * @Date:   2020-04-21
 * @Version: V1.0
 */
public interface ITestWordService extends IService<TestWord> {

	public List<TestWord> selectByMainId(String mainId);
}
