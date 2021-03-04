package org.jeecg.modules.match.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.match.entity.MatchScorep;
import org.jeecg.modules.match.entity.MatchTotalp;
import org.jeecg.modules.match.service.IMatchScorepService;
import org.jeecg.modules.match.service.IMatchTotalpService;
import org.jeecg.modules.match.vo.MatchTotalpPage;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

 /**
 * @Description: 比赛
 * @Author: jeecg-boot
 * @Date:   2019-08-19
 * @Version: V1.0
 */
@RestController
@RequestMapping("/match/matchTotalp")
@Slf4j
public class MatchTotalpController {
	@Autowired
	private IMatchTotalpService matchTotalpService;
	@Autowired
	private IMatchScorepService matchScorepService;

	/**
	  * 分页列表查询
	 * @param matchTotalp
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
    @PermissionData(pageComponent="match/MatchTotalpList")
	public Result<IPage<MatchTotalp>> queryPageList(MatchTotalp matchTotalp,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<MatchTotalp>> result = new Result<IPage<MatchTotalp>>();
		QueryWrapper<MatchTotalp> queryWrapper = QueryGenerator.initQueryWrapper(matchTotalp, req.getParameterMap());
		Page<MatchTotalp> page = new Page<MatchTotalp>(pageNo, pageSize);
		IPage<MatchTotalp> pageList = matchTotalpService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	  *   添加
	 * @param matchTotalpPage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<MatchTotalp> add(@RequestBody MatchTotalpPage matchTotalpPage) {
		Result<MatchTotalp> result = new Result<MatchTotalp>();
		try {
            MatchTotalp matchTotalp = new MatchTotalp();
			BeanUtils.copyProperties(matchTotalpPage, matchTotalp);

			matchTotalpService.save(matchTotalp);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	  *  编辑
	 * @param matchTotalpPage
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<MatchTotalp> edit(@RequestBody MatchTotalpPage matchTotalpPage) {
		Result<MatchTotalp> result = new Result<MatchTotalp>();
		MatchTotalp matchTotalp = new MatchTotalp();
		BeanUtils.copyProperties(matchTotalpPage, matchTotalp);
		MatchTotalp matchTotalpEntity = matchTotalpService.getById(matchTotalp.getId());
		if(matchTotalpEntity==null) {
			result.error500("未找到对应实体");
		}else {
			matchTotalpService.updateById(matchTotalp);
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
			matchTotalpService.delMain(id);
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
	public Result<MatchTotalp> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<MatchTotalp> result = new Result<MatchTotalp>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.matchTotalpService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<MatchTotalp> queryById(@RequestParam(name="id",required=true) String id) {
		Result<MatchTotalp> result = new Result<MatchTotalp>();
		MatchTotalp matchTotalp = matchTotalpService.getById(id);
		if(matchTotalp==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(matchTotalp);
			result.setSuccess(true);
		}
		return result;
	}

//     /**
//      * 生成在添加比赛情况下没有主键的问题,返回给前端,根据该id绑定运动员数据
//      *
//      * @return
//      */
//     @RequestMapping(value = "/generateMatchId", method = RequestMethod.GET)
//     public Result<String> generateMatchId() {
//         Result<String> result = new Result<>();
//         System.out.println("我执行了,生成用户ID==============================");
//         String totalpId = UUID.randomUUID().toString().replace("-", "");
//         result.setSuccess(true);
//         result.setResult(totalpId);
//         return result;
//     }
//
//     /**
//      * 查询所有比赛所对应的运动员信息
//      *
//      * @return
//      */
//     @RequestMapping(value = "/queryMatchSportsmanMap", method = RequestMethod.GET)
//     public Result<Map<String, String>> queryMatchSportsman() {
//         Result<Map<String, String>> result = new Result<>();
//         Map<String, String> map = matchTotalpService.queryMatchSportsman();
//         result.setResult(map);
//         result.setSuccess(true);
//         return result;
//     }
//
//     /**
//      * @功能：根据id 批量查询
//      * @param totalpIds
//      * @return
//      */
//     @RequestMapping(value = "/queryByIds", method = RequestMethod.GET)
//     public Result<Collection<MatchTotalp>> queryByIds(@RequestParam String totalpIds) {
//         Result<Collection<MatchTotalp>> result = new Result<>();
//         String[] totalpId = totalpIds.split(",");
//         Collection<String> idList = Arrays.asList(totalpId);
//         Collection<MatchTotalp> matchSportsman = matchTotalpService.listByIds(idList);
//         result.setSuccess(true);
//         result.setResult(matchSportsman);
//         return result;
//     }
//
//     @RequestMapping(value = "/matchSportsmanList", method = RequestMethod.GET)
//     public Result<IPage<MatchTotalp>> matchSportsmanList(@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
//                                                     @RequestParam(name="pageSize", defaultValue="10") Integer pageSize, HttpServletRequest req) {
//         Result<IPage<MatchTotalp>> result = new Result<IPage<MatchTotalp>>();
//         Page<MatchTotalp> page = new Page<MatchTotalp>(pageNo, pageSize);
//         String sportsmanId = req.getParameter("sportsmanId");
//         String matchName = req.getParameter("matchName");
//         IPage<MatchTotalp> pageList = matchTotalpService.getTotalBySportsmanId(page,sportsmanId,matchName);
//         result.setSuccess(true);
//         result.setResult(pageList);
//         return result;
//     }
//
//     /**
//      * 给指定运动员添加比赛
//      *
//      * @param
//      * @return
//      */
//     @RequestMapping(value = "/addMatchSportsman", method = RequestMethod.POST)
//     public Result<String> addMatchSportsman(@RequestBody MatchSportsmanVO matchSportsmanVO) {
//         Result<String> result = new Result<String>();
//         try {
//             String sportsmanId = matchSportsmanVO.getSportsmanId();
//             for(String matchName:matchSportsmanVO.getMatchNameList()) {
//                 MatchTotalp matchTotalp = new MatchTotalp(matchName,sportsmanId);
//                 QueryWrapper<MatchTotalp> queryWrapper = new QueryWrapper<MatchTotalp>();
//                 queryWrapper.eq("sportsman_id", sportsmanId).eq("match_name",matchName);
//                 MatchTotalp one = matchTotalpService.getOne(queryWrapper);
//                 if(one==null){
//                     matchTotalpService.save(matchTotalp);
//                 }
//
//             }
//             result.setMessage("添加成功!");
//             result.setSuccess(true);
//             return result;
//         }catch(Exception e) {
//             log.error(e.getMessage(), e);
//             result.setSuccess(false);
//             result.setMessage("出错了: " + e.getMessage());
//             return result;
//         }
//     }
//
//     /**
//      *   删除指定运动员的比赛关系
//      * @param
//      * @return
//      */
//     @RequestMapping(value = "/deleteMatchSportsman", method = RequestMethod.DELETE)
//     public Result<MatchTotalp> deleteMatchSportsman(@RequestParam(name="sportsmanId") String sportsmanId,
//                                                            @RequestParam(name="matchName",required=true) String matchName
//     ) {
//         Result<MatchTotalp> result = new Result<MatchTotalp>();
//         try {
//             QueryWrapper<MatchTotalp> queryWrapper = new QueryWrapper<MatchTotalp>();
//             queryWrapper.eq("sportsman_id", sportsmanId).eq("match_name",matchName);
//             matchTotalpService.remove(queryWrapper);
//             result.success("删除成功!");
//         }catch(Exception e) {
//             log.error(e.getMessage(), e);
//             result.error500("删除失败！");
//         }
//         return result;
//     }
//
//     /**
//      * 批量删除指定运动员的比赛关系
//      *
//      * @param
//      * @return
//      */
//     @RequestMapping(value = "/deleteMatchSportsmanBatch", method = RequestMethod.DELETE)
//     public Result<MatchTotalp> deleteMatchSportsmanBatch(
//             @RequestParam(name="sportsmanId") String sportsmanId,
//             @RequestParam(name="matchNames",required=true) String matchNames) {
//         Result<MatchTotalp> result = new Result<MatchTotalp>();
//         try {
//             QueryWrapper<MatchTotalp> queryWrapper = new QueryWrapper<MatchTotalp>();
//             queryWrapper.eq("sportsman_id", sportsmanId).in("match_name",Arrays.asList(matchNames.split(",")));
//             matchTotalpService.remove(queryWrapper);
//             result.success("删除成功!");
//         }catch(Exception e) {
//             log.error(e.getMessage(), e);
//             result.error500("删除失败！");
//         }
//         return result;
//     }
//
//     @RequestMapping(value = "/querySportsmanMember", method = RequestMethod.GET)
//     public Result<List<String>> querySportsmanMember(@RequestParam(name = "matchname", required = true) String matchname) {
//         Result<List<String>> result = new Result<>();
//         List<String> list = new ArrayList<String>();
//         List<MatchTotalp> matchTotalpp = matchTotalpService.list(new QueryWrapper<MatchTotalp>().lambda().eq(MatchTotalp::getMatchName, matchname));
//         if (matchTotalpp == null || matchTotalpp.size() <= 0) {
//             result.error500("未找到比赛相关运动员信息");
//         } else {
//             for (MatchTotalp matchTotalp : matchTotalpp) {
//                 list.add(matchTotalp.getSportsmanId());
//             }
//             result.setSuccess(true);
//             result.setResult(list);
//         }
//         return result;
//     }

