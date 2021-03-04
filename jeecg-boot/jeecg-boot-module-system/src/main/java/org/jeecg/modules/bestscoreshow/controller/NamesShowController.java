package org.jeecg.modules.bestscoreshow.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.modules.bestscoreshow.entity.NamesShow;
import org.jeecg.modules.bestscoreshow.service.INamesShowService;
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
 * @Description: 姓名添加
 * @Author: jeecg-boot
 * @Date:   2019-12-20
 * @Version: V1.0
 */
@Slf4j
@Api(tags="姓名添加")
@RestController
@RequestMapping("/bestscoreshow/namesShow")
public class NamesShowController extends JeecgController<NamesShow, INamesShowService> {
	@Autowired
	private INamesShowService namesShowService;

	/**
	 * 分页列表查询
	 *
	 * @param namesShow
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "姓名添加-分页列表查询")
	@ApiOperation(value="姓名添加-分页列表查询", notes="姓名添加-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(NamesShow namesShow,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<NamesShow> queryWrapper = QueryGenerator.initQueryWrapper(namesShow, req.getParameterMap());
		Page<NamesShow> page = new Page<NamesShow>(pageNo, pageSize);
		IPage<NamesShow> pageList = namesShowService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param namesShow
	 * @return
	 */
	@AutoLog(value = "姓名添加-添加")
	@ApiOperation(value="姓名添加-添加", notes="姓名添加-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody NamesShow namesShow) {
		namesShowService.save(namesShow);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param namesShow
	 * @return
	 */
	@AutoLog(value = "姓名添加-编辑")
	@ApiOperation(value="姓名添加-编辑", notes="姓名添加-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody NamesShow namesShow) {
		namesShowService.updateById(namesShow);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "姓名添加-通过id删除")
	@ApiOperation(value="姓名添加-通过id删除", notes="姓名添加-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		namesShowService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "姓名添加-批量删除")
	@ApiOperation(value="姓名添加-批量删除", notes="姓名添加-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.namesShowService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "姓名添加-通过id查询")
	@ApiOperation(value="姓名添加-通过id查询", notes="姓名添加-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		NamesShow namesShow = namesShowService.getById(id);
		return Result.ok(namesShow);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param namesShow
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, NamesShow namesShow) {
      return super.exportXls(request, namesShow, NamesShow.class, "姓名添加");
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
      return super.importExcel(request, response, NamesShow.class);
  }

}
