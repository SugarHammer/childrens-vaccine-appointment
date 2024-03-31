package com.ruoyi.sau.domain;

/**
 * @author Maxj
 */
public class ProdWeek {

    /**
     * 周报id
     */
    private long worklyId;

    /**
     * 学生生产实习id
     */
    private long stuProdId;

    /**
     * 类别
     * 第*次周报/总结
     */
    private String worklyCategory ;

    /**
     * 截至时间
     */
    private String worklyOver;

    /**
     * 周报内容
     */
    private String worklyMain;

    /**
     * 分数
     */
    private String worklyScore;

    /**
     * 实际得分
     */
    private String worklyActScore;

    /**
     * 教师评语
     */
    private String worklyRemark;

    /**
     * 状态
     * 1：有 2：无
     */
    private int state;

    public long getWorklyId() {
        return worklyId;
    }

    public void setWorklyId(long worklyId) {
        this.worklyId = worklyId;
    }

    public long getStuProdId() {
        return stuProdId;
    }

    public void setStuProdId(long stuProdId) {
        this.stuProdId = stuProdId;
    }

    public String getWorklyCategory() {
        return worklyCategory;
    }

    public void setWorklyCategory(String worklyCategory) {
        this.worklyCategory = worklyCategory;
    }

    public String getWorklyOver() {
        return worklyOver;
    }

    public void setWorklyOver(String worklyOver) {
        this.worklyOver = worklyOver;
    }

    public String getWorklyMain() {
        return worklyMain;
    }

    public void setWorklyMain(String worklyMain) {
        this.worklyMain = worklyMain;
    }

    public String getWorklyScore() {
        return worklyScore;
    }

    public void setWorklyScore(String worklyScore) {
        this.worklyScore = worklyScore;
    }

    public String getWorklyActScore() {
        return worklyActScore;
    }

    public void setWorklyActScore(String worklyActScore) {
        this.worklyActScore = worklyActScore;
    }

    public String getWorklyRemark() {
        return worklyRemark;
    }

    public void setWorklyRemark(String worklyRemark) {
        this.worklyRemark = worklyRemark;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ProdWeek{" +
                "worklyId=" + worklyId +
                ", stuProdId=" + stuProdId +
                ", worklyCategory=" + worklyCategory +
                ", worklyOver='" + worklyOver + '\'' +
                ", worklyMain='" + worklyMain + '\'' +
                ", worklyScore='" + worklyScore + '\'' +
                ", worklyActScore='" + worklyActScore + '\'' +
                ", worklyRemark='" + worklyRemark + '\'' +
                ", state=" + state +
                '}';
    }
}