	//===========================以下是子表信息操作相关API====================================

	/**
	  * 通过主表id查询项目
	 * @param mainId
	 * @return
	 */
	@GetMapping(value = "/listMatchScorepByMainId")
	public Result<List<MatchScorep>> queryMatchScorepListByMainId(@RequestParam(name="mainId",required=false) String mainId) {
		Result<List<MatchScorep>> result = new Result<List<MatchScorep>>();
		List<MatchScorep> matchScorepList = null;
		if (mainId != null) {
			matchScorepList = matchScorepService.selectByMainId(mainId);
            result.setResult(matchScorepList);
            result.setSuccess(true);
            return result;
		}else return null;
	}

	/**
     * 添加项目
     *
     * @param matchScorep
     * @return
     */
    @PostMapping(value = "/addMatchScorep")
    public Result<MatchScorep> addMatchScorep(@RequestBody MatchScorep matchScorep) {
        Result<MatchScorep> result = new Result<>();
        try {
            boolean ok = matchScorepService.save(matchScorep);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("添加项目成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("添加项目失败!");
            }
            return result;
        } catch (Exception e) {
            e.fillInStackTrace();
            result.setSuccess(false);
            result.setMessage("添加项目过程中出现了异常: " + e.getMessage());
            return result;
        }
    }

