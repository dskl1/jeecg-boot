package org.jeecg.modules.restapi.service.impl;

import org.jeecg.modules.restapi.entity.RestApi;
import org.jeecg.modules.restapi.mapper.RestApiMapper;
import org.jeecg.modules.restapi.service.IRestApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: api
 * @Author: jeecg-boot
 * @Date:   2019-11-27
 * @Version: V1.0
 */
@Service
public class RestApiServiceImpl extends ServiceImpl<RestApiMapper, RestApi> implements IRestApiService {


}
