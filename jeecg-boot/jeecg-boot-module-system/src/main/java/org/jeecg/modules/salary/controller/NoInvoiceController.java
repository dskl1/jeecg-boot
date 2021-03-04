package org.jeecg.modules.salary.controller;

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
import org.jeecg.modules.salary.entity.InvoiceManagement;
import org.jeecg.modules.salary.entity.NoInvoice;
import org.jeecg.modules.salary.service.INoInvoiceService;

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
 * @Description: 未开票
 * @Author: jeecg-boot
 * @Date: 2021-01-18
 * @Version: V1.0
 */
@Api(tags = "未开票")
@RestController
@RequestMapping("/salary/noInvoice")
@Slf4j
public class NoInvoiceController extends JeecgController<NoInvoice, INoInvoiceService> {
    @Autowired
    private INoInvoiceService noInvoiceService;

    /**
     * 分页列表查询
     *
     * @param noInvoice
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "未开票-分页列表查询")
    @ApiOperation(value = "未开票-分页列表查询", notes = "未开票-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(NoInvoice noInvoice,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<NoInvoice> queryWrapper = QueryGenerator.initQueryWrapper(noInvoice, req.getParameterMap());
        Page<NoInvoice> page = new Page<NoInvoice>(pageNo, pageSize);
        this.noInvoiceService.manageInsert(); //插入开票管理
        this.noInvoiceService.membInsert(); //插入开票人员
        IPage<NoInvoice> pageList = noInvoiceService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param noInvoice
     * @return
     */
    @AutoLog(value = "未开票-添加")
    @ApiOperation(value = "未开票-添加", notes = "未开票-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody NoInvoice noInvoice) {
        noInvoiceService.save(noInvoice);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param noInvoice
     * @return
     */
    @AutoLog(value = "未开票-编辑")
    @ApiOperation(value = "未开票-编辑", notes = "未开票-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody NoInvoice noInvoice) {
        noInvoiceService.updateById(noInvoice);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "未开票-通过id删除")
    @ApiOperation(value = "未开票-通过id删除", notes = "未开票-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        noInvoiceService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "未开票-批量删除")
    @ApiOperation(value = "未开票-批量删除", notes = "未开票-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.noInvoiceService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "未开票-通过id查询")
    @ApiOperation(value = "未开票-通过id查询", notes = "未开票-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        NoInvoice noInvoice = noInvoiceService.getById(id);
        if (noInvoice == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(noInvoice);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param noInvoice
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, NoInvoice noInvoice) {
        return super.exportXls(request, noInvoice, NoInvoice.class, "未开票");
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
        return super.importExcel(request, response, NoInvoice.class);
    }

    /**
     * 确认开票
     *
     * @return
     */
    @AutoLog(value = "未开票-确认开票")
    @ApiOperation(value = "未开票-确认开票", notes = "未开票-确认开票")
    @GetMapping(value = "/confirm")
    public Result<?> confirm(@RequestParam(name = "ids", required = true) String ids) {
        List<NoInvoice> list1 = this.noInvoiceService.listByIds(Arrays.asList(ids.split(",")));
        for (NoInvoice lis : list1) {
            this.noInvoiceService.stageUpdate(lis.getId());
        }
        return Result.ok("确认成功！");
    }

}
