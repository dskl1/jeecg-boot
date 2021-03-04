package org.jeecg.modules.statistics.service.impl;

import org.jeecg.modules.statistics.entity.Statistics;
import org.jeecg.modules.statistics.entity.D25a;
import org.jeecg.modules.statistics.entity.D50a;
import org.jeecg.modules.statistics.entity.D100a;
import org.jeecg.modules.statistics.entity.D150a;
import org.jeecg.modules.statistics.entity.D200a;
import org.jeecg.modules.statistics.entity.D250a;
import org.jeecg.modules.statistics.entity.D300a;
import org.jeecg.modules.statistics.entity.D350a;
import org.jeecg.modules.statistics.entity.D400a;
import org.jeecg.modules.statistics.entity.D800a;
import org.jeecg.modules.statistics.entity.D1000a;
import org.jeecg.modules.statistics.entity.D1500a;
import org.jeecg.modules.statistics.mapper.D25aMapper;
import org.jeecg.modules.statistics.mapper.D50aMapper;
import org.jeecg.modules.statistics.mapper.D100aMapper;
import org.jeecg.modules.statistics.mapper.D150aMapper;
import org.jeecg.modules.statistics.mapper.D200aMapper;
import org.jeecg.modules.statistics.mapper.D250aMapper;
import org.jeecg.modules.statistics.mapper.D300aMapper;
import org.jeecg.modules.statistics.mapper.D350aMapper;
import org.jeecg.modules.statistics.mapper.D400aMapper;
import org.jeecg.modules.statistics.mapper.D800aMapper;
import org.jeecg.modules.statistics.mapper.D1000aMapper;
import org.jeecg.modules.statistics.mapper.D1500aMapper;
import org.jeecg.modules.statistics.mapper.StatisticsMapper;
import org.jeecg.modules.statistics.service.IStatisticsService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 训练报表
 * @author： jeecg-boot
 * @date：   2019-07-15
 * @version： V1.0
 */
@Service
public class StatisticsServiceImpl extends ServiceImpl<StatisticsMapper, Statistics> implements IStatisticsService {

	@Autowired
	private StatisticsMapper statisticsMapper;
	@Autowired
	private D25aMapper d25aMapper;
	@Autowired
	private D50aMapper d50aMapper;
	@Autowired
	private D100aMapper d100aMapper;
	@Autowired
	private D150aMapper d150aMapper;
	@Autowired
	private D200aMapper d200aMapper;
	@Autowired
	private D250aMapper d250aMapper;
	@Autowired
	private D300aMapper d300aMapper;
	@Autowired
	private D350aMapper d350aMapper;
	@Autowired
	private D400aMapper d400aMapper;
	@Autowired
	private D800aMapper d800aMapper;
	@Autowired
	private D1000aMapper d1000aMapper;
	@Autowired
	private D1500aMapper d1500aMapper;

