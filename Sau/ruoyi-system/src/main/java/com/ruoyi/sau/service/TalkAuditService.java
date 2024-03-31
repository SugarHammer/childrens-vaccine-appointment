package com.ruoyi.sau.service;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
public interface TalkAuditService {
    /**
     * 查询全部帖子
     * @param userId
     * @param author
     * @param talkCenter
     * @param auditState
     * @return
     */
    List<Map<String,Object>> selectAllTalk(long userId,String author,String talkCenter,int auditState);

    /**
     * 通过
     * @param talkId
     * @return
     */
    AjaxResult agree(String talkId);

    /**
     * 拒绝
     * @param talkId
     * @return
     */
    AjaxResult reject(String talkId);
}
