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
import org.jeecg.modules.salary.entity.HeadDisplay;
import org.jeecg.modules.salary.service.IHeadDisplayService;

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
 * @Description: 首页展示
 * @Author: jeecg-boot
 * @Date:   2021-01-21
 * @Version: V1.0
 */
@Api(tags="首页展示")
@RestController
@RequestMapping("/salary/headDisplay")
@Slf4j
public class HeadDisplayController extends JeecgController<HeadDisplay, IHeadDisplayService> {
	@Autowired
	private IHeadDisplayService headDisplayService;

	/**
	 * 分页列表查询
	 *
	 * @param headDisplay
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "首页展示-分页列表查询")
	@ApiOperation(value="首页展示-分页列表查询", notes="首页展示-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(HeadDisplay headDisplay,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<HeadDisplay> queryWrapper = QueryGenerator.initQueryWrapper(headDisplay, req.getParameterMap());
		Page<HeadDisplay> page = new Page<HeadDisplay>(pageNo, pageSize);
		IPage<HeadDisplay> pageList = headDisplayService.page(page, queryWrapper);
		this.headDisplayService.invoiceUpdate();  //更新未开票
		this.headDisplayService.repayUpdate(); //更新未回款
		this.headDisplayService.longRepayUpdate(); //更新呆账
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param headDisplay
	 * @return
	 */
	@AutoLog(value = "首页展示-添加")
	@ApiOperation(value="首页展示-添加", notes="首页展示-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody HeadDisplay headDisplay) {
		headDisplayService.save(headDisplay);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param headDisplay
	 * @return
	 */
	@AutoLog(value = "首页展示-编辑")
	@ApiOperation(value="首页展示-编辑", notes="首页展示-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody HeadDisplay headDisplay) {
		headDisplayService.updateById(headDisplay);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "首页展示-通过id删除")
	@ApiOperation(value="首页展示-通过id删除", notes="首页展示-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		headDisplayService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "首页展示-批量删除")
	@ApiOperation(value="首页展示-批量删除", notes="首页展示-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.headDisplayService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "首页展示-通过id查询")
	@ApiOperation(value="首页展示-通过id查询", notes="首页展示-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		HeadDisplay headDisplay = headDisplayService.getById(id);
		if(headDisplay==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(headDisplay);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param headDisplay
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HeadDisplay headDisplay) {
        return super.exportXls(request, headDisplay, HeadDisplay.class, "首页展示");
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
        return super.importExcel(request, response, HeadDisplay.class);
    }

}
