package com.ruoyi.sau.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.sau.mapper.TalkMapper;
import com.ruoyi.sau.service.TalkAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
@Service
public class TalkAuditServiceImpl implements TalkAuditService {
    @Autowired
    private TalkMapper talkMapper;

    /**
     * 查询全部帖子
     * @param userId
     * @param author
     * @param talkCenter
     * @param auditState
     * @return
     */
    public List<Map<String,Object>> selectAllTalk(long userId, String author, String talkCenter, int auditState){
        return talkMapper.selectAllTalk(userId,author,talkCenter,auditState);
    }

    /**
     * 通过
     * @param talkId
     * @return
     */
    public AjaxResult agree(String talkId){
        Long[] talkIds = Convert.toLongArray(talkId);
        int Count = talkMapper.agree(talkIds);
        if(Count > 0){
            return AjaxResult.success("成功");
        }
        return AjaxResult.warn("失败");
    }

    /**
     * 拒绝
     * @param talkId
     * @return
     */
    public AjaxResult reject(String talkId){
        Long[] talkIds = Convert.toLongArray(talkId);
        int Count = talkMapper.reject(talkIds);
        if(Count > 0){
            return AjaxResult.success("成功");
        }
        return AjaxResult.warn("失败");
    }
}
