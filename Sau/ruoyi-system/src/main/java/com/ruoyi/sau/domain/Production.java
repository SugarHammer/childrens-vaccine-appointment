package com.ruoyi.sau.domain;

/**
 * @author Maxj
 */
public class Production {

    /**
     * 生产实习ID
     */
    private long productionId;

    /**
     * 名称
     */
    private String productionName;

    /**
     * 申请要求
     */
    private String productionRequire;

    /**
     * 开始时间
     */
    private String productionBegin;

    /**
     * 结束时间
     */
    private String productionEnd;

    /**
     * 申请截至时间
     */
    private String productionOver;

    /**
     * 实习单位名称
     */
    private String productionCompany;

    /**
     * 实习单位简介
     */
    private String productionIntro;

    /**
     * 周报数量
     */
    private String productionWeeks;

    /**
     * 周报填写要求
     */
    private String productionWeekly;

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


    public long getProductionId() {
        return productionId;
    }

    public void setProductionId(long productionId) {
        this.productionId = productionId;
    }

    public String getProductionName() {
        return productionName;
    }

    public void setProductionName(String productionName) {
        this.productionName = productionName;
    }

    public String getProductionRequire() {
        return productionRequire;
    }

    public void setProductionRequire(String productionRequire) {
        this.productionRequire = productionRequire;
    }

    public String getProductionBegin() {
        return productionBegin;
    }

    public void setProductionBegin(String productionBegin) {
        this.productionBegin = productionBegin;
    }

    public String getProductionEnd() {
        return productionEnd;
    }

    public void setProductionEnd(String productionEnd) {
        this.productionEnd = productionEnd;
    }

    public String getProductionOver() {
        return productionOver;
    }

    public void setProductionOver(String productionOver) {
        this.productionOver = productionOver;
    }

    public String getProductionCompany() {
        return productionCompany;
    }

    public void setProductionCompany(String productionCompany) {
        this.productionCompany = productionCompany;
    }

    public String getProductionIntro() {
        return productionIntro;
    }

    public void setProductionIntro(String productionIntro) {
        this.productionIntro = productionIntro;
    }

    public String getProductionWeeks() {
        return productionWeeks;
    }

    public void setProductionWeeks(String productionWeeks) {
        this.productionWeeks = productionWeeks;
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

    public String getProductionWeekly() {
        return productionWeekly;
    }

    public void setProductionWeekly(String productionWeekly) {
        this.productionWeekly = productionWeekly;
    }

    @Override
    public String toString() {
        return "Production{" +
                "productionId=" + productionId +
                ", productionName='" + productionName + '\'' +
                ", productionRequire='" + productionRequire + '\'' +
                ", productionBegin='" + productionBegin + '\'' +
                ", productionEnd='" + productionEnd + '\'' +
                ", productionOver='" + productionOver + '\'' +
                ", productionCompany='" + productionCompany + '\'' +
                ", productionIntro='" + productionIntro + '\'' +
                ", productionWeeks='" + productionWeeks + '\'' +
                ", productionWeekly='" + productionWeekly + '\'' +
                ", pacific='" + pacific + '\'' +
                ", exam='" + exam + '\'' +
                ", state=" + state +
                '}';
    }
}
