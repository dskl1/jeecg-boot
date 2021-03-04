package org.jeecg.modules.restapi.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.restapi.entity.RestApi;
import org.jeecg.modules.restapi.service.IRestApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

 /**
 * @Description: api
 * @Author: jeecg-boot
 * @Date:   2019-11-27
 * @Version: V1.0
 */
@Slf4j
@Api(tags="api")
@RestController
@RequestMapping("/restapi/restApi")
public class RestApiController extends JeecgController<RestApi, IRestApiService> {
	@Autowired
	private IRestApiService restApiService;

	/**
	 * 分页列表查询
	 *
	 * @param restApi
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "api-分页列表查询")
	@ApiOperation(value="api-分页列表查询", notes="api-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(RestApi restApi,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<RestApi> queryWrapper = QueryGenerator.initQueryWrapper(restApi, req.getParameterMap());
		Page<RestApi> page = new Page<RestApi>(pageNo, pageSize);
		IPage<RestApi> pageList = restApiService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param restApi
	 * @return
	 */
	@AutoLog(value = "api-添加")
	@ApiOperation(value="api-添加", notes="api-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody RestApi restApi) {
		restApiService.save(restApi);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param restApi
	 * @return
	 */
	@AutoLog(value = "api-编辑")
	@ApiOperation(value="api-编辑", notes="api-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody RestApi restApi) {
		restApiService.updateById(restApi);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "api-通过id删除")
	@ApiOperation(value="api-通过id删除", notes="api-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		restApiService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "api-批量删除")
	@ApiOperation(value="api-批量删除", notes="api-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.restApiService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "api-通过id查询")
	@ApiOperation(value="api-通过id查询", notes="api-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		RestApi restApi = restApiService.getById(id);
		return Result.ok(restApi);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param restApi
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, RestApi restApi) {
      return super.exportXls(request, restApi, RestApi.class, "api");
  }

  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, RestApi.class);
  }

}
