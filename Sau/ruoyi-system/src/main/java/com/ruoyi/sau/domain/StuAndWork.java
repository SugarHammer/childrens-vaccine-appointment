package com.ruoyi.sau.domain;

/**
 * @author Maxj
 */
public class StuAndWork {
    /**
     *学生申请顶岗实习id
     */
    private long stuWorkId;

    /**
     *学生学号
     */
    private long studentId;

    /**
     *学生姓名
     */
    private String studentName;

    /**
     *顶岗实习id
     */
    private long workId;

    /**
     *实习企业
     */
    private String company;

    /**
     *实习地点
     */
    private String site;

    /**
     *实习开始时间
     */
    private String begining;

    /**
     *实习结束时间
     */
    private String ending;

    /**
     *实习内容
     */
    private String content;

    /**
     *周报成绩
     */
    private String pacificScore;

    /**
     *总结成绩
     */
    private String examScore;

    /**
     *总评成绩
     */
    private String score;

    /**
     *审核状态
     */
    private int auditState;

    /**
     *状态
     */
    private  int state;

    public long getStuWorkId() {
        return stuWorkId;
    }

    public void setStuWorkId(long stuWorkId) {
        this.stuWorkId = stuWorkId;
    }

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

    public long getWorkId() {
        return workId;
    }

    public void setWorkId(long workId) {
        this.workId = workId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getBegining() {
        return begining;
    }

    public void setBegining(String begining) {
        this.begining = begining;
    }

    public String getEnding() {
        return ending;
    }

    public void setEnding(String ending) {
        this.ending = ending;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPacificScore() {
        return pacificScore;
    }

    public void setPacificScore(String pacificScore) {
        this.pacificScore = pacificScore;
    }

    public String getExamScore() {
        return examScore;
    }

    public void setExamScore(String examScore) {
        this.examScore = examScore;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getAuditState() {
        return auditState;
    }

    public void setAuditState(int auditState) {
        this.auditState = auditState;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "StuAndWork{" +
                "stuWorkId=" + stuWorkId +
                ", studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", workId=" + workId +
                ", company='" + company + '\'' +
                ", site='" + site + '\'' +
                ", begining='" + begining + '\'' +
                ", ending='" + ending + '\'' +
                ", content='" + content + '\'' +
                ", pacificScore='" + pacificScore + '\'' +
                ", examScore='" + examScore + '\'' +
                ", score='" + score + '\'' +
                ", auditState=" + auditState +
                ", state=" + state +
                '}';
    }
}
