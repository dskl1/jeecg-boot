package org.jeecg.modules.sportsclub.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.modules.sportsclub.entity.SportsClub;
import org.jeecg.modules.sportsclub.service.ISportsClubService;
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
 * @Description: 俱乐部
 * @Author: jeecg-boot
 * @Date:   2019-11-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="俱乐部")
@RestController
@RequestMapping("/sportsclub/sportsClub")
public class SportsClubController extends JeecgController<SportsClub, ISportsClubService> {
	@Autowired
	private ISportsClubService sportsClubService;

	/**
	 * 分页列表查询
	 *
	 * @param sportsClub
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "俱乐部-分页列表查询")
	@ApiOperation(value="俱乐部-分页列表查询", notes="俱乐部-分页列表查询")
	@GetMapping(value = "/list")
	@PermissionData(pageComponent="sportsclubcopy1/SportsClubCopy1List")
	public Result<?> queryPageList(SportsClub sportsClub,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SportsClub> queryWrapper = QueryGenerator.initQueryWrapper(sportsClub, req.getParameterMap());
		Page<SportsClub> page = new Page<SportsClub>(pageNo, pageSize);
		IPage<SportsClub> pageList = sportsClubService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param sportsClub
	 * @return
	 */
	@AutoLog(value = "俱乐部-添加")
	@ApiOperation(value="俱乐部-添加", notes="俱乐部-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SportsClub sportsClub) {
		sportsClubService.save(sportsClub);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sportsClub
	 * @return
	 */
	@AutoLog(value = "俱乐部-编辑")
	@ApiOperation(value="俱乐部-编辑", notes="俱乐部-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SportsClub sportsClub) {
		sportsClubService.updateById(sportsClub);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "俱乐部-通过id删除")
	@ApiOperation(value="俱乐部-通过id删除", notes="俱乐部-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sportsClubService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "俱乐部-批量删除")
	@ApiOperation(value="俱乐部-批量删除", notes="俱乐部-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sportsClubService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "俱乐部-通过id查询")
	@ApiOperation(value="俱乐部-通过id查询", notes="俱乐部-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SportsClub sportsClub = sportsClubService.getById(id);
		return Result.ok(sportsClub);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sportsClub
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SportsClub sportsClub) {
      return super.exportXls(request, sportsClub, SportsClub.class, "俱乐部");
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
      return super.importExcel(request, response, SportsClub.class);
  }

}
