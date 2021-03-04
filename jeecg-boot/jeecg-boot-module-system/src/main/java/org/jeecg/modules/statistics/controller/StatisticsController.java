package org.jeecg.modules.statistics.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.statistics.entity.*;
import org.jeecg.modules.statistics.service.*;
import org.jeecg.modules.statistics.vo.StatisticsPage;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

 /**
 * @Title: Controller
 * @Description: 训练报表
 * @author： jeecg-boot
 * @date：   2019-07-15
 * @version： V1.0
 */
@RestController
@RequestMapping("/statistics/statistics")
@Slf4j
public class StatisticsController {
	@Autowired
	private IStatisticsService statisticsService;
	@Autowired
	private ID25aService d25aService;
	@Autowired
	private ID50aService d50aService;
	@Autowired
	private ID100aService d100aService;
	@Autowired
	private ID150aService d150aService;
	@Autowired
	private ID200aService d200aService;
	@Autowired
	private ID250aService d250aService;
	@Autowired
	private ID300aService d300aService;
	@Autowired
	private ID350aService d350aService;
	@Autowired
	private ID400aService d400aService;
	@Autowired
	private ID800aService d800aService;
	@Autowired
	private ID1000aService d1000aService;
	@Autowired
	private ID1500aService d1500aService;

	/**
	  * 分页列表查询
	 * @param statistics
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
    @PermissionData(pageComponent="statistics/StatisticsList")
	public Result<IPage<Statistics>> queryPageList(Statistics statistics,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Statistics>> result = new Result<IPage<Statistics>>();
		QueryWrapper<Statistics> queryWrapper = QueryGenerator.initQueryWrapper(statistics, req.getParameterMap());
		Page<Statistics> page = new Page<Statistics>(pageNo, pageSize);
		IPage<Statistics> pageList = statisticsService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	  *   添加
	 * @param statisticsPage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<Statistics> add(@RequestBody StatisticsPage statisticsPage) {
		Result<Statistics> result = new Result<Statistics>();
		try {
			Statistics statistics = new Statistics();
			BeanUtils.copyProperties(statisticsPage, statistics);

			statisticsService.save(statistics);
			result.success("添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			result.error500("操作失败");
		}
		return result;
	}

	/**
	  *  编辑
	 * @param statisticsPage
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<Statistics> edit(@RequestBody StatisticsPage statisticsPage) {
		Result<Statistics> result = new Result<Statistics>();
		Statistics statistics = new Statistics();
		BeanUtils.copyProperties(statisticsPage, statistics);
		Statistics statisticsEntity = statisticsService.getById(statistics.getId());
		if(statisticsEntity==null) {
			result.error500("未找到对应实体");
		}else {
			statisticsService.updateById(statistics);
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
	public Result<Statistics> delete(@RequestParam(name="id",required=true) String id) {
		Result<Statistics> result = new Result<Statistics>();
		Statistics statistics = statisticsService.getById(id);
		if(statistics==null) {
			result.error500("未找到对应实体");
		}else {
			statisticsService.delMain(id);
			result.success("删除成功!");
		}

		return result;
	}

	/**
	  *  批量删除
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	public Result<Statistics> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Statistics> result = new Result<Statistics>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.statisticsService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<Statistics> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Statistics> result = new Result<Statistics>();
		Statistics statistics = statisticsService.getById(id);
		if(statistics==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(statistics);
			result.setSuccess(true);
		}
		return result;
	}

     /**
      * 获取访问量
      * @return
      */
     @GetMapping("trainingInfo50")
     public Result<List<Map<String,Object>>> trainingInfo50(@RequestParam(name="mainId",required=false) String mainId,
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
         List<Map<String,Object>> list = d50aService.findTrainingCount(selectValue,mainId,dayStart, dayEnd);
         result.setResult(oConvertUtils.toLowerCasePageList(list));
         return result;
     }

