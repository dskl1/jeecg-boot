package org.jeecg.modules.sportsman.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.modules.sportsman.entity.VipEntry;
import org.jeecg.modules.sportsman.service.IVipEntryService;
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
 * @Description: 会员登记
 * @Author: jeecg-boot
 * @Date:   2020-01-13
 * @Version: V1.0
 */
@Slf4j
@Api(tags="会员登记")
@RestController
@RequestMapping("/sportsman/vipEntry")
public class VipEntryController extends JeecgController<VipEntry, IVipEntryService> {
	@Autowired
	private IVipEntryService vipEntryService;

	/**
	 * 分页列表查询
	 *
	 * @param vipEntry
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "会员登记-分页列表查询")
	@ApiOperation(value="会员登记-分页列表查询", notes="会员登记-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(VipEntry vipEntry,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<VipEntry> queryWrapper = QueryGenerator.initQueryWrapper(vipEntry, req.getParameterMap());
		Page<VipEntry> page = new Page<VipEntry>(pageNo, pageSize);
		IPage<VipEntry> pageList = vipEntryService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param vipEntry
	 * @return
	 */
	@AutoLog(value = "会员登记-添加")
	@ApiOperation(value="会员登记-添加", notes="会员登记-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody VipEntry vipEntry) {
		vipEntryService.save(vipEntry);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param vipEntry
	 * @return
	 */
	@AutoLog(value = "会员登记-编辑")
	@ApiOperation(value="会员登记-编辑", notes="会员登记-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody VipEntry vipEntry) {
		vipEntryService.updateById(vipEntry);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "会员登记-通过id删除")
	@ApiOperation(value="会员登记-通过id删除", notes="会员登记-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		vipEntryService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "会员登记-批量删除")
	@ApiOperation(value="会员登记-批量删除", notes="会员登记-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.vipEntryService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "会员登记-通过id查询")
	@ApiOperation(value="会员登记-通过id查询", notes="会员登记-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		VipEntry vipEntry = vipEntryService.getById(id);
		return Result.ok(vipEntry);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param vipEntry
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, VipEntry vipEntry) {
      return super.exportXls(request, vipEntry, VipEntry.class, "会员登记");
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
      return super.importExcel(request, response, VipEntry.class);
  }

}
