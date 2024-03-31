package com.ruoyi.sau.domain;

/**
 * @author Maxj
 */
public class Talk {
    /**
     * 帖子编号
     */
    private long talkId;

    /**
     * 工号
     */
    private long userId;

    /**
     * 内容
     */
    private String talkContent;

    /**
     * 主题
     */
    private String talkCenter;

    /**
     * 发帖时间
     */
    private String talkTime;

    /**
     * 审核状态
     * 1：待审核 2：通过 3：未通过
     */
    private int auditState;

    /**
     * 状态
     * 1：有 2：无
     */
    private int state;

    public long getTalkId() {
        return talkId;
    }

    public void setTalkId(long talkId) {
        this.talkId = talkId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTalkContent() {
        return talkContent;
    }

    public void setTalkContent(String talkContent) {
        this.talkContent = talkContent;
    }

    public String getTalkCenter() {
        return talkCenter;
    }

    public void setTalkCenter(String talkCenter) {
        this.talkCenter = talkCenter;
    }

    public String getTalkTime() {
        return talkTime;
    }

    public void setTalkTime(String talkTime) {
        this.talkTime = talkTime;
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
        return "Talk{" +
                "talkId=" + talkId +
                ", userId=" + userId +
                ", talkContent='" + talkContent + '\'' +
                ", talkCenter='" + talkCenter + '\'' +
                ", talkTime='" + talkTime + '\'' +
                ", auditState=" + auditState +
                ", state=" + state +
                '}';
    }
}
