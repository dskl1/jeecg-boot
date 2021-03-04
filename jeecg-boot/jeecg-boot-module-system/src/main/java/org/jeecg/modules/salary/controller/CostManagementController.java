package org.jeecg.modules.salary.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.io.UnsupportedEncodingException;
import javax.activation.MimetypesFileTypeMap;
import javax.mail.MessagingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hslf.record.Record;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.salary.FileUtil;
import org.jeecg.modules.salary.entity.CostManagement;
import org.jeecg.modules.salary.service.ICostManagementService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

/**
 * @Description: 成本管理
 * @Author: jeecg-boot
 * @Date: 2020-08-13
 * @Version: V1.0
 */
@Api(tags = "成本管理")
@RestController
@RequestMapping("/salary/costManagement")
@Slf4j
public class CostManagementController extends JeecgController<CostManagement, ICostManagementService> {
    @Autowired
    private ICostManagementService costManagementService;

    /**
     * 分页列表查询
     *
     * @param costManagement
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "成本管理-分页列表查询")
    @ApiOperation(value = "成本管理-分页列表查询", notes = "成本管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(CostManagement costManagement,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<CostManagement> queryWrapper = QueryGenerator.initQueryWrapper(costManagement, req.getParameterMap());
        this.costManagementService.costUpdate(); //更新资金成本
        this.costManagementService.profitUpdate(); //更新利润
        this.costManagementService.intReimbursement(); //报销取整
        Page<CostManagement> page = new Page<CostManagement>(pageNo, pageSize);
        IPage<CostManagement> pageList = costManagementService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param costManagement
     * @return
     */
    @AutoLog(value = "成本管理-添加")
    @ApiOperation(value = "成本管理-添加", notes = "成本管理-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody CostManagement costManagement) {
        costManagementService.save(costManagement);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param costManagement
     * @return
     */
    @AutoLog(value = "成本管理-编辑")
    @ApiOperation(value = "成本管理-编辑", notes = "成本管理-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody CostManagement costManagement) {
        costManagementService.updateById(costManagement);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "成本管理-通过id删除")
    @ApiOperation(value = "成本管理-通过id删除", notes = "成本管理-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        costManagementService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "成本管理-批量删除")
    @ApiOperation(value = "成本管理-批量删除", notes = "成本管理-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.costManagementService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "成本管理-通过id查询")
    @ApiOperation(value = "成本管理-通过id查询", notes = "成本管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        CostManagement costManagement = costManagementService.getById(id);
        if (costManagement == null) {
            return Result.error("未找到对应数据");
        }
        return Result.ok(costManagement);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param costManagement
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CostManagement costManagement) {
        return super.exportXls(request, costManagement, CostManagement.class, "成本管理");
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
        return super.importExcel(request, response, CostManagement.class);
    }

    /**
     * 下载文件
     *
     * @return
     * @throws MessagingException
     */
    @AutoLog(value = "成本管理-下载txt")
    @ResponseBody
    @ApiOperation(value = "成本管理-下载txt", notes = "成本管理-下载txt")
    @RequestMapping(value = "/txt", method = RequestMethod.PUT)
    public Result<?> testSend(@RequestBody JSONObject jsonObject, HttpServletResponse response, HttpServletRequest request) throws MessagingException {
        String ids = jsonObject.getString("ids");
        this.costManagementService.exportLand2ndClassIndex(ids);

        return Result.ok("下载成功");
    }

}
