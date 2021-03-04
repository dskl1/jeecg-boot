package org.jeecg.modules.sportsman.service.impl;

import org.jeecg.modules.sportsman.entity.StudyScore;
import org.jeecg.modules.sportsman.entity.StudyPost;
import org.jeecg.modules.sportsman.mapper.StudyPostMapper;
import org.jeecg.modules.sportsman.mapper.StudyScoreMapper;
import org.jeecg.modules.sportsman.service.IStudyScoreService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 学习成绩
 * @Author: jeecg-boot
 * @Date:   2020-11-06
 * @Version: V1.0
 */
@Service
public class StudyScoreServiceImpl extends ServiceImpl<StudyScoreMapper, StudyScore> implements IStudyScoreService {

	@Autowired
	private StudyScoreMapper studyScoreMapper;
	@Autowired
	private StudyPostMapper studyPostMapper;
	
	@Override
	@Transactional
	public void saveMain(StudyScore studyScore, List<StudyPost> studyPostList) {
		studyScoreMapper.insert(studyScore);
		if(studyPostList!=null && studyPostList.size()>0) {
			for(StudyPost entity:studyPostList) {
				//外键设置
				entity.setSportsmanId(studyScore.getId());
				studyPostMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(StudyScore studyScore,List<StudyPost> studyPostList) {
		studyScoreMapper.updateById(studyScore);
		
		//1.先删除子表数据
		studyPostMapper.deleteByMainId(studyScore.getId());
		
		//2.子表数据重新插入
		if(studyPostList!=null && studyPostList.size()>0) {
			for(StudyPost entity:studyPostList) {
				//外键设置
				entity.setSportsmanId(studyScore.getId());
				studyPostMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		studyPostMapper.deleteByMainId(id);
		studyScoreMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			studyPostMapper.deleteByMainId(id.toString());
			studyScoreMapper.deleteById(id);
		}
	}
	
}
