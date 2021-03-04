package org.jeecg.modules.sportsman.service;

import org.jeecg.modules.sportsman.entity.SportsmanResume;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 运动员训练简历
 * @author： jeecg-boot
 * @date：   2019-06-20
 * @version： V1.0
 */
public interface ISportsmanResumeService extends IService<SportsmanResume> {

	public List<SportsmanResume> selectByMainId(String mainId);
}
