package org.jeecg.modules.training.controller;

import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.system.query.QueryGenerator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.training.entity.*;
import org.jeecg.modules.training.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

import org.jeecg.common.util.oConvertUtils;
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
 * @Description: 队员表
 * @Author: jeecg-boot
 * @Date: 2020-05-20
 * @Version: V1.0
 */
@Api(tags = "队员表")
@RestController
@RequestMapping("/training/trainingScore")
@Slf4j
public class TrainingScoreController extends JeecgController<TrainingScore, ITrainingScoreService> {

    @Autowired
    private ITrainingScoreService trainingScoreService;

    @Autowired
    private ID25Service d25Service;

    @Autowired
    private ID50Service d50Service;

    @Autowired
    private ID100Service d100Service;

    @Autowired
    private ID150Service d150Service;

    @Autowired
    private ID200Service d200Service;

    @Autowired
    private ID250Service d250Service;

    @Autowired
    private ID300Service d300Service;

    @Autowired
    private ID350Service d350Service;

    @Autowired
    private ID400Service d400Service;

    @Autowired
    private ID800Service d800Service;

    @Autowired
    private ID1000Service d1000Service;

    @Autowired
    private ID1500Service d1500Service;


    /*---------------------------------主表处理-begin-------------------------------------*/

    /**
     * 分页列表查询
     *
     * @param trainingScore
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "队员表-分页列表查询")
    @ApiOperation(value = "队员表-分页列表查询", notes = "队员表-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(TrainingScore trainingScore,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<TrainingScore> queryWrapper = QueryGenerator.initQueryWrapper(trainingScore, req.getParameterMap());
        Page<TrainingScore> page = new Page<TrainingScore>(pageNo, pageSize);
        this.trainingScoreService.insertName(); //打卡人员插到当天计划下
        this.trainingScoreService.setFlagSee(); //设置打卡标记为0
        this.trainingScoreService.setFlagNoSee(); //设置打卡标记为1
        this.trainingScoreService.insertNameCopy();  //count(name)=1必定是当天新增的数据，直接插入training_score_copy1表
        this.trainingScoreService.updateTimeCopy(); //count(name) > 1时，查找在training_score表中当天是否有新增数据，有就更新training_score_copy1对应的名字的update_time
        this.trainingScoreService.setFlagSeeCopy();  //设置training_score_copy1卡标记为0
        this.trainingScoreService.setFlagNoSeeCopy(); //设置training_score_copy1卡标记为1
        IPage<TrainingScore> pageList = trainingScoreService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param trainingScore
     * @return
     */
    @AutoLog(value = "队员表-添加")
    @ApiOperation(value = "队员表-添加", notes = "队员表-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody TrainingScore trainingScore) {
        trainingScoreService.save(trainingScore);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param trainingScore
     * @return
     */
    @AutoLog(value = "队员表-编辑")
    @ApiOperation(value = "队员表-编辑", notes = "队员表-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody TrainingScore trainingScore) {
        trainingScoreService.updateById(trainingScore);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "队员表-通过id删除")
    @ApiOperation(value = "队员表-通过id删除", notes = "队员表-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        trainingScoreService.delMain(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "队员表-批量删除")
    @ApiOperation(value = "队员表-批量删除", notes = "队员表-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.trainingScoreService.delBatchMain(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功!");
    }

    /**
     * 导出
     *
     * @return
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TrainingScore trainingScore) {
        return super.exportXls(request, trainingScore, TrainingScore.class, "队员表");
    }

    /**
     * 导入
     *
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, TrainingScore.class);
    }
    /*---------------------------------主表处理-end-------------------------------------*/


    /*--------------------------------子表处理-25-begin----------------------------------------------*/

    /**
     * 通过主表ID查询
     *
     * @return
     */
    @AutoLog(value = "25-通过主表ID查询")
    @ApiOperation(value = "25-通过主表ID查询", notes = "25-通过主表ID查询")
    @GetMapping(value = "/listD25ByMainId")
    public Result<?> listD25ByMainId(D25 d25,
                                     @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                     HttpServletRequest req) {
        QueryWrapper<D25> queryWrapper = QueryGenerator.initQueryWrapper(d25, req.getParameterMap());
        Page<D25> page = new Page<D25>(pageNo, pageSize);
        IPage<D25> pageList = d25Service.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param d25
     * @return
     */
    @AutoLog(value = "25-添加")
    @ApiOperation(value = "25-添加", notes = "25-添加")
    @PostMapping(value = "/addD25")
    public Result<?> addD25(@RequestBody D25 d25) {
        d25Service.save(d25);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param d25
     * @return
     */
    @AutoLog(value = "25-编辑")
    @ApiOperation(value = "25-编辑", notes = "25-编辑")
    @PutMapping(value = "/editD25")
    public Result<?> editD25(@RequestBody D25 d25) {
        d25Service.updateById(d25);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "25-通过id删除")
    @ApiOperation(value = "25-通过id删除", notes = "25-通过id删除")
    @DeleteMapping(value = "/deleteD25")
    public Result<?> deleteD25(@RequestParam(name = "id", required = true) String id) {
        d25Service.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "25-批量删除")
    @ApiOperation(value = "25-批量删除", notes = "25-批量删除")
    @DeleteMapping(value = "/deleteBatchD25")
    public Result<?> deleteBatchD25(@RequestParam(name = "ids", required = true) String ids) {
        this.d25Service.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功!");
    }

    /**
     * 导出
     *
     * @return
     */
    @RequestMapping(value = "/exportD25")
    public ModelAndView exportD25(HttpServletRequest request, D25 d25) {
        // Step.1 组装查询条件
        QueryWrapper<D25> queryWrapper = QueryGenerator.initQueryWrapper(d25, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        // Step.2 获取导出数据
        List<D25> pageList = d25Service.list(queryWrapper);
        List<D25> exportList = null;

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
        mv.addObject(NormalExcelConstants.FILE_NAME, "25"); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, D25.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("25报表", "导出人:" + sysUser.getRealname(), "25"));
        mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
        return mv;
    }

    /**
     * 导入
     *
     * @return
     */
    @RequestMapping(value = "/importD25/{mainId}")
    public Result<?> importD25(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<D25> list = ExcelImportUtil.importExcel(file.getInputStream(), D25.class, params);
                for (D25 temp : list) {
                    temp.setSportsmanId(mainId);
                }
                long start = System.currentTimeMillis();
                d25Service.saveBatch(list);
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

    /*--------------------------------子表处理-25-end----------------------------------------------*/

    /*--------------------------------子表处理-50-begin----------------------------------------------*/

    /**
     * 通过主表ID查询
     *
     * @return
     */
    @AutoLog(value = "50-通过主表ID查询")
    @ApiOperation(value = "50-通过主表ID查询", notes = "50-通过主表ID查询")
    @GetMapping(value = "/listD50ByMainId")
    public Result<?> listD50ByMainId(D50 d50,
                                     @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                     HttpServletRequest req) {
        QueryWrapper<D50> queryWrapper = QueryGenerator.initQueryWrapper(d50, req.getParameterMap());
        Page<D50> page = new Page<D50>(pageNo, pageSize);
        IPage<D50> pageList = d50Service.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param d50
     * @return
     */
    @AutoLog(value = "50-添加")
    @ApiOperation(value = "50-添加", notes = "50-添加")
    @PostMapping(value = "/addD50")
    public Result<?> addD50(@RequestBody D50 d50) {
        d50Service.save(d50);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param d50
     * @return
     */
    @AutoLog(value = "50-编辑")
    @ApiOperation(value = "50-编辑", notes = "50-编辑")
    @PutMapping(value = "/editD50")
    public Result<?> editD50(@RequestBody D50 d50) {
        d50Service.updateById(d50);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "50-通过id删除")
    @ApiOperation(value = "50-通过id删除", notes = "50-通过id删除")
    @DeleteMapping(value = "/deleteD50")
    public Result<?> deleteD50(@RequestParam(name = "id", required = true) String id) {
        d50Service.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "50-批量删除")
    @ApiOperation(value = "50-批量删除", notes = "50-批量删除")
    @DeleteMapping(value = "/deleteBatchD50")
    public Result<?> deleteBatchD50(@RequestParam(name = "ids", required = true) String ids) {
        this.d50Service.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功!");
    }

    /**
     * 导出
     *
     * @return
     */
    @RequestMapping(value = "/exportD50")
    public ModelAndView exportD50(HttpServletRequest request, D50 d50) {
        // Step.1 组装查询条件
        QueryWrapper<D50> queryWrapper = QueryGenerator.initQueryWrapper(d50, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        // Step.2 获取导出数据
        List<D50> pageList = d50Service.list(queryWrapper);
        List<D50> exportList = null;

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
        mv.addObject(NormalExcelConstants.FILE_NAME, "50"); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, D50.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("50报表", "导出人:" + sysUser.getRealname(), "50"));
        mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
        return mv;
    }

    /**
     * 导入
     *
     * @return
     */
    @RequestMapping(value = "/importD50/{mainId}")
    public Result<?> importD50(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<D50> list = ExcelImportUtil.importExcel(file.getInputStream(), D50.class, params);
                for (D50 temp : list) {
                    temp.setSportsmanId(mainId);
                }
                long start = System.currentTimeMillis();
                d50Service.saveBatch(list);
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

    /*--------------------------------子表处理-50-end----------------------------------------------*/

    /*--------------------------------子表处理-100-begin----------------------------------------------*/

    /**
     * 通过主表ID查询
     *
     * @return
     */
    @AutoLog(value = "100-通过主表ID查询")
    @ApiOperation(value = "100-通过主表ID查询", notes = "100-通过主表ID查询")
    @GetMapping(value = "/listD100ByMainId")
    public Result<?> listD100ByMainId(D100 d100,
                                      @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                      HttpServletRequest req) {
        QueryWrapper<D100> queryWrapper = QueryGenerator.initQueryWrapper(d100, req.getParameterMap());
        Page<D100> page = new Page<D100>(pageNo, pageSize);
        IPage<D100> pageList = d100Service.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param d100
     * @return
     */
    @AutoLog(value = "100-添加")
    @ApiOperation(value = "100-添加", notes = "100-添加")
    @PostMapping(value = "/addD100")
    public Result<?> addD100(@RequestBody D100 d100) {
        d100Service.save(d100);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param d100
     * @return
     */
    @AutoLog(value = "100-编辑")
    @ApiOperation(value = "100-编辑", notes = "100-编辑")
    @PutMapping(value = "/editD100")
    public Result<?> editD100(@RequestBody D100 d100) {
        d100Service.updateById(d100);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "100-通过id删除")
    @ApiOperation(value = "100-通过id删除", notes = "100-通过id删除")
    @DeleteMapping(value = "/deleteD100")
    public Result<?> deleteD100(@RequestParam(name = "id", required = true) String id) {
        d100Service.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "100-批量删除")
    @ApiOperation(value = "100-批量删除", notes = "100-批量删除")
    @DeleteMapping(value = "/deleteBatchD100")
    public Result<?> deleteBatchD100(@RequestParam(name = "ids", required = true) String ids) {
        this.d100Service.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功!");
    }

    /**
     * 导出
     *
     * @return
     */
    @RequestMapping(value = "/exportD100")
    public ModelAndView exportD100(HttpServletRequest request, D100 d100) {
        // Step.1 组装查询条件
        QueryWrapper<D100> queryWrapper = QueryGenerator.initQueryWrapper(d100, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        // Step.2 获取导出数据
        List<D100> pageList = d100Service.list(queryWrapper);
        List<D100> exportList = null;

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
        mv.addObject(NormalExcelConstants.FILE_NAME, "100"); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, D100.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("100报表", "导出人:" + sysUser.getRealname(), "100"));
        mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
        return mv;
    }

    /**
     * 导入
     *
     * @return
     */
    @RequestMapping(value = "/importD100/{mainId}")
    public Result<?> importD100(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<D100> list = ExcelImportUtil.importExcel(file.getInputStream(), D100.class, params);
                for (D100 temp : list) {
                    temp.setSportsmanId(mainId);
                }
                long start = System.currentTimeMillis();
                d100Service.saveBatch(list);
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

    /*--------------------------------子表处理-100-end----------------------------------------------*/

    /*--------------------------------子表处理-150-begin----------------------------------------------*/

    /**
     * 通过主表ID查询
     *
     * @return
     */
    @AutoLog(value = "150-通过主表ID查询")
    @ApiOperation(value = "150-通过主表ID查询", notes = "150-通过主表ID查询")
    @GetMapping(value = "/listD150ByMainId")
    public Result<?> listD150ByMainId(D150 d150,
                                      @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                      HttpServletRequest req) {
        QueryWrapper<D150> queryWrapper = QueryGenerator.initQueryWrapper(d150, req.getParameterMap());
        Page<D150> page = new Page<D150>(pageNo, pageSize);
        IPage<D150> pageList = d150Service.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param d150
     * @return
     */
    @AutoLog(value = "150-添加")
    @ApiOperation(value = "150-添加", notes = "150-添加")
    @PostMapping(value = "/addD150")
    public Result<?> addD150(@RequestBody D150 d150) {
        d150Service.save(d150);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param d150
     * @return
     */
    @AutoLog(value = "150-编辑")
    @ApiOperation(value = "150-编辑", notes = "150-编辑")
    @PutMapping(value = "/editD150")
    public Result<?> editD150(@RequestBody D150 d150) {
        d150Service.updateById(d150);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "150-通过id删除")
    @ApiOperation(value = "150-通过id删除", notes = "150-通过id删除")
    @DeleteMapping(value = "/deleteD150")
    public Result<?> deleteD150(@RequestParam(name = "id", required = true) String id) {
        d150Service.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "150-批量删除")
    @ApiOperation(value = "150-批量删除", notes = "150-批量删除")
    @DeleteMapping(value = "/deleteBatchD150")
    public Result<?> deleteBatchD150(@RequestParam(name = "ids", required = true) String ids) {
        this.d150Service.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功!");
    }

    /**
     * 导出
     *
     * @return
     */
    @RequestMapping(value = "/exportD150")
    public ModelAndView exportD150(HttpServletRequest request, D150 d150) {
        // Step.1 组装查询条件
        QueryWrapper<D150> queryWrapper = QueryGenerator.initQueryWrapper(d150, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        // Step.2 获取导出数据
        List<D150> pageList = d150Service.list(queryWrapper);
        List<D150> exportList = null;

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
        mv.addObject(NormalExcelConstants.FILE_NAME, "150"); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, D150.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("150报表", "导出人:" + sysUser.getRealname(), "150"));
        mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
        return mv;
    }

    /**
     * 导入
     *
     * @return
     */
    @RequestMapping(value = "/importD150/{mainId}")
    public Result<?> importD150(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<D150> list = ExcelImportUtil.importExcel(file.getInputStream(), D150.class, params);
                for (D150 temp : list) {
                    temp.setSportsmanId(mainId);
                }
                long start = System.currentTimeMillis();
                d150Service.saveBatch(list);
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

    /*--------------------------------子表处理-150-end----------------------------------------------*/

    /*--------------------------------子表处理-200-begin----------------------------------------------*/

    /**
     * 通过主表ID查询
     *
     * @return
     */
    @AutoLog(value = "200-通过主表ID查询")
    @ApiOperation(value = "200-通过主表ID查询", notes = "200-通过主表ID查询")
    @GetMapping(value = "/listD200ByMainId")
    public Result<?> listD200ByMainId(D200 d200,
                                      @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                      HttpServletRequest req) {
        QueryWrapper<D200> queryWrapper = QueryGenerator.initQueryWrapper(d200, req.getParameterMap());
        Page<D200> page = new Page<D200>(pageNo, pageSize);
        IPage<D200> pageList = d200Service.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param d200
     * @return
     */
    @AutoLog(value = "200-添加")
    @ApiOperation(value = "200-添加", notes = "200-添加")
    @PostMapping(value = "/addD200")
    public Result<?> addD200(@RequestBody D200 d200) {
        d200Service.save(d200);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param d200
     * @return
     */
    @AutoLog(value = "200-编辑")
    @ApiOperation(value = "200-编辑", notes = "200-编辑")
    @PutMapping(value = "/editD200")
    public Result<?> editD200(@RequestBody D200 d200) {
        d200Service.updateById(d200);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "200-通过id删除")
    @ApiOperation(value = "200-通过id删除", notes = "200-通过id删除")
    @DeleteMapping(value = "/deleteD200")
    public Result<?> deleteD200(@RequestParam(name = "id", required = true) String id) {
        d200Service.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "200-批量删除")
    @ApiOperation(value = "200-批量删除", notes = "200-批量删除")
    @DeleteMapping(value = "/deleteBatchD200")
    public Result<?> deleteBatchD200(@RequestParam(name = "ids", required = true) String ids) {
        this.d200Service.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功!");
    }

    /**
     * 导出
     *
     * @return
     */
    @RequestMapping(value = "/exportD200")
    public ModelAndView exportD200(HttpServletRequest request, D200 d200) {
        // Step.1 组装查询条件
        QueryWrapper<D200> queryWrapper = QueryGenerator.initQueryWrapper(d200, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        // Step.2 获取导出数据
        List<D200> pageList = d200Service.list(queryWrapper);
        List<D200> exportList = null;

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
        mv.addObject(NormalExcelConstants.FILE_NAME, "200"); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, D200.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("200报表", "导出人:" + sysUser.getRealname(), "200"));
        mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
        return mv;
    }

    /**
     * 导入
     *
     * @return
     */
    @RequestMapping(value = "/importD200/{mainId}")
    public Result<?> importD200(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<D200> list = ExcelImportUtil.importExcel(file.getInputStream(), D200.class, params);
                for (D200 temp : list) {
                    temp.setSportsmanId(mainId);
                }
                long start = System.currentTimeMillis();
                d200Service.saveBatch(list);
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

    /*--------------------------------子表处理-200-end----------------------------------------------*/

    /*--------------------------------子表处理-250-begin----------------------------------------------*/

    /**
     * 通过主表ID查询
     *
     * @return
     */
    @AutoLog(value = "250-通过主表ID查询")
    @ApiOperation(value = "250-通过主表ID查询", notes = "250-通过主表ID查询")
    @GetMapping(value = "/listD250ByMainId")
    public Result<?> listD250ByMainId(D250 d250,
                                      @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                      HttpServletRequest req) {
        QueryWrapper<D250> queryWrapper = QueryGenerator.initQueryWrapper(d250, req.getParameterMap());
        Page<D250> page = new Page<D250>(pageNo, pageSize);
        IPage<D250> pageList = d250Service.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param d250
     * @return
     */
    @AutoLog(value = "250-添加")
    @ApiOperation(value = "250-添加", notes = "250-添加")
    @PostMapping(value = "/addD250")
    public Result<?> addD250(@RequestBody D250 d250) {
        d250Service.save(d250);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param d250
     * @return
     */
    @AutoLog(value = "250-编辑")
    @ApiOperation(value = "250-编辑", notes = "250-编辑")
    @PutMapping(value = "/editD250")
    public Result<?> editD250(@RequestBody D250 d250) {
        d250Service.updateById(d250);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "250-通过id删除")
    @ApiOperation(value = "250-通过id删除", notes = "250-通过id删除")
    @DeleteMapping(value = "/deleteD250")
    public Result<?> deleteD250(@RequestParam(name = "id", required = true) String id) {
        d250Service.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "250-批量删除")
    @ApiOperation(value = "250-批量删除", notes = "250-批量删除")
    @DeleteMapping(value = "/deleteBatchD250")
    public Result<?> deleteBatchD250(@RequestParam(name = "ids", required = true) String ids) {
        this.d250Service.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功!");
    }

    /**
     * 导出
     *
     * @return
     */
    @RequestMapping(value = "/exportD250")
    public ModelAndView exportD250(HttpServletRequest request, D250 d250) {
        // Step.1 组装查询条件
        QueryWrapper<D250> queryWrapper = QueryGenerator.initQueryWrapper(d250, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        // Step.2 获取导出数据
        List<D250> pageList = d250Service.list(queryWrapper);
        List<D250> exportList = null;

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
        mv.addObject(NormalExcelConstants.FILE_NAME, "250"); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, D250.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("250报表", "导出人:" + sysUser.getRealname(), "250"));
        mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
        return mv;
    }

    /**
     * 导入
     *
     * @return
     */
    @RequestMapping(value = "/importD250/{mainId}")
    public Result<?> importD250(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<D250> list = ExcelImportUtil.importExcel(file.getInputStream(), D250.class, params);
                for (D250 temp : list) {
                    temp.setSportsmanId(mainId);
                }
                long start = System.currentTimeMillis();
                d250Service.saveBatch(list);
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

    /*--------------------------------子表处理-250-end----------------------------------------------*/

    /*--------------------------------子表处理-300-begin----------------------------------------------*/

    /**
     * 通过主表ID查询
     *
     * @return
     */
    @AutoLog(value = "300-通过主表ID查询")
    @ApiOperation(value = "300-通过主表ID查询", notes = "300-通过主表ID查询")
    @GetMapping(value = "/listD300ByMainId")
    public Result<?> listD300ByMainId(D300 d300,
                                      @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                      HttpServletRequest req) {
        QueryWrapper<D300> queryWrapper = QueryGenerator.initQueryWrapper(d300, req.getParameterMap());
        Page<D300> page = new Page<D300>(pageNo, pageSize);
        IPage<D300> pageList = d300Service.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param d300
     * @return
     */
    @AutoLog(value = "300-添加")
    @ApiOperation(value = "300-添加", notes = "300-添加")
    @PostMapping(value = "/addD300")
    public Result<?> addD300(@RequestBody D300 d300) {
        d300Service.save(d300);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param d300
     * @return
     */
    @AutoLog(value = "300-编辑")
    @ApiOperation(value = "300-编辑", notes = "300-编辑")
    @PutMapping(value = "/editD300")
    public Result<?> editD300(@RequestBody D300 d300) {
        d300Service.updateById(d300);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "300-通过id删除")
    @ApiOperation(value = "300-通过id删除", notes = "300-通过id删除")
    @DeleteMapping(value = "/deleteD300")
    public Result<?> deleteD300(@RequestParam(name = "id", required = true) String id) {
        d300Service.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "300-批量删除")
    @ApiOperation(value = "300-批量删除", notes = "300-批量删除")
    @DeleteMapping(value = "/deleteBatchD300")
    public Result<?> deleteBatchD300(@RequestParam(name = "ids", required = true) String ids) {
        this.d300Service.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功!");
    }

    /**
     * 导出
     *
     * @return
     */
    @RequestMapping(value = "/exportD300")
    public ModelAndView exportD300(HttpServletRequest request, D300 d300) {
        // Step.1 组装查询条件
        QueryWrapper<D300> queryWrapper = QueryGenerator.initQueryWrapper(d300, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        // Step.2 获取导出数据
        List<D300> pageList = d300Service.list(queryWrapper);
        List<D300> exportList = null;

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
        mv.addObject(NormalExcelConstants.FILE_NAME, "300"); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, D300.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("300报表", "导出人:" + sysUser.getRealname(), "300"));
        mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
        return mv;
    }

    /**
     * 导入
     *
     * @return
     */
    @RequestMapping(value = "/importD300/{mainId}")
    public Result<?> importD300(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<D300> list = ExcelImportUtil.importExcel(file.getInputStream(), D300.class, params);
                for (D300 temp : list) {
                    temp.setSportsmanId(mainId);
                }
                long start = System.currentTimeMillis();
                d300Service.saveBatch(list);
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

    /*--------------------------------子表处理-300-end----------------------------------------------*/

    /*--------------------------------子表处理-350-begin----------------------------------------------*/

    /**
     * 通过主表ID查询
     *
     * @return
     */
    @AutoLog(value = "350-通过主表ID查询")
    @ApiOperation(value = "350-通过主表ID查询", notes = "350-通过主表ID查询")
    @GetMapping(value = "/listD350ByMainId")
    public Result<?> listD350ByMainId(D350 d350,
                                      @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                      HttpServletRequest req) {
        QueryWrapper<D350> queryWrapper = QueryGenerator.initQueryWrapper(d350, req.getParameterMap());
        Page<D350> page = new Page<D350>(pageNo, pageSize);
        IPage<D350> pageList = d350Service.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param d350
     * @return
     */
    @AutoLog(value = "350-添加")
    @ApiOperation(value = "350-添加", notes = "350-添加")
    @PostMapping(value = "/addD350")
    public Result<?> addD350(@RequestBody D350 d350) {
        d350Service.save(d350);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param d350
     * @return
     */
    @AutoLog(value = "350-编辑")
    @ApiOperation(value = "350-编辑", notes = "350-编辑")
    @PutMapping(value = "/editD350")
    public Result<?> editD350(@RequestBody D350 d350) {
        d350Service.updateById(d350);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "350-通过id删除")
    @ApiOperation(value = "350-通过id删除", notes = "350-通过id删除")
    @DeleteMapping(value = "/deleteD350")
    public Result<?> deleteD350(@RequestParam(name = "id", required = true) String id) {
        d350Service.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "350-批量删除")
    @ApiOperation(value = "350-批量删除", notes = "350-批量删除")
    @DeleteMapping(value = "/deleteBatchD350")
    public Result<?> deleteBatchD350(@RequestParam(name = "ids", required = true) String ids) {
        this.d350Service.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功!");
    }

    /**
     * 导出
     *
     * @return
     */
    @RequestMapping(value = "/exportD350")
    public ModelAndView exportD350(HttpServletRequest request, D350 d350) {
        // Step.1 组装查询条件
        QueryWrapper<D350> queryWrapper = QueryGenerator.initQueryWrapper(d350, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        // Step.2 获取导出数据
        List<D350> pageList = d350Service.list(queryWrapper);
        List<D350> exportList = null;

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
        mv.addObject(NormalExcelConstants.FILE_NAME, "350"); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, D350.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("350报表", "导出人:" + sysUser.getRealname(), "350"));
        mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
        return mv;
    }

    /**
     * 导入
     *
     * @return
     */
    @RequestMapping(value = "/importD350/{mainId}")
    public Result<?> importD350(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<D350> list = ExcelImportUtil.importExcel(file.getInputStream(), D350.class, params);
                for (D350 temp : list) {
                    temp.setSportsmanId(mainId);
                }
                long start = System.currentTimeMillis();
                d350Service.saveBatch(list);
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

    /*--------------------------------子表处理-350-end----------------------------------------------*/

    /*--------------------------------子表处理-400-begin----------------------------------------------*/

    /**
     * 通过主表ID查询
     *
     * @return
     */
    @AutoLog(value = "400-通过主表ID查询")
    @ApiOperation(value = "400-通过主表ID查询", notes = "400-通过主表ID查询")
    @GetMapping(value = "/listD400ByMainId")
    public Result<?> listD400ByMainId(D400 d400,
                                      @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                      HttpServletRequest req) {
        QueryWrapper<D400> queryWrapper = QueryGenerator.initQueryWrapper(d400, req.getParameterMap());
        Page<D400> page = new Page<D400>(pageNo, pageSize);
        IPage<D400> pageList = d400Service.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param d400
     * @return
     */
    @AutoLog(value = "400-添加")
    @ApiOperation(value = "400-添加", notes = "400-添加")
    @PostMapping(value = "/addD400")
    public Result<?> addD400(@RequestBody D400 d400) {
        d400Service.save(d400);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param d400
     * @return
     */
    @AutoLog(value = "400-编辑")
    @ApiOperation(value = "400-编辑", notes = "400-编辑")
    @PutMapping(value = "/editD400")
    public Result<?> editD400(@RequestBody D400 d400) {
        d400Service.updateById(d400);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "400-通过id删除")
    @ApiOperation(value = "400-通过id删除", notes = "400-通过id删除")
    @DeleteMapping(value = "/deleteD400")
    public Result<?> deleteD400(@RequestParam(name = "id", required = true) String id) {
        d400Service.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "400-批量删除")
    @ApiOperation(value = "400-批量删除", notes = "400-批量删除")
    @DeleteMapping(value = "/deleteBatchD400")
    public Result<?> deleteBatchD400(@RequestParam(name = "ids", required = true) String ids) {
        this.d400Service.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功!");
    }

    /**
     * 导出
     *
     * @return
     */
    @RequestMapping(value = "/exportD400")
    public ModelAndView exportD400(HttpServletRequest request, D400 d400) {
        // Step.1 组装查询条件
        QueryWrapper<D400> queryWrapper = QueryGenerator.initQueryWrapper(d400, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        // Step.2 获取导出数据
        List<D400> pageList = d400Service.list(queryWrapper);
        List<D400> exportList = null;

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
        mv.addObject(NormalExcelConstants.FILE_NAME, "400"); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, D400.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("400报表", "导出人:" + sysUser.getRealname(), "400"));
        mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
        return mv;
    }

    /**
     * 导入
     *
     * @return
     */
    @RequestMapping(value = "/importD400/{mainId}")
    public Result<?> importD400(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<D400> list = ExcelImportUtil.importExcel(file.getInputStream(), D400.class, params);
                for (D400 temp : list) {
                    temp.setSportsmanId(mainId);
                }
                long start = System.currentTimeMillis();
                d400Service.saveBatch(list);
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

    /*--------------------------------子表处理-400-end----------------------------------------------*/

    /*--------------------------------子表处理-800-begin----------------------------------------------*/

    /**
     * 通过主表ID查询
     *
     * @return
     */
    @AutoLog(value = "800-通过主表ID查询")
    @ApiOperation(value = "800-通过主表ID查询", notes = "800-通过主表ID查询")
    @GetMapping(value = "/listD800ByMainId")
    public Result<?> listD800ByMainId(D800 d800,
                                      @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                      HttpServletRequest req) {
        QueryWrapper<D800> queryWrapper = QueryGenerator.initQueryWrapper(d800, req.getParameterMap());
        Page<D800> page = new Page<D800>(pageNo, pageSize);
        IPage<D800> pageList = d800Service.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param d800
     * @return
     */
    @AutoLog(value = "800-添加")
    @ApiOperation(value = "800-添加", notes = "800-添加")
    @PostMapping(value = "/addD800")
    public Result<?> addD800(@RequestBody D800 d800) {
        d800Service.save(d800);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param d800
     * @return
     */
    @AutoLog(value = "800-编辑")
    @ApiOperation(value = "800-编辑", notes = "800-编辑")
    @PutMapping(value = "/editD800")
    public Result<?> editD800(@RequestBody D800 d800) {
        d800Service.updateById(d800);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "800-通过id删除")
    @ApiOperation(value = "800-通过id删除", notes = "800-通过id删除")
    @DeleteMapping(value = "/deleteD800")
    public Result<?> deleteD800(@RequestParam(name = "id", required = true) String id) {
        d800Service.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "800-批量删除")
    @ApiOperation(value = "800-批量删除", notes = "800-批量删除")
    @DeleteMapping(value = "/deleteBatchD800")
    public Result<?> deleteBatchD800(@RequestParam(name = "ids", required = true) String ids) {
        this.d800Service.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功!");
    }

    /**
     * 导出
     *
     * @return
     */
    @RequestMapping(value = "/exportD800")
    public ModelAndView exportD800(HttpServletRequest request, D800 d800) {
        // Step.1 组装查询条件
        QueryWrapper<D800> queryWrapper = QueryGenerator.initQueryWrapper(d800, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        // Step.2 获取导出数据
        List<D800> pageList = d800Service.list(queryWrapper);
        List<D800> exportList = null;

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
        mv.addObject(NormalExcelConstants.FILE_NAME, "800"); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, D800.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("800报表", "导出人:" + sysUser.getRealname(), "800"));
        mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
        return mv;
    }

    /**
     * 导入
     *
     * @return
     */
    @RequestMapping(value = "/importD800/{mainId}")
    public Result<?> importD800(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<D800> list = ExcelImportUtil.importExcel(file.getInputStream(), D800.class, params);
                for (D800 temp : list) {
                    temp.setSportsmanId(mainId);
                }
                long start = System.currentTimeMillis();
                d800Service.saveBatch(list);
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

    /*--------------------------------子表处理-800-end----------------------------------------------*/

    /*--------------------------------子表处理-1000-begin----------------------------------------------*/

    /**
     * 通过主表ID查询
     *
     * @return
     */
    @AutoLog(value = "1000-通过主表ID查询")
    @ApiOperation(value = "1000-通过主表ID查询", notes = "1000-通过主表ID查询")
    @GetMapping(value = "/listD1000ByMainId")
    public Result<?> listD1000ByMainId(D1000 d1000,
                                       @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                       @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                       HttpServletRequest req) {
        QueryWrapper<D1000> queryWrapper = QueryGenerator.initQueryWrapper(d1000, req.getParameterMap());
        Page<D1000> page = new Page<D1000>(pageNo, pageSize);
        IPage<D1000> pageList = d1000Service.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param d1000
     * @return
     */
    @AutoLog(value = "1000-添加")
    @ApiOperation(value = "1000-添加", notes = "1000-添加")
    @PostMapping(value = "/addD1000")
    public Result<?> addD1000(@RequestBody D1000 d1000) {
        d1000Service.save(d1000);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param d1000
     * @return
     */
    @AutoLog(value = "1000-编辑")
    @ApiOperation(value = "1000-编辑", notes = "1000-编辑")
    @PutMapping(value = "/editD1000")
    public Result<?> editD1000(@RequestBody D1000 d1000) {
        d1000Service.updateById(d1000);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "1000-通过id删除")
    @ApiOperation(value = "1000-通过id删除", notes = "1000-通过id删除")
    @DeleteMapping(value = "/deleteD1000")
    public Result<?> deleteD1000(@RequestParam(name = "id", required = true) String id) {
        d1000Service.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "1000-批量删除")
    @ApiOperation(value = "1000-批量删除", notes = "1000-批量删除")
    @DeleteMapping(value = "/deleteBatchD1000")
    public Result<?> deleteBatchD1000(@RequestParam(name = "ids", required = true) String ids) {
        this.d1000Service.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功!");
    }

    /**
     * 导出
     *
     * @return
     */
    @RequestMapping(value = "/exportD1000")
    public ModelAndView exportD1000(HttpServletRequest request, D1000 d1000) {
        // Step.1 组装查询条件
        QueryWrapper<D1000> queryWrapper = QueryGenerator.initQueryWrapper(d1000, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        // Step.2 获取导出数据
        List<D1000> pageList = d1000Service.list(queryWrapper);
        List<D1000> exportList = null;

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
        mv.addObject(NormalExcelConstants.FILE_NAME, "1000"); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, D1000.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("1000报表", "导出人:" + sysUser.getRealname(), "1000"));
        mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
        return mv;
    }

    /**
     * 导入
     *
     * @return
     */
    @RequestMapping(value = "/importD1000/{mainId}")
    public Result<?> importD1000(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<D1000> list = ExcelImportUtil.importExcel(file.getInputStream(), D1000.class, params);
                for (D1000 temp : list) {
                    temp.setSportsmanId(mainId);
                }
                long start = System.currentTimeMillis();
                d1000Service.saveBatch(list);
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

    /*--------------------------------子表处理-1000-end----------------------------------------------*/

    /*--------------------------------子表处理-1500-begin----------------------------------------------*/

    /**
     * 通过主表ID查询
     *
     * @return
     */
    @AutoLog(value = "1500-通过主表ID查询")
    @ApiOperation(value = "1500-通过主表ID查询", notes = "1500-通过主表ID查询")
    @GetMapping(value = "/listD1500ByMainId")
    public Result<?> listD1500ByMainId(D1500 d1500,
                                       @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                       @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                       HttpServletRequest req) {
        QueryWrapper<D1500> queryWrapper = QueryGenerator.initQueryWrapper(d1500, req.getParameterMap());
        Page<D1500> page = new Page<D1500>(pageNo, pageSize);
        IPage<D1500> pageList = d1500Service.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param d1500
     * @return
     */
    @AutoLog(value = "1500-添加")
    @ApiOperation(value = "1500-添加", notes = "1500-添加")
    @PostMapping(value = "/addD1500")
    public Result<?> addD1500(@RequestBody D1500 d1500) {
        d1500Service.save(d1500);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param d1500
     * @return
     */
    @AutoLog(value = "1500-编辑")
    @ApiOperation(value = "1500-编辑", notes = "1500-编辑")
    @PutMapping(value = "/editD1500")
    public Result<?> editD1500(@RequestBody D1500 d1500) {
        d1500Service.updateById(d1500);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "1500-通过id删除")
    @ApiOperation(value = "1500-通过id删除", notes = "1500-通过id删除")
    @DeleteMapping(value = "/deleteD1500")
    public Result<?> deleteD1500(@RequestParam(name = "id", required = true) String id) {
        d1500Service.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "1500-批量删除")
    @ApiOperation(value = "1500-批量删除", notes = "1500-批量删除")
    @DeleteMapping(value = "/deleteBatchD1500")
    public Result<?> deleteBatchD1500(@RequestParam(name = "ids", required = true) String ids) {
        this.d1500Service.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功!");
    }

    /**
     * 导出
     *
     * @return
     */
    @RequestMapping(value = "/exportD1500")
    public ModelAndView exportD1500(HttpServletRequest request, D1500 d1500) {
        // Step.1 组装查询条件
        QueryWrapper<D1500> queryWrapper = QueryGenerator.initQueryWrapper(d1500, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        // Step.2 获取导出数据
        List<D1500> pageList = d1500Service.list(queryWrapper);
        List<D1500> exportList = null;

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
        mv.addObject(NormalExcelConstants.FILE_NAME, "1500"); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, D1500.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("1500报表", "导出人:" + sysUser.getRealname(), "1500"));
        mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
        return mv;
    }

    /**
     * 导入
     *
     * @return
     */
    @RequestMapping(value = "/importD1500/{mainId}")
    public Result<?> importD1500(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<D1500> list = ExcelImportUtil.importExcel(file.getInputStream(), D1500.class, params);
                for (D1500 temp : list) {
                    temp.setSportsmanId(mainId);
                }
                long start = System.currentTimeMillis();
                d1500Service.saveBatch(list);
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

    /*--------------------------------子表处理-1500-end----------------------------------------------*/


}
