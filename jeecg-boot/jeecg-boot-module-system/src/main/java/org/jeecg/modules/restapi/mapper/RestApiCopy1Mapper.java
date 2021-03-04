package org.jeecg.modules.restapi.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.jeecg.modules.restapi.entity.RestApiCopy1;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: rest_api_copy1
 * @Author: jeecg-boot
 * @Date:   2020-06-01
 * @Version: V1.0
 */
public interface RestApiCopy1Mapper extends BaseMapper<RestApiCopy1> {

        @Update("update rest_api_copy1\n" +
                "set name = '金梓恒'\n" +
                "where NFC_ID = 'EA3DE6DD'")
        public void jzhName();
}
