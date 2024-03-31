package com.ruoyi.sau.service.impl;

import com.ruoyi.sau.mapper.TalkMapper;
import com.ruoyi.sau.service.TalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
@Service
public class TalkServiceImpl implements TalkService {
    @Autowired
    private TalkMapper talkMapper;

    /**
     * 查询所有帖子
     * @param userId 工号
     * @param author 作者
     * @param talkCenter 主题
     * @return
     */
    public List<Map<String,Object>> selectTalk(long userId, String author, String talkCenter){
        return talkMapper.selectTalk(userId, author, talkCenter);
    }
}
