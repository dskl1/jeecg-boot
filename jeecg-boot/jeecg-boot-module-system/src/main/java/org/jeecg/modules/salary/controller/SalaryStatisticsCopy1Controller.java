package org.jeecg.modules.salary.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.salary.entity.SalaryStatisticsCopy1;
import org.jeecg.modules.salary.service.ISalaryStatisticsCopy1Service;

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
import org.jeecg.common.aspect.annotation.AutoLog;

/**
 * @Description: 历史工资
 * @Author: jeecg-boot
 * @Date: 2020-08-18
 * @Version: V1.0
 */
@Api(tags = "历史工资")
@RestController
@RequestMapping("/salary/salaryStatisticsCopy1")
@Slf4j
public class SalaryStatisticsCopy1Controller extends JeecgController<SalaryStatisticsCopy1, ISalaryStatisticsCopy1Service> {
    @Autowired
    private ISalaryStatisticsCopy1Service salaryStatisticsCopy1Service;

    /**
     * 分页列表查询
     *
     * @param salaryStatisticsCopy1
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "历史工资-分页列表查询")
    @ApiOperation(value = "历史工资-分页列表查询", notes = "历史工资-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(SalaryStatisticsCopy1 salaryStatisticsCopy1,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<SalaryStatisticsCopy1> queryWrapper = QueryGenerator.initQueryWrapper(salaryStatisticsCopy1, req.getParameterMap());
        Page<SalaryStatisticsCopy1> page = new Page<SalaryStatisticsCopy1>(pageNo, pageSize);
        IPage<SalaryStatisticsCopy1> pageList = salaryStatisticsCopy1Service.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param salaryStatisticsCopy1
     * @return
     */
    @AutoLog(value = "历史工资-添加")
    @ApiOperation(value = "历史工资-添加", notes = "历史工资-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody SalaryStatisticsCopy1 salaryStatisticsCopy1) {
        salaryStatisticsCopy1Service.save(salaryStatisticsCopy1);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param salaryStatisticsCopy1
     * @return
     */
    @AutoLog(value = "历史工资-编辑")
    @ApiOperation(value = "历史工资-编辑", notes = "历史工资-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody SalaryStatisticsCopy1 salaryStatisticsCopy1) {
        salaryStatisticsCopy1Service.updateById(salaryStatisticsCopy1);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "历史工资-通过id删除")
    @ApiOperation(value = "历史工资-通过id删除", notes = "历史工资-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        salaryStatisticsCopy1Service.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "历史工资-批量删除")
    @ApiOperation(value = "历史工资-批量删除", notes = "历史工资-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.salaryStatisticsCopy1Service.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "历史工资-通过id查询")
    @ApiOperation(value = "历史工资-通过id查询", notes = "历史工资-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        SalaryStatisticsCopy1 salaryStatisticsCopy1 = salaryStatisticsCopy1Service.getById(id);
        if (salaryStatisticsCopy1 == null) {
            return Result.error("未找到对应数据");
        }
        return Result.ok(salaryStatisticsCopy1);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param salaryStatisticsCopy1
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SalaryStatisticsCopy1 salaryStatisticsCopy1) {
        return super.exportXls(request, salaryStatisticsCopy1, SalaryStatisticsCopy1.class, "历史工资");
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
        return super.importExcel(request, response, SalaryStatisticsCopy1.class);
    }

    /**
     * 查询历史工资发放
     *
     * @return
     */
    @AutoLog(value = "成本管理-查询历史工资发放")
    @ApiOperation(value = "成本管理-查询历史工资发放", notes = "成本管理-查询历史工资发放")
    @GetMapping(value = "/queryByMonth")
    public Result<Object> queryByMonth(@RequestParam(name = "format", required = true) String format) {
        List<SalaryStatisticsCopy1> salaryStatisticsCopy1 = salaryStatisticsCopy1Service.selectByMonth(format);
        if (salaryStatisticsCopy1 == null) {
            return Result.error("未找到对应数据");
        }
        return Result.ok(salaryStatisticsCopy1);
    }

}
