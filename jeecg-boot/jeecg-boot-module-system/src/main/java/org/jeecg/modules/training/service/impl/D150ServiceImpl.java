package org.jeecg.modules.training.service.impl;

import org.jeecg.modules.training.entity.D150;
import org.jeecg.modules.training.mapper.D150Mapper;
import org.jeecg.modules.training.service.ID150Service;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 150
 * @Author: jeecg-boot
 * @Date:   2020-05-20
 * @Version: V1.0
 */
@Service
public class D150ServiceImpl extends ServiceImpl<D150Mapper, D150> implements ID150Service {

	@Autowired
	private D150Mapper d150Mapper;

	@Override
	public List<D150> selectByMainId(String mainId) {
		return d150Mapper.selectByMainId(mainId);
	}

	@Override
	public void insertD150() {
		this.d150Mapper.insertD150();
	}
}
