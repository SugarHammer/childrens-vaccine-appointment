package com.ruoyi.sau.mapper;

import com.ruoyi.sau.domain.Talk;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
@Mapper
public interface TalkMapper {
    /**
     * 拒绝
     * @param talkId
     * @return
     */
    int agree(Long[] talkId);


    /**
     * 拒绝
     * @param talkId
     * @return
     */
    int reject(Long[] talkId);

    /**
     * 发帖
     * @param map
     * @return
     */
    int addMyTalk(Map<String,Object> map);

    /**
     * 删帖
     * @param talkId
     * @return
     */
    int deleteMyWork(Long[] talkId);

    /**
     * 编辑帖子
     * @param map
     * @return
     */
    int updateMyTalk(Map<String,Object> map);

    /**
     * 查询所有帖子
     * @param userId 工号
     * @param author 作者
     * @param talkCenter 主题
     * @return
     */
    List<Map<String,Object>> selectTalk(@Param("userId") long userId,
                                        @Param("author") String author,
                                        @Param("talkCenter") String talkCenter);

    /**
     * 查询所有帖子
     * @param userId
     * @param author
     * @param talkCenter
     * @param auditState
     * @return
     */
    List<Map<String,Object>> selectAllTalk(@Param("userId") long userId,
                                           @Param("author") String author,
                                           @Param("talkCenter") String talkCenter,
                                           @Param("auditState") int auditState);

    /**
     * 查找我的所有贴子
     * @param talkId
     * @param talkCenter
     * @return
     */
    List<Map<String,Object>> selectMyTalk(@Param("talkId") long talkId,
                                          @Param("talkCenter") String talkCenter);

    /**
     * 根据talkId查找帖子
     * @param talkId
     * @return
     */
    Talk selectTalkByTalkId(@Param("talkId") long talkId);
}
