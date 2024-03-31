package com.ruoyi.project.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 学生基本信息类
 * @author Maxj
 */
public class Student extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 学生学号
     */
    @Excel(name = "学生学号", cellType = Excel.ColumnType.NUMERIC, prompt = "用户编号")
    private long studentId;

    /**
     * 学生姓名
     */
    @Excel(name = "学生姓名")
    private String studentName;

    /**
     * 学生性别
     * (1：男 2：女 3：未知)
     */
    @Excel(name = "学生性别", readConverterExp = "1=男,2=女,3=未知")
    private int studentSex;

    /**
     * 出生日期
     */
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private String studentBirthday;

    /**
     * 入学成绩
     */
    @Excel(name = "入学成绩", cellType = Excel.ColumnType.NUMERIC)
    private  double studentGrade;

    /**
     * 所在单位号
     */
    @Excel(name = "所在单位", cellType = Excel.ColumnType.NUMERIC)
    private long departmentId;

    /**
     * 状态
     * 1：正常 2：停用
     */
    @Excel(name = "状态", readConverterExp = "1=正常,2=停用", type = Excel.Type.EXPORT)
    private int state;

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(int studentSex) {
        this.studentSex = studentSex;
    }

    public String getStudentBirthday() {
        return studentBirthday;
    }

    public void setStudentBirthday(String studentBirthday) {
        this.studentBirthday = studentBirthday;
    }

    public double getStudentGrade() {
        return studentGrade;
    }

    public void setStudentGrade(double studentGrade) {
        this.studentGrade = studentGrade;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


}
