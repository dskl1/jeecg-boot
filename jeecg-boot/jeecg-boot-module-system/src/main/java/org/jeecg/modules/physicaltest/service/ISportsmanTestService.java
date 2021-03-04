package org.jeecg.modules.physicaltest.service;

import org.jeecg.modules.physicaltest.entity.BasicPhysicalTestScore;
import org.jeecg.modules.physicaltest.entity.SportsmanTest;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 人员表
 * @Author: jeecg-boot
 * @Date: 2020-05-12
 * @Version: V1.0
 */
public interface ISportsmanTestService extends IService<SportsmanTest> {

    /**
     * 删除一对多
     */
    public void delMain (String id);

    /**
     * 批量删除一对多
     */
    public void delBatchMain (Collection<? extends Serializable> idList);

    /**
     * 判断身份证号的第17位为奇偶数，给性别赋值
     */
    public void cardNumberToSex();

    /**
     * 将队员表的性别字段值赋给基础体能表
     */
    public void doubleSex();

}
