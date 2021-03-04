package org.jeecg.modules.company.controller;

import com.alibaba.fastjson.JSON;
import org.jeecg.common.system.query.QueryGenerator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecg.modules.company.entity.TestWord;
import org.jeecg.modules.company.entity.BasicSituation;
import org.jeecg.modules.company.service.IBasicSituationService;
import org.jeecg.modules.company.service.ITestWordService;


/**
 * @Description: 简历
 * @Author: jeecg-boot
 * @Date: 2020-04-21
 * @Version: V1.0
 */
@RestController
@RequestMapping("/company/basicSituation")
@Slf4j
public class BasicSituationController extends JeecgController<BasicSituation, IBasicSituationService> {

    @Autowired
    private IBasicSituationService basicSituationService;

    @Autowired
    private ITestWordService testWordService;


    /*---------------------------------主表处理-begin-------------------------------------*/

    /**
     * 分页列表查询
     *
     * @param basicSituation
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @GetMapping(value = "/list")
    public Result<?> queryPageList(BasicSituation basicSituation,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<BasicSituation> queryWrapper = QueryGenerator.initQueryWrapper(basicSituation, req.getParameterMap());
        Page<BasicSituation> page = new Page<BasicSituation>(pageNo, pageSize);
        IPage<BasicSituation> pageList = basicSituationService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param basicSituation
     * @return
     */
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody BasicSituation basicSituation) {
        basicSituationService.save(basicSituation);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param basicSituation
     * @return
     */
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody BasicSituation basicSituation) {
        basicSituationService.updateById(basicSituation);
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
        basicSituationService.delMain(id);
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
        this.basicSituationService.delBatchMain(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功!");
    }

    /**
     * 导出
     *
     * @return
     */
    @RequestMapping(value = "/exportXls")
    public String export(HttpServletRequest request, HttpServletResponse response, @RequestParam(name = "name", required = false) String name) {
        Map<String, Object> params = new HashMap<>();
        List<BasicSituation> list1 = basicSituationService.selectUserInfoByNa(name);
        for (BasicSituation lis : list1) {//遍历集合往map中添加数据 由于我定义的map都是string 所以部分做了相应的转换
            params.put("name", lis.getName());
            params.put("sex", lis.getSex());
            params.put("birthday", lis.getBirthday());
            params.put("job", lis.getJob());
            params.put("hometown", lis.getHometown());
            params.put("educationBackground", lis.getEducationBackground());
            params.put("profession", lis.getProfession());
            params.put("workExperience", lis.getWorkExperience());
            params.put("projectExperience", lis.getProjectExperience());
            params.put("selfEvaluation", lis.getSelfEvaluation());

        }
        ExportWordUtils.exportWord("D:/info.docx", "D:/test", "info.docx", params, request, response);
        return JSON.toJSONString(basicSituationService.selectUserInfoByNa(name));
    }

//	 @RequestMapping(value = "/exportXlss")
//	 public XWPFDocument exportXls(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		 TemplateExportParams params = new TemplateExportParams();
//		 params.setHeadingRows(2);
//		 params.setHeadingStartRow(2);
//		 Map<String,Object> map = new HashMap<String, Object>();
//		 map.put("job", "2013");
//		 map.put("sex", "女");
//		 map.put("name", "dsa");
//		 XWPFDocument book = WordExportUtil.exportWord07("D://info.docx", map);
//		 return book;
//	 }

    /**
     * 导入
     *
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, BasicSituation.class);
    }
    /*---------------------------------主表处理-end-------------------------------------*/


    /*--------------------------------子表处理-子表-begin----------------------------------------------*/

    /**
     * 查询子表信息 会传入主表ID
     *
     * @return
     */
    @GetMapping(value = "/listTestWordByMainId")
    public Result<?> listTestWordByMainId(TestWord testWord,
                                          @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                          HttpServletRequest req) {
        QueryWrapper<TestWord> queryWrapper = QueryGenerator.initQueryWrapper(testWord, req.getParameterMap());
        Page<TestWord> page = new Page<TestWord>(pageNo, pageSize);
        IPage<TestWord> pageList = testWordService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param testWord
     * @return
     */
    @PostMapping(value = "/addTestWord")
    public Result<?> addTestWord(@RequestBody TestWord testWord) {
        testWordService.save(testWord);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param testWord
     * @return
     */
    @PutMapping(value = "/editTestWord")
    public Result<?> editTestWord(@RequestBody TestWord testWord) {
        testWordService.updateById(testWord);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteTestWord")
    public Result<?> deleteTestWord(@RequestParam(name = "id", required = true) String id) {
        testWordService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatchTestWord")
    public Result<?> deleteBatchTestWord(@RequestParam(name = "ids", required = true) String ids) {
        this.testWordService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功!");
    }

    /*--------------------------------子表处理-子表-end----------------------------------------------*/


}
