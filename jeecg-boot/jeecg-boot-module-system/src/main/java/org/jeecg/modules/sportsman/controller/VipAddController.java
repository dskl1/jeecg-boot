package org.jeecg.modules.sportsman.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.modules.sportsman.entity.VipAdd;
import org.jeecg.modules.sportsman.service.IVipAddService;
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
 * @Description: 添加会员
 * @Author: jeecg-boot
 * @Date:   2020-01-15
 * @Version: V1.0
 */
@Slf4j
@Api(tags="添加会员")
@RestController
@RequestMapping("/sportsman/vipAdd")
public class VipAddController extends JeecgController<VipAdd, IVipAddService> {
	@Autowired
	private IVipAddService vipAddService;

	/**
	 * 分页列表查询
	 *
	 * @param vipAdd
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "添加会员-分页列表查询")
	@ApiOperation(value="添加会员-分页列表查询", notes="添加会员-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(VipAdd vipAdd,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<VipAdd> queryWrapper = QueryGenerator.initQueryWrapper(vipAdd, req.getParameterMap());
		Page<VipAdd> page = new Page<VipAdd>(pageNo, pageSize);
		IPage<VipAdd> pageList = vipAddService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param vipAdd
	 * @return
	 */
	@AutoLog(value = "添加会员-添加")
	@ApiOperation(value="添加会员-添加", notes="添加会员-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody VipAdd vipAdd) {
		vipAddService.save(vipAdd);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param vipAdd
	 * @return
	 */
	@AutoLog(value = "添加会员-编辑")
	@ApiOperation(value="添加会员-编辑", notes="添加会员-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody VipAdd vipAdd) {
		vipAddService.updateById(vipAdd);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "添加会员-通过id删除")
	@ApiOperation(value="添加会员-通过id删除", notes="添加会员-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		vipAddService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "添加会员-批量删除")
	@ApiOperation(value="添加会员-批量删除", notes="添加会员-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.vipAddService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "添加会员-通过id查询")
	@ApiOperation(value="添加会员-通过id查询", notes="添加会员-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		VipAdd vipAdd = vipAddService.getById(id);
		return Result.ok(vipAdd);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param vipAdd
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, VipAdd vipAdd) {
      return super.exportXls(request, vipAdd, VipAdd.class, "添加会员");
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
      return super.importExcel(request, response, VipAdd.class);
  }

}
