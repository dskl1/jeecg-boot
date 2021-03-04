package org.jeecg.modules.training.service.impl;

import org.jeecg.modules.training.entity.D1000;
import org.jeecg.modules.training.mapper.D1000Mapper;
import org.jeecg.modules.training.service.ID1000Service;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 1000
 * @Author: jeecg-boot
 * @Date:   2020-05-20
 * @Version: V1.0
 */
@Service
public class D1000ServiceImpl extends ServiceImpl<D1000Mapper, D1000> implements ID1000Service {

	@Autowired
	private D1000Mapper d1000Mapper;

	@Override
	public List<D1000> selectByMainId(String mainId) {
		return d1000Mapper.selectByMainId(mainId);
	}

	@Override
	public void insertD1000() {
		this.d1000Mapper.insertD1000();
	}
}
