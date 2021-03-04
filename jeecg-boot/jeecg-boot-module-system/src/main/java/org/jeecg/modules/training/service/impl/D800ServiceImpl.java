package org.jeecg.modules.training.service.impl;

import org.jeecg.modules.training.entity.D800;
import org.jeecg.modules.training.mapper.D800Mapper;
import org.jeecg.modules.training.service.ID800Service;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 800
 * @Author: jeecg-boot
 * @Date:   2020-05-20
 * @Version: V1.0
 */
@Service
public class D800ServiceImpl extends ServiceImpl<D800Mapper, D800> implements ID800Service {

	@Autowired
	private D800Mapper d800Mapper;

	@Override
	public List<D800> selectByMainId(String mainId) {
		return d800Mapper.selectByMainId(mainId);
	}

	@Override
	public void insertD800() {
		this.d800Mapper.insertD800();
	}
}
