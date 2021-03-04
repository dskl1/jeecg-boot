package org.jeecg.modules.training.service.impl;

import org.jeecg.modules.training.entity.D250;
import org.jeecg.modules.training.mapper.D250Mapper;
import org.jeecg.modules.training.service.ID250Service;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 250
 * @Author: jeecg-boot
 * @Date:   2020-05-20
 * @Version: V1.0
 */
@Service
public class D250ServiceImpl extends ServiceImpl<D250Mapper, D250> implements ID250Service {

	@Autowired
	private D250Mapper d250Mapper;

	@Override
	public List<D250> selectByMainId(String mainId) {
		return d250Mapper.selectByMainId(mainId);
	}

	@Override
	public void insertD250() {
		this.d250Mapper.insertD250();
	}
}
