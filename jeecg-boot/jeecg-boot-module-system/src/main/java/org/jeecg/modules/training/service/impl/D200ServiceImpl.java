package org.jeecg.modules.training.service.impl;

import org.jeecg.modules.training.entity.D200;
import org.jeecg.modules.training.mapper.D200Mapper;
import org.jeecg.modules.training.service.ID200Service;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 200
 * @Author: jeecg-boot
 * @Date:   2020-05-20
 * @Version: V1.0
 */
@Service
public class D200ServiceImpl extends ServiceImpl<D200Mapper, D200> implements ID200Service {

	@Autowired
	private D200Mapper d200Mapper;

	@Override
	public List<D200> selectByMainId(String mainId) {
		return d200Mapper.selectByMainId(mainId);
	}

	@Override
	public void insertD200() {
		this.d200Mapper.insertD200();
	}
}
