package org.jeecg.modules.sportsman.controller;

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
import org.jeecg.modules.sportsman.entity.StudyPost;
import org.jeecg.modules.sportsman.entity.StudyScore;
import org.jeecg.modules.sportsman.vo.StudyScorePage;
import org.jeecg.modules.sportsman.service.IStudyScoreService;
import org.jeecg.modules.sportsman.service.IStudyPostService;
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
 * @Description: 学习成绩
 * @Author: jeecg-boot
 * @Date:   2020-11-06
 * @Version: V1.0
 */
@Api(tags="学习成绩")
@RestController
@RequestMapping("/sportsman/studyScore")
@Slf4j
public class StudyScoreController {
	@Autowired
	private IStudyScoreService studyScoreService;
	@Autowired
	private IStudyPostService studyPostService;
	
	/**
	 * 分页列表查询
	 *
	 * @param studyScore
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "学习成绩-分页列表查询")
	@ApiOperation(value="学习成绩-分页列表查询", notes="学习成绩-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(StudyScore studyScore,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<StudyScore> queryWrapper = QueryGenerator.initQueryWrapper(studyScore, req.getParameterMap());
		Page<StudyScore> page = new Page<StudyScore>(pageNo, pageSize);
		IPage<StudyScore> pageList = studyScoreService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param studyScorePage
	 * @return
	 */
	@AutoLog(value = "学习成绩-添加")
	@ApiOperation(value="学习成绩-添加", notes="学习成绩-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody StudyScorePage studyScorePage) {
		StudyScore studyScore = new StudyScore();
		BeanUtils.copyProperties(studyScorePage, studyScore);
		studyScoreService.saveMain(studyScore, studyScorePage.getStudyPostList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param studyScorePage
	 * @return
	 */
	@AutoLog(value = "学习成绩-编辑")
	@ApiOperation(value="学习成绩-编辑", notes="学习成绩-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody StudyScorePage studyScorePage) {
		StudyScore studyScore = new StudyScore();
		BeanUtils.copyProperties(studyScorePage, studyScore);
		StudyScore studyScoreEntity = studyScoreService.getById(studyScore.getId());
		if(studyScoreEntity==null) {
			return Result.error("未找到对应数据");
		}
		studyScoreService.updateMain(studyScore, studyScorePage.getStudyPostList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "学习成绩-通过id删除")
	@ApiOperation(value="学习成绩-通过id删除", notes="学习成绩-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		studyScoreService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "学习成绩-批量删除")
	@ApiOperation(value="学习成绩-批量删除", notes="学习成绩-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.studyScoreService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "学习成绩-通过id查询")
	@ApiOperation(value="学习成绩-通过id查询", notes="学习成绩-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		StudyScore studyScore = studyScoreService.getById(id);
		if(studyScore==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(studyScore);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "科目通过主表ID查询")
	@ApiOperation(value="科目主表ID查询", notes="科目-通主表ID查询")
	@GetMapping(value = "/queryStudyPostByMainId")
	public Result<?> queryStudyPostListByMainId(@RequestParam(name="id",required=true) String id) {
		List<StudyPost> studyPostList = studyPostService.selectByMainId(id);
		return Result.OK(studyPostList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param studyScore
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, StudyScore studyScore) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<StudyScore> queryWrapper = QueryGenerator.initQueryWrapper(studyScore, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<StudyScore> queryList = studyScoreService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<StudyScore> studyScoreList = new ArrayList<StudyScore>();
      if(oConvertUtils.isEmpty(selections)) {
          studyScoreList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          studyScoreList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<StudyScorePage> pageList = new ArrayList<StudyScorePage>();
      for (StudyScore main : studyScoreList) {
          StudyScorePage vo = new StudyScorePage();
          BeanUtils.copyProperties(main, vo);
          List<StudyPost> studyPostList = studyPostService.selectByMainId(main.getId());
          vo.setStudyPostList(studyPostList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "学习成绩列表");
      mv.addObject(NormalExcelConstants.CLASS, StudyScorePage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("学习成绩数据", "导出人:"+sysUser.getRealname(), "学习成绩"));
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
              List<StudyScorePage> list = ExcelImportUtil.importExcel(file.getInputStream(), StudyScorePage.class, params);
              for (StudyScorePage page : list) {
                  StudyScore po = new StudyScore();
                  BeanUtils.copyProperties(page, po);
                  studyScoreService.saveMain(po, page.getStudyPostList());
              }
              return Result.OK("文件导入成功！数据行数:" + list.size());
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
      return Result.OK("文件导入失败！");
    }

}
