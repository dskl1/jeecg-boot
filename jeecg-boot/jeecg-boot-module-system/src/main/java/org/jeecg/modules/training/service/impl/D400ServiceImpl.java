package org.jeecg.modules.training.service.impl;

import org.jeecg.modules.training.entity.D400;
import org.jeecg.modules.training.mapper.D400Mapper;
import org.jeecg.modules.training.service.ID400Service;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 400
 * @Author: jeecg-boot
 * @Date:   2020-05-20
 * @Version: V1.0
 */
@Service
public class D400ServiceImpl extends ServiceImpl<D400Mapper, D400> implements ID400Service {

	@Autowired
	private D400Mapper d400Mapper;

	@Override
	public List<D400> selectByMainId(String mainId) {
		return d400Mapper.selectByMainId(mainId);
	}

	@Override
	public void insertD400() {
		this.d400Mapper.insertD400();
	}
}
