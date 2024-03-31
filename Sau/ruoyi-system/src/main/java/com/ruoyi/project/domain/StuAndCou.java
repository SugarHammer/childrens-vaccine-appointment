package com.ruoyi.project.domain;

/**
 * @author Maxj
 */
public class StuAndCou {

    /**
     * id
     */
    private long curriculaId;

    /**
     * 学生学号
     */
    private long studentId;

    /**
     * 课程号
     */
    private long courseId;

    /**
     * 平时成绩
     */
    private String pacificScore;

    /**
     * 考试成绩
     */
    private String examScore;

    /**
     * 总成绩
     */
    private String score;

    /**
     * 状态
     * 1：有 2：无
     */
    private int state;

    public long getCurriculaId() {
        return curriculaId;
    }

    public void setCurriculaId(long curriculaId) {
        this.curriculaId = curriculaId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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

    @Override
    public String toString() {
        return "StuAndCou{" +
                "curriculaId=" + curriculaId +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", pacificScore=" + pacificScore +
                ", examScore=" + examScore +
                ", score='" + score + '\'' +
                ", state=" + state +
                '}';
    }
}