	@Override
	@Transactional
	public void saveMain(Statistics statistics, List<D25a> d25aList,List<D50a> d50aList,List<D100a> d100aList,List<D150a> d150aList,List<D200a> d200aList,List<D250a> d250aList,List<D300a> d300aList,List<D350a> d350aList,List<D400a> d400aList,List<D800a> d800aList,List<D1000a> d1000aList,List<D1500a> d1500aList) {
		statisticsMapper.insert(statistics);
		for(D25a entity:d25aList) {
			//外键设置
			entity.setSportsmanId(statistics.getId());
			d25aMapper.insert(entity);
		}
		for(D50a entity:d50aList) {
			//外键设置
			entity.setSportsmanId(statistics.getId());
			d50aMapper.insert(entity);
		}
		for(D100a entity:d100aList) {
			//外键设置
			entity.setSportsmanId(statistics.getId());
			d100aMapper.insert(entity);
		}
		for(D150a entity:d150aList) {
			//外键设置
			entity.setSportsmanId(statistics.getId());
			d150aMapper.insert(entity);
		}
		for(D200a entity:d200aList) {
			//外键设置
			entity.setSportsmanId(statistics.getId());
			d200aMapper.insert(entity);
		}
		for(D250a entity:d250aList) {
			//外键设置
			entity.setSportsmanId(statistics.getId());
			d250aMapper.insert(entity);
		}
		for(D300a entity:d300aList) {
			//外键设置
			entity.setSportsmanId(statistics.getId());
			d300aMapper.insert(entity);
		}
		for(D350a entity:d350aList) {
			//外键设置
			entity.setSportsmanId(statistics.getId());
			d350aMapper.insert(entity);
		}
		for(D400a entity:d400aList) {
			//外键设置
			entity.setSportsmanId(statistics.getId());
			d400aMapper.insert(entity);
		}
		for(D800a entity:d800aList) {
			//外键设置
			entity.setSportsmanId(statistics.getId());
			d800aMapper.insert(entity);
		}
		for(D1000a entity:d1000aList) {
			//外键设置
			entity.setSportsmanId(statistics.getId());
			d1000aMapper.insert(entity);
		}
		for(D1500a entity:d1500aList) {
			//外键设置
			entity.setSportsmanId(statistics.getId());
			d1500aMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(Statistics statistics,List<D25a> d25aList,List<D50a> d50aList,List<D100a> d100aList,List<D150a> d150aList,List<D200a> d200aList,List<D250a> d250aList,List<D300a> d300aList,List<D350a> d350aList,List<D400a> d400aList,List<D800a> d800aList,List<D1000a> d1000aList,List<D1500a> d1500aList) {
		statisticsMapper.updateById(statistics);

		//1.先删除子表数据
		d25aMapper.deleteByMainId(statistics.getId());
		d50aMapper.deleteByMainId(statistics.getId());
		d100aMapper.deleteByMainId(statistics.getId());
		d150aMapper.deleteByMainId(statistics.getId());
		d200aMapper.deleteByMainId(statistics.getId());
		d250aMapper.deleteByMainId(statistics.getId());
		d300aMapper.deleteByMainId(statistics.getId());
		d350aMapper.deleteByMainId(statistics.getId());
		d400aMapper.deleteByMainId(statistics.getId());
		d800aMapper.deleteByMainId(statistics.getId());
		d1000aMapper.deleteByMainId(statistics.getId());
		d1500aMapper.deleteByMainId(statistics.getId());

		//2.子表数据重新插入
		for(D25a entity:d25aList) {
			//外键设置
			entity.setSportsmanId(statistics.getId());
			d25aMapper.insert(entity);
		}
		for(D50a entity:d50aList) {
			//外键设置
			entity.setSportsmanId(statistics.getId());
			d50aMapper.insert(entity);
		}
		for(D100a entity:d100aList) {
			//外键设置
			entity.setSportsmanId(statistics.getId());
			d100aMapper.insert(entity);
		}
		for(D150a entity:d150aList) {
			//外键设置
			entity.setSportsmanId(statistics.getId());
			d150aMapper.insert(entity);
		}
		for(D200a entity:d200aList) {
			//外键设置
			entity.setSportsmanId(statistics.getId());
			d200aMapper.insert(entity);
		}
		for(D250a entity:d250aList) {
			//外键设置
			entity.setSportsmanId(statistics.getId());
			d250aMapper.insert(entity);
		}
		for(D300a entity:d300aList) {
			//外键设置
			entity.setSportsmanId(statistics.getId());
			d300aMapper.insert(entity);
		}
		for(D350a entity:d350aList) {
			//外键设置
			entity.setSportsmanId(statistics.getId());
			d350aMapper.insert(entity);
		}
		for(D400a entity:d400aList) {
			//外键设置
			entity.setSportsmanId(statistics.getId());
			d400aMapper.insert(entity);
		}
		for(D800a entity:d800aList) {
			//外键设置
			entity.setSportsmanId(statistics.getId());
			d800aMapper.insert(entity);
		}
		for(D1000a entity:d1000aList) {
			//外键设置
			entity.setSportsmanId(statistics.getId());
			d1000aMapper.insert(entity);
		}
		for(D1500a entity:d1500aList) {
			//外键设置
			entity.setSportsmanId(statistics.getId());
			d1500aMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		statisticsMapper.deleteById(id);
		d25aMapper.deleteByMainId(id);
		d50aMapper.deleteByMainId(id);
		d100aMapper.deleteByMainId(id);
		d150aMapper.deleteByMainId(id);
		d200aMapper.deleteByMainId(id);
		d250aMapper.deleteByMainId(id);
		d300aMapper.deleteByMainId(id);
		d350aMapper.deleteByMainId(id);
		d400aMapper.deleteByMainId(id);
		d800aMapper.deleteByMainId(id);
		d1000aMapper.deleteByMainId(id);
		d1500aMapper.deleteByMainId(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			statisticsMapper.deleteById(id);
			d25aMapper.deleteByMainId(id.toString());
			d50aMapper.deleteByMainId(id.toString());
			d100aMapper.deleteByMainId(id.toString());
			d150aMapper.deleteByMainId(id.toString());
			d200aMapper.deleteByMainId(id.toString());
			d250aMapper.deleteByMainId(id.toString());
			d300aMapper.deleteByMainId(id.toString());
			d350aMapper.deleteByMainId(id.toString());
			d400aMapper.deleteByMainId(id.toString());
			d800aMapper.deleteByMainId(id.toString());
			d1000aMapper.deleteByMainId(id.toString());
			d1500aMapper.deleteByMainId(id.toString());
		}
	}

}
