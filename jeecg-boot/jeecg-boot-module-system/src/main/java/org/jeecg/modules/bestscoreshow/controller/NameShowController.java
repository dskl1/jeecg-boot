package org.jeecg.modules.bestscoreshow.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;

import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.bestscoreshow.entity.ItemShow;
import org.jeecg.modules.bestscoreshow.entity.NameShow;
import org.jeecg.modules.bestscoreshow.vo.NameShowPage;
import org.jeecg.modules.bestscoreshow.service.INameShowService;
import org.jeecg.modules.bestscoreshow.service.IItemShowService;
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
 * @Description: 姓名展示
 * @Author: jeecg-boot
 * @Date:   2019-12-20
 * @Version: V1.0
 */
@RestController
@RequestMapping("/bestscoreshow/nameShow")
@Slf4j
public class NameShowController {
	@Autowired
	private INameShowService nameShowService;
	@Autowired
	private IItemShowService itemShowService;

	/**
	 * 分页列表查询
	 *
	 * @param nameShow
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	@PermissionData(pageComponent="bestscoreshow/NameShowList")
	public Result<?> queryPageList(NameShow nameShow,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<NameShow> queryWrapper = QueryGenerator.initQueryWrapper(nameShow, req.getParameterMap());
		Page<NameShow> page = new Page<NameShow>(pageNo, pageSize);
		IPage<NameShow> pageList = nameShowService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 *   添加
	 *
	 * @param nameShowPage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody NameShowPage nameShowPage) {
       NameShow nameShow = new NameShow();
		BeanUtils.copyProperties(nameShowPage, nameShow);
		nameShowService.save(nameShow);
		return Result.ok("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param nameShowPage
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody NameShowPage nameShowPage) {
		NameShow nameShow = new NameShow();
		BeanUtils.copyProperties(nameShowPage, nameShow);
		nameShowService.updateById(nameShow);
		return Result.ok("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		nameShowService.delMain(id);
		return Result.ok("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
	    this.nameShowService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		NameShow nameShow = nameShowService.getById(id);
		return Result.ok(nameShow);
	}

	//===========================以下是子表信息操作相关API====================================

	/**
	 * 通过主表id查询项目展示
	 *
  	 * @param itemShow
	 * @return
	 */
	@GetMapping(value = "/listItemShowByMainId")
	public Result<?> listItemShowByMainId(ItemShow itemShow,
                								  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                								  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                								  HttpServletRequest req) {
        if(itemShow.getSportsmanId()!=null) {
            QueryWrapper<ItemShow> queryWrapper = QueryGenerator.initQueryWrapper(itemShow, req.getParameterMap());
            Page<ItemShow> page = new Page<ItemShow>(pageNo, pageSize);
            IPage<ItemShow> pageList =  itemShowService.page(page, queryWrapper);
            return Result.ok(pageList);
        }else{
            return Result.ok();
        }
    }

	/**
     * 添加项目展示
     *
     * @param itemShow
     * @return
     */
    @PostMapping(value = "/addItemShow")
    public Result<?> addItemShow(@RequestBody ItemShow itemShow) {
        itemShowService.save(itemShow);
        return Result.ok("添加项目展示成功！");
    }

    /**
     * 编辑项目展示
     *
     * @param itemShow
     * @return
     */
    @PutMapping("/editItemShow")
    public Result<?> editItemShow(@RequestBody ItemShow itemShow) {
        itemShowService.updateById(itemShow);
        return Result.ok("编辑项目展示成功！");
    }

    /**
     * 通过id删除项目展示
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteItemShow")
    public Result<?> deleteItemShow(@RequestParam(name = "id", required = true) String id) {
       itemShowService.removeById(id);
        return Result.ok("删除项目展示成功！");
    }

	/**
     * 批量删除项目展示
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatchItemShow")
    public Result<?> deleteBatchItemShow(@RequestParam(name = "ids", required = true) String ids) {
        if (ids == null || "".equals(ids.trim())) {
            return Result.error("参数不识别！");
        }
        this.itemShowService.removeByIds(Arrays.asList(ids.split(",")));
       	return Result.ok("批量删除成功！");

    }


    /**
    * 导出excel
    *
    * @param request
    * @param nameShow
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, NameShow nameShow) {
      // Step.1 组装查询条件
      QueryWrapper<NameShow> queryWrapper = QueryGenerator.initQueryWrapper(nameShow, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<NameShowPage> pageList = new ArrayList<NameShowPage>();
      List<NameShow> nameShowList = nameShowService.list(queryWrapper);
      for (NameShow temp : nameShowList) {
          NameShowPage vo = new NameShowPage();
          BeanUtils.copyProperties(temp, vo);
          List<ItemShow> itemShowList = itemShowService.selectByMainId(temp.getId());
          vo.setItemShowList(itemShowList);
          pageList.add(vo);
      }
      //Step.3 调用AutoPoi导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "姓名展示");
      mv.addObject(NormalExcelConstants.CLASS, NameShowPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("姓名展示数据", "导出人:"+sysUser.getRealname(), "姓名展示"));
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
              List<NameShowPage> list = ExcelImportUtil.importExcel(file.getInputStream(), NameShowPage.class, params);
              for (NameShowPage page : list) {
                  NameShow po = new NameShow();
                  BeanUtils.copyProperties(page, po);
                  nameShowService.saveMain(po, page.getItemShowList());
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
