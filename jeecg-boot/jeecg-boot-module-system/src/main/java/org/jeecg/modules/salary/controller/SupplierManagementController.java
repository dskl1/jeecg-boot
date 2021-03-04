package org.jeecg.modules.salary.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecg.modules.salary.entity.InvoiceManagement;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.salary.entity.SupplierManagementMember;
import org.jeecg.modules.salary.entity.SupplierManagement;
import org.jeecg.modules.salary.vo.SupplierManagementPage;
import org.jeecg.modules.salary.service.ISupplierManagementService;
import org.jeecg.modules.salary.service.ISupplierManagementMemberService;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

/**
 * @Description: 供应商管理
 * @Author: jeecg-boot
 * @Date: 2021-02-23
 * @Version: V1.0
 */
@Api(tags = "供应商管理")
@RestController
@RequestMapping("/salary/supplierManagement")
@Slf4j
public class SupplierManagementController {
    @Autowired
    private ISupplierManagementService supplierManagementService;
    @Autowired
    private ISupplierManagementMemberService supplierManagementMemberService;

    /**
     * 分页列表查询
     *
     * @param supplierManagement
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "供应商管理-分页列表查询")
    @ApiOperation(value = "供应商管理-分页列表查询", notes = "供应商管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(SupplierManagement supplierManagement,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<SupplierManagement> queryWrapper = QueryGenerator.initQueryWrapper(supplierManagement, req.getParameterMap());
        Page<SupplierManagement> page = new Page<SupplierManagement>(pageNo, pageSize);
        this.supplierManagementService.billingInsert(); //插入开票管理（分包公司）
        this.supplierManagementMemberService.memberInsert2(); //插入开票人员(分包公司)
        this.supplierManagementService.totalUpdate();//计算总金额
        this.supplierManagementService.memberDelete(); //删除子表外键不存在的数据
        this.supplierManagementService.deleteId(); //开票编号重复覆盖原始数据
        IPage<SupplierManagement> pageList = supplierManagementService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param supplierManagementPage
     * @return
     */
    @AutoLog(value = "供应商管理-添加")
    @ApiOperation(value = "供应商管理-添加", notes = "供应商管理-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody SupplierManagementPage supplierManagementPage) {
        SupplierManagement supplierManagement = new SupplierManagement();
        BeanUtils.copyProperties(supplierManagementPage, supplierManagement);
        supplierManagementService.saveMain(supplierManagement, supplierManagementPage.getSupplierManagementMemberList());
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param supplierManagementPage
     * @return
     */
    @AutoLog(value = "供应商管理-编辑")
    @ApiOperation(value = "供应商管理-编辑", notes = "供应商管理-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody SupplierManagementPage supplierManagementPage) {
        SupplierManagement supplierManagement = new SupplierManagement();
        BeanUtils.copyProperties(supplierManagementPage, supplierManagement);
        SupplierManagement supplierManagementEntity = supplierManagementService.getById(supplierManagement.getId());
        if (supplierManagementEntity == null) {
            return Result.error("未找到对应数据");
        }
        supplierManagementService.updateMain(supplierManagement, supplierManagementPage.getSupplierManagementMemberList());
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "供应商管理-通过id删除")
    @ApiOperation(value = "供应商管理-通过id删除", notes = "供应商管理-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        supplierManagementService.delMain(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "供应商管理-批量删除")
    @ApiOperation(value = "供应商管理-批量删除", notes = "供应商管理-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.supplierManagementService.delBatchMain(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "供应商管理-通过id查询")
    @ApiOperation(value = "供应商管理-通过id查询", notes = "供应商管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        SupplierManagement supplierManagement = supplierManagementService.getById(id);
        if (supplierManagement == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(supplierManagement);

    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "人员名单-通过主表ID查询")
    @ApiOperation(value = "人员名单-通过主表ID查询", notes = "人员名单-通过主表ID查询")
    @GetMapping(value = "/querySupplierManagementMemberByMainId")
    public Result<?> querySupplierManagementMemberListByMainId(@RequestParam(name = "id", required = true) String id) {
        List<SupplierManagementMember> supplierManagementMemberList = supplierManagementMemberService.selectByMainId(id);
        IPage<SupplierManagementMember> page = new Page<>();
        page.setRecords(supplierManagementMemberList);
        page.setTotal(supplierManagementMemberList.size());
        return Result.OK(page);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param supplierManagement
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SupplierManagement supplierManagement) {
        // Step.1 组装查询条件查询数据
        QueryWrapper<SupplierManagement> queryWrapper = QueryGenerator.initQueryWrapper(supplierManagement, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        //Step.2 获取导出数据
        List<SupplierManagement> queryList = supplierManagementService.list(queryWrapper);
        // 过滤选中数据
        String selections = request.getParameter("selections");
        List<SupplierManagement> supplierManagementList = new ArrayList<SupplierManagement>();
        if (oConvertUtils.isEmpty(selections)) {
            supplierManagementList = queryList;
        } else {
            List<String> selectionList = Arrays.asList(selections.split(","));
            supplierManagementList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
        }

        // Step.3 组装pageList
        List<SupplierManagementPage> pageList = new ArrayList<SupplierManagementPage>();
        for (SupplierManagement main : supplierManagementList) {
            SupplierManagementPage vo = new SupplierManagementPage();
            BeanUtils.copyProperties(main, vo);
            List<SupplierManagementMember> supplierManagementMemberList = supplierManagementMemberService.selectByMainId(main.getId());
            vo.setSupplierManagementMemberList(supplierManagementMemberList);
            pageList.add(vo);
        }

        // Step.4 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, "供应商管理列表");
        mv.addObject(NormalExcelConstants.CLASS, SupplierManagementPage.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("供应商管理数据", "导出人:" + sysUser.getRealname(), "供应商管理"));
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
                List<SupplierManagementPage> list = ExcelImportUtil.importExcel(file.getInputStream(), SupplierManagementPage.class, params);
                for (SupplierManagementPage page : list) {
                    SupplierManagement po = new SupplierManagement();
                    BeanUtils.copyProperties(page, po);
                    supplierManagementService.saveMain(po, page.getSupplierManagementMemberList());
                }
                return Result.OK("文件导入成功！数据行数:" + list.size());
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
        return Result.OK("文件导入失败！");
    }

    /**
     *   确认
     * @return
     */
    @AutoLog(value = "供应商管理-确认")
    @ApiOperation(value="供应商管理-确认", notes="供应商管理-确认")
    @GetMapping(value = "/confirm")
    public Result<?> confirm(@RequestParam(name="ids",required=true) String ids){
        List<SupplierManagement> list1 = this.supplierManagementService.listByIds(Arrays.asList(ids.split(",")));
        for(SupplierManagement lis : list1){
            this.supplierManagementService.stageUpdate(lis.getId());
        }
        return Result.ok("确认成功！");
    }

}
