package org.jeecg.modules.sportsclubcopy1.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.modules.sportsclubcopy1.entity.SportsClubCopy1;
import org.jeecg.modules.sportsclubcopy1.service.ISportsClubCopy1Service;
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
 * @Description: 俱乐部1
 * @Author: jeecg-boot
 * @Date:   2019-11-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="俱乐部1")
@RestController
@RequestMapping("/sportsclubcopy1/sportsClubCopy1")
public class SportsClubCopy1Controller extends JeecgController<SportsClubCopy1, ISportsClubCopy1Service> {
	@Autowired
	private ISportsClubCopy1Service sportsClubCopy1Service;

	/**
	 * 分页列表查询
	 *
	 * @param sportsClubCopy1
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "俱乐部1-分页列表查询")
	@ApiOperation(value="俱乐部1-分页列表查询", notes="俱乐部1-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SportsClubCopy1 sportsClubCopy1,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SportsClubCopy1> queryWrapper = QueryGenerator.initQueryWrapper(sportsClubCopy1, req.getParameterMap());
		Page<SportsClubCopy1> page = new Page<SportsClubCopy1>(pageNo, pageSize);
		IPage<SportsClubCopy1> pageList = sportsClubCopy1Service.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param sportsClubCopy1
	 * @return
	 */
	@AutoLog(value = "俱乐部1-添加")
	@ApiOperation(value="俱乐部1-添加", notes="俱乐部1-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SportsClubCopy1 sportsClubCopy1) {
		sportsClubCopy1Service.save(sportsClubCopy1);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sportsClubCopy1
	 * @return
	 */
	@AutoLog(value = "俱乐部1-编辑")
	@ApiOperation(value="俱乐部1-编辑", notes="俱乐部1-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SportsClubCopy1 sportsClubCopy1) {
		sportsClubCopy1Service.updateById(sportsClubCopy1);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "俱乐部1-通过id删除")
	@ApiOperation(value="俱乐部1-通过id删除", notes="俱乐部1-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sportsClubCopy1Service.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "俱乐部1-批量删除")
	@ApiOperation(value="俱乐部1-批量删除", notes="俱乐部1-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sportsClubCopy1Service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "俱乐部1-通过id查询")
	@ApiOperation(value="俱乐部1-通过id查询", notes="俱乐部1-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SportsClubCopy1 sportsClubCopy1 = sportsClubCopy1Service.getById(id);
		return Result.ok(sportsClubCopy1);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sportsClubCopy1
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SportsClubCopy1 sportsClubCopy1) {
      return super.exportXls(request, sportsClubCopy1, SportsClubCopy1.class, "俱乐部1");
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
      return super.importExcel(request, response, SportsClubCopy1.class);
  }

}
