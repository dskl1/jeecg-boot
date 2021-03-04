package org.jeecg.modules.company.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.company.entity.BillingReport;
import org.jeecg.modules.company.entity.Employee;
import org.jeecg.modules.company.vo.EmployeePage;
import org.jeecg.modules.company.service.IEmployeeService;
import org.jeecg.modules.company.service.IBillingReportService;
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

/**
 * @Description: 清单
 * @Author: jeecg-boot
 * @Date:   2020-03-17
 * @Version: V1.0
 */
@RestController
@RequestMapping("/company/employee")
@Slf4j
public class EmployeeController {
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IBillingReportService billingReportService;

	/**
	 * 分页列表查询
	 *
	 * @param employee
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	@PermissionData(pageComponent="company/EmployeeList")
	public Result<?> queryPageList(Employee employee,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Employee> queryWrapper = QueryGenerator.initQueryWrapper(employee, req.getParameterMap());
		Page<Employee> page = new Page<Employee>(pageNo, pageSize);
		this.employeeService.sportsmanDelete(); //删除子表外键不存在的数据
		this.employeeService.deleteName(); //名字重复覆盖原始数据
		IPage<Employee> pageList = employeeService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 *   添加
	 *
	 * @param employeePage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody EmployeePage employeePage) {
       Employee employee = new Employee();
		BeanUtils.copyProperties(employeePage, employee);
		employeeService.save(employee);
		return Result.ok("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param employeePage
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody EmployeePage employeePage) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeePage, employee);
		employeeService.updateById(employee);
		return Result.ok("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		employeeService.delMain(id);
		return Result.ok("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
	    this.employeeService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		System.out.println(id);
		Employee employee = employeeService.getById(id);
		System.out.println(id);
		return Result.ok(employee);
	}

	/**
	 * 全部删除
	 *
	 * @return
	 *
	 */
	@DeleteMapping(value = "/deleteAll")
	public Result<?> deleteAll() {
		this.employeeService.deleteAll();
		return Result.ok("全部删除成功！");
	}

	//===========================以下是子表信息操作相关API====================================

	/**
	 * 通过主表id查询账单
	 *
  	 * @param billingReport
	 * @return
	 */
	@GetMapping(value = "/listBillingReportByMainId")
	public Result<?> listBillingReportByMainId(BillingReport billingReport,
                								  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                								  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                								  HttpServletRequest req) {
        if(billingReport.getSportsmanId()!=null) {
            QueryWrapper<BillingReport> queryWrapper = QueryGenerator.initQueryWrapper(billingReport, req.getParameterMap());
            Page<BillingReport> page = new Page<BillingReport>(pageNo, pageSize);
            IPage<BillingReport> pageList =  billingReportService.page(page, queryWrapper);
            return Result.ok(pageList);
        }else{
            return Result.ok();
        }
    }

	/**
     * 添加账单
     *
     * @param billingReport
     * @return
     */
    @PostMapping(value = "/addBillingReport")
    public Result<?> addBillingReport(@RequestBody BillingReport billingReport) {
        billingReportService.save(billingReport);
        return Result.ok("添加账单成功！");
    }

    /**
     * 编辑账单
     *
     * @param billingReport
     * @return
     */
    @PutMapping("/editBillingReport")
    public Result<?> editBillingReport(@RequestBody BillingReport billingReport) {
        billingReportService.updateById(billingReport);
        return Result.ok("编辑账单成功！");
    }

    /**
     * 通过id删除账单
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteBillingReport")
    public Result<?> deleteBillingReport(@RequestParam(name = "id", required = true) String id) {
       billingReportService.removeById(id);
        return Result.ok("删除账单成功！");
    }

	/**
     * 批量删除账单
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatchBillingReport")
    public Result<?> deleteBatchBillingReport(@RequestParam(name = "ids", required = true) String ids) {
        if (ids == null || "".equals(ids.trim())) {
            return Result.error("参数不识别！");
        }
        this.billingReportService.removeByIds(Arrays.asList(ids.split(",")));
       	return Result.ok("批量删除成功！");

    }


    /**
    * 导出excel
    *
    * @param request
    * @param employee
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Employee employee) {
      // Step.1 组装查询条件
      QueryWrapper<Employee> queryWrapper = QueryGenerator.initQueryWrapper(employee, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<EmployeePage> pageList = new ArrayList<EmployeePage>();
      List<Employee> employeeList = employeeService.list(queryWrapper);
      for (Employee temp : employeeList) {
          EmployeePage vo = new EmployeePage();
          BeanUtils.copyProperties(temp, vo);
          List<BillingReport> billingReportList = billingReportService.selectByMainId(temp.getId());
          vo.setBillingReportList(billingReportList);
          pageList.add(vo);
      }
      //Step.3 调用AutoPoi导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "清单");
      mv.addObject(NormalExcelConstants.CLASS, EmployeePage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("清单数据", "导出人:"+sysUser.getRealname(), "清单"));
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
              List<EmployeePage> list = ExcelImportUtil.importExcel(file.getInputStream(), EmployeePage.class, params);
              for (EmployeePage page : list) {
                  Employee po = new Employee();
                  BeanUtils.copyProperties(page, po);
                  employeeService.saveMain(po, page.getBillingReportList());
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
}
