package org.jeecg.modules.company.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.company.entity.SalaryMan;
import org.jeecg.modules.company.service.ISalaryManService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

/**
 * @Description: 公司人员信息
 * @Author: jeecg-boot
 * @Date: 2020-09-08
 * @Version: V1.0
 */
@Api(tags = "公司人员信息")
@RestController
@RequestMapping("/company/salaryMan")
@Slf4j
public class SalaryManController extends JeecgController<SalaryMan, ISalaryManService> {
    @Autowired
    private ISalaryManService salaryManService;

    /**
     * 分页列表查询
     *
     * @param salaryMan
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "公司人员信息-分页列表查询")
    @ApiOperation(value = "公司人员信息-分页列表查询", notes = "公司人员信息-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(SalaryMan salaryMan,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<SalaryMan> queryWrapper = QueryGenerator.initQueryWrapper(salaryMan, req.getParameterMap());
        Page<SalaryMan> page = new Page<SalaryMan>(pageNo, pageSize);
        this.salaryManService.sexUpdate();
        this.salaryManService.birUpdate();
        IPage<SalaryMan> pageList = salaryManService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param salaryMan
     * @return
     */
    @AutoLog(value = "公司人员信息-添加")
    @ApiOperation(value = "公司人员信息-添加", notes = "公司人员信息-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody SalaryMan salaryMan) {
        salaryManService.save(salaryMan);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param salaryMan
     * @return
     */
    @AutoLog(value = "公司人员信息-编辑")
    @ApiOperation(value = "公司人员信息-编辑", notes = "公司人员信息-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody SalaryMan salaryMan) {
        salaryManService.updateById(salaryMan);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "公司人员信息-通过id删除")
    @ApiOperation(value = "公司人员信息-通过id删除", notes = "公司人员信息-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        salaryManService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "公司人员信息-批量删除")
    @ApiOperation(value = "公司人员信息-批量删除", notes = "公司人员信息-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.salaryManService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "公司人员信息-通过id查询")
    @ApiOperation(value = "公司人员信息-通过id查询", notes = "公司人员信息-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        SalaryMan salaryMan = salaryManService.getById(id);
        if (salaryMan == null) {
            return Result.error("未找到对应数据");
        }
        return Result.ok(salaryMan);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param salaryMan
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SalaryMan salaryMan) {
        return super.exportXls(request, salaryMan, SalaryMan.class, "公司人员信息");
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
        return super.importExcel(request, response, SalaryMan.class);
    }

}
