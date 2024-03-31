package com.ruoyi.project.domain;

/**
 * @author Maxj
 */
public class Teacher {

    /**
     * 职工号
     */
    private long teacherId;

    /**
     * 职工姓名
     */
    private String teacherName;

    /**
     * 职工性别
     * 1：男 2：女 3：未知
     */
    private int teacherSex;

    /**
     * 出生年月
     */
    private String teacherBirthday;

    /**
     * 所在单位号
     */
    private long departmentId;

    /**
     * 职称
     */
    private long teacherTitle;

    /**
     * 技术专长
     */
    private String teacherTechnical;

    /**
     * 状态
     * 1：有 2：无
     */
    private int state;

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getTeacherSex() {
        return teacherSex;
    }

    public void setTeacherSex(int teacherSex) {
        this.teacherSex = teacherSex;
    }

    public String getTeacherBirthday() {
        return teacherBirthday;
    }

    public void setTeacherBirthday(String teacherBirthday) {
        this.teacherBirthday = teacherBirthday;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public long getTeacherTitle() {
        return teacherTitle;
    }

    public void setTeacherTitle(long teacherTitle) {
        this.teacherTitle = teacherTitle;
    }

    public String getTeacherTechnical() {
        return teacherTechnical;
    }

    public void setTeacherTechnical(String teacherTechnical) {
        this.teacherTechnical = teacherTechnical;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", teacherSex=" + teacherSex +
                ", teacherBirthday='" + teacherBirthday + '\'' +
                ", departmentId=" + departmentId +
                ", teacherTitle=" + teacherTitle +
                ", teacherTechnical='" + teacherTechnical + '\'' +
                ", state=" + state +
                '}';
    }
}
