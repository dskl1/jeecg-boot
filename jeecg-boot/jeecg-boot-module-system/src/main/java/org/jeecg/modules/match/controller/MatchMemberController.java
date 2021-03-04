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
import org.jeecg.modules.match.entity.MatchMember;
import org.jeecg.modules.match.entity.MatchTotal;
import org.jeecg.modules.match.service.IMatchMemberService;
import org.jeecg.modules.match.service.IMatchScoreService;
import org.jeecg.modules.match.service.IMatchTotalService;
import org.jeecg.modules.match.vo.MatchMemberPage;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.jeecg.modules.system.entity.SysRole;

/**
 * @Description: 运动员
 * @Author: jeecg-boot
 * @Date: 2019-08-07
 * @Version: V1.0
 */
@RestController
@RequestMapping("/match/matchMember")
@Slf4j
public class MatchMemberController {
    @Autowired
    private IMatchMemberService matchMemberService;
    @Autowired
    private IMatchTotalService matchTotalService;
    @Autowired
    private IMatchScoreService matchScoreService;

    /**
     * 分页列表查询
     *
     * @param matchMember
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @GetMapping(value = "/list")
    @PermissionData(pageComponent = "match/MatchMemberList")
    public Result<IPage<MatchMember>> queryPageList(MatchMember matchMember,
                                                    @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                    HttpServletRequest req) {
        Result<IPage<MatchMember>> result = new Result<IPage<MatchMember>>();
        QueryWrapper<MatchMember> queryWrapper = QueryGenerator.initQueryWrapper(matchMember, req.getParameterMap());
        Page<MatchMember> page = new Page<MatchMember>(pageNo, pageSize);
        this.matchScoreService.scoreDelete(); //删除子表外键不存在的数据
        IPage<MatchMember> pageList = matchMemberService.page(page, queryWrapper);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 添加
     *
     * @param matchMemberPage
     * @return
     */
    @PostMapping(value = "/add")
    public Result<MatchMember> add(@RequestBody MatchMemberPage matchMemberPage) {
        Result<MatchMember> result = new Result<MatchMember>();
        try {
            MatchMember matchMember = new MatchMember();
            BeanUtils.copyProperties(matchMemberPage, matchMember);

            matchMemberService.save(matchMember);
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
     * @param matchMemberPage
     * @return
     */
    @PutMapping(value = "/edit")
    public Result<MatchMember> edit(@RequestBody MatchMemberPage matchMemberPage) {
        Result<MatchMember> result = new Result<MatchMember>();
        MatchMember matchMember = new MatchMember();
        BeanUtils.copyProperties(matchMemberPage, matchMember);
        MatchMember matchMemberEntity = matchMemberService.getById(matchMember.getId());
        if (matchMemberEntity == null) {
            result.error500("未找到对应实体");
        } else {
            matchMemberService.updateById(matchMember);
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
            matchMemberService.delMain(id);
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
    public Result<MatchMember> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        Result<MatchMember> result = new Result<MatchMember>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.matchMemberService.removeByIds(Arrays.asList(ids.split(",")));
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
    public Result<MatchMember> queryById(@RequestParam(name = "id", required = true) String id) {
        Result<MatchMember> result = new Result<MatchMember>();
        MatchMember matchMember = matchMemberService.getById(id);
        if (matchMember == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(matchMember);
            result.setSuccess(true);
        }
        return result;
    }

    @RequestMapping(value = "/queryallll", method = RequestMethod.GET)
    public Result<List<MatchMember>> queryallll() {
        Result<List<MatchMember>> result = new Result<>();
        List<MatchMember> list = matchMemberService.list();
        if (list == null || list.size() <= 0) {
            result.error500("未找到角色信息");
        } else {
            result.setResult(list);
            result.setSuccess(true);
        }
        return result;
    }

    //===========================以下是子表信息操作相关API====================================

    /**
     * 通过主表id查询比赛名称
     *
     * @param mainId
     * @return
     */
    @GetMapping(value = "/listMatchTotalByMainId")
    public Result<List<MatchTotal>> queryMatchTotalListByMainId(@RequestParam(name = "mainId", required = false) String mainId) {
        Result<List<MatchTotal>> result = new Result<List<MatchTotal>>();
        List<MatchTotal> matchTotalList = null;
        if (mainId != null) {
            matchTotalList = matchTotalService.selectByMainId(mainId);
            result.setResult(matchTotalList);
            result.setSuccess(true);
            return result;
        } else return null;
    }

    /**
     * 添加比赛名称
     *
     * @param matchTotal
     * @return
     */
    @PostMapping(value = "/addMatchTotal")
    public Result<MatchTotal> addMatchTotal(@RequestBody MatchTotal matchTotal) {
        Result<MatchTotal> result = new Result<>();
        try {
            boolean ok = matchTotalService.save(matchTotal);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("添加比赛名称成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("添加比赛名称失败!");
            }
            return result;
        } catch (Exception e) {
            e.fillInStackTrace();
            result.setSuccess(false);
            result.setMessage("添加比赛名称过程中出现了异常: " + e.getMessage());
            return result;
        }
    }

    /**
     * 编辑比赛名称
     *
     * @param matchTotal
     * @return
     */
    @PutMapping("/editMatchTotal")
    public Result<MatchTotal> editMatchTotal(@RequestBody MatchTotal matchTotal) {
        Result<MatchTotal> result = new Result<>();
        try {
            boolean ok = matchTotalService.updateById(matchTotal);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("更新比赛名称成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("更新比赛名称失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("更新数据过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 通过id删除比赛名称
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteMatchTotal")
    public Result<MatchTotal> deleteMatchTotal(@RequestParam(name = "id", required = true) String id) {
        Result<MatchTotal> result = new Result<>();
        try {
            boolean ok = matchTotalService.removeById(id);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("删除比赛名称成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("删除比赛名称失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除比赛名称过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 批量删除比赛名称
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatchMatchTotal")
    public Result<MatchTotal> deleteBatchMatchTotal(@RequestParam(name = "ids", required = true) String ids) {
        Result<MatchTotal> result = new Result<MatchTotal>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.matchTotalService.removeByIds(Arrays.asList(ids.split(",")));
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
        QueryWrapper<MatchMember> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                MatchMember matchMember = JSON.parseObject(deString, MatchMember.class);
                queryWrapper = QueryGenerator.initQueryWrapper(matchMember, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<MatchMemberPage> pageList = new ArrayList<MatchMemberPage>();
        List<MatchMember> matchMemberList = matchMemberService.list(queryWrapper);
        for (MatchMember matchMember : matchMemberList) {
            MatchMemberPage vo = new MatchMemberPage();
            BeanUtils.copyProperties(matchMember, vo);
            List<MatchTotal> matchTotalList = matchTotalService.selectByMainId(matchMember.getId());
            vo.setMatchTotalList(matchTotalList);
            pageList.add(vo);
        }
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "运动员列表");
        mv.addObject(NormalExcelConstants.CLASS, MatchMemberPage.class);
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
                List<MatchMemberPage> list = ExcelImportUtil.importExcel(file.getInputStream(), MatchMemberPage.class, params);
                for (MatchMemberPage page : list) {
                    MatchMember po = new MatchMember();
                    BeanUtils.copyProperties(page, po);
                    matchMemberService.saveMain(po, page.getMatchTotalList());
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
