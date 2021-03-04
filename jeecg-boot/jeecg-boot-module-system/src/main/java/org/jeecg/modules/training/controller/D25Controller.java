package org.jeecg.modules.training.controller;

import org.jeecg.common.aspect.annotation.PermissionData;
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
import org.jeecg.modules.training.entity.TrainingScoree;
import org.jeecg.modules.training.entity.D25;
import org.jeecg.modules.training.service.ID25Service;
import org.jeecg.modules.training.service.ITrainingScoreeService;
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
 * @Description: 25
 * @Author: jeecg-boot
 * @Date: 2020-05-20
 * @Version: V1.0
 */
@Api(tags = "25")
@RestController
@RequestMapping("/training/d25")
@Slf4j
public class D25Controller extends JeecgController<D25, ID25Service> {

    @Autowired
    private ID25Service d25Service;

    @Autowired
    private ITrainingScoreeService trainingScoreeService;


    /*---------------------------------主表处理-begin-------------------------------------*/

    /**
     * 分页列表查询
     *
     * @param d25
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "25-分页列表查询")
    @ApiOperation(value = "25-分页列表查询", notes = "25-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(D25 d25,
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
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody D25 d25) {
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
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody D25 d25) {
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
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        d25Service.delMain(id);
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
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.d25Service.delBatchMain(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功!");
    }

    /**
     * 导出
     *
     * @return
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, D25 d25) {
        return super.exportXls(request, d25, D25.class, "25");
    }

    /**
     * 导入
     *
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, D25.class);
    }
    /*---------------------------------主表处理-end-------------------------------------*/


    /*--------------------------------子表处理-成绩-begin----------------------------------------------*/

    /**
     * 通过主表ID查询
     *
     * @return
     */
    @AutoLog(value = "成绩-通过主表ID查询")
    @ApiOperation(value = "成绩-通过主表ID查询", notes = "成绩-通过主表ID查询")
    @GetMapping(value = "/listTrainingScoreeByMainId")
    @PermissionData(pageComponent="training/TrainingScoreList")
    public Result<?> listTrainingScoreeByMainId(TrainingScoree trainingScoree,
                                                @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                HttpServletRequest req,
                                                @RequestParam(name = "mainId", required = false) String mainId) {
        QueryWrapper<TrainingScoree> queryWrapper = QueryGenerator.initQueryWrapper(trainingScoree, req.getParameterMap());
        Page<TrainingScoree> page = new Page<TrainingScoree>(pageNo, pageSize);
        IPage<TrainingScoree> pageList = trainingScoreeService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param trainingScoree
     * @return
     */
    @AutoLog(value = "成绩-添加")
    @ApiOperation(value = "成绩-添加", notes = "成绩-添加")
    @PostMapping(value = "/addTrainingScoree")
    public Result<?> addTrainingScoree(@RequestBody TrainingScoree trainingScoree) {
        trainingScoreeService.save(trainingScoree);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param trainingScoree
     * @return
     */
    @AutoLog(value = "成绩-编辑")
    @ApiOperation(value = "成绩-编辑", notes = "成绩-编辑")
    @PutMapping(value = "/editTrainingScoree")
    public Result<?> editTrainingScoree(@RequestBody TrainingScoree trainingScoree) {
        trainingScoreeService.updateById(trainingScoree);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "成绩-通过id删除")
    @ApiOperation(value = "成绩-通过id删除", notes = "成绩-通过id删除")
    @DeleteMapping(value = "/deleteTrainingScoree")
    public Result<?> deleteTrainingScoree(@RequestParam(name = "id", required = true) String id) {
        trainingScoreeService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "成绩-批量删除")
    @ApiOperation(value = "成绩-批量删除", notes = "成绩-批量删除")
    @DeleteMapping(value = "/deleteBatchTrainingScoree")
    public Result<?> deleteBatchTrainingScoree(@RequestParam(name = "ids", required = true) String ids) {
        this.trainingScoreeService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功!");
    }

    /**
     * 导出
     *
     * @return
     */
    @RequestMapping(value = "/exportTrainingScoree")
    public ModelAndView exportTrainingScoree(HttpServletRequest request, TrainingScoree trainingScoree) {
        // Step.1 组装查询条件
        QueryWrapper<TrainingScoree> queryWrapper = QueryGenerator.initQueryWrapper(trainingScoree, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        // Step.2 获取导出数据
        List<TrainingScoree> pageList = trainingScoreeService.list(queryWrapper);
        List<TrainingScoree> exportList = null;

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
        mv.addObject(NormalExcelConstants.FILE_NAME, "成绩"); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, TrainingScoree.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("成绩报表", "导出人:" + sysUser.getRealname(), "成绩"));
        mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
        return mv;
    }

    /**
     * 导入
     *
     * @return
     */
    @RequestMapping(value = "/importTrainingScoree/{mainId}")
    public Result<?> importTrainingScoree(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<TrainingScoree> list = ExcelImportUtil.importExcel(file.getInputStream(), TrainingScoree.class, params);
                for (TrainingScoree temp : list) {
                    temp.setSportsmanId(mainId);
                }
                long start = System.currentTimeMillis();
                trainingScoreeService.saveBatch(list);
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

    /*--------------------------------子表处理-成绩-end----------------------------------------------*/


}
