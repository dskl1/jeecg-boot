package org.jeecg.modules.sportsman.controller;

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
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.sportsman.entity.SportsmanRank;
import org.jeecg.modules.sportsman.entity.SportsmanResume;
import org.jeecg.modules.sportsman.entity.SportsmanFamilySituation;
import org.jeecg.modules.sportsman.entity.SportsmanData;
import org.jeecg.modules.sportsman.entity.DevelopSituation;
import org.jeecg.modules.sportsman.entity.BodyType;
import org.jeecg.modules.sportsman.entity.BodyAbility;
import org.jeecg.modules.sportsman.entity.SportsmanPersonalArchives;
import org.jeecg.modules.sportsman.vo.SportsmanPersonalArchivesPage;
import org.jeecg.modules.sportsman.service.ISportsmanPersonalArchivesService;
import org.jeecg.modules.sportsman.service.ISportsmanRankService;
import org.jeecg.modules.sportsman.service.ISportsmanResumeService;
import org.jeecg.modules.sportsman.service.ISportsmanFamilySituationService;
import org.jeecg.modules.sportsman.service.ISportsmanDataService;
import org.jeecg.modules.sportsman.service.IDevelopSituationService;
import org.jeecg.modules.sportsman.service.IBodyTypeService;
import org.jeecg.modules.sportsman.service.IBodyAbilityService;
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
 * @Title: Controller
 * @Description: 档案管理
 * @author： jeecg-boot
 * @date： 2019-06-20
 * @version： V1.0
 */
@RestController
@RequestMapping("/sportsman/sportsmanPersonalArchives")
@Slf4j
public class SportsmanPersonalArchivesController {
    @Autowired
    private ISportsmanPersonalArchivesService sportsmanPersonalArchivesService;
    @Autowired
    private ISportsmanRankService sportsmanRankService;
    @Autowired
    private ISportsmanResumeService sportsmanResumeService;
    @Autowired
    private ISportsmanFamilySituationService sportsmanFamilySituationService;
    @Autowired
    private ISportsmanDataService sportsmanDataService;
    @Autowired
    private IDevelopSituationService developSituationService;
    @Autowired
    private IBodyTypeService bodyTypeService;
    @Autowired
    private IBodyAbilityService bodyAbilityService;

    /**
     * 分页列表查询
     *
     * @param sportsmanPersonalArchives
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @GetMapping(value = "/list")
    @PermissionData(pageComponent = "sportsman/SportsmanPersonalArchivesList")
    public Result<IPage<SportsmanPersonalArchives>> queryPageList(SportsmanPersonalArchives sportsmanPersonalArchives,
                                                                  @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                                  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                                  HttpServletRequest req) {
        Result<IPage<SportsmanPersonalArchives>> result = new Result<IPage<SportsmanPersonalArchives>>();
        QueryWrapper<SportsmanPersonalArchives> queryWrapper = QueryGenerator.initQueryWrapper(sportsmanPersonalArchives, req.getParameterMap());
        Page<SportsmanPersonalArchives> page = new Page<SportsmanPersonalArchives>(pageNo, pageSize);
        this.sportsmanRankService.rankDelete(); //删除子表外键不存在的数据
        this.sportsmanRankService.rankInsert(); //比赛成绩达到某一等级的插入sports_rank表
        this.sportsmanRankService.cardUpdate(); //插入身份证号码
        IPage<SportsmanPersonalArchives> pageList = sportsmanPersonalArchivesService.page(page, queryWrapper);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 添加
     *
     * @param sportsmanPersonalArchivesPage
     * @return
     */
    @PostMapping(value = "/add")
    public Result<SportsmanPersonalArchives> add(@RequestBody SportsmanPersonalArchivesPage sportsmanPersonalArchivesPage) {
        Result<SportsmanPersonalArchives> result = new Result<SportsmanPersonalArchives>();
        try {
            SportsmanPersonalArchives sportsmanPersonalArchives = new SportsmanPersonalArchives();
            BeanUtils.copyProperties(sportsmanPersonalArchivesPage, sportsmanPersonalArchives);

            sportsmanPersonalArchivesService.save(sportsmanPersonalArchives);
            result.success("添加成功！");
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            result.error500("操作失败");
        }
        return result;
    }

