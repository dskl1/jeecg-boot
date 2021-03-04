package org.jeecg.modules.restapi.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.jeecg.modules.restapi.entity.RestApiCopy1;
import org.jeecg.modules.restapi.mapper.RestApiCopy1Mapper;
import org.jeecg.modules.restapi.service.IRestApiCopy1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: rest_api_copy1
 * @Author: jeecg-boot
 * @Date: 2020-06-01
 * @Version: V1.0
 */
@Service
public class RestApiCopy1ServiceImpl extends ServiceImpl<RestApiCopy1Mapper, RestApiCopy1> implements IRestApiCopy1Service {

    @Autowired
    private RestApiCopy1Mapper restApiCopy1Mapper;

    @Override
    public void jzhName(){this.restApiCopy1Mapper.jzhName();}
}
