package com.ruoyi.sau.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.sau.mapper.CommentMapper;
import com.ruoyi.sau.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 评论
 * @author Maxj
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    /**
     * 根据帖子ID查找评论
     * @return
     */
    public List<Map<Object,Object>> selectCommentByTalkId(long parentId){
        List<Map<Object,Object>> commentList = commentMapper.selectCommentByTalkId(parentId);
        for(Map<Object, Object> map : commentList){
            map.put("commentCount",
                    commentMapper.selectCommentByTalkId(Long.parseLong(map.get("commentId").toString())).size());
        }
        return commentList;
    }

    /**
     * 获取回复下的评论
     * @return
     */
    public List<Map<Object,Object>> getComments(Long parentId){
        List<Map<Object,Object>> commentList = commentMapper.selectCommentByTalkId(parentId);
        for(Map<Object, Object> map : commentList){
            map.put("commentCount",
                    commentMapper.selectCommentByTalkId(Long.parseLong(map.get("commentId").toString())).size());
        }
        return commentList;
    }

    /**
     * 添加评论
     * @param parentId
     * @param content
     * @return
     */
    public AjaxResult insertComment(long parentId, String content,long userId) {
        int count =  commentMapper.insertComment(parentId,content,userId);
        if(count > 0){
            return AjaxResult.success("添加评论成功");
        }else{
            return AjaxResult.error("添加评论失败");
        }
    }
}
