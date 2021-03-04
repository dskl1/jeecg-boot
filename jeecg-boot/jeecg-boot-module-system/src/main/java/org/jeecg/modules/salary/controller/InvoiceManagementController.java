package org.jeecg.modules.salary.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.modules.salary.entity.InvoiceManagementMember;
import org.jeecg.modules.salary.entity.SalaryStatistics;
import org.jeecg.modules.salary.service.IInvoiceManagementMemberService;
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
import org.jeecg.modules.salary.entity.InvoiceManagementAttached;
import org.jeecg.modules.salary.entity.InvoiceManagement;
import org.jeecg.modules.salary.vo.InvoiceManagementPage;
import org.jeecg.modules.salary.service.IInvoiceManagementService;
import org.jeecg.modules.salary.service.IInvoiceManagementAttachedService;
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
 * @Description: 开票管理
 * @Author: jeecg-boot
 * @Date:   2020-08-31
 * @Version: V1.0
 */
@Api(tags="开票管理")
@RestController
@RequestMapping("/salary/invoiceManagement")
@Slf4j
public class InvoiceManagementController {
	@Autowired
	private IInvoiceManagementService invoiceManagementService;
	@Autowired
	private IInvoiceManagementAttachedService invoiceManagementAttachedService;
	 @Autowired
	 private IInvoiceManagementMemberService invoiceManagementMemberService;

