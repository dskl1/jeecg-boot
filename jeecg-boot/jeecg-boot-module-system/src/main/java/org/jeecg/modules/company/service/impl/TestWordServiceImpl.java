package org.jeecg.modules.company.service.impl;

import org.jeecg.modules.company.entity.TestWord;
import org.jeecg.modules.company.mapper.TestWordMapper;
import org.jeecg.modules.company.service.ITestWordService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 子表
 * @Author: jeecg-boot
 * @Date:   2020-04-21
 * @Version: V1.0
 */
@Service
public class TestWordServiceImpl extends ServiceImpl<TestWordMapper, TestWord> implements ITestWordService {

	@Autowired
	private TestWordMapper testWordMapper;

	@Override
	public List<TestWord> selectByMainId(String mainId) {
		return testWordMapper.selectByMainId(mainId);
	}
}
