package com.ruoyi.sau.service;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 评论
 * @author Maxj
 */
public interface CommentService {
    /**
     * 根据帖子ID查找评论
     * @param parentId
     * @return
     */
    List<Map<Object,Object>> selectCommentByTalkId(long parentId);

    /**
     * 获取回复下的评论
     * @return
     */
    List<Map<Object,Object>> getComments(Long parentId);

    /**
     * 添加评论
     * @param parentId
     * @param content
     * @return
     */
    AjaxResult insertComment(long parentId, String content,long userId);
}
