package org.jeecg.modules.sportsman.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.modules.sportsman.entity.ArchiveInfo;
import org.jeecg.modules.sportsman.service.IArchiveInfoService;
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
 * @Description: 归档信息
 * @Author: jeecg-boot
 * @Date:   2020-01-16
 * @Version: V1.0
 */
@Slf4j
@Api(tags="归档信息")
@RestController
@RequestMapping("/sportsman/archiveInfo")
public class ArchiveInfoController extends JeecgController<ArchiveInfo, IArchiveInfoService> {
	@Autowired
	private IArchiveInfoService archiveInfoService;

	/**
	 * 分页列表查询
	 *
	 * @param archiveInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "归档信息-分页列表查询")
	@ApiOperation(value="归档信息-分页列表查询", notes="归档信息-分页列表查询")
	@GetMapping(value = "/list")
	@PermissionData(pageComponent="sportsman/ArchiveInfoList")
	public Result<?> queryPageList(ArchiveInfo archiveInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ArchiveInfo> queryWrapper = QueryGenerator.initQueryWrapper(archiveInfo, req.getParameterMap());
		Page<ArchiveInfo> page = new Page<ArchiveInfo>(pageNo, pageSize);
		IPage<ArchiveInfo> pageList = archiveInfoService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param archiveInfo
	 * @return
	 */
	@AutoLog(value = "归档信息-添加")
	@ApiOperation(value="归档信息-添加", notes="归档信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ArchiveInfo archiveInfo) {
		archiveInfoService.save(archiveInfo);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param archiveInfo
	 * @return
	 */
	@AutoLog(value = "归档信息-编辑")
	@ApiOperation(value="归档信息-编辑", notes="归档信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ArchiveInfo archiveInfo) {
		archiveInfoService.updateById(archiveInfo);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "归档信息-通过id删除")
	@ApiOperation(value="归档信息-通过id删除", notes="归档信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		archiveInfoService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "归档信息-批量删除")
	@ApiOperation(value="归档信息-批量删除", notes="归档信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.archiveInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "归档信息-通过id查询")
	@ApiOperation(value="归档信息-通过id查询", notes="归档信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ArchiveInfo archiveInfo = archiveInfoService.getById(id);
		return Result.ok(archiveInfo);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param archiveInfo
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ArchiveInfo archiveInfo) {
      return super.exportXls(request, archiveInfo, ArchiveInfo.class, "归档信息");
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
      return super.importExcel(request, response, ArchiveInfo.class);
  }

}
