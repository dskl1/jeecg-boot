package org.jeecg.modules.sportsman.service;

import org.jeecg.modules.sportsman.entity.StudyPost;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 科目
 * @Author: jeecg-boot
 * @Date:   2020-11-06
 * @Version: V1.0
 */
public interface IStudyPostService extends IService<StudyPost> {

	public List<StudyPost> selectByMainId(String mainId);
}
