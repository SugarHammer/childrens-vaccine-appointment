package com.ruoyi.project.domain;

/**
 * 系部基本信息类
 * @author Maxj
 */
public class Department {

    /**
     * 系号
     */
    private long departmentId;

    /**
     * 系名称
     */
    private String departmentName;

    /**
     * 系简介
     */
    private String departmentIntro;

    /**
     * 状态
     * 1：有 2：无
     */
    private int state;

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentIntro() {
        return departmentIntro;
    }

    public void setDepartmentIntro(String departmentIntro) {
        this.departmentIntro = departmentIntro;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", departmentIntro='" + departmentIntro + '\'' +
                ", state=" + state +
                '}';
    }
}
