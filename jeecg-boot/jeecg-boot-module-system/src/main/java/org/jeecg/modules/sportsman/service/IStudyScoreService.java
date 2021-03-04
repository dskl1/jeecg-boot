package org.jeecg.modules.sportsman.service;

import org.jeecg.modules.sportsman.entity.StudyPost;
import org.jeecg.modules.sportsman.entity.StudyScore;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 学习成绩
 * @Author: jeecg-boot
 * @Date:   2020-11-06
 * @Version: V1.0
 */
public interface IStudyScoreService extends IService<StudyScore> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(StudyScore studyScore,List<StudyPost> studyPostList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(StudyScore studyScore,List<StudyPost> studyPostList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
