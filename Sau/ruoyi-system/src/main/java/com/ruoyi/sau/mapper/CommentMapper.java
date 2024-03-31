package com.ruoyi.sau.mapper;

import com.ruoyi.common.core.domain.AjaxResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommentMapper {
    /**
     * 根据帖子ID查找评论
     * @return
     */
    List<Map<Object,Object>> selectCommentByTalkId(@Param("parentId") long parentId);

    /**
     * 添加评论
     * @param parentId
     * @param content
     * @return
     */
    int insertComment(@Param("parentId") long parentId, @Param("content") String content, @Param("userId") long userId);
}
