package org.jeecg.modules.physicaltest.controller;

import org.jeecg.common.system.query.QueryGenerator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import java.util.Arrays;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.physicaltest.entity.BasicPhysicalTestScore;
import org.jeecg.modules.physicaltest.entity.SportsmanTest;
import org.jeecg.modules.physicaltest.service.ISportsmanTestService;
import org.jeecg.modules.physicaltest.service.IBasicPhysicalTestScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

 /**
 * @Description: 体能测试
 * @Author: jeecg-boot
 * @Date:   2020-05-19
 * @Version: V1.0
 */
@Api(tags="体能测试")
@RestController
@RequestMapping("/physicaltest/sportsmanTest")
@Slf4j
public class SportsmanTestController extends JeecgController<SportsmanTest, ISportsmanTestService> {

	@Autowired
	private ISportsmanTestService sportsmanTestService;

	@Autowired
	private IBasicPhysicalTestScoreService basicPhysicalTestScoreService;


	/*---------------------------------主表处理-begin-------------------------------------*/

	/**
	 * 分页列表查询
	 * @param sportsmanTest
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "体能测试-分页列表查询")
	@ApiOperation(value="体能测试-分页列表查询", notes="体能测试-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SportsmanTest sportsmanTest,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SportsmanTest> queryWrapper = QueryGenerator.initQueryWrapper(sportsmanTest, req.getParameterMap());
		Page<SportsmanTest> page = new Page<SportsmanTest>(pageNo, pageSize);
		IPage<SportsmanTest> pageList = sportsmanTestService.page(page, queryWrapper);
		sportsmanTestService.cardNumberToSex();//刷新页面时，给队员表年龄赋值
		sportsmanTestService.doubleSex();//刷新页面时，给基础体能表年龄赋值
		return Result.ok(pageList);
	}

	/**
     *   添加
     * @param sportsmanTest
     * @return
     */
    @AutoLog(value = "体能测试-添加")
    @ApiOperation(value="体能测试-添加", notes="体能测试-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody SportsmanTest sportsmanTest) {
        sportsmanTestService.save(sportsmanTest);
        return Result.ok("添加成功！");
    }

