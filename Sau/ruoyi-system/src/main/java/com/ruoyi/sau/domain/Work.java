package com.ruoyi.sau.domain;

/**
 * @author Maxj
 */
public class Work {

    /**
     * 顶岗实习ID
     */
    private long workId;

    /**
     * 名称
     * 默认：顶岗实习
     */
    private String workName;

    /**
     * 申请要求
     */
    private String workRequire;

    /**
     * 申请截至时间
     */
    private String workOver;

    /**
     * 周报数量
     */
    private String workWeeks;

    /**
     * 周报填写要求
     */
    private String workWeekly;

    /**
     * 周报开始时间
     */
    private String workBegin;

    /**
     * 周报结束时间
     */
    private String workEnd;

    /**
     * 周报成绩占比
     */
    private String pacific;

    /**
     * 总结成绩占比
     */
    private String exam;

    /**
     * 状态
     * 1：有 2：无
     */
    private int state;

    public long getWorkId() {
        return workId;
    }

    public void setWorkId(long workId) {
        this.workId = workId;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getWorkRequire() {
        return workRequire;
    }

    public void setWorkRequire(String workRequire) {
        this.workRequire = workRequire;
    }

    public String getWorkOver() {
        return workOver;
    }

    public void setWorkOver(String workOver) {
        this.workOver = workOver;
    }

    public String getWorkWeeks() {
        return workWeeks;
    }

    public void setWorkWeeks(String workWeeks) {
        this.workWeeks = workWeeks;
    }

    public String getPacific() {
        return pacific;
    }

    public void setPacific(String pacific) {
        this.pacific = pacific;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getWorkWeekly() {
        return workWeekly;
    }

    public void setWorkWeekly(String workWeekly) {
        this.workWeekly = workWeekly;
    }

    public String getWorkBegin() {
        return workBegin;
    }

    public void setWorkBegin(String workBegin) {
        this.workBegin = workBegin;
    }

    public String getWorkEnd() {
        return workEnd;
    }

    public void setWorkEnd(String workEnd) {
        this.workEnd = workEnd;
    }

    @Override
    public String toString() {
        return "Work{" +
                "workId=" + workId +
                ", workName='" + workName + '\'' +
                ", workRequire='" + workRequire + '\'' +
                ", workOver='" + workOver + '\'' +
                ", workWeeks='" + workWeeks + '\'' +
                ", workWeekly='" + workWeekly + '\'' +
                ", workBegin='" + workBegin + '\'' +
                ", workEnd='" + workEnd + '\'' +
                ", pacific='" + pacific + '\'' +
                ", exam='" + exam + '\'' +
                ", state=" + state +
                '}';
    }
}
