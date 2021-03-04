package org.jeecg.modules.sportsman.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.physicaltest.service.ISportsmanTestService;
import org.jeecg.modules.sportsman.entity.SportsmanRegistrationEntry;
import org.jeecg.modules.sportsman.service.ISportsmanRegistrationEntryService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;

 /**
 * @Title: Controller
 * @Description: 队员登记
 * @author： jeecg-boot
 * @date：   2019-06-04
 * @version： V1.0
 */
@RestController
@RequestMapping("/sportsman/sportsmanRegistrationEntry")
@Slf4j
public class SportsmanRegistrationEntryController {
	@Autowired
	private ISportsmanRegistrationEntryService sportsmanRegistrationEntryService;
	 @Autowired
	 private ISportsmanTestService sportsmanTestService;

	/**
	  * 分页列表查询
	 * @param sportsmanRegistrationEntry
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	@PermissionData(pageComponent="sportsman/SportsmanRegistrationEntryList")
	public Result<IPage<SportsmanRegistrationEntry>> queryPageList(SportsmanRegistrationEntry sportsmanRegistrationEntry,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<SportsmanRegistrationEntry>> result = new Result<IPage<SportsmanRegistrationEntry>>();
		QueryWrapper<SportsmanRegistrationEntry> queryWrapper = QueryGenerator.initQueryWrapper(sportsmanRegistrationEntry, req.getParameterMap());
		Page<SportsmanRegistrationEntry> page = new Page<SportsmanRegistrationEntry>(pageNo, pageSize);
		IPage<SportsmanRegistrationEntry> pageList = sportsmanRegistrationEntryService.page(page, queryWrapper);
		sportsmanTestService.cardNumberToSex();//刷新页面时，给年龄赋值
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	  *   添加
	 * @param sportsmanRegistrationEntry
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<SportsmanRegistrationEntry> add(@RequestBody SportsmanRegistrationEntry sportsmanRegistrationEntry) {
		Result<SportsmanRegistrationEntry> result = new Result<SportsmanRegistrationEntry>();
		try {
			sportsmanRegistrationEntryService.save(sportsmanRegistrationEntry);
			result.success("添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			result.error500("操作失败");
		}
		return result;
	}

	/**
	  *  编辑
	 * @param sportsmanRegistrationEntry
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<SportsmanRegistrationEntry> edit(@RequestBody SportsmanRegistrationEntry sportsmanRegistrationEntry) {
		Result<SportsmanRegistrationEntry> result = new Result<SportsmanRegistrationEntry>();
		SportsmanRegistrationEntry sportsmanRegistrationEntryEntity = sportsmanRegistrationEntryService.getById(sportsmanRegistrationEntry.getId());
		if(sportsmanRegistrationEntryEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = sportsmanRegistrationEntryService.updateById(sportsmanRegistrationEntry);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}

		return result;
	}

	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<SportsmanRegistrationEntry> delete(@RequestParam(name="id",required=true) String id) {
		Result<SportsmanRegistrationEntry> result = new Result<SportsmanRegistrationEntry>();
		SportsmanRegistrationEntry sportsmanRegistrationEntry = sportsmanRegistrationEntryService.getById(id);
		if(sportsmanRegistrationEntry==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = sportsmanRegistrationEntryService.removeById(id);
			if(ok) {
				result.success("删除成功!");
			}
		}

		return result;
	}

	/**
	  *  批量删除
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	public Result<SportsmanRegistrationEntry> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<SportsmanRegistrationEntry> result = new Result<SportsmanRegistrationEntry>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.sportsmanRegistrationEntryService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}

	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryById")
	public Result<SportsmanRegistrationEntry> queryById(@RequestParam(name="id",required=true) String id) {
		Result<SportsmanRegistrationEntry> result = new Result<SportsmanRegistrationEntry>();
		SportsmanRegistrationEntry sportsmanRegistrationEntry = sportsmanRegistrationEntryService.getById(id);
		if(sportsmanRegistrationEntry==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(sportsmanRegistrationEntry);
			result.setSuccess(true);
		}
		return result;
	}

  /**
      * 导出excel
   *
   * @param request
   * @param response
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
      // Step.1 组装查询条件
      QueryWrapper<SportsmanRegistrationEntry> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              SportsmanRegistrationEntry sportsmanRegistrationEntry = JSON.parseObject(deString, SportsmanRegistrationEntry.class);
              queryWrapper = QueryGenerator.initQueryWrapper(sportsmanRegistrationEntry, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<SportsmanRegistrationEntry> pageList = sportsmanRegistrationEntryService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "队员登记列表");
      mv.addObject(NormalExcelConstants.CLASS, SportsmanRegistrationEntry.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("队员登记列表数据", "导出人:Jeecg", "导出信息"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
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
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<SportsmanRegistrationEntry> listSportsmanRegistrationEntrys = ExcelImportUtil.importExcel(file.getInputStream(), SportsmanRegistrationEntry.class, params);
              for (SportsmanRegistrationEntry sportsmanRegistrationEntryExcel : listSportsmanRegistrationEntrys) {
                  sportsmanRegistrationEntryService.save(sportsmanRegistrationEntryExcel);
              }
              return Result.ok("文件导入成功！数据行数：" + listSportsmanRegistrationEntrys.size());
          } catch (Exception e) {
              log.error(e.getMessage());
              return Result.error("文件导入失败！");
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.ok("文件导入失败！");
  }

}
