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

import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.modules.match.service.IMatchScorepService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.match.entity.MatchTotalp;
import org.jeecg.modules.match.entity.MatchMemberp;
import org.jeecg.modules.match.vo.MatchMemberpPage;
import org.jeecg.modules.match.service.IMatchMemberpService;
import org.jeecg.modules.match.service.IMatchTotalpService;
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
 * @Description: 运动员
 * @Author: jeecg-boot
 * @Date: 2019-08-19
 * @Version: V1.0
 */
@RestController
@RequestMapping("/match/matchMemberp")
@Slf4j
public class MatchMemberpController {
    @Autowired
    private IMatchMemberpService matchMemberpService;
    @Autowired
    private IMatchTotalpService matchTotalpService;
    @Autowired
    private IMatchScorepService matchScorepService;

    /**
     * 分页列表查询
     *
     * @param matchMemberp
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @GetMapping(value = "/list")
    @PermissionData(pageComponent = "match/MatchMemberpList")
    public Result<IPage<MatchMemberp>> queryPageList(MatchMemberp matchMemberp,
                                                     @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                     HttpServletRequest req) {
        Result<IPage<MatchMemberp>> result = new Result<IPage<MatchMemberp>>();
        QueryWrapper<MatchMemberp> queryWrapper = QueryGenerator.initQueryWrapper(matchMemberp, req.getParameterMap());
        Page<MatchMemberp> page = new Page<MatchMemberp>(pageNo, pageSize);
        this.matchScorepService.scoreDelete(); //删除子表外键不存在的数据
        IPage<MatchMemberp> pageList = matchMemberpService.page(page, queryWrapper);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 添加
     *
     * @param matchMemberpPage
     * @return
     */
    @PostMapping(value = "/add")
    public Result<MatchMemberp> add(@RequestBody MatchMemberpPage matchMemberpPage) {
        Result<MatchMemberp> result = new Result<MatchMemberp>();
        try {
            MatchMemberp matchMemberp = new MatchMemberp();
            BeanUtils.copyProperties(matchMemberpPage, matchMemberp);

            matchMemberpService.save(matchMemberp);
            result.success("添加成功！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败");
        }
        return result;
    }

    /**
     * 编辑
     *
     * @param matchMemberpPage
     * @return
     */
    @PutMapping(value = "/edit")
    public Result<MatchMemberp> edit(@RequestBody MatchMemberpPage matchMemberpPage) {
        Result<MatchMemberp> result = new Result<MatchMemberp>();
        MatchMemberp matchMemberp = new MatchMemberp();
        BeanUtils.copyProperties(matchMemberpPage, matchMemberp);
        MatchMemberp matchMemberpEntity = matchMemberpService.getById(matchMemberp.getId());
        if (matchMemberpEntity == null) {
            result.error500("未找到对应实体");
        } else {
            matchMemberpService.updateById(matchMemberp);
            result.success("修改成功!");
        }

        return result;
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        try {
            matchMemberpService.delMain(id);
        } catch (Exception e) {
            log.error("删除失败", e.getMessage());
            return Result.error("删除失败!");
        }
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatch")
    public Result<MatchMemberp> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        Result<MatchMemberp> result = new Result<MatchMemberp>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.matchMemberpService.removeByIds(Arrays.asList(ids.split(",")));
            result.success("删除成功!");
        }
        return result;
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/queryById")
    public Result<MatchMemberp> queryById(@RequestParam(name = "id", required = true) String id) {
        Result<MatchMemberp> result = new Result<MatchMemberp>();
        MatchMemberp matchMemberp = matchMemberpService.getById(id);
        if (matchMemberp == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(matchMemberp);
            result.setSuccess(true);
        }
        return result;
    }

    /**
     * 通过主表
     *
     * @param
     * @return
     */
    @GetMapping(value = "/list123")
    public Result<IPage<MatchMemberp>> queryMatchTotalpListByMainId() {
        Result<IPage<MatchMemberp>> result = new Result<IPage<MatchMemberp>>();
        IPage<MatchMemberp> matchMemberpList = null;
        matchMemberpList = matchMemberpService.selectByDivide();
        result.setResult(matchMemberpList);
        result.setSuccess(true);
        return result;
    }

