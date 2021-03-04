package org.jeecg.modules.training.service.impl;

import org.jeecg.modules.training.entity.D50;
import org.jeecg.modules.training.mapper.D50Mapper;
import org.jeecg.modules.training.service.ID50Service;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 50
 * @Author: jeecg-boot
 * @Date:   2020-05-20
 * @Version: V1.0
 */
@Service
public class D50ServiceImpl extends ServiceImpl<D50Mapper, D50> implements ID50Service {

	@Autowired
	private D50Mapper d50Mapper;

	@Override
	public List<D50> selectByMainId(String mainId) {
		return d50Mapper.selectByMainId(mainId);
	}

	@Override
	public void insertD50() {
		this.d50Mapper.insertD50();
	}
}