     /**
      * 获取访问量
      * @return
      */
     @GetMapping("trainingInfo25")
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
         List<Map<String,Object>> list = d25aService.findTrainingCount(selectValue,mainId,dayStart, dayEnd);
         result.setResult(oConvertUtils.toLowerCasePageList(list));
         return result;
     }

     /**
      * 获取访问量
      * @return
      */
     @GetMapping("trainingInfo100")
     public Result<List<Map<String,Object>>> trainingInfo100(@RequestParam(name="mainId",required=false) String mainId,
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
         List<Map<String,Object>> list = d100aService.findTrainingCount(selectValue,mainId,dayStart, dayEnd);
         result.setResult(oConvertUtils.toLowerCasePageList(list));
         return result;
     }

     /**
      * 获取访问量
      * @return
      */
     @GetMapping("trainingInfo150")
     public Result<List<Map<String,Object>>> trainingInfo150(@RequestParam(name="mainId",required=false) String mainId,
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
         List<Map<String,Object>> list = d150aService.findTrainingCount(selectValue,mainId,dayStart, dayEnd);
         result.setResult(oConvertUtils.toLowerCasePageList(list));
         return result;
     }

     /**
      * 获取访问量
      * @return
      */
     @GetMapping("trainingInfo200")
     public Result<List<Map<String,Object>>> trainingInfo200(@RequestParam(name="mainId",required=false) String mainId,
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
         List<Map<String,Object>> list = d200aService.findTrainingCount(selectValue,mainId,dayStart, dayEnd);
         result.setResult(oConvertUtils.toLowerCasePageList(list));
         return result;
     }

     /**
      * 获取访问量
      * @return
      */
     @GetMapping("trainingInfo250")
     public Result<List<Map<String,Object>>> trainingInfo250(@RequestParam(name="mainId",required=false) String mainId,
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
         List<Map<String,Object>> list = d250aService.findTrainingCount(selectValue,mainId,dayStart, dayEnd);
         result.setResult(oConvertUtils.toLowerCasePageList(list));
         return result;
     }

     /**
      * 获取访问量
      * @return
      */
     @GetMapping("trainingInfo300")
     public Result<List<Map<String,Object>>> trainingInfo300(@RequestParam(name="mainId",required=false) String mainId,
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
         List<Map<String,Object>> list = d300aService.findTrainingCount(selectValue,mainId,dayStart, dayEnd);
         result.setResult(oConvertUtils.toLowerCasePageList(list));
         return result;
     }

     /**
      * 获取访问量
      * @return
      */
     @GetMapping("trainingInfo350")
     public Result<List<Map<String,Object>>> trainingInfo350(@RequestParam(name="mainId",required=false) String mainId,
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
         List<Map<String,Object>> list = d350aService.findTrainingCount(selectValue,mainId,dayStart, dayEnd);
         result.setResult(oConvertUtils.toLowerCasePageList(list));
         return result;
     }

     /**
      * 获取访问量
      * @return
      */
     @GetMapping("trainingInfo400")
     public Result<List<Map<String,Object>>> trainingInfo400(@RequestParam(name="mainId",required=false) String mainId,
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
         List<Map<String,Object>> list = d400aService.findTrainingCount(selectValue,mainId,dayStart, dayEnd);
         result.setResult(oConvertUtils.toLowerCasePageList(list));
         return result;
     }

     /**
      * 获取访问量
      * @return
      */
     @GetMapping("trainingInfo800")
     public Result<List<Map<String,Object>>> trainingInfo800(@RequestParam(name="mainId",required=false) String mainId,
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
         List<Map<String,Object>> list = d800aService.findTrainingCount(selectValue,mainId,dayStart, dayEnd);
         result.setResult(oConvertUtils.toLowerCasePageList(list));
         return result;
     }

     /**
      * 获取访问量
      * @return
      */
     @GetMapping("trainingInfo1000")
     public Result<List<Map<String,Object>>> trainingInfo1000(@RequestParam(name="mainId",required=false) String mainId,
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
         List<Map<String,Object>> list = d1000aService.findTrainingCount(selectValue,mainId,dayStart, dayEnd);
         result.setResult(oConvertUtils.toLowerCasePageList(list));
         return result;
     }

     /**
      * 获取访问量
      * @return
      */
     @GetMapping("trainingInfo1500")
     public Result<List<Map<String,Object>>> trainingInfo1500(@RequestParam(name="mainId",required=false) String mainId,
                                                          @RequestParam(name="selectValue",required=false) Integer selectValue) throws ParseException {
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

         String date = "2000-01-01 00:00:00";
         SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");//12小时制
         long time2 = simpleDateFormat.parse(date).getTime();
         System.out.println(time2);

         List<Map<String,Object>> list = d1500aService.findTrainingCount(selectValue,mainId,dayStart, dayEnd);
         result.setResult(oConvertUtils.toLowerCasePageList(list));
         return result;
     }

	//===========================以下是子表信息操作相关API====================================

	/**
	  * 通过主表id查询25
	 * @param mainId
	 * @return
	 */
	@GetMapping(value = "/listD25aByMainId")
	public Result<List<D25a>> queryD25aListByMainId(@RequestParam(name="mainId",required=false) String mainId) {
		Result<List<D25a>> result = new Result<List<D25a>>();
		List<D25a> d25aList = null;
		if (mainId != null) {
			d25aList = d25aService.selectByMainId(mainId);
            result.setResult(d25aList);
            result.setSuccess(true);
            return result;
		}else return null;
	}

	/**
     * 添加25
     *
     * @param d25a
     * @return
     */
    @PostMapping(value = "/addD25a")
    public Result<D25a> addD25a(@RequestBody D25a d25a) {
        Result<D25a> result = new Result<>();
        try {
            boolean ok = d25aService.save(d25a);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("添加25成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("添加25失败!");
            }
            return result;
        } catch (Exception e) {
            e.fillInStackTrace();
            result.setSuccess(false);
            result.setMessage("添加25过程中出现了异常: " + e.getMessage());
            return result;
        }
    }

    /**
     * 编辑25
     *
     * @param d25a
     * @return
     */
    @PutMapping("/editD25a")
    public Result<D25a> editD25a(@RequestBody D25a d25a) {
        Result<D25a> result = new Result<>();
        try {
            boolean ok = d25aService.updateById(d25a);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("更新25成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("更新25失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("更新数据过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 通过id删除25
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteD25a")
    public Result<D25a> deleteD25a(@RequestParam(name = "id", required = true) String id) {
        Result<D25a> result = new Result<>();
        try {
            boolean ok = d25aService.removeById(id);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("删除25成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("删除25失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除25过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

	/**
     * 批量删除25
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatchD25a")
    public Result<D25a> deleteBatchD25a(@RequestParam(name = "ids", required = true) String ids) {
        Result<D25a> result = new Result<D25a>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.d25aService.removeByIds(Arrays.asList(ids.split(",")));
            result.success("删除成功!");
        }
        return result;
    }

	/**
	  * 通过主表id查询50
	 * @param mainId
	 * @return
	 */
	@GetMapping(value = "/listD50aByMainId")
	public Result<List<D50a>> queryD50aListByMainId(@RequestParam(name="mainId",required=false) String mainId) {
		Result<List<D50a>> result = new Result<List<D50a>>();
		List<D50a> d50aList = null;
		if (mainId != null) {
			d50aList = d50aService.selectByMainId(mainId);
            result.setResult(d50aList);
            result.setSuccess(true);
            return result;
		}else return null;
	}

	/**
     * 添加50
     *
     * @param d50a
     * @return
     */
    @PostMapping(value = "/addD50a")
    public Result<D50a> addD50a(@RequestBody D50a d50a) {
        Result<D50a> result = new Result<>();
        try {
            boolean ok = d50aService.save(d50a);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("添加50成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("添加50失败!");
            }
            return result;
        } catch (Exception e) {
            e.fillInStackTrace();
            result.setSuccess(false);
            result.setMessage("添加50过程中出现了异常: " + e.getMessage());
            return result;
        }
    }

    /**
     * 编辑50
     *
     * @param d50a
     * @return
     */
    @PutMapping("/editD50a")
    public Result<D50a> editD50a(@RequestBody D50a d50a) {
        Result<D50a> result = new Result<>();
        try {
            boolean ok = d50aService.updateById(d50a);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("更新50成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("更新50失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("更新数据过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 通过id删除50
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteD50a")
    public Result<D50a> deleteD50a(@RequestParam(name = "id", required = true) String id) {
        Result<D50a> result = new Result<>();
        try {
            boolean ok = d50aService.removeById(id);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("删除50成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("删除50失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除50过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

	/**
     * 批量删除50
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatchD50a")
    public Result<D50a> deleteBatchD50a(@RequestParam(name = "ids", required = true) String ids) {
        Result<D50a> result = new Result<D50a>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.d50aService.removeByIds(Arrays.asList(ids.split(",")));
            result.success("删除成功!");
        }
        return result;
    }

	/**
	  * 通过主表id查询100
	 * @param mainId
	 * @return
	 */
	@GetMapping(value = "/listD100aByMainId")
	public Result<List<D100a>> queryD100aListByMainId(@RequestParam(name="mainId",required=false) String mainId) {
		Result<List<D100a>> result = new Result<List<D100a>>();
		List<D100a> d100aList = null;
		if (mainId != null) {
			d100aList = d100aService.selectByMainId(mainId);
            result.setResult(d100aList);
            result.setSuccess(true);
            return result;
		}else return null;
	}

	/**
     * 添加100
     *
     * @param d100a
     * @return
     */
    @PostMapping(value = "/addD100a")
    public Result<D100a> addD100a(@RequestBody D100a d100a) {
        Result<D100a> result = new Result<>();
        try {
            boolean ok = d100aService.save(d100a);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("添加100成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("添加100失败!");
            }
            return result;
        } catch (Exception e) {
            e.fillInStackTrace();
            result.setSuccess(false);
            result.setMessage("添加100过程中出现了异常: " + e.getMessage());
            return result;
        }
    }

    /**
     * 编辑100
     *
     * @param d100a
     * @return
     */
    @PutMapping("/editD100a")
    public Result<D100a> editD100a(@RequestBody D100a d100a) {
        Result<D100a> result = new Result<>();
        try {
            boolean ok = d100aService.updateById(d100a);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("更新100成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("更新100失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("更新数据过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 通过id删除100
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteD100a")
    public Result<D100a> deleteD100a(@RequestParam(name = "id", required = true) String id) {
        Result<D100a> result = new Result<>();
        try {
            boolean ok = d100aService.removeById(id);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("删除100成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("删除100失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除100过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

	/**
     * 批量删除100
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatchD100a")
    public Result<D100a> deleteBatchD100a(@RequestParam(name = "ids", required = true) String ids) {
        Result<D100a> result = new Result<D100a>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.d100aService.removeByIds(Arrays.asList(ids.split(",")));
            result.success("删除成功!");
        }
        return result;
    }

	/**
	  * 通过主表id查询150
	 * @param mainId
	 * @return
	 */
	@GetMapping(value = "/listD150aByMainId")
	public Result<List<D150a>> queryD150aListByMainId(@RequestParam(name="mainId",required=false) String mainId) {
		Result<List<D150a>> result = new Result<List<D150a>>();
		List<D150a> d150aList = null;
		if (mainId != null) {
			d150aList = d150aService.selectByMainId(mainId);
            result.setResult(d150aList);
            result.setSuccess(true);
            return result;
		}else return null;
	}

	/**
     * 添加150
     *
     * @param d150a
     * @return
     */
    @PostMapping(value = "/addD150a")
    public Result<D150a> addD150a(@RequestBody D150a d150a) {
        Result<D150a> result = new Result<>();
        try {
            boolean ok = d150aService.save(d150a);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("添加150成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("添加150失败!");
            }
            return result;
        } catch (Exception e) {
            e.fillInStackTrace();
            result.setSuccess(false);
            result.setMessage("添加150过程中出现了异常: " + e.getMessage());
            return result;
        }
    }

    /**
     * 编辑150
     *
     * @param d150a
     * @return
     */
    @PutMapping("/editD150a")
    public Result<D150a> editD150a(@RequestBody D150a d150a) {
        Result<D150a> result = new Result<>();
        try {
            boolean ok = d150aService.updateById(d150a);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("更新150成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("更新150失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("更新数据过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 通过id删除150
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteD150a")
    public Result<D150a> deleteD150a(@RequestParam(name = "id", required = true) String id) {
        Result<D150a> result = new Result<>();
        try {
            boolean ok = d150aService.removeById(id);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("删除150成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("删除150失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除150过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

	/**
     * 批量删除150
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatchD150a")
    public Result<D150a> deleteBatchD150a(@RequestParam(name = "ids", required = true) String ids) {
        Result<D150a> result = new Result<D150a>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.d150aService.removeByIds(Arrays.asList(ids.split(",")));
            result.success("删除成功!");
        }
        return result;
    }

	/**
	  * 通过主表id查询200
	 * @param mainId
	 * @return
	 */
	@GetMapping(value = "/listD200aByMainId")
	public Result<List<D200a>> queryD200aListByMainId(@RequestParam(name="mainId",required=false) String mainId) {
		Result<List<D200a>> result = new Result<List<D200a>>();
		List<D200a> d200aList = null;
		if (mainId != null) {
			d200aList = d200aService.selectByMainId(mainId);
            result.setResult(d200aList);
            result.setSuccess(true);
            return result;
		}else return null;
	}

	/**
     * 添加200
     *
     * @param d200a
     * @return
     */
    @PostMapping(value = "/addD200a")
    public Result<D200a> addD200a(@RequestBody D200a d200a) {
        Result<D200a> result = new Result<>();
        try {
            boolean ok = d200aService.save(d200a);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("添加200成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("添加200失败!");
            }
            return result;
        } catch (Exception e) {
            e.fillInStackTrace();
            result.setSuccess(false);
            result.setMessage("添加200过程中出现了异常: " + e.getMessage());
            return result;
        }
    }

    /**
     * 编辑200
     *
     * @param d200a
     * @return
     */
    @PutMapping("/editD200a")
    public Result<D200a> editD200a(@RequestBody D200a d200a) {
        Result<D200a> result = new Result<>();
        try {
            boolean ok = d200aService.updateById(d200a);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("更新200成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("更新200失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("更新数据过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 通过id删除200
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteD200a")
    public Result<D200a> deleteD200a(@RequestParam(name = "id", required = true) String id) {
        Result<D200a> result = new Result<>();
        try {
            boolean ok = d200aService.removeById(id);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("删除200成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("删除200失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除200过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

	/**
     * 批量删除200
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatchD200a")
    public Result<D200a> deleteBatchD200a(@RequestParam(name = "ids", required = true) String ids) {
        Result<D200a> result = new Result<D200a>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.d200aService.removeByIds(Arrays.asList(ids.split(",")));
            result.success("删除成功!");
        }
        return result;
    }

	/**
	  * 通过主表id查询250
	 * @param mainId
	 * @return
	 */
	@GetMapping(value = "/listD250aByMainId")
	public Result<List<D250a>> queryD250aListByMainId(@RequestParam(name="mainId",required=false) String mainId) {
		Result<List<D250a>> result = new Result<List<D250a>>();
		List<D250a> d250aList = null;
		if (mainId != null) {
			d250aList = d250aService.selectByMainId(mainId);
            result.setResult(d250aList);
            result.setSuccess(true);
            return result;
		}else return null;
	}

	/**
     * 添加250
     *
     * @param d250a
     * @return
     */
    @PostMapping(value = "/addD250a")
    public Result<D250a> addD250a(@RequestBody D250a d250a) {
        Result<D250a> result = new Result<>();
        try {
            boolean ok = d250aService.save(d250a);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("添加250成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("添加250失败!");
            }
            return result;
        } catch (Exception e) {
            e.fillInStackTrace();
            result.setSuccess(false);
            result.setMessage("添加250过程中出现了异常: " + e.getMessage());
            return result;
        }
    }

    /**
     * 编辑250
     *
     * @param d250a
     * @return
     */
    @PutMapping("/editD250a")
    public Result<D250a> editD250a(@RequestBody D250a d250a) {
        Result<D250a> result = new Result<>();
        try {
            boolean ok = d250aService.updateById(d250a);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("更新250成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("更新250失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("更新数据过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 通过id删除250
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteD250a")
    public Result<D250a> deleteD250a(@RequestParam(name = "id", required = true) String id) {
        Result<D250a> result = new Result<>();
        try {
            boolean ok = d250aService.removeById(id);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("删除250成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("删除250失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除250过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

	/**
     * 批量删除250
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatchD250a")
    public Result<D250a> deleteBatchD250a(@RequestParam(name = "ids", required = true) String ids) {
        Result<D250a> result = new Result<D250a>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.d250aService.removeByIds(Arrays.asList(ids.split(",")));
            result.success("删除成功!");
        }
        return result;
    }

	/**
	  * 通过主表id查询300
	 * @param mainId
	 * @return
	 */
	@GetMapping(value = "/listD300aByMainId")
	public Result<List<D300a>> queryD300aListByMainId(@RequestParam(name="mainId",required=false) String mainId) {
		Result<List<D300a>> result = new Result<List<D300a>>();
		List<D300a> d300aList = null;
		if (mainId != null) {
			d300aList = d300aService.selectByMainId(mainId);
            result.setResult(d300aList);
            result.setSuccess(true);
            return result;
		}else return null;
	}

	/**
     * 添加300
     *
     * @param d300a
     * @return
     */
    @PostMapping(value = "/addD300a")
    public Result<D300a> addD300a(@RequestBody D300a d300a) {
        Result<D300a> result = new Result<>();
        try {
            boolean ok = d300aService.save(d300a);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("添加300成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("添加300失败!");
            }
            return result;
        } catch (Exception e) {
            e.fillInStackTrace();
            result.setSuccess(false);
            result.setMessage("添加300过程中出现了异常: " + e.getMessage());
            return result;
        }
    }

    /**
     * 编辑300
     *
     * @param d300a
     * @return
     */
    @PutMapping("/editD300a")
    public Result<D300a> editD300a(@RequestBody D300a d300a) {
        Result<D300a> result = new Result<>();
        try {
            boolean ok = d300aService.updateById(d300a);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("更新300成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("更新300失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("更新数据过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 通过id删除300
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteD300a")
    public Result<D300a> deleteD300a(@RequestParam(name = "id", required = true) String id) {
        Result<D300a> result = new Result<>();
        try {
            boolean ok = d300aService.removeById(id);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("删除300成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("删除300失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除300过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

	/**
     * 批量删除300
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatchD300a")
    public Result<D300a> deleteBatchD300a(@RequestParam(name = "ids", required = true) String ids) {
        Result<D300a> result = new Result<D300a>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.d300aService.removeByIds(Arrays.asList(ids.split(",")));
            result.success("删除成功!");
        }
        return result;
    }

	/**
	  * 通过主表id查询350
	 * @param mainId
	 * @return
	 */
	@GetMapping(value = "/listD350aByMainId")
	public Result<List<D350a>> queryD350aListByMainId(@RequestParam(name="mainId",required=false) String mainId) {
		Result<List<D350a>> result = new Result<List<D350a>>();
		List<D350a> d350aList = null;
		if (mainId != null) {
			d350aList = d350aService.selectByMainId(mainId);
            result.setResult(d350aList);
            result.setSuccess(true);
            return result;
		}else return null;
	}

	/**
     * 添加350
     *
     * @param d350a
     * @return
     */
    @PostMapping(value = "/addD350a")
    public Result<D350a> addD350a(@RequestBody D350a d350a) {
        Result<D350a> result = new Result<>();
        try {
            boolean ok = d350aService.save(d350a);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("添加350成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("添加350失败!");
            }
            return result;
        } catch (Exception e) {
            e.fillInStackTrace();
            result.setSuccess(false);
            result.setMessage("添加350过程中出现了异常: " + e.getMessage());
            return result;
        }
    }

    /**
     * 编辑350
     *
     * @param d350a
     * @return
     */
    @PutMapping("/editD350a")
    public Result<D350a> editD350a(@RequestBody D350a d350a) {
        Result<D350a> result = new Result<>();
        try {
            boolean ok = d350aService.updateById(d350a);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("更新350成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("更新350失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("更新数据过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 通过id删除350
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteD350a")
    public Result<D350a> deleteD350a(@RequestParam(name = "id", required = true) String id) {
        Result<D350a> result = new Result<>();
        try {
            boolean ok = d350aService.removeById(id);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("删除350成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("删除350失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除350过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

	/**
     * 批量删除350
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatchD350a")
    public Result<D350a> deleteBatchD350a(@RequestParam(name = "ids", required = true) String ids) {
        Result<D350a> result = new Result<D350a>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.d350aService.removeByIds(Arrays.asList(ids.split(",")));
            result.success("删除成功!");
        }
        return result;
    }

	/**
	  * 通过主表id查询400
	 * @param mainId
	 * @return
	 */
	@GetMapping(value = "/listD400aByMainId")
	public Result<List<D400a>> queryD400aListByMainId(@RequestParam(name="mainId",required=false) String mainId) {
		Result<List<D400a>> result = new Result<List<D400a>>();
		List<D400a> d400aList = null;
		if (mainId != null) {
			d400aList = d400aService.selectByMainId(mainId);
            result.setResult(d400aList);
            result.setSuccess(true);
            return result;
		}else return null;
	}

	/**
     * 添加400
     *
     * @param d400a
     * @return
     */
    @PostMapping(value = "/addD400a")
    public Result<D400a> addD400a(@RequestBody D400a d400a) {
        Result<D400a> result = new Result<>();
        try {
            boolean ok = d400aService.save(d400a);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("添加400成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("添加400失败!");
            }
            return result;
        } catch (Exception e) {
            e.fillInStackTrace();
            result.setSuccess(false);
            result.setMessage("添加400过程中出现了异常: " + e.getMessage());
            return result;
        }
    }

    /**
     * 编辑400
     *
     * @param d400a
     * @return
     */
    @PutMapping("/editD400a")
    public Result<D400a> editD400a(@RequestBody D400a d400a) {
        Result<D400a> result = new Result<>();
        try {
            boolean ok = d400aService.updateById(d400a);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("更新400成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("更新400失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("更新数据过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 通过id删除400
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteD400a")
    public Result<D400a> deleteD400a(@RequestParam(name = "id", required = true) String id) {
        Result<D400a> result = new Result<>();
        try {
            boolean ok = d400aService.removeById(id);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("删除400成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("删除400失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除400过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

	/**
     * 批量删除400
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatchD400a")
    public Result<D400a> deleteBatchD400a(@RequestParam(name = "ids", required = true) String ids) {
        Result<D400a> result = new Result<D400a>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.d400aService.removeByIds(Arrays.asList(ids.split(",")));
            result.success("删除成功!");
        }
        return result;
    }

	/**
	  * 通过主表id查询800
	 * @param mainId
	 * @return
	 */
	@GetMapping(value = "/listD800aByMainId")
	public Result<List<D800a>> queryD800aListByMainId(@RequestParam(name="mainId",required=false) String mainId) {
		Result<List<D800a>> result = new Result<List<D800a>>();
		List<D800a> d800aList = null;
		if (mainId != null) {
			d800aList = d800aService.selectByMainId(mainId);
            result.setResult(d800aList);
            result.setSuccess(true);
            return result;
		}else return null;
	}

	/**
     * 添加800
     *
     * @param d800a
     * @return
     */
    @PostMapping(value = "/addD800a")
    public Result<D800a> addD800a(@RequestBody D800a d800a) {
        Result<D800a> result = new Result<>();
        try {
            boolean ok = d800aService.save(d800a);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("添加800成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("添加800失败!");
            }
            return result;
        } catch (Exception e) {
            e.fillInStackTrace();
            result.setSuccess(false);
            result.setMessage("添加800过程中出现了异常: " + e.getMessage());
            return result;
        }
    }

    /**
     * 编辑800
     *
     * @param d800a
     * @return
     */
    @PutMapping("/editD800a")
    public Result<D800a> editD800a(@RequestBody D800a d800a) {
        Result<D800a> result = new Result<>();
        try {
            boolean ok = d800aService.updateById(d800a);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("更新800成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("更新800失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("更新数据过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 通过id删除800
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteD800a")
    public Result<D800a> deleteD800a(@RequestParam(name = "id", required = true) String id) {
        Result<D800a> result = new Result<>();
        try {
            boolean ok = d800aService.removeById(id);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("删除800成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("删除800失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除800过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

	/**
     * 批量删除800
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatchD800a")
    public Result<D800a> deleteBatchD800a(@RequestParam(name = "ids", required = true) String ids) {
        Result<D800a> result = new Result<D800a>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.d800aService.removeByIds(Arrays.asList(ids.split(",")));
            result.success("删除成功!");
        }
        return result;
    }

	/**
	  * 通过主表id查询1000
	 * @param mainId
	 * @return
	 */
	@GetMapping(value = "/listD1000aByMainId")
	public Result<List<D1000a>> queryD1000aListByMainId(@RequestParam(name="mainId",required=false) String mainId) {
		Result<List<D1000a>> result = new Result<List<D1000a>>();
		List<D1000a> d1000aList = null;
		if (mainId != null) {
			d1000aList = d1000aService.selectByMainId(mainId);
            result.setResult(d1000aList);
            result.setSuccess(true);
            return result;
		}else return null;
	}

	/**
     * 添加1000
     *
     * @param d1000a
     * @return
     */
    @PostMapping(value = "/addD1000a")
    public Result<D1000a> addD1000a(@RequestBody D1000a d1000a) {
        Result<D1000a> result = new Result<>();
        try {
            boolean ok = d1000aService.save(d1000a);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("添加1000成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("添加1000失败!");
            }
            return result;
        } catch (Exception e) {
            e.fillInStackTrace();
            result.setSuccess(false);
            result.setMessage("添加1000过程中出现了异常: " + e.getMessage());
            return result;
        }
    }

    /**
     * 编辑1000
     *
     * @param d1000a
     * @return
     */
    @PutMapping("/editD1000a")
    public Result<D1000a> editD1000a(@RequestBody D1000a d1000a) {
        Result<D1000a> result = new Result<>();
        try {
            boolean ok = d1000aService.updateById(d1000a);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("更新1000成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("更新1000失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("更新数据过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 通过id删除1000
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteD1000a")
    public Result<D1000a> deleteD1000a(@RequestParam(name = "id", required = true) String id) {
        Result<D1000a> result = new Result<>();
        try {
            boolean ok = d1000aService.removeById(id);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("删除1000成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("删除1000失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除1000过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

	/**
     * 批量删除1000
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatchD1000a")
    public Result<D1000a> deleteBatchD1000a(@RequestParam(name = "ids", required = true) String ids) {
        Result<D1000a> result = new Result<D1000a>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.d1000aService.removeByIds(Arrays.asList(ids.split(",")));
            result.success("删除成功!");
        }
        return result;
    }

	/**
	  * 通过主表id查询1500
	 * @param mainId
	 * @return
	 */
	@GetMapping(value = "/listD1500aByMainId")
	public Result<List<D1500a>> queryD1500aListByMainId(@RequestParam(name="mainId",required=false) String mainId) {
		Result<List<D1500a>> result = new Result<List<D1500a>>();
		List<D1500a> d1500aList = null;
		if (mainId != null) {
			d1500aList = d1500aService.selectByMainId(mainId);
            result.setResult(d1500aList);
            result.setSuccess(true);
            return result;
		}else return null;
	}

	/**
     * 添加1500
     *
     * @param d1500a
     * @return
     */
    @PostMapping(value = "/addD1500a")
    public Result<D1500a> addD1500a(@RequestBody D1500a d1500a) {
        Result<D1500a> result = new Result<>();
        try {
            boolean ok = d1500aService.save(d1500a);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("添加1500成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("添加1500失败!");
            }
            return result;
        } catch (Exception e) {
            e.fillInStackTrace();
            result.setSuccess(false);
            result.setMessage("添加1500过程中出现了异常: " + e.getMessage());
            return result;
        }
    }

    /**
     * 编辑1500
     *
     * @param d1500a
     * @return
     */
    @PutMapping("/editD1500a")
    public Result<D1500a> editD1500a(@RequestBody D1500a d1500a) {
        Result<D1500a> result = new Result<>();
        try {
            boolean ok = d1500aService.updateById(d1500a);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("更新1500成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("更新1500失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("更新数据过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

    /**
     * 通过id删除1500
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteD1500a")
    public Result<D1500a> deleteD1500a(@RequestParam(name = "id", required = true) String id) {
        Result<D1500a> result = new Result<>();
        try {
            boolean ok = d1500aService.removeById(id);
            if (ok) {
                result.setSuccess(true);
                result.setMessage("删除1500成功.");
            } else {
                result.setSuccess(false);
                result.setMessage("删除1500失败!");
            }
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除1500过程中出现异常啦: " + e.getMessage());
            return result;
        }
    }

	/**
     * 批量删除1500
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatchD1500a")
    public Result<D1500a> deleteBatchD1500a(@RequestParam(name = "ids", required = true) String ids) {
        Result<D1500a> result = new Result<D1500a>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.d1500aService.removeByIds(Arrays.asList(ids.split(",")));
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
        QueryWrapper<Statistics> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                Statistics statistics = JSON.parseObject(deString, Statistics.class);
                queryWrapper = QueryGenerator.initQueryWrapper(statistics, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<StatisticsPage> pageList = new ArrayList<StatisticsPage>();
        List<Statistics> statisticsList = statisticsService.list(queryWrapper);
        for (Statistics statistics : statisticsList) {
            StatisticsPage vo = new StatisticsPage();
            BeanUtils.copyProperties(statistics, vo);
            List<D25a> d25aList = d25aService.selectByMainId(statistics.getId());
            vo.setD25aList(d25aList);
            List<D50a> d50aList = d50aService.selectByMainId(statistics.getId());
            vo.setD50aList(d50aList);
            List<D100a> d100aList = d100aService.selectByMainId(statistics.getId());
            vo.setD100aList(d100aList);
            List<D150a> d150aList = d150aService.selectByMainId(statistics.getId());
            vo.setD150aList(d150aList);
            List<D200a> d200aList = d200aService.selectByMainId(statistics.getId());
            vo.setD200aList(d200aList);
            List<D250a> d250aList = d250aService.selectByMainId(statistics.getId());
            vo.setD250aList(d250aList);
            List<D300a> d300aList = d300aService.selectByMainId(statistics.getId());
            vo.setD300aList(d300aList);
            List<D350a> d350aList = d350aService.selectByMainId(statistics.getId());
            vo.setD350aList(d350aList);
            List<D400a> d400aList = d400aService.selectByMainId(statistics.getId());
            vo.setD400aList(d400aList);
            List<D800a> d800aList = d800aService.selectByMainId(statistics.getId());
            vo.setD800aList(d800aList);
            List<D1000a> d1000aList = d1000aService.selectByMainId(statistics.getId());
            vo.setD1000aList(d1000aList);
            List<D1500a> d1500aList = d1500aService.selectByMainId(statistics.getId());
            vo.setD1500aList(d1500aList);
            pageList.add(vo);
        }
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "训练报表列表");
        mv.addObject(NormalExcelConstants.CLASS, StatisticsPage.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("训练报表列表数据", "导出人:Jeecg", "导出信息"));
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
                List<StatisticsPage> list = ExcelImportUtil.importExcel(file.getInputStream(), StatisticsPage.class, params);
                for (StatisticsPage page : list) {
                    Statistics po = new Statistics();
                    BeanUtils.copyProperties(page, po);
                    statisticsService.saveMain(po, page.getD25aList(),page.getD50aList(),page.getD100aList(),page.getD150aList(),page.getD200aList(),page.getD250aList(),page.getD300aList(),page.getD350aList(),page.getD400aList(),page.getD800aList(),page.getD1000aList(),page.getD1500aList());
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
