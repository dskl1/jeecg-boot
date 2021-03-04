package org.jeecg.modules.bestscoreshow.service.impl;

import org.jeecg.modules.bestscoreshow.entity.ItemShow;
import org.jeecg.modules.bestscoreshow.mapper.ItemShowMapper;
import org.jeecg.modules.bestscoreshow.service.IItemShowService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 项目展示
 * @Author: jeecg-boot
 * @Date:   2019-12-20
 * @Version: V1.0
 */
@Service
public class ItemShowServiceImpl extends ServiceImpl<ItemShowMapper, ItemShow> implements IItemShowService {
	
	@Autowired
	private ItemShowMapper itemShowMapper;
	
	@Override
	public List<ItemShow> selectByMainId(String mainId) {
		return itemShowMapper.selectByMainId(mainId);
	}
}