    /**
     * 编辑
     *
     * @param sportsmanPersonalArchivesPage
     * @return
     */
    @PutMapping(value = "/edit")
    public Result<SportsmanPersonalArchives> edit(@RequestBody SportsmanPersonalArchivesPage sportsmanPersonalArchivesPage) {
        Result<SportsmanPersonalArchives> result = new Result<SportsmanPersonalArchives>();
        SportsmanPersonalArchives sportsmanPersonalArchives = new SportsmanPersonalArchives();
        BeanUtils.copyProperties(sportsmanPersonalArchivesPage, sportsmanPersonalArchives);
        SportsmanPersonalArchives sportsmanPersonalArchivesEntity = sportsmanPersonalArchivesService.getById(sportsmanPersonalArchives.getId());
        if (sportsmanPersonalArchivesEntity == null) {
            result.error500("未找到对应实体");
        } else {
            sportsmanPersonalArchivesService.updateById(sportsmanPersonalArchives);
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
    public Result<SportsmanPersonalArchives> delete(@RequestParam(name = "id", required = true) String id) {
        Result<SportsmanPersonalArchives> result = new Result<SportsmanPersonalArchives>();
        SportsmanPersonalArchives sportsmanPersonalArchives = sportsmanPersonalArchivesService.getById(id);
        if (sportsmanPersonalArchives == null) {
            result.error500("未找到对应实体");
        } else {
            sportsmanPersonalArchivesService.delMain(id);
            result.success("删除成功!");
        }

        return result;
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatch")
    public Result<SportsmanPersonalArchives> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        Result<SportsmanPersonalArchives> result = new Result<SportsmanPersonalArchives>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.sportsmanPersonalArchivesService.removeByIds(Arrays.asList(ids.split(",")));
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
    public Result<SportsmanPersonalArchives> queryById(@RequestParam(name = "id", required = true) String id) {
        Result<SportsmanPersonalArchives> result = new Result<SportsmanPersonalArchives>();
        SportsmanPersonalArchives sportsmanPersonalArchives = sportsmanPersonalArchivesService.getById(id);
        if (sportsmanPersonalArchives == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(sportsmanPersonalArchives);
            result.setSuccess(true);
        }
        return result;
    }

    //===========================以下是子表信息操作相关API====================================

    /**
     * 通过主表id查询运动员等级
     *
     * @param mainId
     * @return
     */
    @GetMapping(value = "/listSportsmanRankByMainId")
    public Result<List<SportsmanRank>> querySportsmanRankListByMainId(@RequestParam(name = "mainId", required = false) String mainId) {
        Result<List<SportsmanRank>> result = new Result<List<SportsmanRank>>();
        List<SportsmanRank> sportsmanRankList = null;
        if (mainId != null) {
            sportsmanRankList = sportsmanRankService.selectByMainId(mainId);
            result.setResult(sportsmanRankList);
            result.setSuccess(true);
            return result;
        } else return null;
    }

    /**
     * 添加运动员等级
     *
     * @param sportsmanRank
     * @return
     */
    @PostMapping(value = "/addSportsmanRank")
    public Result<SportsmanRank> addSportsmanRank(@RequestBody SportsmanRank sportsmanRank) {
        Result<SportsmanRank> result = new Result<>();
        try {
            boolean ok = sportsmanRankService.save(sportsmanRank);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("添加运动员等级成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("添加运动员等级失败!");
            }
            return result;
        } catch (Exception e) {
            e.fillInStackTrace();
            result.setSuccess(false);
            result.setMessage("添加运动员等级过程中出现了异常: " + e.getMessage());
            return result;
        }
    }

    /**
     * 编辑运动员等级
     *
     * @param sportsmanRank
     * @return
     */
    @PutMapping("/editSportsmanRank")
    public Result<SportsmanRank> editSportsmanRank(@RequestBody SportsmanRank sportsmanRank) {
        Result<SportsmanRank> result = new Result<>();
        try {
            boolean ok = sportsmanRankService.updateById(sportsmanRank);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("更新运动员等级成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("更新运动员等级失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("更新数据过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 通过id删除运动员等级
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteSportsmanRank")
    public Result<SportsmanRank> deleteSportsmanRank(@RequestParam(name = "id", required = true) String id) {
        Result<SportsmanRank> result = new Result<>();
        try {
            boolean ok = sportsmanRankService.removeById(id);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("删除运动员等级成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("删除运动员等级失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除运动员等级过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 批量删除运动员等级
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatchSportsmanRank")
    public Result<SportsmanRank> deleteBatchSportsmanRank(@RequestParam(name = "ids", required = true) String ids) {
        Result<SportsmanRank> result = new Result<SportsmanRank>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.sportsmanRankService.removeByIds(Arrays.asList(ids.split(",")));
            result.success("删除成功!");
        }
        return result;
    }

    /**
     * 通过主表id查询运动员训练简历
     *
     * @param mainId
     * @return
     */
    @GetMapping(value = "/listSportsmanResumeByMainId")
    public Result<List<SportsmanResume>> querySportsmanResumeListByMainId(@RequestParam(name = "mainId", required = false) String mainId) {
        Result<List<SportsmanResume>> result = new Result<List<SportsmanResume>>();
        List<SportsmanResume> sportsmanResumeList = null;
        if (mainId != null) {
            sportsmanResumeList = sportsmanResumeService.selectByMainId(mainId);
            result.setResult(sportsmanResumeList);
            result.setSuccess(true);
            return result;
        } else return null;
    }

    /**
     * 添加运动员训练简历
     *
     * @param sportsmanResume
     * @return
     */
    @PostMapping(value = "/addSportsmanResume")
    public Result<SportsmanResume> addSportsmanResume(@RequestBody SportsmanResume sportsmanResume) {
        Result<SportsmanResume> result = new Result<>();
        try {
            boolean ok = sportsmanResumeService.save(sportsmanResume);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("添加运动员训练简历成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("添加运动员训练简历失败!");
            }
            return result;
        } catch (Exception e) {
            e.fillInStackTrace();
            result.setSuccess(false);
            result.setMessage("添加运动员训练简历过程中出现了异常: " + e.getMessage());
            return result;
        }
    }

    /**
     * 编辑运动员训练简历
     *
     * @param sportsmanResume
     * @return
     */
    @PutMapping("/editSportsmanResume")
    public Result<SportsmanResume> editSportsmanResume(@RequestBody SportsmanResume sportsmanResume) {
        Result<SportsmanResume> result = new Result<>();
        try {
            boolean ok = sportsmanResumeService.updateById(sportsmanResume);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("更新运动员训练简历成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("更新运动员训练简历失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("更新数据过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 通过id删除运动员训练简历
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteSportsmanResume")
    public Result<SportsmanResume> deleteSportsmanResume(@RequestParam(name = "id", required = true) String id) {
        Result<SportsmanResume> result = new Result<>();
        try {
            boolean ok = sportsmanResumeService.removeById(id);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("删除运动员训练简历成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("删除运动员训练简历失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除运动员训练简历过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 批量删除运动员训练简历
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatchSportsmanResume")
    public Result<SportsmanResume> deleteBatchSportsmanResume(@RequestParam(name = "ids", required = true) String ids) {
        Result<SportsmanResume> result = new Result<SportsmanResume>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.sportsmanResumeService.removeByIds(Arrays.asList(ids.split(",")));
            result.success("删除成功!");
        }
        return result;
    }

    /**
     * 通过主表id查询运动员家庭成员情况
     *
     * @param mainId
     * @return
     */
    @GetMapping(value = "/listSportsmanFamilySituationByMainId")
    public Result<List<SportsmanFamilySituation>> querySportsmanFamilySituationListByMainId(@RequestParam(name = "mainId", required = false) String mainId) {
        Result<List<SportsmanFamilySituation>> result = new Result<List<SportsmanFamilySituation>>();
        List<SportsmanFamilySituation> sportsmanFamilySituationList = null;
        if (mainId != null) {
            sportsmanFamilySituationList = sportsmanFamilySituationService.selectByMainId(mainId);
            result.setResult(sportsmanFamilySituationList);
            result.setSuccess(true);
            return result;
        } else return null;
    }

    /**
     * 添加运动员家庭成员情况
     *
     * @param sportsmanFamilySituation
     * @return
     */
    @PostMapping(value = "/addSportsmanFamilySituation")
    public Result<SportsmanFamilySituation> addSportsmanFamilySituation(@RequestBody SportsmanFamilySituation sportsmanFamilySituation) {
        Result<SportsmanFamilySituation> result = new Result<>();
        try {
            boolean ok = sportsmanFamilySituationService.save(sportsmanFamilySituation);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("添加运动员家庭成员情况成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("添加运动员家庭成员情况失败!");
            }
            return result;
        } catch (Exception e) {
            e.fillInStackTrace();
            result.setSuccess(false);
            result.setMessage("添加运动员家庭成员情况过程中出现了异常: " + e.getMessage());
            return result;
        }
    }

    /**
     * 编辑运动员家庭成员情况
     *
     * @param sportsmanFamilySituation
     * @return
     */
    @PutMapping("/editSportsmanFamilySituation")
    public Result<SportsmanFamilySituation> editSportsmanFamilySituation(@RequestBody SportsmanFamilySituation sportsmanFamilySituation) {
        Result<SportsmanFamilySituation> result = new Result<>();
        try {
            boolean ok = sportsmanFamilySituationService.updateById(sportsmanFamilySituation);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("更新运动员家庭成员情况成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("更新运动员家庭成员情况失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("更新数据过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 通过id删除运动员家庭成员情况
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteSportsmanFamilySituation")
    public Result<SportsmanFamilySituation> deleteSportsmanFamilySituation(@RequestParam(name = "id", required = true) String id) {
        Result<SportsmanFamilySituation> result = new Result<>();
        try {
            boolean ok = sportsmanFamilySituationService.removeById(id);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("删除运动员家庭成员情况成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("删除运动员家庭成员情况失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除运动员家庭成员情况过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 批量删除运动员家庭成员情况
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatchSportsmanFamilySituation")
    public Result<SportsmanFamilySituation> deleteBatchSportsmanFamilySituation(@RequestParam(name = "ids", required = true) String ids) {
        Result<SportsmanFamilySituation> result = new Result<SportsmanFamilySituation>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.sportsmanFamilySituationService.removeByIds(Arrays.asList(ids.split(",")));
            result.success("删除成功!");
        }
        return result;
    }

    /**
     * 通过主表id查询个人资料
     *
     * @param mainId
     * @return
     */
    @GetMapping(value = "/listSportsmanDataByMainId")
    public Result<List<SportsmanData>> querySportsmanDataListByMainId(@RequestParam(name = "mainId", required = false) String mainId) {
        Result<List<SportsmanData>> result = new Result<List<SportsmanData>>();
        List<SportsmanData> sportsmanDataList = null;
        if (mainId != null) {
            sportsmanDataList = sportsmanDataService.selectByMainId(mainId);
            result.setResult(sportsmanDataList);
            result.setSuccess(true);
            return result;
        } else return null;
    }

    /**
     * 添加个人资料
     *
     * @param sportsmanData
     * @return
     */
    @PostMapping(value = "/addSportsmanData")
    public Result<SportsmanData> addSportsmanData(@RequestBody SportsmanData sportsmanData) {
        Result<SportsmanData> result = new Result<>();
        try {
            boolean ok = sportsmanDataService.save(sportsmanData);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("添加个人资料成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("添加个人资料失败!");
            }
            return result;
        } catch (Exception e) {
            e.fillInStackTrace();
            result.setSuccess(false);
            result.setMessage("添加个人资料过程中出现了异常: " + e.getMessage());
            return result;
        }
    }

    /**
     * 编辑个人资料
     *
     * @param sportsmanData
     * @return
     */
    @PutMapping("/editSportsmanData")
    public Result<SportsmanData> editSportsmanData(@RequestBody SportsmanData sportsmanData) {
        Result<SportsmanData> result = new Result<>();
        try {
            boolean ok = sportsmanDataService.updateById(sportsmanData);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("更新个人资料成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("更新个人资料失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("更新数据过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 通过id删除个人资料
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteSportsmanData")
    public Result<SportsmanData> deleteSportsmanData(@RequestParam(name = "id", required = true) String id) {
        Result<SportsmanData> result = new Result<>();
        try {
            boolean ok = sportsmanDataService.removeById(id);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("删除个人资料成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("删除个人资料失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除个人资料过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 批量删除个人资料
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatchSportsmanData")
    public Result<SportsmanData> deleteBatchSportsmanData(@RequestParam(name = "ids", required = true) String ids) {
        Result<SportsmanData> result = new Result<SportsmanData>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.sportsmanDataService.removeByIds(Arrays.asList(ids.split(",")));
            result.success("删除成功!");
        }
        return result;
    }

    /**
     * 通过主表id查询历年机能发育情况
     *
     * @param mainId
     * @return
     */
    @GetMapping(value = "/listDevelopSituationByMainId")
    public Result<List<DevelopSituation>> queryDevelopSituationListByMainId(@RequestParam(name = "mainId", required = false) String mainId) {
        Result<List<DevelopSituation>> result = new Result<List<DevelopSituation>>();
        List<DevelopSituation> developSituationList = null;
        if (mainId != null) {
            developSituationList = developSituationService.selectByMainId(mainId);
            result.setResult(developSituationList);
            result.setSuccess(true);
            return result;
        } else return null;
    }

    /**
     * 添加历年机能发育情况
     *
     * @param developSituation
     * @return
     */
    @PostMapping(value = "/addDevelopSituation")
    public Result<DevelopSituation> addDevelopSituation(@RequestBody DevelopSituation developSituation) {
        Result<DevelopSituation> result = new Result<>();
        try {
            boolean ok = developSituationService.save(developSituation);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("添加历年机能发育情况成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("添加历年机能发育情况失败!");
            }
            return result;
        } catch (Exception e) {
            e.fillInStackTrace();
            result.setSuccess(false);
            result.setMessage("添加历年机能发育情况过程中出现了异常: " + e.getMessage());
            return result;
        }
    }

    /**
     * 编辑历年机能发育情况
     *
     * @param developSituation
     * @return
     */
    @PutMapping("/editDevelopSituation")
    public Result<DevelopSituation> editDevelopSituation(@RequestBody DevelopSituation developSituation) {
        Result<DevelopSituation> result = new Result<>();
        try {
            boolean ok = developSituationService.updateById(developSituation);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("更新历年机能发育情况成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("更新历年机能发育情况失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("更新数据过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 通过id删除历年机能发育情况
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteDevelopSituation")
    public Result<DevelopSituation> deleteDevelopSituation(@RequestParam(name = "id", required = true) String id) {
        Result<DevelopSituation> result = new Result<>();
        try {
            boolean ok = developSituationService.removeById(id);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("删除历年机能发育情况成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("删除历年机能发育情况失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除历年机能发育情况过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 批量删除历年机能发育情况
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatchDevelopSituation")
    public Result<DevelopSituation> deleteBatchDevelopSituation(@RequestParam(name = "ids", required = true) String ids) {
        Result<DevelopSituation> result = new Result<DevelopSituation>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.developSituationService.removeByIds(Arrays.asList(ids.split(",")));
            result.success("删除成功!");
        }
        return result;
    }

    /**
     * 通过主表id查询身体形态指标
     *
     * @param mainId
     * @return
     */
    @GetMapping(value = "/listBodyTypeByMainId")
    public Result<List<BodyType>> queryBodyTypeListByMainId(@RequestParam(name = "mainId", required = false) String mainId) {
        Result<List<BodyType>> result = new Result<List<BodyType>>();
        List<BodyType> bodyTypeList = null;
        if (mainId != null) {
            bodyTypeList = bodyTypeService.selectByMainId(mainId);
            result.setResult(bodyTypeList);
            result.setSuccess(true);
            return result;
        } else return null;
    }

    /**
     * 添加身体形态指标
     *
     * @param bodyType
     * @return
     */
    @PostMapping(value = "/addBodyType")
    public Result<BodyType> addBodyType(@RequestBody BodyType bodyType) {
        Result<BodyType> result = new Result<>();
        try {
            boolean ok = bodyTypeService.save(bodyType);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("添加身体形态指标成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("添加身体形态指标失败!");
            }
            return result;
        } catch (Exception e) {
            e.fillInStackTrace();
            result.setSuccess(false);
            result.setMessage("添加身体形态指标过程中出现了异常: " + e.getMessage());
            return result;
        }
    }

    /**
     * 编辑身体形态指标
     *
     * @param bodyType
     * @return
     */
    @PutMapping("/editBodyType")
    public Result<BodyType> editBodyType(@RequestBody BodyType bodyType) {
        Result<BodyType> result = new Result<>();
        try {
            boolean ok = bodyTypeService.updateById(bodyType);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("更新身体形态指标成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("更新身体形态指标失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("更新数据过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 通过id删除身体形态指标
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteBodyType")
    public Result<BodyType> deleteBodyType(@RequestParam(name = "id", required = true) String id) {
        Result<BodyType> result = new Result<>();
        try {
            boolean ok = bodyTypeService.removeById(id);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("删除身体形态指标成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("删除身体形态指标失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除身体形态指标过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 批量删除身体形态指标
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatchBodyType")
    public Result<BodyType> deleteBatchBodyType(@RequestParam(name = "ids", required = true) String ids) {
        Result<BodyType> result = new Result<BodyType>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.bodyTypeService.removeByIds(Arrays.asList(ids.split(",")));
            result.success("删除成功!");
        }
        return result;
    }

    /**
     * 通过主表id查询身体素质
     *
     * @param mainId
     * @return
     */
    @GetMapping(value = "/listBodyAbilityByMainId")
    public Result<List<BodyAbility>> queryBodyAbilityListByMainId(@RequestParam(name = "mainId", required = false) String mainId) {
        Result<List<BodyAbility>> result = new Result<List<BodyAbility>>();
        List<BodyAbility> bodyAbilityList = null;
        if (mainId != null) {
            bodyAbilityList = bodyAbilityService.selectByMainId(mainId);
            result.setResult(bodyAbilityList);
            result.setSuccess(true);
            return result;
        } else return null;
    }

    /**
     * 添加身体素质
     *
     * @param bodyAbility
     * @return
     */
    @PostMapping(value = "/addBodyAbility")
    public Result<BodyAbility> addBodyAbility(@RequestBody BodyAbility bodyAbility) {
        Result<BodyAbility> result = new Result<>();
        try {
            boolean ok = bodyAbilityService.save(bodyAbility);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("添加身体素质成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("添加身体素质失败!");
            }
            return result;
        } catch (Exception e) {
            e.fillInStackTrace();
            result.setSuccess(false);
            result.setMessage("添加身体素质过程中出现了异常: " + e.getMessage());
            return result;
        }
    }

    /**
     * 编辑身体素质
     *
     * @param bodyAbility
     * @return
     */
    @PutMapping("/editBodyAbility")
    public Result<BodyAbility> editBodyAbility(@RequestBody BodyAbility bodyAbility) {
        Result<BodyAbility> result = new Result<>();
        try {
            boolean ok = bodyAbilityService.updateById(bodyAbility);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("更新身体素质成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("更新身体素质失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("更新数据过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 通过id删除身体素质
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteBodyAbility")
    public Result<BodyAbility> deleteBodyAbility(@RequestParam(name = "id", required = true) String id) {
        Result<BodyAbility> result = new Result<>();
        try {
            boolean ok = bodyAbilityService.removeById(id);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("删除身体素质成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("删除身体素质失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除身体素质过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 批量删除身体素质
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatchBodyAbility")
    public Result<BodyAbility> deleteBatchBodyAbility(@RequestParam(name = "ids", required = true) String ids) {
        Result<BodyAbility> result = new Result<BodyAbility>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.bodyAbilityService.removeByIds(Arrays.asList(ids.split(",")));
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
        QueryWrapper<SportsmanPersonalArchives> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                SportsmanPersonalArchives sportsmanPersonalArchives = JSON.parseObject(deString, SportsmanPersonalArchives.class);
                queryWrapper = QueryGenerator.initQueryWrapper(sportsmanPersonalArchives, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<SportsmanPersonalArchivesPage> pageList = new ArrayList<SportsmanPersonalArchivesPage>();
        List<SportsmanPersonalArchives> sportsmanPersonalArchivesList = sportsmanPersonalArchivesService.list(queryWrapper);
        for (SportsmanPersonalArchives sportsmanPersonalArchives : sportsmanPersonalArchivesList) {
            SportsmanPersonalArchivesPage vo = new SportsmanPersonalArchivesPage();
            BeanUtils.copyProperties(sportsmanPersonalArchives, vo);
            List<SportsmanRank> sportsmanRankList = sportsmanRankService.selectByMainId(sportsmanPersonalArchives.getId());
            vo.setSportsmanRankList(sportsmanRankList);
            List<SportsmanResume> sportsmanResumeList = sportsmanResumeService.selectByMainId(sportsmanPersonalArchives.getId());
            vo.setSportsmanResumeList(sportsmanResumeList);
            List<SportsmanFamilySituation> sportsmanFamilySituationList = sportsmanFamilySituationService.selectByMainId(sportsmanPersonalArchives.getId());
            vo.setSportsmanFamilySituationList(sportsmanFamilySituationList);
            List<SportsmanData> sportsmanDataList = sportsmanDataService.selectByMainId(sportsmanPersonalArchives.getId());
            vo.setSportsmanDataList(sportsmanDataList);
            List<DevelopSituation> developSituationList = developSituationService.selectByMainId(sportsmanPersonalArchives.getId());
            vo.setDevelopSituationList(developSituationList);
            List<BodyType> bodyTypeList = bodyTypeService.selectByMainId(sportsmanPersonalArchives.getId());
            vo.setBodyTypeList(bodyTypeList);
            List<BodyAbility> bodyAbilityList = bodyAbilityService.selectByMainId(sportsmanPersonalArchives.getId());
            vo.setBodyAbilityList(bodyAbilityList);
            pageList.add(vo);
        }
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "档案管理列表");
        mv.addObject(NormalExcelConstants.CLASS, SportsmanPersonalArchivesPage.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("档案管理列表数据", "导出人:Jeecg", "导出信息"));
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
                List<SportsmanPersonalArchivesPage> list = ExcelImportUtil.importExcel(file.getInputStream(), SportsmanPersonalArchivesPage.class, params);
                for (SportsmanPersonalArchivesPage page : list) {
                    SportsmanPersonalArchives po = new SportsmanPersonalArchives();
                    BeanUtils.copyProperties(page, po);
                    sportsmanPersonalArchivesService.saveMain(po, page.getSportsmanRankList(), page.getSportsmanResumeList(), page.getSportsmanFamilySituationList(), page.getSportsmanDataList(), page.getDevelopSituationList(), page.getBodyTypeList(), page.getBodyAbilityList());
                }
                return Result.ok("文件导入成功！数据行数：" + list.size());
            } catch (Exception e) {
                log.error(e.getMessage());
                return Result.error("文件导入失败！");
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
