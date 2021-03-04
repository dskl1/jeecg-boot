package org.jeecg.modules.match.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.match.entity.MatchScore;
import org.jeecg.modules.match.entity.MatchTotal;
import org.jeecg.modules.match.vo.MatchTotalPage;
import org.jeecg.modules.match.service.IMatchTotalService;
import org.jeecg.modules.match.service.IMatchScoreService;
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
 * @Description: 比赛名称
 * @Author: jeecg-boot
 * @Date:   2019-08-07
 * @Version: V1.0
 */
@RestController
@RequestMapping("/match/matchTotal")
@Slf4j
public class MatchTotalController {
	@Autowired
	private IMatchTotalService matchTotalService;
	@Autowired
	private IMatchScoreService matchScoreService;

	/**
	  * 分页列表查询
	 * @param matchTotal
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<MatchTotal>> queryPageList(MatchTotal matchTotal,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<MatchTotal>> result = new Result<IPage<MatchTotal>>();
		QueryWrapper<MatchTotal> queryWrapper = QueryGenerator.initQueryWrapper(matchTotal, req.getParameterMap());
		Page<MatchTotal> page = new Page<MatchTotal>(pageNo, pageSize);
		IPage<MatchTotal> pageList = matchTotalService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	  *   添加
	 * @param matchTotalPage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<MatchTotal> add(@RequestBody MatchTotalPage matchTotalPage) {
		Result<MatchTotal> result = new Result<MatchTotal>();
		try {
			MatchTotal matchTotal = new MatchTotal();
			BeanUtils.copyProperties(matchTotalPage, matchTotal);

			matchTotalService.save(matchTotal);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	  *  编辑
	 * @param matchTotalPage
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<MatchTotal> edit(@RequestBody MatchTotalPage matchTotalPage) {
		Result<MatchTotal> result = new Result<MatchTotal>();
		MatchTotal matchTotal = new MatchTotal();
		BeanUtils.copyProperties(matchTotalPage, matchTotal);
		MatchTotal matchTotalEntity = matchTotalService.getById(matchTotal.getId());
		if(matchTotalEntity==null) {
			result.error500("未找到对应实体");
		}else {
			matchTotalService.updateById(matchTotal);
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
			matchTotalService.delMain(id);
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
	public Result<MatchTotal> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<MatchTotal> result = new Result<MatchTotal>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.matchTotalService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<MatchTotal> queryById(@RequestParam(name="id",required=true) String id) {
		Result<MatchTotal> result = new Result<MatchTotal>();
		MatchTotal matchTotal = matchTotalService.getById(id);
		if(matchTotal==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(matchTotal);
			result.setSuccess(true);
		}
		return result;
	}

	//===========================以下是子表信息操作相关API====================================

	/**
	  * 通过主表id查询比赛成绩
	 * @param mainId
	 * @return
	 */
	@GetMapping(value = "/listMatchScoreByMainId")
	public Result<List<MatchScore>> queryMatchScoreListByMainId(@RequestParam(name="mainId",required=false) String mainId) {
		Result<List<MatchScore>> result = new Result<List<MatchScore>>();
		List<MatchScore> matchScoreList = null;
		if (mainId != null) {
			matchScoreList = matchScoreService.selectByMainId(mainId);
            result.setResult(matchScoreList);
            result.setSuccess(true);
            return result;
		}else return null;
	}

	/**
     * 添加比赛成绩
     *
     * @param matchScore
     * @return
     */
    @PostMapping(value = "/addMatchScore")
    public Result<MatchScore> addMatchScore(@RequestBody MatchScore matchScore) {
        Result<MatchScore> result = new Result<>();
        try {
            boolean ok = matchScoreService.save(matchScore);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("添加比赛成绩成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("添加比赛成绩失败!");
            }
            return result;
        } catch (Exception e) {
            e.fillInStackTrace();
            result.setSuccess(false);
            result.setMessage("添加比赛成绩过程中出现了异常: " + e.getMessage());
            return result;
        }
    }

    /**
     * 编辑比赛成绩
     *
     * @param matchScore
     * @return
     */
    @PutMapping("/editMatchScore")
    public Result<MatchScore> editMatchScore(@RequestBody MatchScore matchScore) {
        Result<MatchScore> result = new Result<>();
        try {
            boolean ok = matchScoreService.updateById(matchScore);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("更新比赛成绩成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("更新比赛成绩失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("更新数据过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 通过id删除比赛成绩
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteMatchScore")
    public Result<MatchScore> deleteMatchScore(@RequestParam(name = "id", required = true) String id) {
        Result<MatchScore> result = new Result<>();
        try {
            boolean ok = matchScoreService.removeById(id);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("删除比赛成绩成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("删除比赛成绩失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除比赛成绩过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

	/**
     * 批量删除比赛成绩
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatchMatchScore")
    public Result<MatchScore> deleteBatchMatchScore(@RequestParam(name = "ids", required = true) String ids) {
        Result<MatchScore> result = new Result<MatchScore>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.matchScoreService.removeByIds(Arrays.asList(ids.split(",")));
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
        QueryWrapper<MatchTotal> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                MatchTotal matchTotal = JSON.parseObject(deString, MatchTotal.class);
                queryWrapper = QueryGenerator.initQueryWrapper(matchTotal, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<MatchTotalPage> pageList = new ArrayList<MatchTotalPage>();
        List<MatchTotal> matchTotalList = matchTotalService.list(queryWrapper);
        for (MatchTotal matchTotal : matchTotalList) {
            MatchTotalPage vo = new MatchTotalPage();
            BeanUtils.copyProperties(matchTotal, vo);
            List<MatchScore> matchScoreList = matchScoreService.selectByMainId(matchTotal.getId());
            vo.setMatchScoreList(matchScoreList);
            pageList.add(vo);
        }
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "比赛名称列表");
        mv.addObject(NormalExcelConstants.CLASS, MatchTotalPage.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("比赛名称列表数据", "导出人:Jeecg", "导出信息"));
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
                List<MatchTotalPage> list = ExcelImportUtil.importExcel(file.getInputStream(), MatchTotalPage.class, params);
                for (MatchTotalPage page : list) {
                    MatchTotal po = new MatchTotal();
                    BeanUtils.copyProperties(page, po);
                    matchTotalService.saveMain(po, page.getMatchScoreList());
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
