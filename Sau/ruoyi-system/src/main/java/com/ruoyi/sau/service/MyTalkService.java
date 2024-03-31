package com.ruoyi.sau.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.sau.domain.Talk;

import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
public interface MyTalkService {
    /**
     * 发帖
     * @param map
     * @return
     */
    AjaxResult addMyTalk(Map<String,Object> map);

    /**
     * 删帖
     * @param talkId
     * @return
     */
    AjaxResult deleteMyWork(String talkId);

    /**
     * 编辑帖子
     * @param map
     * @return
     */
    AjaxResult updateMyTalk(Map<String,Object> map);

    /**
     * 查找我的所有贴子
     * @param talkId
     * @param talkCenter
     * @return
     */
    List<Map<String,Object>> selectMyTalk(long talkId, String talkCenter);

    /**
     * 根据talkId查找帖子
     * @param talkId
     * @return
     */
    Talk selectTalkByTalkId(long talkId);

}
