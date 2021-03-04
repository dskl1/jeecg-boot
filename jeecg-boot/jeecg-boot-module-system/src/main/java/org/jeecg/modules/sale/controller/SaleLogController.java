package org.jeecg.modules.sale.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.modules.sale.entity.SaleLog;
import org.jeecg.modules.sale.service.ISaleLogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 销售记录
 * @Author: jeecg-boot
 * @Date:   2020-01-15
 * @Version: V1.0
 */
@Slf4j
@Api(tags="销售记录")
@RestController
@RequestMapping("/sale/saleLog")
public class SaleLogController extends JeecgController<SaleLog, ISaleLogService> {
	@Autowired
	private ISaleLogService saleLogService;

	/**
	 * 分页列表查询
	 *
	 * @param saleLog
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "销售记录-分页列表查询")
	@ApiOperation(value="销售记录-分页列表查询", notes="销售记录-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SaleLog saleLog,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SaleLog> queryWrapper = QueryGenerator.initQueryWrapper(saleLog, req.getParameterMap());
		Page<SaleLog> page = new Page<SaleLog>(pageNo, pageSize);
		IPage<SaleLog> pageList = saleLogService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param saleLog
	 * @return
	 */
	@AutoLog(value = "销售记录-添加")
	@ApiOperation(value="销售记录-添加", notes="销售记录-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SaleLog saleLog) {
		saleLogService.save(saleLog);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param saleLog
	 * @return
	 */
	@AutoLog(value = "销售记录-编辑")
	@ApiOperation(value="销售记录-编辑", notes="销售记录-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SaleLog saleLog) {
		saleLogService.updateById(saleLog);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "销售记录-通过id删除")
	@ApiOperation(value="销售记录-通过id删除", notes="销售记录-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		saleLogService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "销售记录-批量删除")
	@ApiOperation(value="销售记录-批量删除", notes="销售记录-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.saleLogService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "销售记录-通过id查询")
	@ApiOperation(value="销售记录-通过id查询", notes="销售记录-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SaleLog saleLog = saleLogService.getById(id);
		return Result.ok(saleLog);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param saleLog
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SaleLog saleLog) {
      return super.exportXls(request, saleLog, SaleLog.class, "销售记录");
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
      return super.importExcel(request, response, SaleLog.class);
  }

}
