package org.jeecg.modules.company.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.modules.company.service.IEmployeeService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.company.entity.NewestBillingReport;
import org.jeecg.modules.company.entity.NewestEmployee;
import org.jeecg.modules.company.vo.NewestEmployeePage;
import org.jeecg.modules.company.service.INewestEmployeeService;
import org.jeecg.modules.company.service.INewestBillingReportService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;

/**
 * @Description: 清单
 * @Author: jeecg-boot
 * @Date: 2020-04-02
 * @Version: V1.0
 */
@RestController
@RequestMapping("/company/newestEmployee")
@Slf4j
public class NewestEmployeeController {
    @Autowired
    private INewestEmployeeService newestEmployeeService;
    @Autowired
    private INewestBillingReportService newestBillingReportService;
    @Autowired
    private IEmployeeService employeeService;

    /**
     * 分页列表查询
     *
     * @param newestEmployee
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @GetMapping(value = "/list")
    @PermissionData(pageComponent="company/NewestEmployeeList")
    public Result<?> queryPageList(NewestEmployee newestEmployee,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<NewestEmployee> queryWrapper = QueryGenerator.initQueryWrapper(newestEmployee, req.getParameterMap());
        Page<NewestEmployee> page = new Page<NewestEmployee>(pageNo, pageSize);
        this.employeeService.sportsmanDelete(); //删除子表外键不存在的数据
        this.employeeService.deleteName(); //名字重复覆盖原始数据
        IPage<NewestEmployee> pageList = newestEmployeeService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param newestEmployeePage
     * @return
     */
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody NewestEmployeePage newestEmployeePage) {
        NewestEmployee newestEmployee = new NewestEmployee();
        BeanUtils.copyProperties(newestEmployeePage, newestEmployee);
        newestEmployeeService.save(newestEmployee);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param newestEmployeePage
     * @return
     */
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody NewestEmployeePage newestEmployeePage) {
        NewestEmployee newestEmployee = new NewestEmployee();
        BeanUtils.copyProperties(newestEmployeePage, newestEmployee);
        newestEmployeeService.updateById(newestEmployee);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        newestEmployeeService.delMain(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.newestEmployeeService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        NewestEmployee newestEmployee = newestEmployeeService.getById(id);
        return Result.ok(newestEmployee);
    }

    /**
     * 全部删除
     *
     * @return
     */
    @DeleteMapping(value = "/deleteAll")
    public Result<?> deleteAll() {
        this.newestEmployeeService.deleteAll();
        return Result.ok("全部删除成功！");
    }

    /**
     * 逻辑删除
     *
     * @return
     */
    @GetMapping(value = "status")
    public Result<?> updateStatus(@RequestParam(name = "id", required = true) String id) {
        this.newestEmployeeService.updateStatus(id);
        return Result.ok("删除成功");
    }

    //===========================以下是子表信息操作相关API====================================

    /**
     * 通过主表id查询账单
     *
     * @param newestBillingReport
     * @return
     */
    @GetMapping(value = "/listNewestBillingReportByMainId")
    public Result<?> listNewestBillingReportByMainId(NewestBillingReport newestBillingReport,
                                                     @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                     HttpServletRequest req) {
        if (newestBillingReport.getSportsmanId() != null) {
            QueryWrapper<NewestBillingReport> queryWrapper = QueryGenerator.initQueryWrapper(newestBillingReport, req.getParameterMap());
            Page<NewestBillingReport> page = new Page<NewestBillingReport>(pageNo, pageSize);
            IPage<NewestBillingReport> pageList = newestBillingReportService.page(page, queryWrapper);
            return Result.ok(pageList);
        } else {
            return Result.ok();
        }
    }

    /**
     * 添加账单
     *
     * @param newestBillingReport
     * @return
     */
    @PostMapping(value = "/addNewestBillingReport")
    public Result<?> addNewestBillingReport(@RequestBody NewestBillingReport newestBillingReport) {
        newestBillingReportService.save(newestBillingReport);
        return Result.ok("添加账单成功！");
    }

    /**
     * 编辑账单
     *
     * @param newestBillingReport
     * @return
     */
    @PutMapping("/editNewestBillingReport")
    public Result<?> editNewestBillingReport(@RequestBody NewestBillingReport newestBillingReport) {
        newestBillingReportService.updateById(newestBillingReport);
        return Result.ok("编辑账单成功！");
    }

    /**
     * 通过id删除账单
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteNewestBillingReport")
    public Result<?> deleteNewestBillingReport(@RequestParam(name = "id", required = true) String id) {
        newestBillingReportService.removeById(id);
        return Result.ok("删除账单成功！");
    }

    /**
     * 批量删除账单
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatchNewestBillingReport")
    public Result<?> deleteBatchNewestBillingReport(@RequestParam(name = "ids", required = true) String ids) {
        if (ids == null || "".equals(ids.trim())) {
            return Result.error("参数不识别！");
        }
        this.newestBillingReportService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");

    }


    /**
     * 导出excel
     *
     * @param request
     * @param newestEmployee
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, NewestEmployee newestEmployee) {
        // Step.1 组装查询条件
        QueryWrapper<NewestEmployee> queryWrapper = QueryGenerator.initQueryWrapper(newestEmployee, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        //Step.2 获取导出数据
        List<NewestEmployeePage> pageList = new ArrayList<NewestEmployeePage>();
        List<NewestEmployee> newestEmployeeList = newestEmployeeService.list(queryWrapper);
        for (NewestEmployee temp : newestEmployeeList) {
            NewestEmployeePage vo = new NewestEmployeePage();
            BeanUtils.copyProperties(temp, vo);
            List<NewestBillingReport> newestBillingReportList = newestBillingReportService.selectByMainId(temp.getId());
            vo.setNewestBillingReportList(newestBillingReportList);
            pageList.add(vo);
        }
        //Step.3 调用AutoPoi导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, "清单");
        mv.addObject(NormalExcelConstants.CLASS, NewestEmployeePage.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("清单数据", "导出人:" + sysUser.getRealname(), "清单"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;
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
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<NewestEmployeePage> list = ExcelImportUtil.importExcel(file.getInputStream(), NewestEmployeePage.class, params);
                for (NewestEmployeePage page : list) {
                    NewestEmployee po = new NewestEmployee();
                    BeanUtils.copyProperties(page, po);
                    newestEmployeeService.saveMain(po, page.getNewestBillingReportList());
                }
                return Result.ok("文件导入成功！数据行数:" + list.size());
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
        return Result.ok("文件导入失败！");
    }
}
