package org.jeecg.modules.sportsman.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.modules.sportsman.entity.Vip;
import org.jeecg.modules.sportsman.service.IVipService;
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
 * @Description: 会员管理
 * @Author: jeecg-boot
 * @Date:   2020-01-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="会员管理")
@RestController
@RequestMapping("/sportsman/vip")
public class VipController extends JeecgController<Vip, IVipService> {
	@Autowired
	private IVipService vipService;

	/**
	 * 分页列表查询
	 *
	 * @param vip
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "会员管理-分页列表查询")
	@ApiOperation(value="会员管理-分页列表查询", notes="会员管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Vip vip,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Vip> queryWrapper = QueryGenerator.initQueryWrapper(vip, req.getParameterMap());
		Page<Vip> page = new Page<Vip>(pageNo, pageSize);
		IPage<Vip> pageList = vipService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param vip
	 * @return
	 */
	@AutoLog(value = "会员管理-添加")
	@ApiOperation(value="会员管理-添加", notes="会员管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Vip vip) {
		vipService.save(vip);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param vip
	 * @return
	 */
	@AutoLog(value = "会员管理-编辑")
	@ApiOperation(value="会员管理-编辑", notes="会员管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Vip vip) {
		vipService.updateById(vip);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "会员管理-通过id删除")
	@ApiOperation(value="会员管理-通过id删除", notes="会员管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		vipService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "会员管理-批量删除")
	@ApiOperation(value="会员管理-批量删除", notes="会员管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.vipService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "会员管理-通过id查询")
	@ApiOperation(value="会员管理-通过id查询", notes="会员管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Vip vip = vipService.getById(id);
		return Result.ok(vip);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param vip
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Vip vip) {
      return super.exportXls(request, vip, Vip.class, "会员管理");
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
      return super.importExcel(request, response, Vip.class);
  }

}