    /**
     * 编辑项目
     *
     * @param matchScorep
     * @return
     */
    @PutMapping("/editMatchScorep")
    public Result<MatchScorep> editMatchScorep(@RequestBody MatchScorep matchScorep) {
        Result<MatchScorep> result = new Result<>();
        try {
            boolean ok = matchScorepService.updateById(matchScorep);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("更新项目成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("更新项目失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("更新数据过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 通过id删除项目
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteMatchScorep")
    public Result<MatchScorep> deleteMatchScorep(@RequestParam(name = "id", required = true) String id) {
        Result<MatchScorep> result = new Result<>();
        try {
            boolean ok = matchScorepService.removeById(id);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("删除项目成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("删除项目失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除项目过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

	/**
     * 批量删除项目
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatchMatchScorep")
    public Result<MatchScorep> deleteBatchMatchScorep(@RequestParam(name = "ids", required = true) String ids) {
        Result<MatchScorep> result = new Result<MatchScorep>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.matchScorepService.removeByIds(Arrays.asList(ids.split(",")));
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
        QueryWrapper<MatchTotalp> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                MatchTotalp matchTotalp = JSON.parseObject(deString, MatchTotalp.class);
                queryWrapper = QueryGenerator.initQueryWrapper(matchTotalp, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<MatchTotalpPage> pageList = new ArrayList<MatchTotalpPage>();
        List<MatchTotalp> matchTotalpList = matchTotalpService.list(queryWrapper);
        for (MatchTotalp matchTotalp : matchTotalpList) {
            MatchTotalpPage vo = new MatchTotalpPage();
            BeanUtils.copyProperties(matchTotalp, vo);
            List<MatchScorep> matchScorepList = matchScorepService.selectByMainId(matchTotalp.getId());
            vo.setMatchScorepList(matchScorepList);
            pageList.add(vo);
        }
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "比赛列表");
        mv.addObject(NormalExcelConstants.CLASS, MatchTotalpPage.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("比赛列表数据", "导出人:Jeecg", "导出信息"));
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
                List<MatchTotalpPage> list = ExcelImportUtil.importExcel(file.getInputStream(), MatchTotalpPage.class, params);
                for (MatchTotalpPage page : list) {
                    MatchTotalp po = new MatchTotalp();
                    BeanUtils.copyProperties(page, po);
                    matchTotalpService.saveMain(po, page.getMatchScorepList());
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