    /**
     *  编辑
     * @param sportsmanTest
     * @return
     */
    @AutoLog(value = "体能测试-编辑")
    @ApiOperation(value="体能测试-编辑", notes="体能测试-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody SportsmanTest sportsmanTest) {
        sportsmanTestService.updateById(sportsmanTest);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     * @param id
     * @return
     */
    @AutoLog(value = "体能测试-通过id删除")
    @ApiOperation(value="体能测试-通过id删除", notes="体能测试-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name="id",required=true) String id) {
        sportsmanTestService.delMain(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @AutoLog(value = "体能测试-批量删除")
    @ApiOperation(value="体能测试-批量删除", notes="体能测试-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
        this.sportsmanTestService.delBatchMain(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功!");
    }

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SportsmanTest sportsmanTest) {
        return super.exportXls(request, sportsmanTest, SportsmanTest.class, "体能测试");
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, SportsmanTest.class);
    }
	/*---------------------------------主表处理-end-------------------------------------*/


    /*--------------------------------子表处理-基础体能评分标准-begin----------------------------------------------*/
	/**
	 * 通过主表ID查询
	 * @return
	 */
	@AutoLog(value = "基础体能评分标准-通过主表ID查询")
	@ApiOperation(value="基础体能评分标准-通过主表ID查询", notes="基础体能评分标准-通过主表ID查询")
	@GetMapping(value = "/listBasicPhysicalTestScoreByMainId")
    public Result<?> listBasicPhysicalTestScoreByMainId(BasicPhysicalTestScore basicPhysicalTestScore,
                                                    @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                    HttpServletRequest req) {
        QueryWrapper<BasicPhysicalTestScore> queryWrapper = QueryGenerator.initQueryWrapper(basicPhysicalTestScore, req.getParameterMap());
        Page<BasicPhysicalTestScore> page = new Page<BasicPhysicalTestScore>(pageNo, pageSize);
        IPage<BasicPhysicalTestScore> pageList = basicPhysicalTestScoreService.page(page, queryWrapper);
		sportsmanTestService.cardNumberToSex();//刷新页面时，给队员表年龄赋值
		sportsmanTestService.doubleSex();//刷新页面时，给基础体能表年龄赋值
        return Result.ok(pageList);
    }

	/**
	 * 添加
	 * @param basicPhysicalTestScore
	 * @return
	 */
	@AutoLog(value = "基础体能评分标准-添加")
	@ApiOperation(value="基础体能评分标准-添加", notes="基础体能评分标准-添加")
	@PostMapping(value = "/addBasicPhysicalTestScore")
	public Result<?> addBasicPhysicalTestScore(@RequestBody BasicPhysicalTestScore basicPhysicalTestScore) {
		basicPhysicalTestScoreService.save(basicPhysicalTestScore);
		return Result.ok("添加成功！");
	}

    /**
	 * 编辑
	 * @param basicPhysicalTestScore
	 * @return
	 */
	@AutoLog(value = "基础体能评分标准-编辑")
	@ApiOperation(value="基础体能评分标准-编辑", notes="基础体能评分标准-编辑")
	@PutMapping(value = "/editBasicPhysicalTestScore")
	public Result<?> editBasicPhysicalTestScore(@RequestBody BasicPhysicalTestScore basicPhysicalTestScore) {
		basicPhysicalTestScoreService.updateById(basicPhysicalTestScore);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "基础体能评分标准-通过id删除")
	@ApiOperation(value="基础体能评分标准-通过id删除", notes="基础体能评分标准-通过id删除")
	@DeleteMapping(value = "/deleteBasicPhysicalTestScore")
	public Result<?> deleteBasicPhysicalTestScore(@RequestParam(name="id",required=true) String id) {
		basicPhysicalTestScoreService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "基础体能评分标准-批量删除")
	@ApiOperation(value="基础体能评分标准-批量删除", notes="基础体能评分标准-批量删除")
	@DeleteMapping(value = "/deleteBatchBasicPhysicalTestScore")
	public Result<?> deleteBatchBasicPhysicalTestScore(@RequestParam(name="ids",required=true) String ids) {
	    this.basicPhysicalTestScoreService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportBasicPhysicalTestScore")
    public ModelAndView exportBasicPhysicalTestScore(HttpServletRequest request, BasicPhysicalTestScore basicPhysicalTestScore) {
		 // Step.1 组装查询条件
		 QueryWrapper<BasicPhysicalTestScore> queryWrapper = QueryGenerator.initQueryWrapper(basicPhysicalTestScore, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		 // Step.2 获取导出数据
		 List<BasicPhysicalTestScore> pageList = basicPhysicalTestScoreService.list(queryWrapper);
		 List<BasicPhysicalTestScore> exportList = null;

		 // 过滤选中数据
		 String selections = request.getParameter("selections");
		 if (oConvertUtils.isNotEmpty(selections)) {
			 List<String> selectionList = Arrays.asList(selections.split(","));
			 exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
		 } else {
			 exportList = pageList;
		 }

		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, "基础体能评分标准"); //此处设置的filename无效 ,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.CLASS, BasicPhysicalTestScore.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("基础体能评分标准报表", "导出人:" + sysUser.getRealname(), "基础体能评分标准"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importBasicPhysicalTestScore/{mainId}")
    public Result<?> importBasicPhysicalTestScore(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
		 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		 Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		 for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			 MultipartFile file = entity.getValue();// 获取上传文件对象
			 ImportParams params = new ImportParams();
			 params.setTitleRows(2);
			 params.setHeadRows(1);
			 params.setNeedSave(true);
			 try {
				 List<BasicPhysicalTestScore> list = ExcelImportUtil.importExcel(file.getInputStream(), BasicPhysicalTestScore.class, params);
				 for (BasicPhysicalTestScore temp : list) {
                    temp.setSportsmanId(mainId);
				 }
				 long start = System.currentTimeMillis();
				 basicPhysicalTestScoreService.saveBatch(list);
				 log.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
				 return Result.ok("文件导入成功！数据行数：" + list.size());
			 } catch (Exception e) {
				 log.error(e.getMessage(), e);
				 return Result.error("文件导入失败:" + e.getMessage());
			 } finally {
				 try {
					 file.getInputStream().close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
		 }
		 return Result.error("文件导入失败！");
    }

    /*--------------------------------子表处理-基础体能评分标准-end----------------------------------------------*/




}
