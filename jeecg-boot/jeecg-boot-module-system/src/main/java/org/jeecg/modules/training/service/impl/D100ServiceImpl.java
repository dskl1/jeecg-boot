package org.jeecg.modules.training.service.impl;

import org.jeecg.modules.training.entity.D100;
import org.jeecg.modules.training.mapper.D100Mapper;
import org.jeecg.modules.training.service.ID100Service;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 100
 * @Author: jeecg-boot
 * @Date:   2020-05-20
 * @Version: V1.0
 */
@Service
public class D100ServiceImpl extends ServiceImpl<D100Mapper, D100> implements ID100Service {

	@Autowired
	private D100Mapper d100Mapper;

	@Override
	public List<D100> selectByMainId(String mainId) {
		return d100Mapper.selectByMainId(mainId);
	}

	@Override
	public void insertD100() {
		this.d100Mapper.insertD100();
	}
}
