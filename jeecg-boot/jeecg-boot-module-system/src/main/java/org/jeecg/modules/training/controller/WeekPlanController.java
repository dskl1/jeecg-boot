package org.jeecg.modules.training.controller;

import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.system.query.QueryGenerator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.company.service.IEmployeeService;
import org.jeecg.modules.restapi.service.IRestApiCopy1Service;
import org.jeecg.modules.training.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.training.entity.WeekTrainingScore;
import org.jeecg.modules.training.entity.WeekPlan;
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
 * @Description: 一周训练内容
 * @Author: jeecg-boot
 * @Date: 2020-06-17
 * @Version: V1.0
 */
@Api(tags = "一周训练内容")
@RestController
@RequestMapping("/training/weekPlan")
@Slf4j
public class WeekPlanController extends JeecgController<WeekPlan, IWeekPlanService> {

    @Autowired
    private IWeekPlanService weekPlanService;

    @Autowired
    private IWeekTrainingScoreService weekTrainingScoreService;

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
    @Autowired
    private IRestApiCopy1Service restApiCopy1Service;
    @Autowired
    private IEmployeeService employeeService;


    /*---------------------------------主表处理-begin-------------------------------------*/

    /**
     * 分页列表查询
     *
     * @param weekPlan
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "一周训练内容-分页列表查询")
    @ApiOperation(value = "一周训练内容-分页列表查询", notes = "一周训练内容-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(WeekPlan weekPlan,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req, @RequestParam(name = "mainId", required = false) String mainId) {
        QueryWrapper<WeekPlan> queryWrapper = QueryGenerator.initQueryWrapper(weekPlan, req.getParameterMap());
        Page<WeekPlan> page = new Page<WeekPlan>(pageNo, pageSize);
        this.trainingScoreService.insertName(); //打卡人员插到当天计划下
        this.trainingScoreService.setFlagSee(); //设置training_score卡标记为0
        this.trainingScoreService.setFlagNoSee(); //设置training_score卡标记为1
        this.trainingScoreService.insertNameCopy();  //count(name)=1必定是当天新增的数据，直接插入training_score_copy1表
        this.trainingScoreService.updateTimeCopy(); //count(name) > 1时，查找在training_score表中当天是否有新增数据，有就更新training_score_copy1对应的名字的update_time
        this.trainingScoreService.setFlagSeeCopy();  //设置training_score_copy1卡标记为0
        this.trainingScoreService.setFlagNoSeeCopy(); //设置training_score_copy1卡标记为1
        this.restApiCopy1Service.jzhName(); //设置名字等于金梓恒
        this.d25Service.insertD25(); //把当天得训练计划插到25对应的训练项目里
        this.d50Service.insertD50(); //把当天得训练计划插到50对应的训练项目里
        this.d100Service.insertD100(); //把当天得训练计划插到100对应的训练项目里
        this.d150Service.insertD150(); //把当天得训练计划插到150对应的训练项目里
        this.d200Service.insertD200(); //把当天得训练计划插到200对应的训练项目里
        this.d250Service.insertD250(); //把当天得训练计划插到250对应的训练项目里
        this.d300Service.insertD300(); //把当天得训练计划插到300对应的训练项目里
        this.d350Service.insertD350(); //把当天得训练计划插到350对应的训练项目里
        this.d400Service.insertD400(); //把当天得训练计划插到400对应的训练项目里
        this.d800Service.insertD800(); //把当天得训练计划插到800对应的训练项目里
        this.d1000Service.insertD1000(); //把当天得训练计划插到1000对应的训练项目里
        this.d1500Service.insertD1500(); //把当天得训练计划插到1500对应的训练项目里
        this.weekPlanService.insertTrainingScore(mainId); //选择计划后，插入当天相同计划内容的各人对应成绩
        this.employeeService.updateRaise(); //调薪加到工资里
        IPage<WeekPlan> pageList = weekPlanService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param weekPlan
     * @return
     */
    @AutoLog(value = "一周训练内容-添加")
    @ApiOperation(value = "一周训练内容-添加", notes = "一周训练内容-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody WeekPlan weekPlan) {
        weekPlanService.save(weekPlan);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param weekPlan
     * @return
     */
    @AutoLog(value = "一周训练内容-编辑")
    @ApiOperation(value = "一周训练内容-编辑", notes = "一周训练内容-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody WeekPlan weekPlan) {
        weekPlanService.updateById(weekPlan);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "一周训练内容-通过id删除")
    @ApiOperation(value = "一周训练内容-通过id删除", notes = "一周训练内容-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        weekPlanService.delMain(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "一周训练内容-批量删除")
    @ApiOperation(value = "一周训练内容-批量删除", notes = "一周训练内容-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.weekPlanService.delBatchMain(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功!");
    }

    /**
     * 导出
     *
     * @return
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, WeekPlan weekPlan) {
        return super.exportXls(request, weekPlan, WeekPlan.class, "一周训练内容");
    }

    /**
     * 导入
     *
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, WeekPlan.class);
    }
    /*---------------------------------主表处理-end-------------------------------------*/


    /*--------------------------------子表处理-训练人员名单-begin----------------------------------------------*/

    /**
     * 通过主表ID查询
     *
     * @return
     */
    @AutoLog(value = "训练人员名单-通过主表ID查询")
    @ApiOperation(value = "训练人员名单-通过主表ID查询", notes = "训练人员名单-通过主表ID查询")
    @GetMapping(value = "/listWeekTrainingScoreByMainId")
    @PermissionData(pageComponent = "training/WeekPlanList")
    public Result<?> listWeekTrainingScoreByMainId(WeekTrainingScore weekTrainingScore,
                                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                   HttpServletRequest req) {
        QueryWrapper<WeekTrainingScore> queryWrapper = QueryGenerator.initQueryWrapper(weekTrainingScore, req.getParameterMap());
        Page<WeekTrainingScore> page = new Page<WeekTrainingScore>(pageNo, pageSize);
        IPage<WeekTrainingScore> pageList = weekTrainingScoreService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param weekTrainingScore
     * @return
     */
    @AutoLog(value = "训练人员名单-添加")
    @ApiOperation(value = "训练人员名单-添加", notes = "训练人员名单-添加")
    @PostMapping(value = "/addWeekTrainingScore")
    public Result<?> addWeekTrainingScore(@RequestBody WeekTrainingScore weekTrainingScore) {
        weekTrainingScoreService.save(weekTrainingScore);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param weekTrainingScore
     * @return
     */
    @AutoLog(value = "训练人员名单-编辑")
    @ApiOperation(value = "训练人员名单-编辑", notes = "训练人员名单-编辑")
    @PutMapping(value = "/editWeekTrainingScore")
    public Result<?> editWeekTrainingScore(@RequestBody WeekTrainingScore weekTrainingScore) {
        weekTrainingScoreService.updateById(weekTrainingScore);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "训练人员名单-通过id删除")
    @ApiOperation(value = "训练人员名单-通过id删除", notes = "训练人员名单-通过id删除")
    @DeleteMapping(value = "/deleteWeekTrainingScore")
    public Result<?> deleteWeekTrainingScore(@RequestParam(name = "id", required = true) String id) {
        weekTrainingScoreService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "训练人员名单-批量删除")
    @ApiOperation(value = "训练人员名单-批量删除", notes = "训练人员名单-批量删除")
    @DeleteMapping(value = "/deleteBatchWeekTrainingScore")
    public Result<?> deleteBatchWeekTrainingScore(@RequestParam(name = "ids", required = true) String ids) {
        this.weekTrainingScoreService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功!");
    }

    /**
     * 导出
     *
     * @return
     */
    @RequestMapping(value = "/exportWeekTrainingScore")
    public ModelAndView exportWeekTrainingScore(HttpServletRequest request, WeekTrainingScore weekTrainingScore) {
        // Step.1 组装查询条件
        QueryWrapper<WeekTrainingScore> queryWrapper = QueryGenerator.initQueryWrapper(weekTrainingScore, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        // Step.2 获取导出数据
        List<WeekTrainingScore> pageList = weekTrainingScoreService.list(queryWrapper);
        List<WeekTrainingScore> exportList = null;

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
        mv.addObject(NormalExcelConstants.FILE_NAME, "训练人员名单"); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, WeekTrainingScore.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("训练人员名单报表", "导出人:" + sysUser.getRealname(), "训练人员名单"));
        mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
        return mv;
    }

    /**
     * 导入
     *
     * @return
     */
    @RequestMapping(value = "/importWeekTrainingScore/{mainId}")
    public Result<?> importWeekTrainingScore(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<WeekTrainingScore> list = ExcelImportUtil.importExcel(file.getInputStream(), WeekTrainingScore.class, params);
                for (WeekTrainingScore temp : list) {
                    temp.setSportsmanId(mainId);
                }
                long start = System.currentTimeMillis();
                weekTrainingScoreService.saveBatch(list);
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

    /*--------------------------------子表处理-训练人员名单-end----------------------------------------------*/


}
