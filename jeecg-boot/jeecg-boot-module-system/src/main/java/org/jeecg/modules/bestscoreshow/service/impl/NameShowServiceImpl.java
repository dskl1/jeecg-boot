package org.jeecg.modules.bestscoreshow.service.impl;

import org.jeecg.modules.bestscoreshow.entity.NameShow;
import org.jeecg.modules.bestscoreshow.entity.ItemShow;
import org.jeecg.modules.bestscoreshow.mapper.ItemShowMapper;
import org.jeecg.modules.bestscoreshow.mapper.NameShowMapper;
import org.jeecg.modules.bestscoreshow.service.INameShowService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 姓名展示
 * @Author: jeecg-boot
 * @Date:   2019-12-20
 * @Version: V1.0
 */
@Service
public class NameShowServiceImpl extends ServiceImpl<NameShowMapper, NameShow> implements INameShowService {

	@Autowired
	private NameShowMapper nameShowMapper;
	@Autowired
	private ItemShowMapper itemShowMapper;

	@Override
	@Transactional
	public void saveMain(NameShow nameShow, List<ItemShow> itemShowList) {
		nameShowMapper.insert(nameShow);
		for(ItemShow entity:itemShowList) {
			//外键设置
			entity.setSportsmanId(nameShow.getId());
			itemShowMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(NameShow nameShow,List<ItemShow> itemShowList) {
		nameShowMapper.updateById(nameShow);

		//1.先删除子表数据
		itemShowMapper.deleteByMainId(nameShow.getId());

		//2.子表数据重新插入
		for(ItemShow entity:itemShowList) {
			//外键设置
			entity.setSportsmanId(nameShow.getId());
			itemShowMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		itemShowMapper.deleteByMainId(id);
		nameShowMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			itemShowMapper.deleteByMainId(id.toString());
			nameShowMapper.deleteById(id);
		}
	}

}
