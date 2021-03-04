package org.jeecg.modules.statistics.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.statistics.entity.MatchTotala;
import org.jeecg.modules.statistics.entity.Statistic;
import org.jeecg.modules.statistics.vo.StatisticPage;
import org.jeecg.modules.statistics.service.IStatisticService;
import org.jeecg.modules.statistics.service.IMatchTotalaService;
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
 * @Description: 比赛报表
 * @Author: jeecg-boot
 * @Date:   2019-09-18
 * @Version: V1.0
 */
@RestController
@RequestMapping("/statistics/statistic")
@Slf4j
public class StatisticController {
	@Autowired
	private IStatisticService statisticService;
	@Autowired
	private IMatchTotalaService matchTotalaService;

	/**
	  * 分页列表查询
	 * @param statistic
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
    @PermissionData(pageComponent="statistics/StatisticList")
	public Result<IPage<Statistic>> queryPageList(Statistic statistic,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Statistic>> result = new Result<IPage<Statistic>>();
		QueryWrapper<Statistic> queryWrapper = QueryGenerator.initQueryWrapper(statistic, req.getParameterMap());
		Page<Statistic> page = new Page<Statistic>(pageNo, pageSize);
		IPage<Statistic> pageList = statisticService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	  *   添加
	 * @param statisticPage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<Statistic> add(@RequestBody StatisticPage statisticPage) {
		Result<Statistic> result = new Result<Statistic>();
		try {
			Statistic statistic = new Statistic();
			BeanUtils.copyProperties(statisticPage, statistic);

			statisticService.save(statistic);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	  *  编辑
	 * @param statisticPage
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<Statistic> edit(@RequestBody StatisticPage statisticPage) {
		Result<Statistic> result = new Result<Statistic>();
		Statistic statistic = new Statistic();
		BeanUtils.copyProperties(statisticPage, statistic);
		Statistic statisticEntity = statisticService.getById(statistic.getId());
		if(statisticEntity==null) {
			result.error500("未找到对应实体");
		}else {
			statisticService.updateById(statistic);
			result.success("修改成功!");
		}

		return result;
	}

	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			statisticService.delMain(id);
		} catch (Exception e) {
			log.error("删除失败",e.getMessage());
			return Result.error("删除失败!");
		}
		return Result.ok("删除成功!");
	}

	/**
	  *  批量删除
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	public Result<Statistic> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Statistic> result = new Result<Statistic>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.statisticService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}

	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryById")
	public Result<Statistic> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Statistic> result = new Result<Statistic>();
		Statistic statistic = statisticService.getById(id);
		if(statistic==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(statistic);
			result.setSuccess(true);
		}
		return result;
	}

     /**
      * 获取访问量
      * @return
      */
     @GetMapping("trainingInfo")
     public Result<List<Map<String,Object>>> trainingInfo(@RequestParam(name="mainId",required=false) String mainId,
                                                          @RequestParam(name="selectValue",required=false) Integer selectValue) {
         Result<List<Map<String,Object>>> result = new Result<List<Map<String,Object>>>();
         Calendar calendar = new GregorianCalendar();
         calendar.set(Calendar.HOUR_OF_DAY,0);
         calendar.set(Calendar.MINUTE,0);
         calendar.set(Calendar.SECOND,0);
         calendar.set(Calendar.MILLISECOND,0);
         calendar.add(Calendar.DAY_OF_MONTH, 1);
         Date dayEnd = calendar.getTime();
         calendar.add(Calendar.DAY_OF_MONTH, -selectValue);
         Date dayStart = calendar.getTime();
         List<Map<String,Object>> list = matchTotalaService.findTrainingCount(selectValue,mainId,dayStart, dayEnd);
         result.setResult(oConvertUtils.toLowerCasePageList(list));
         return result;
     }

	//===========================以下是子表信息操作相关API====================================

	/**
	  * 通过主表id查询比赛报表
	 * @param mainId
	 * @return
	 */
	@GetMapping(value = "/listMatchTotalaByMainId")
	public Result<List<MatchTotala>> queryMatchTotalaListByMainId(@RequestParam(name="mainId",required=false) String mainId) {
		Result<List<MatchTotala>> result = new Result<List<MatchTotala>>();
		List<MatchTotala> matchTotalaList = null;
		if (mainId != null) {
			matchTotalaList = matchTotalaService.selectByMainId(mainId);
            result.setResult(matchTotalaList);
            result.setSuccess(true);
            return result;
		}else return null;
	}

	/**
     * 添加比赛报表
     *
     * @param matchTotala
     * @return
     */
    @PostMapping(value = "/addMatchTotala")
    public Result<MatchTotala> addMatchTotala(@RequestBody MatchTotala matchTotala) {
        Result<MatchTotala> result = new Result<>();
        try {
            boolean ok = matchTotalaService.save(matchTotala);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("添加比赛报表成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("添加比赛报表失败!");
            }
            return result;
        } catch (Exception e) {
            e.fillInStackTrace();
            result.setSuccess(false);
            result.setMessage("添加比赛报表过程中出现了异常: " + e.getMessage());
            return result;
        }
    }

    /**
     * 编辑比赛报表
     *
     * @param matchTotala
     * @return
     */
    @PutMapping("/editMatchTotala")
    public Result<MatchTotala> editMatchTotala(@RequestBody MatchTotala matchTotala) {
        Result<MatchTotala> result = new Result<>();
        try {
            boolean ok = matchTotalaService.updateById(matchTotala);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("更新比赛报表成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("更新比赛报表失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("更新数据过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 通过id删除比赛报表
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteMatchTotala")
    public Result<MatchTotala> deleteMatchTotala(@RequestParam(name = "id", required = true) String id) {
        Result<MatchTotala> result = new Result<>();
        try {
            boolean ok = matchTotalaService.removeById(id);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("删除比赛报表成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("删除比赛报表失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除比赛报表过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

	/**
     * 批量删除比赛报表
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatchMatchTotala")
    public Result<MatchTotala> deleteBatchMatchTotala(@RequestParam(name = "ids", required = true) String ids) {
        Result<MatchTotala> result = new Result<MatchTotala>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.matchTotalaService.removeByIds(Arrays.asList(ids.split(",")));
            result.success("删除成功!");
        }
        return result;
    }


    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
        // Step.1 组装查询条件
        QueryWrapper<Statistic> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                Statistic statistic = JSON.parseObject(deString, Statistic.class);
                queryWrapper = QueryGenerator.initQueryWrapper(statistic, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<StatisticPage> pageList = new ArrayList<StatisticPage>();
        List<Statistic> statisticList = statisticService.list(queryWrapper);
        for (Statistic statistic : statisticList) {
            StatisticPage vo = new StatisticPage();
            BeanUtils.copyProperties(statistic, vo);
            List<MatchTotala> matchTotalaList = matchTotalaService.selectByMainId(statistic.getId());
            vo.setMatchTotalaList(matchTotalaList);
            pageList.add(vo);
        }
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "比赛报表列表");
        mv.addObject(NormalExcelConstants.CLASS, StatisticPage.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("比赛报表列表数据", "导出人:Jeecg", "导出信息"));
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
                List<StatisticPage> list = ExcelImportUtil.importExcel(file.getInputStream(), StatisticPage.class, params);
                for (StatisticPage page : list) {
                    Statistic po = new Statistic();
                    BeanUtils.copyProperties(page, po);
                    statisticService.saveMain(po, page.getMatchTotalaList());
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