    //===========================以下是子表信息操作相关API====================================

    /**
     * 通过主表id查询比赛
     *
     * @param mainId
     * @return
     */
    @GetMapping(value = "/listMatchTotalpByMainId")
    @PermissionData(pageComponent = "match/MatchTotalpList")
    public Result<List<MatchTotalp>> queryMatchTotalpListByMainId(@RequestParam(name = "mainId", required = false) String mainId) {
        Result<List<MatchTotalp>> result = new Result<List<MatchTotalp>>();
        List<MatchTotalp> matchTotalpList = null;
        if (mainId != null) {
            matchTotalpList = matchTotalpService.selectByMainId(mainId);
            result.setResult(matchTotalpList);
            result.setSuccess(true);
            return result;
        } else return null;
    }

    /**
     * 添加比赛
     *
     * @param matchTotalp
     * @return
     */
    @PostMapping(value = "/addMatchTotalp")
    public Result<MatchTotalp> addMatchTotalp(@RequestBody MatchTotalp matchTotalp) {
        Result<MatchTotalp> result = new Result<>();
        try {
            boolean ok = matchTotalpService.save(matchTotalp);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("添加比赛成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("添加比赛失败!");
            }
            return result;
        } catch (Exception e) {
            e.fillInStackTrace();
            result.setSuccess(false);
            result.setMessage("添加比赛过程中出现了异常: " + e.getMessage());
            return result;
        }
    }

    /**
     * 编辑比赛
     *
     * @param matchTotalp
     * @return
     */
    @PutMapping("/editMatchTotalp")
    public Result<MatchTotalp> editMatchTotalp(@RequestBody MatchTotalp matchTotalp) {
        Result<MatchTotalp> result = new Result<>();
        try {
            boolean ok = matchTotalpService.updateById(matchTotalp);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("更新比赛成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("更新比赛失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("更新数据过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 通过id删除比赛
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteMatchTotalp")
    public Result<MatchTotalp> deleteMatchTotalp(@RequestParam(name = "id", required = true) String id) {
        Result<MatchTotalp> result = new Result<>();
        try {
            boolean ok = matchTotalpService.removeById(id);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("删除比赛成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("删除比赛失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除比赛过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 批量删除比赛
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatchMatchTotalp")
    public Result<MatchTotalp> deleteBatchMatchTotalp(@RequestParam(name = "ids", required = true) String ids) {
        Result<MatchTotalp> result = new Result<MatchTotalp>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.matchTotalpService.removeByIds(Arrays.asList(ids.split(",")));
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
        QueryWrapper<MatchMemberp> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                MatchMemberp matchMemberp = JSON.parseObject(deString, MatchMemberp.class);
                queryWrapper = QueryGenerator.initQueryWrapper(matchMemberp, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<MatchMemberpPage> pageList = new ArrayList<MatchMemberpPage>();
        List<MatchMemberp> matchMemberpList = matchMemberpService.list(queryWrapper);
        for (MatchMemberp matchMemberp : matchMemberpList) {
            MatchMemberpPage vo = new MatchMemberpPage();
            BeanUtils.copyProperties(matchMemberp, vo);
            List<MatchTotalp> matchTotalpList = matchTotalpService.selectByMainId(matchMemberp.getId());
            vo.setMatchTotalpList(matchTotalpList);
            pageList.add(vo);
        }
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "运动员列表");
        mv.addObject(NormalExcelConstants.CLASS, MatchMemberpPage.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("运动员列表数据", "导出人:Jeecg", "导出信息"));
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
                List<MatchMemberpPage> list = ExcelImportUtil.importExcel(file.getInputStream(), MatchMemberpPage.class, params);
                for (MatchMemberpPage page : list) {
                    MatchMemberp po = new MatchMemberp();
                    BeanUtils.copyProperties(page, po);
                    matchMemberpService.saveMain(po, page.getMatchTotalpList());
                }
                return Result.ok("文件导入成功！数据行数:" + list.size());
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
        return Result.ok("文件导入失败！");
    }
}
