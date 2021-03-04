package org.jeecg.modules.sportsman.service.impl;

import org.jeecg.modules.sportsman.entity.StudyPost;
import org.jeecg.modules.sportsman.mapper.StudyPostMapper;
import org.jeecg.modules.sportsman.service.IStudyPostService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 科目
 * @Author: jeecg-boot
 * @Date:   2020-11-06
 * @Version: V1.0
 */
@Service
public class StudyPostServiceImpl extends ServiceImpl<StudyPostMapper, StudyPost> implements IStudyPostService {
	
	@Autowired
	private StudyPostMapper studyPostMapper;
	
	@Override
	public List<StudyPost> selectByMainId(String mainId) {
		return studyPostMapper.selectByMainId(mainId);
	}
}
