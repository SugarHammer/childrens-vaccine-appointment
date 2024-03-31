package com.ruoyi.sau.service;

import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
public interface TalkService {
    /**
     * 查询所有帖子
     * @param userId 工号
     * @param author 作者
     * @param talkCenter 主题
     * @return
     */
    List<Map<String,Object>> selectTalk(long userId, String author, String talkCenter);
}
