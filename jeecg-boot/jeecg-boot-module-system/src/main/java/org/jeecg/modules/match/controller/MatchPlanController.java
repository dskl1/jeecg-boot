package org.jeecg.modules.match.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.match.entity.MatchMemberConnect;
import org.jeecg.modules.match.entity.MatchPlan;
import org.jeecg.modules.match.service.IMatchPlanService;
import org.jeecg.modules.match.service.IMatchMemberConnectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.match.vo.MatchMemberVO;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 比赛计划
 * @Author: jeecg-boot
 * @Date:   2019-08-06
 * @Version: V1.0
 */
@Slf4j
@Api(tags="比赛计划")
@RestController
@RequestMapping("/match/matchPlan")
public class MatchPlanController {
	@Autowired
	private IMatchPlanService matchPlanService;
	 @Autowired
	 private IMatchMemberConnectService matchMemberService;
	 @Autowired
	 private IMatchMemberConnectService matchMemberConnectService;

	 /**
       * 分页列表查询
      * @param matchPlan
      * @param pageNo
      * @param pageSize
      * @param req
      * @return
      */
	@AutoLog(value = "比赛计划-分页列表查询")
	@ApiOperation(value="比赛计划-分页列表查询", notes="比赛计划-分页列表查询")
	@GetMapping(value = "/list")
	@PermissionData(pageComponent="match/MatchPlanList")
	public Result<IPage<MatchPlan>> queryPageList(MatchPlan matchPlan,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<MatchPlan>> result = new Result<IPage<MatchPlan>>();
		QueryWrapper<MatchPlan> queryWrapper = QueryGenerator.initQueryWrapper(matchPlan, req.getParameterMap());
		Page<MatchPlan> page = new Page<MatchPlan>(pageNo, pageSize);
		IPage<MatchPlan> pageList = matchPlanService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	  *   添加
	 * @param matchPlan
	 * @return
	 */
	@AutoLog(value = "比赛计划-添加")
	@ApiOperation(value="比赛计划-添加", notes="比赛计划-添加")
	@PostMapping(value = "/add")
	public Result<MatchPlan> add(@RequestBody MatchPlan matchPlan) {
		Result<MatchPlan> result = new Result<MatchPlan>();
//		String selectedSportsmans = jsonObject.getString("selectedsportsmans");
		try {
//			MatchPlan match = JSON.parseObject(jsonObject.toJSONString(), MatchPlan.class);
//			matchPlanService.addMatchWithSportsman(match, selectedSportsmans);
			matchPlanService.save(matchPlan);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	  *  编辑
	 * @param matchPlan
	 * @return
	 */
	@AutoLog(value = "比赛计划-编辑")
	@ApiOperation(value="比赛计划-编辑", notes="比赛计划-编辑")
	@PutMapping(value = "/edit")
	public Result<MatchPlan> edit(@RequestBody MatchPlan matchPlan) {
		Result<MatchPlan> result = new Result<MatchPlan>();
//		try{
//			MatchPlan matchPlan1 = matchPlanService.getById(jsonObject.getString("id"));
//			if(matchPlan1==null) {
//				result.error500("未找到对应实体");
//			}else{
//				MatchPlan match = JSON.parseObject(jsonObject.toJSONString(), MatchPlan.class);
//				match.setUpdateTime(new Date());
//				String sportsmans = jsonObject.getString("selectedroles");
//				matchPlanService.editMatchWithSportsman(match, sportsmans);
//				result.success("修改成功!");
//			}
//		}catch (Exception e) {
//			log.error(e.getMessage(), e);
//			result.error500("操作失败");
//		}
		MatchPlan matchPlanEntity = matchPlanService.getById(matchPlan.getId());
		if(matchPlanEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = matchPlanService.updateById(matchPlan);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}

		return result;
	}

	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "比赛计划-通过id删除")
	@ApiOperation(value="比赛计划-通过id删除", notes="比赛计划-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			matchPlanService.removeById(id);
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
	@AutoLog(value = "比赛计划-批量删除")
	@ApiOperation(value="比赛计划-批量删除", notes="比赛计划-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<MatchPlan> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<MatchPlan> result = new Result<MatchPlan>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.matchPlanService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}

	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "比赛计划-通过id查询")
	@ApiOperation(value="比赛计划-通过id查询", notes="比赛计划-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<MatchPlan> queryById(@RequestParam(name="id",required=true) String id) {
		Result<MatchPlan> result = new Result<MatchPlan>();
		MatchPlan matchPlan = matchPlanService.getById(id);
		if(matchPlan==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(matchPlan);
			result.setSuccess(true);
		}
		return result;
	}

     @RequestMapping(value = "/queryalll", method = RequestMethod.GET)
     public Result<List<MatchPlan>> queryalll() {
         Result<List<MatchPlan>> result = new Result<>();
         List<MatchPlan> list = matchPlanService.list();
         if(list==null||list.size()<=0) {
             result.error500("未找到角色信息");
         }else {
             result.setResult(list);
             result.setSuccess(true);
         }
         return result;
     }

	 /**
	  * 生成在添加比赛情况下没有主键的问题,返回给前端,根据该id绑定运动员数据
	  *
	  * @return
	  */
	 @RequestMapping(value = "/generateMatchId", method = RequestMethod.GET)
	 public Result<String> generateMatchId() {
		 Result<String> result = new Result<>();
		 System.out.println("我执行了,生成比赛ID==============================");
		 String matchId = UUID.randomUUID().toString().replace("-", "");
		 result.setSuccess(true);
		 result.setResult(matchId);
		 return result;
	 }

	 /**
	  * 查询所有比赛所对应的运动员信息
	  *
	  * @return
	  */
	 @RequestMapping(value = "/queryMatchMemberMap", method = RequestMethod.GET)
	 public Result<Map<String, String>> queryMatchMember() {
		 Result<Map<String, String>> result = new Result<>();
		 Map<String, String> map = matchMemberService.queryMatchMember();
		 result.setResult(map);
		 result.setSuccess(true);
		 return result;
	 }

	 /**
	  * @功能：根据id 批量查询
	  * @param matchIds
	  * @return
	  */
	 @RequestMapping(value = "/queryByIds", method = RequestMethod.GET)
	 public Result<Collection<MatchPlan>> queryByIds(@RequestParam String matchIds) {
		 Result<Collection<MatchPlan>> result = new Result<>();
		 String[] matchId = matchIds.split(",");
		 Collection<String> idList = Arrays.asList(matchId);
		 Collection<MatchPlan> matchMember = matchPlanService.listByIds(idList);
		 result.setSuccess(true);
		 result.setResult(matchMember);
		 return result;
	 }

	 @RequestMapping(value = "/matchMemberList", method = RequestMethod.GET)
	 public Result<IPage<MatchPlan>> matchMemberList(@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
												@RequestParam(name="pageSize", defaultValue="10") Integer pageSize, HttpServletRequest req) {
		 Result<IPage<MatchPlan>> result = new Result<IPage<MatchPlan>>();
		 Page<MatchPlan> page = new Page<MatchPlan>(pageNo, pageSize);
		 String sportsmanId = req.getParameter("sportsmanId");
		 String matchName = req.getParameter("matchName");
		 IPage<MatchPlan> pageList = matchPlanService.getMatchBySportsmanId(page,sportsmanId,matchName);
		 result.setSuccess(true);
		 result.setResult(pageList);
		 return result;
	 }

	 /**
	  * 给指定运动员添加比赛
	  *
	  * @param
	  * @return
	  */
	 @RequestMapping(value = "/addMatchMember", method = RequestMethod.POST)
	 public Result<String> addMatchMember(@RequestBody MatchMemberVO matchMemberVO) {
		 Result<String> result = new Result<String>();
		 try {
			 String sportsmanId = matchMemberVO.getSportsmanId();
			 for(String matchId:matchMemberVO.getMatchIdList()) {
				 MatchMemberConnect matchMemberConnect = new MatchMemberConnect(matchId,sportsmanId);
				 QueryWrapper<MatchMemberConnect> queryWrapper = new QueryWrapper<MatchMemberConnect>();
				 queryWrapper.eq("sportsman_id", sportsmanId).eq("match_id",matchId);
				 MatchMemberConnect one = matchMemberConnectService.getOne(queryWrapper);
				 if(one==null){
					 matchMemberConnectService.save(matchMemberConnect);
				 }

			 }
			 result.setMessage("添加成功!");
			 result.setSuccess(true);
			 return result;
		 }catch(Exception e) {
			 log.error(e.getMessage(), e);
			 result.setSuccess(false);
			 result.setMessage("出错了: " + e.getMessage());
			 return result;
		 }
	 }

	 /**
	  *   删除指定运动员的比赛关系
	  * @param
	  * @return
	  */
	 @RequestMapping(value = "/deleteMatchSportsman", method = RequestMethod.DELETE)
	 public Result<MatchMemberConnect> deleteMatchSportsman(@RequestParam(name="sportsmanId") String sportsmanId,
											   @RequestParam(name="matchId",required=true) String matchId
	 ) {
		 Result<MatchMemberConnect> result = new Result<MatchMemberConnect>();
		 try {
			 QueryWrapper<MatchMemberConnect> queryWrapper = new QueryWrapper<MatchMemberConnect>();
			 queryWrapper.eq("sportsman_id", sportsmanId).eq("match_id",matchId);
			 matchMemberConnectService.remove(queryWrapper);
			 result.success("删除成功!");
		 }catch(Exception e) {
			 log.error(e.getMessage(), e);
			 result.error500("删除失败！");
		 }
		 return result;
	 }

	 /**
	  * 批量删除指定运动员的比赛关系
	  *
	  * @param
	  * @return
	  */
	 @RequestMapping(value = "/deleteMatchSportsmanBatch", method = RequestMethod.DELETE)
	 public Result<MatchMemberConnect> deleteMatchSportsmanBatch(
			 @RequestParam(name="sportsmanId") String sportsmanId,
			 @RequestParam(name="matchIds",required=true) String matchIds) {
		 Result<MatchMemberConnect> result = new Result<MatchMemberConnect>();
		 try {
			 QueryWrapper<MatchMemberConnect> queryWrapper = new QueryWrapper<MatchMemberConnect>();
			 queryWrapper.eq("sportsman_id", sportsmanId).in("match_id",Arrays.asList(matchIds.split(",")));
			 matchMemberConnectService.remove(queryWrapper);
			 result.success("删除成功!");
		 }catch(Exception e) {
			 log.error(e.getMessage(), e);
			 result.error500("删除失败！");
		 }
		 return result;
	 }

	 @RequestMapping(value = "/queryMatchMember", method = RequestMethod.GET)
	 public Result<List<String>> queryMatchMember(@RequestParam(name = "matchid", required = true) String matchid) {
		 Result<List<String>> result = new Result<>();
		 List<String> list = new ArrayList<String>();
		 List<MatchMemberConnect> matchMember = matchMemberConnectService.list(new QueryWrapper<MatchMemberConnect>().lambda().eq(MatchMemberConnect::getMatchId, matchid));
		 if (matchMember == null || matchMember.size() <= 0) {
			 result.error500("未找到比赛相关运动员信息");
		 } else {
			 for (MatchMemberConnect matchMemberConnect : matchMember) {
				 list.add(matchMemberConnect.getSportsmanId());
			 }
			 result.setSuccess(true);
			 result.setResult(list);
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
      QueryWrapper<MatchPlan> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              MatchPlan matchPlan = JSON.parseObject(deString, MatchPlan.class);
              queryWrapper = QueryGenerator.initQueryWrapper(matchPlan, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<MatchPlan> pageList = matchPlanService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "比赛计划列表");
      mv.addObject(NormalExcelConstants.CLASS, MatchPlan.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("比赛计划列表数据", "导出人:Jeecg", "导出信息"));
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
              List<MatchPlan> listMatchPlans = ExcelImportUtil.importExcel(file.getInputStream(), MatchPlan.class, params);
              matchPlanService.saveBatch(listMatchPlans);
              return Result.ok("文件导入成功！数据行数:" + listMatchPlans.size());
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
