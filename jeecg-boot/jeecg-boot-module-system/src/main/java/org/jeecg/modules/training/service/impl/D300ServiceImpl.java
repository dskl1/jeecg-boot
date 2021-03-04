package org.jeecg.modules.training.service.impl;

import org.jeecg.modules.training.entity.D300;
import org.jeecg.modules.training.mapper.D300Mapper;
import org.jeecg.modules.training.service.ID300Service;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 300
 * @Author: jeecg-boot
 * @Date:   2020-05-20
 * @Version: V1.0
 */
@Service
public class D300ServiceImpl extends ServiceImpl<D300Mapper, D300> implements ID300Service {

	@Autowired
	private D300Mapper d300Mapper;

	@Override
	public List<D300> selectByMainId(String mainId) {
		return d300Mapper.selectByMainId(mainId);
	}

	@Override
	public void insertD300() {
		this.d300Mapper.insertD300();
	}
}
