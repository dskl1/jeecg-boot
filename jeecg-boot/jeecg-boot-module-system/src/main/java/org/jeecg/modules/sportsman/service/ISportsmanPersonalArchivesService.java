package org.jeecg.modules.sportsman.service;

import org.jeecg.modules.sportsman.entity.SportsmanRank;
import org.jeecg.modules.sportsman.entity.SportsmanResume;
import org.jeecg.modules.sportsman.entity.SportsmanFamilySituation;
import org.jeecg.modules.sportsman.entity.SportsmanData;
import org.jeecg.modules.sportsman.entity.DevelopSituation;
import org.jeecg.modules.sportsman.entity.BodyType;
import org.jeecg.modules.sportsman.entity.BodyAbility;
import org.jeecg.modules.sportsman.entity.SportsmanPersonalArchives;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 档案管理
 * @author： jeecg-boot
 * @date：   2019-06-20
 * @version： V1.0
 */
public interface ISportsmanPersonalArchivesService extends IService<SportsmanPersonalArchives> {

	/**
	 * 添加一对多
	 *
	 */
	public void saveMain(SportsmanPersonalArchives sportsmanPersonalArchives, List<SportsmanRank> sportsmanRankList, List<SportsmanResume> sportsmanResumeList, List<SportsmanFamilySituation> sportsmanFamilySituationList, List<SportsmanData> sportsmanDataList, List<DevelopSituation> developSituationList, List<BodyType> bodyTypeList, List<BodyAbility> bodyAbilityList) ;

	/**
	 * 修改一对多
	 *
	 */
	public void updateMain(SportsmanPersonalArchives sportsmanPersonalArchives, List<SportsmanRank> sportsmanRankList, List<SportsmanResume> sportsmanResumeList, List<SportsmanFamilySituation> sportsmanFamilySituationList, List<SportsmanData> sportsmanDataList, List<DevelopSituation> developSituationList, List<BodyType> bodyTypeList, List<BodyAbility> bodyAbilityList);

	/**
	 * 删除一对多
	 */
	public void delMain(String id);

	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);

}
