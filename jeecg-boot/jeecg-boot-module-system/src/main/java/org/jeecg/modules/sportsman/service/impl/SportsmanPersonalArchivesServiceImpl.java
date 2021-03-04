package org.jeecg.modules.sportsman.service.impl;

import org.jeecg.modules.sportsman.entity.SportsmanPersonalArchives;
import org.jeecg.modules.sportsman.entity.SportsmanRank;
import org.jeecg.modules.sportsman.entity.SportsmanResume;
import org.jeecg.modules.sportsman.entity.SportsmanFamilySituation;
import org.jeecg.modules.sportsman.entity.SportsmanData;
import org.jeecg.modules.sportsman.entity.DevelopSituation;
import org.jeecg.modules.sportsman.entity.BodyType;
import org.jeecg.modules.sportsman.entity.BodyAbility;
import org.jeecg.modules.sportsman.mapper.SportsmanRankMapper;
import org.jeecg.modules.sportsman.mapper.SportsmanResumeMapper;
import org.jeecg.modules.sportsman.mapper.SportsmanFamilySituationMapper;
import org.jeecg.modules.sportsman.mapper.SportsmanDataMapper;
import org.jeecg.modules.sportsman.mapper.DevelopSituationMapper;
import org.jeecg.modules.sportsman.mapper.BodyTypeMapper;
import org.jeecg.modules.sportsman.mapper.BodyAbilityMapper;
import org.jeecg.modules.sportsman.mapper.SportsmanPersonalArchivesMapper;
import org.jeecg.modules.sportsman.service.ISportsmanPersonalArchivesService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 档案管理
 * @author： jeecg-boot
 * @date：   2019-06-20
 * @version： V1.0
 */
@Service
public class SportsmanPersonalArchivesServiceImpl extends ServiceImpl<SportsmanPersonalArchivesMapper, SportsmanPersonalArchives> implements ISportsmanPersonalArchivesService {

	@Autowired
	private SportsmanPersonalArchivesMapper sportsmanPersonalArchivesMapper;
	@Autowired
	private SportsmanRankMapper sportsmanRankMapper;
	@Autowired
	private SportsmanResumeMapper sportsmanResumeMapper;
	@Autowired
	private SportsmanFamilySituationMapper sportsmanFamilySituationMapper;
	@Autowired
	private SportsmanDataMapper sportsmanDataMapper;
	@Autowired
	private DevelopSituationMapper developSituationMapper;
	@Autowired
	private BodyTypeMapper bodyTypeMapper;
	@Autowired
	private BodyAbilityMapper bodyAbilityMapper;

