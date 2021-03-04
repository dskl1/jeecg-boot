package org.jeecg.modules.salary.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.salary.entity.InvoicingStatistics;
import org.jeecg.modules.salary.entity.SalaryStatisticsCopy1;
import org.jeecg.modules.salary.service.IInvoicingStatisticsService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 开票统计
 * @Author: jeecg-boot
 * @Date:   2020-09-28
 * @Version: V1.0
 */
@Api(tags="开票统计")
@RestController
@RequestMapping("/salary/invoicingStatistics")
@Slf4j
public class InvoicingStatisticsController extends JeecgController<InvoicingStatistics, IInvoicingStatisticsService> {
	@Autowired
	private IInvoicingStatisticsService invoicingStatisticsService;

	/**
	 * 分页列表查询
	 *
	 * @param invoicingStatistics
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "开票统计-分页列表查询")
	@ApiOperation(value="开票统计-分页列表查询", notes="开票统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(InvoicingStatistics invoicingStatistics,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<InvoicingStatistics> queryWrapper = QueryGenerator.initQueryWrapper(invoicingStatistics, req.getParameterMap());
		Page<InvoicingStatistics> page = new Page<InvoicingStatistics>(pageNo, pageSize);
		IPage<InvoicingStatistics> pageList = invoicingStatisticsService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 *   添加
	 *
	 * @param invoicingStatistics
	 * @return
	 */
	@AutoLog(value = "开票统计-添加")
	@ApiOperation(value="开票统计-添加", notes="开票统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody InvoicingStatistics invoicingStatistics) {
		invoicingStatisticsService.save(invoicingStatistics);
		return Result.ok("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param invoicingStatistics
	 * @return
	 */
	@AutoLog(value = "开票统计-编辑")
	@ApiOperation(value="开票统计-编辑", notes="开票统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody InvoicingStatistics invoicingStatistics) {
		invoicingStatisticsService.updateById(invoicingStatistics);
		return Result.ok("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "开票统计-通过id删除")
	@ApiOperation(value="开票统计-通过id删除", notes="开票统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		invoicingStatisticsService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "开票统计-批量删除")
	@ApiOperation(value="开票统计-批量删除", notes="开票统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.invoicingStatisticsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "开票统计-通过id查询")
	@ApiOperation(value="开票统计-通过id查询", notes="开票统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		InvoicingStatistics invoicingStatistics = invoicingStatisticsService.getById(id);
		if(invoicingStatistics==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(invoicingStatistics);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param invoicingStatistics
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, InvoicingStatistics invoicingStatistics) {
        return super.exportXls(request, invoicingStatistics, InvoicingStatistics.class, "开票统计");
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
        return super.importExcel(request, response, InvoicingStatistics.class);
    }

	 /**
	  * 查询历史工资发放
	  *
	  * @return
	  */
	 @AutoLog(value = "开票统计-查询历史工资发放")
	 @ApiOperation(value = "开票统计-查询历史工资发放", notes = "开票统计-查询历史工资发放")
	 @GetMapping(value = "/queryByMonth")
	 public Result<Object> queryByMonth(@RequestParam(name = "format", required = true) String format) {
		 List<InvoicingStatistics> invoicingStatistics = invoicingStatisticsService.selectByMonth(format);
		 if (invoicingStatistics == null) {
			 return Result.error("未找到对应数据");
		 }
		 return Result.ok(invoicingStatistics);
	 }

}