	/**
	 * 分页列表查询
	 *
	 * @param invoiceManagement
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "开票管理-分页列表查询")
	@ApiOperation(value="开票管理-分页列表查询", notes="开票管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(InvoiceManagement invoiceManagement,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<InvoiceManagement> queryWrapper = QueryGenerator.initQueryWrapper(invoiceManagement, req.getParameterMap());
		Page<InvoiceManagement> page = new Page<InvoiceManagement>(pageNo, pageSize);
		this.invoiceManagementService.invoiceInsert(); //插入开票管理
		this.invoiceManagementMemberService.memberInsert(); //插入开票人员
		this.invoiceManagementService.totalUpdate();//计算总金额
		this.invoiceManagementService.memberDelete(); //删除子表外键不存在的数据
		this.invoiceManagementService.deleteId(); //开票编号重复覆盖原始数据
		IPage<InvoiceManagement> pageList = invoiceManagementService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 *   添加
	 *
	 * @param invoiceManagementPage
	 * @return
	 */
	@AutoLog(value = "开票管理-添加")
	@ApiOperation(value="开票管理-添加", notes="开票管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody InvoiceManagementPage invoiceManagementPage) {
		InvoiceManagement invoiceManagement = new InvoiceManagement();
		BeanUtils.copyProperties(invoiceManagementPage, invoiceManagement);
		invoiceManagementService.saveMain(invoiceManagement, invoiceManagementPage.getInvoiceManagementAttachedList(),invoiceManagementPage.getInvoiceManagementMemberList());
		return Result.ok("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param invoiceManagementPage
	 * @return
	 */
	@AutoLog(value = "开票管理-编辑")
	@ApiOperation(value="开票管理-编辑", notes="开票管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody InvoiceManagementPage invoiceManagementPage) {
		InvoiceManagement invoiceManagement = new InvoiceManagement();
		BeanUtils.copyProperties(invoiceManagementPage, invoiceManagement);
		InvoiceManagement invoiceManagementEntity = invoiceManagementService.getById(invoiceManagement.getId());
		if(invoiceManagementEntity==null) {
			return Result.error("未找到对应数据");
		}
		invoiceManagementService.updateMain(invoiceManagement, invoiceManagementPage.getInvoiceManagementAttachedList(),invoiceManagementPage.getInvoiceManagementMemberList());
		return Result.ok("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "开票管理-通过id删除")
	@ApiOperation(value="开票管理-通过id删除", notes="开票管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		invoiceManagementService.delMain(id);
		return Result.ok("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "开票管理-批量删除")
	@ApiOperation(value="开票管理-批量删除", notes="开票管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.invoiceManagementService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "开票管理-通过id查询")
	@ApiOperation(value="开票管理-通过id查询", notes="开票管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		InvoiceManagement invoiceManagement = invoiceManagementService.getById(id);
		if(invoiceManagement==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(invoiceManagement);

	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "附票-通过主表ID查询")
	@ApiOperation(value="附票-通过主表ID查询", notes="附票-通过主表ID查询")
	@GetMapping(value = "/queryInvoiceManagementAttachedByMainId")
	public Result<?> queryInvoiceManagementAttachedListByMainId(@RequestParam(name="id",required=true) String id) {
		List<InvoiceManagementAttached> invoiceManagementAttachedList = invoiceManagementAttachedService.selectByMainId(id);
		IPage <InvoiceManagementAttached> page = new Page<>();
		page.setRecords(invoiceManagementAttachedList);
		page.setTotal(invoiceManagementAttachedList.size());
		return Result.ok(page);
	}

	 /**
	  * 通过id查询
	  *
	  * @param id
	  * @return
	  */
	 @AutoLog(value = "对应人员-通过主表ID查询")
	 @ApiOperation(value="对应人员-通过主表ID查询", notes="对应人员-通过主表ID查询")
	 @GetMapping(value = "/queryInvoiceManagementMemberByMainId")
	 public Result<?> queryInvoiceManagementMemberListByMainId(@RequestParam(name="id",required=true) String id) {
		 List<InvoiceManagementMember> invoiceManagementMemberList = invoiceManagementMemberService.selectByMainId(id);
		 IPage <InvoiceManagementMember> page = new Page<>();
		 page.setRecords(invoiceManagementMemberList);
		 page.setTotal(invoiceManagementMemberList.size());
		 return Result.ok(page);
	 }

    /**
    * 导出excel
    *
    * @param request
    * @param invoiceManagement
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, InvoiceManagement invoiceManagement) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<InvoiceManagement> queryWrapper = QueryGenerator.initQueryWrapper(invoiceManagement, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<InvoiceManagement> queryList = invoiceManagementService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<InvoiceManagement> invoiceManagementList = new ArrayList<InvoiceManagement>();
      if(oConvertUtils.isEmpty(selections)) {
          invoiceManagementList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          invoiceManagementList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<InvoiceManagementPage> pageList = new ArrayList<InvoiceManagementPage>();
      for (InvoiceManagement main : invoiceManagementList) {
          InvoiceManagementPage vo = new InvoiceManagementPage();
          BeanUtils.copyProperties(main, vo);
          List<InvoiceManagementAttached> invoiceManagementAttachedList = invoiceManagementAttachedService.selectByMainId(main.getId());
          vo.setInvoiceManagementAttachedList(invoiceManagementAttachedList);
		  List<InvoiceManagementMember> invoiceManagementMemberList = invoiceManagementMemberService.selectByMainId(main.getId());
		  vo.setInvoiceManagementMemberList(invoiceManagementMemberList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "开票管理列表");
      mv.addObject(NormalExcelConstants.CLASS, InvoiceManagementPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("开票管理数据", "导出人:"+sysUser.getRealname(), "开票管理"));
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
              List<InvoiceManagementPage> list = ExcelImportUtil.importExcel(file.getInputStream(), InvoiceManagementPage.class, params);
              for (InvoiceManagementPage page : list) {
                  InvoiceManagement po = new InvoiceManagement();
                  BeanUtils.copyProperties(page, po);
                  invoiceManagementService.saveMain(po, page.getInvoiceManagementAttachedList(),page.getInvoiceManagementMemberList());
              }
              return Result.ok("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
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

	 /**
	  *   确认
	  * @return
	  */
	 @AutoLog(value = "开票管理-确认")
	 @ApiOperation(value="开票管理-确认", notes="开票管理-确认")
	 @GetMapping(value = "/confirm")
	 public Result<?> confirm(@RequestParam(name="ids",required=true) String ids){
		 List<InvoiceManagement> list1 = this.invoiceManagementService.listByIds(Arrays.asList(ids.split(",")));
		 for(InvoiceManagement lis : list1){
			this.invoiceManagementService.stageUpdate(lis.getId());
		 }
		 return Result.ok("确认成功！");
	 }

	 /**
	  *   确认
	  * @return
	  */
	 @AutoLog(value = "开票管理-确认")
	 @ApiOperation(value="开票管理-确认", notes="开票管理-确认")
	 @GetMapping(value = "/confirms")
	 public Result<?> confirms(@RequestParam(name="ids",required=true) String ids){
		 List<InvoiceManagementAttached> list1 = this.invoiceManagementAttachedService.listByIds(Arrays.asList(ids.split(",")));
		 for(InvoiceManagementAttached lis : list1){
			 this.invoiceManagementAttachedService.stageUpdate(lis.getId());
		 }
		 return Result.ok("确认成功！");
	 }

}
