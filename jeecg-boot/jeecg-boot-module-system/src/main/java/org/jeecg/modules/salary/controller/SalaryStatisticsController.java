package org.jeecg.modules.salary.controller;

import java.io.File;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Email;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.alibaba.fastjson.JSONObject;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.company.entity.BasicSituation;
import org.jeecg.modules.salary.entity.SalaryStatistics;
import org.jeecg.modules.salary.service.ISalaryStatisticsService;

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
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

/**
 * @Description: 工资统计
 * @Author: jeecg-boot
 * @Date: 2020-07-17
 * @Version: V1.0
 */
@Api(tags = "工资统计")
@RestController
@RequestMapping("/salary/salaryStatistics")
@Slf4j
public class SalaryStatisticsController extends JeecgController<SalaryStatistics, ISalaryStatisticsService> {
    @Autowired
    private ISalaryStatisticsService salaryStatisticsService;
    @Autowired
    private JavaMailSender javaMailSender;


    /**
     * 分页列表查询
     *
     * @param salaryStatistics
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "工资统计-分页列表查询")
    @ApiOperation(value = "工资统计-分页列表查询", notes = "工资统计-分页列表查询")
    @GetMapping(value = "/list")
    @PermissionData(pageComponent = "salary/SalaryStatisticsList")
    public Result<?> queryPageList(SalaryStatistics salaryStatistics,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<SalaryStatistics> queryWrapper = QueryGenerator.initQueryWrapper(salaryStatistics, req.getParameterMap());
        this.salaryStatisticsService.basicWageUpdate(); //计算基本工资标准
        this.salaryStatisticsService.dispatchSubsidyUpdate(); //计算外派补贴标准
        this.salaryStatisticsService.basicUpdate();  //计算员工基本工资,住房补贴
        this.salaryStatisticsService.overtimeUpdate(); //计算员工加班工资
        this.salaryStatisticsService.dispatchUpdate(); //计算员工外派补贴,工资总计,税前总计
//        this.salaryStatisticsService.taxUpdate();  //计算个税
        this.salaryStatisticsService.incomeUpdate();  //计算实际收入
        this.salaryStatisticsService.totalUpdate(); //计算总计支出工资
        Page<SalaryStatistics> page = new Page<SalaryStatistics>(pageNo, pageSize);
        IPage<SalaryStatistics> pageList = salaryStatisticsService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param salaryStatistics
     * @return
     */
    @AutoLog(value = "工资统计-添加")
    @ApiOperation(value = "工资统计-添加", notes = "工资统计-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody SalaryStatistics salaryStatistics) {
        salaryStatisticsService.save(salaryStatistics);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param salaryStatistics
     * @return
     */
    @AutoLog(value = "工资统计-编辑")
    @ApiOperation(value = "工资统计-编辑", notes = "工资统计-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody SalaryStatistics salaryStatistics) {
        salaryStatisticsService.updateById(salaryStatistics);
        return Result.ok("编辑成功!");
    }

    /**
     * 发送工资(正式)
     *
     * @return
     * @throws MessagingException
     */
    @AutoLog(value = "工资统计-发送工资(正式)")
    @ApiOperation(value = "工资统计-发送工资(正式)", notes = "工资统计-发送工资(正式)")
    @RequestMapping(value = "/mail", method = RequestMethod.PUT)
    public Result<?> testSend(@RequestBody JSONObject jsonObject) throws MessagingException {
        String ids = jsonObject.getString("ids");
        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("mayanfan21@163.com");
        message.setFrom("zhangmy@evinco.cn");
        List<SalaryStatistics> list1 = this.salaryStatisticsService.listByIds(Arrays.asList(ids.split(",")));
        for (SalaryStatistics lis : list1) {//遍历集合往map中添加数据 由于我定义的map都是string 所以部分做了相应的转换
            message.setTo(lis.getEmail());
            message.setSubject("工资");
            message.setText("姓名:\t\t" + lis.getName() + "\n" +
                    "基本工资标准:\t" + lis.getBasicWageStandard() + "\n" +
                    "外派补贴标准:\t" + lis.getDispatchSubsidyStandard() + "\n" +
                    "应出勤天数:\t" + lis.getTotalDay() + "\n" +
                    "实际出勤天数:\t" + (lis.getTrialDay() + lis.getPositiveDay()) + "\n" +
                    "外派天数:\t" + (lis.getTrialDispatchDay() + lis.getPositiveDispatchDay()) + "\n" +
                    "加班时间:\t" + lis.getOvertimeHours() + "\n\n" +
                    "工资明细:\n" +
                    "基本工资:\t" + lis.getBasicWage() + "\n" +
                    "外派补贴:\t" + lis.getDispatchSubsidy() + "\n" +
                    "加班工资:\t" + lis.getOvertimeSalary() + "\n" +
                    "绩效:\t\t" + lis.getPerformance() + "\n" +
                    "餐补:\t\t" + lis.getMealSupplement() + "\n" +
                    "其他:\t\t" + lis.getOtherThing() + "\n" +
                    "电脑补贴:\t" + lis.getComputerSubsidy() + "\n\n" +
                    "扣除项:\n" +
                    "预支工资:\t" + lis.getAdvanceSalary() + "\n" +
                    "公积金:\t\t" + lis.getProvidentFund() + "\n" +
                    "社保:\t\t" + lis.getSocialSecurityPerson() + "\n" +
                    "迟到:\t\t" + lis.getLate() + "\n\n" +
                    "工资总计:\t" + lis.getTotalIncome() + "\n" +
                    "税前总计:\t" + lis.getPreTaxIncome() + "\n" +
                    "所得税:\t\t" + lis.getPersonIncome() + "\n" +
                    "应发:\t\t" + lis.getActualSalary() + "\n");
            javaMailSender.send(message);
        }
        return Result.ok("发送成功!");
    }

    /**
     * 发送工资(试用)
     *
     * @return
     * @throws MessagingException
     */
    @AutoLog(value = "工资统计-发送工资(试用)")
    @ApiOperation(value = "工资统计-发送工资(试用)", notes = "工资统计-发送工资(试用)")
    @RequestMapping(value = "/mailt", method = RequestMethod.PUT)
    public Result<?> trialSend(@RequestBody JSONObject jsonObject) throws MessagingException {
        String ids = jsonObject.getString("ids");
        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("mayanfan21@163.com");
        message.setFrom("zhangmy@evinco.cn");
        List<SalaryStatistics> list1 = this.salaryStatisticsService.listByIds(Arrays.asList(ids.split(",")));
        for (SalaryStatistics lis : list1) {//遍历集合往map中添加数据 由于我定义的map都是string 所以部分做了相应的转换
            message.setTo(lis.getEmail());
            message.setSubject("工资");
            message.setText("姓名:\t\t" + lis.getName() + "\n" +
                    "基本工资标准:\t" + lis.getBasicWageStandard() + "\n" +
                    "外派补贴标准:\t" + lis.getDispatchSubsidyStandard() + "\n" +
                    "应出勤天数:\t" + lis.getTotalDay() + "\n" +
                    "试用天数:\t" + lis.getTrialDay() + "\n" +
                    "正式天数:\t" + lis.getPositiveDay() + "\n" +
                    "试用外派天数:\t" + lis.getTrialDispatchDay() + "\n" +
                    "正式外派天数:\t" + lis.getPositiveDispatchDay() + "\n" +
                    "加班时间:\t" + lis.getOvertimeHours() + "\n\n" +
                    "工资明细:\n" +
                    "基本工资:\t" + lis.getBasicWage() + "\n" +
                    "外派补贴:\t" + lis.getDispatchSubsidy() + "\n" +
                    "加班工资:\t" + lis.getOvertimeSalary() + "\n" +
                    "绩效:\t\t" + lis.getPerformance() + "\n" +
                    "餐补:\t\t" + lis.getMealSupplement() + "\n" +
                    "其他:\t\t" + lis.getOtherThing() + "\n" +
                    "电脑补贴:\t" + lis.getComputerSubsidy() + "\n\n" +
                    "扣除项:\n" +
                    "预支工资:\t" + lis.getAdvanceSalary() + "\n" +
                    "公积金:\t\t" + lis.getProvidentFund() + "\n" +
                    "社保:\t\t" + lis.getSocialSecurityPerson() + "\n" +
                    "迟到:\t\t" + lis.getLate() + "\n\n" +
                    "工资总计:\t" + lis.getTotalIncome() + "\n" +
                    "税前总计:\t" + lis.getPreTaxIncome() + "\n" +
                    "所得税:\t\t" + lis.getPersonIncome() + "\n" +
                    "应发:\t\t" + lis.getActualSalary() + "\n");
            javaMailSender.send(message);
        }
        return Result.ok("发送成功!");
    }

    /**
     * 发送通知
     *
     * @return
     * @throws MessagingException
     */
    @AutoLog(value = "工资统计-发送通知")
    @ApiOperation(value = "工资统计-发送通知", notes = "工资统计-发送通知")
    @RequestMapping(value = "/mails", method = RequestMethod.PUT)

    public Result<?> testSends(@RequestBody JSONObject jsonObject) throws MessagingException, UnsupportedEncodingException {
        String ids = jsonObject.getString("ids");
        MimeMessage message = javaMailSender.createMimeMessage();
        try {

//            message.setFrom("mayanfan21@163.com");
            message.setFrom("zhangmy@evinco.cn");
            List<SalaryStatistics> list1 = this.salaryStatisticsService.listByIds(Arrays.asList(ids.split(",")));
            for (int i = 0; i < ids.split(",").length; i++) {
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                //标题
                helper.setSubject("通知");
                helper.setTo(list1.get(i).getEmail());
                //true指的是html邮件，false指的是普通文本
                helper.setText(list1.get(i).getNotice(), true);
                String[] imgPath = list1.get(i).getImgPath().split(",");
                FileSystemResource file = new FileSystemResource(new File("D:\\avatar\\" + list1.get(i).getImgPath()));
                helper.addAttachment("附件.pdf", file);
                javaMailSender.send(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发送带附件的邮件失败");
        }

        return Result.ok("发送成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "工资统计-通过id删除")
    @ApiOperation(value = "工资统计-通过id删除", notes = "工资统计-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        salaryStatisticsService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     * @throws MessagingException
     */
    @AutoLog(value = "工资统计-批量删除")
    @ApiOperation(value = "工资统计-批量删除", notes = "工资统计-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.salaryStatisticsService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "工资统计-通过id查询")
    @ApiOperation(value = "工资统计-通过id查询", notes = "工资统计-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        SalaryStatistics salaryStatistics = salaryStatisticsService.getById(id);
        if (salaryStatistics == null) {
            return Result.error("未找到对应数据");
        }
        return Result.ok(salaryStatistics);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param salaryStatistics
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SalaryStatistics salaryStatistics) {
        return super.exportXls(request, salaryStatistics, SalaryStatistics.class, "工资统计");
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
        this.salaryStatisticsService.deleteName(); //覆盖原始数据
        return super.importExcel(request, response, SalaryStatistics.class);
    }

    /**
     * 计算年假
     *
     * @return
     * @throws MessagingException
     */
    @AutoLog(value = "工资统计-计算年假")
    @ApiOperation(value = "工资统计-计算年假", notes = "工资统计-计算年假")
    @RequestMapping(value = "/holiday", method = RequestMethod.PUT)
    public Result<?> yearHoliday(@RequestBody JSONObject jsonObject) throws MessagingException {
        String ids = jsonObject.getString("ids");
        List<SalaryStatistics> list1 = this.salaryStatisticsService.listByIds(Arrays.asList(ids.split(",")));
        for (SalaryStatistics lis : list1) {
            this.salaryStatisticsService.holidayUpdate(lis.getId());
        }
        return Result.ok("计算成功!");
    }

    /**
     * 年假减一
     *
     * @return
     * @throws MessagingException
     */
    @AutoLog(value = "工资统计-年假减一")
    @ApiOperation(value = "工资统计-年假减一", notes = "工资统计-年假减一")
    @RequestMapping(value = "/minis", method = RequestMethod.PUT)
    public Result<?> minisHoliday(@RequestBody JSONObject jsonObject) throws MessagingException {
        String ids = jsonObject.getString("ids");
        List<SalaryStatistics> list1 = this.salaryStatisticsService.listByIds(Arrays.asList(ids.split(",")));
        for (SalaryStatistics lis : list1) {
            this.salaryStatisticsService.minisHoliday(lis.getId());
        }
        return Result.ok("成功!");
    }

    /**
     * 下载文件
     *
     * @return
     * @throws MessagingException
     */
    @AutoLog(value = "工资统计-下载txt")
    @ResponseBody
    @ApiOperation(value = "工资统计-下载txt", notes = "工资统计-下载txt")
    @RequestMapping(value = "/txt", method = RequestMethod.PUT)
    public Result<?> testSend(@RequestBody JSONObject jsonObject, HttpServletResponse response, HttpServletRequest request) throws MessagingException, MalformedURLException {
        String ids = jsonObject.getString("ids");
        this.salaryStatisticsService.exportLandAndClassIndex(ids);
        return Result.ok("下载成功");
    }


}
