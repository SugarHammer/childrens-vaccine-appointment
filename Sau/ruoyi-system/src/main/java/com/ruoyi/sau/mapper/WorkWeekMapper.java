package com.ruoyi.sau.mapper;

import com.ruoyi.sau.domain.ProdWeek;
import com.ruoyi.sau.domain.WorkWeek;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 顶岗实习周报
 * @author Maxj
 */
@Mapper
public interface WorkWeekMapper {
    /**
     * 添加学生顶岗实习周报
     * @param stuWorkId 学生生产实习id
     * @param worklyCategory 类别
     * @param worklyOver 截至时间
     * @param worklyMain 周报内容
     * @param worklyScore 分数
     * @param worklyRemark 教师评语int
     */
    int addWorkly(@Param("stuWorkId") long stuWorkId,
                  @Param("worklyCategory") String worklyCategory,
                  @Param("worklyOver") String worklyOver,
                  @Param("worklyMain") String worklyMain,
                  @Param("worklyScore") String worklyScore,
                  @Param("worklyRemark") String worklyRemark);

    /**
     * 根据stuWorkId删除周报
     * @param stuWorkId
     */
    void deleteWeekByStuWorkId(@Param("stuWorkId") long stuWorkId);


    /**
     * 更新周报内容
     * @param map
     */
    int updateWeekly(HashMap<String,Object> map);

    /**
     *添加教师批阅学生周报信息
     * @param map
     * @return
     */
    int updateWeeklyByTea(HashMap<String,Object> map);

    /**
     * 查询全部顶岗实习周报
     * @param workId
     * @param studentId
     * @param worklyCategory
     * @return
     */
    List<Map<String,Object>> selectWeeks(@Param("workId") long workId,
                                         @Param("studentId") long studentId,
                                         @Param("worklyCategory") String worklyCategory);

    /**
     * 根据学生ID获取周报信息
     * @param stuId
     * @param worklyId
     * @param workId
     * @param worklyCategory
     * @return
     */
    List<Map<String,Object>> selectWeekBystuId(@Param("stuId") long stuId,
                                               @Param("worklyId") long worklyId,
                                               @Param("workId") long workId,
                                               @Param("worklyCategory") String worklyCategory);

    /**
     * 根据周报ID获取周报信息
     * @param worklyId
     * @return
     */
    WorkWeek selectWeekById(@Param("worklyId") long worklyId);
}
