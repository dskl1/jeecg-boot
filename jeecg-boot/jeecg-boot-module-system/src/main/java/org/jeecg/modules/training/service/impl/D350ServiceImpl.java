package org.jeecg.modules.training.service.impl;

import org.jeecg.modules.training.entity.D350;
import org.jeecg.modules.training.mapper.D350Mapper;
import org.jeecg.modules.training.service.ID350Service;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 350
 * @Author: jeecg-boot
 * @Date:   2020-05-20
 * @Version: V1.0
 */
@Service
public class D350ServiceImpl extends ServiceImpl<D350Mapper, D350> implements ID350Service {

	@Autowired
	private D350Mapper d350Mapper;

	@Override
	public List<D350> selectByMainId(String mainId) {
		return d350Mapper.selectByMainId(mainId);
	}

	@Override
	public void insertD350() {
		this.d350Mapper.insertD350();
	}
}
