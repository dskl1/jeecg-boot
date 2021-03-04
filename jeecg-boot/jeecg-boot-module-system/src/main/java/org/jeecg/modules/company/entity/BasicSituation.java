package org.jeecg.modules.company.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.jeecgframework.poi.excel.annotation.Excel;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 简历
 * @Author: jeecg-boot
 * @Date: 2020-04-21
 * @Version: V1.0
 */
@Data
@TableName("basic_situation")
public class BasicSituation implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 人员信息
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private java.lang.String id;
    /**
     * 姓名
     */
    @Excel(name = "姓名", width = 15)
    private java.lang.String name;
    /**
     * 职位
     */
    @Excel(name = "职位", width = 15)
    private java.lang.String job;
    /**
     * 性别
     */
    @Excel(name = "性别", width = 15)
    private java.lang.String sex;
    /**
     * 出生年月
     */
    @Excel(name = "出生年月", width = 15)
    private java.lang.String birthday;
    /**
     * 籍贯
     */
    @Excel(name = "籍贯", width = 15)
    private java.lang.String hometown;
    /**
     * 教育背景
     */
    @Excel(name = "教育背景", width = 15)
    private java.lang.String educationBackground;
    /**
     * 专业
     */
    @Excel(name = "专业", width = 15)
    private java.lang.String profession;
    /**
     * 工作经历
     */
    @Excel(name = "工作经历", width = 15)
    private java.lang.String workExperience;
    /**
     * 项目经验
     */
    @Excel(name = "项目经验", width = 15)
    private java.lang.String projectExperience;
    /**
     * 自我评价
     */
    @Excel(name = "自我评价", width = 15)
    private java.lang.String selfEvaluation;
    /**
     * 创建人
     */
//    @Excel(name = "创建人", width = 15)
    private java.lang.String createBy;
    /**
     * 创建时间
     */
//    @Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date createTime;
    /**
     * 修改人
     */
//    @Excel(name = "修改人", width = 15)
    private java.lang.String updateBy;
    /**
     * 修改时间
     */
//    @Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date updateTime;
    /**
     * 所属部门编码
     */
//    @Excel(name = "所属部门编码", width = 15)
    private java.lang.String sysOrgCode;

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getJob() {
        return job;
    }

    public String getHometown() {
        return hometown;
    }

    public String getEducationBackground() {
        return educationBackground;
    }

    public String getProfession() {
        return profession;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public String getProjectExperience() {
        return projectExperience;
    }

    public String getSelfEvaluation() {
        return selfEvaluation;
    }
}