	@Override
	@Transactional
	public void saveMain(SportsmanPersonalArchives sportsmanPersonalArchives, List<SportsmanRank> sportsmanRankList,List<SportsmanResume> sportsmanResumeList,List<SportsmanFamilySituation> sportsmanFamilySituationList,List<SportsmanData> sportsmanDataList,List<DevelopSituation> developSituationList,List<BodyType> bodyTypeList,List<BodyAbility> bodyAbilityList) {
		sportsmanPersonalArchivesMapper.insert(sportsmanPersonalArchives);
		for(SportsmanRank entity:sportsmanRankList) {
			//外键设置
			entity.setSportsmanId(sportsmanPersonalArchives.getId());
			sportsmanRankMapper.insert(entity);
		}
		for(SportsmanResume entity:sportsmanResumeList) {
			//外键设置
			entity.setSportsmanId(sportsmanPersonalArchives.getId());
			sportsmanResumeMapper.insert(entity);
		}
		for(SportsmanFamilySituation entity:sportsmanFamilySituationList) {
			//外键设置
			entity.setSportsmanId(sportsmanPersonalArchives.getId());
			sportsmanFamilySituationMapper.insert(entity);
		}
		for(SportsmanData entity:sportsmanDataList) {
			//外键设置
			entity.setSportsmanId(sportsmanPersonalArchives.getId());
			sportsmanDataMapper.insert(entity);
		}
		for(DevelopSituation entity:developSituationList) {
			//外键设置
			entity.setSportsmanId(sportsmanPersonalArchives.getId());
			developSituationMapper.insert(entity);
		}
		for(BodyType entity:bodyTypeList) {
			//外键设置
			entity.setSportsmanId(sportsmanPersonalArchives.getId());
			bodyTypeMapper.insert(entity);
		}
		for(BodyAbility entity:bodyAbilityList) {
			//外键设置
			entity.setSportsmanId(sportsmanPersonalArchives.getId());
			bodyAbilityMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(SportsmanPersonalArchives sportsmanPersonalArchives,List<SportsmanRank> sportsmanRankList,List<SportsmanResume> sportsmanResumeList,List<SportsmanFamilySituation> sportsmanFamilySituationList,List<SportsmanData> sportsmanDataList,List<DevelopSituation> developSituationList,List<BodyType> bodyTypeList,List<BodyAbility> bodyAbilityList) {
		sportsmanPersonalArchivesMapper.updateById(sportsmanPersonalArchives);

		//1.先删除子表数据
		sportsmanRankMapper.deleteByMainId(sportsmanPersonalArchives.getId());
		sportsmanResumeMapper.deleteByMainId(sportsmanPersonalArchives.getId());
		sportsmanFamilySituationMapper.deleteByMainId(sportsmanPersonalArchives.getId());
		sportsmanDataMapper.deleteByMainId(sportsmanPersonalArchives.getId());
		developSituationMapper.deleteByMainId(sportsmanPersonalArchives.getId());
		bodyTypeMapper.deleteByMainId(sportsmanPersonalArchives.getId());
		bodyAbilityMapper.deleteByMainId(sportsmanPersonalArchives.getId());

		//2.子表数据重新插入
		for(SportsmanRank entity:sportsmanRankList) {
			//外键设置
			entity.setSportsmanId(sportsmanPersonalArchives.getId());
			sportsmanRankMapper.insert(entity);
		}
		for(SportsmanResume entity:sportsmanResumeList) {
			//外键设置
			entity.setSportsmanId(sportsmanPersonalArchives.getId());
			sportsmanResumeMapper.insert(entity);
		}
		for(SportsmanFamilySituation entity:sportsmanFamilySituationList) {
			//外键设置
			entity.setSportsmanId(sportsmanPersonalArchives.getId());
			sportsmanFamilySituationMapper.insert(entity);
		}
		for(SportsmanData entity:sportsmanDataList) {
			//外键设置
			entity.setSportsmanId(sportsmanPersonalArchives.getId());
			sportsmanDataMapper.insert(entity);
		}
		for(DevelopSituation entity:developSituationList) {
			//外键设置
			entity.setSportsmanId(sportsmanPersonalArchives.getId());
			developSituationMapper.insert(entity);
		}
		for(BodyType entity:bodyTypeList) {
			//外键设置
			entity.setSportsmanId(sportsmanPersonalArchives.getId());
			bodyTypeMapper.insert(entity);
		}
		for(BodyAbility entity:bodyAbilityList) {
			//外键设置
			entity.setSportsmanId(sportsmanPersonalArchives.getId());
			bodyAbilityMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		sportsmanPersonalArchivesMapper.deleteById(id);
		sportsmanRankMapper.deleteByMainId(id);
		sportsmanResumeMapper.deleteByMainId(id);
		sportsmanFamilySituationMapper.deleteByMainId(id);
		sportsmanDataMapper.deleteByMainId(id);
		developSituationMapper.deleteByMainId(id);
		bodyTypeMapper.deleteByMainId(id);
		bodyAbilityMapper.deleteByMainId(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			sportsmanPersonalArchivesMapper.deleteById(id);
			sportsmanRankMapper.deleteByMainId(id.toString());
			sportsmanResumeMapper.deleteByMainId(id.toString());
			sportsmanFamilySituationMapper.deleteByMainId(id.toString());
			sportsmanDataMapper.deleteByMainId(id.toString());
			developSituationMapper.deleteByMainId(id.toString());
			bodyTypeMapper.deleteByMainId(id.toString());
			bodyAbilityMapper.deleteByMainId(id.toString());
		}
	}

}
