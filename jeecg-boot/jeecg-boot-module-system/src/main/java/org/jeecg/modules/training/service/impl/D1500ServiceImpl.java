package org.jeecg.modules.training.service.impl;

import org.jeecg.modules.training.entity.D1500;
import org.jeecg.modules.training.mapper.D1500Mapper;
import org.jeecg.modules.training.service.ID1500Service;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 1500
 * @Author: jeecg-boot
 * @Date:   2020-05-20
 * @Version: V1.0
 */
@Service
public class D1500ServiceImpl extends ServiceImpl<D1500Mapper, D1500> implements ID1500Service {

	@Autowired
	private D1500Mapper d1500Mapper;

	@Override
	public List<D1500> selectByMainId(String mainId) {
		return d1500Mapper.selectByMainId(mainId);
	}

	@Override
	public void insertD1500() {
		this.d1500Mapper.insertD1500();
	}
}
