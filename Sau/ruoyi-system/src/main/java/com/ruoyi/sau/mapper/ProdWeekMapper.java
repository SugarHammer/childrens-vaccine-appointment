package com.ruoyi.sau.mapper;

import com.ruoyi.sau.domain.ProdWeek;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 * 生产实习周报
 */
@Mapper
public interface ProdWeekMapper {

    /**
     * 添加学生周报
     * @param stuProdId 学生生产实习id
     * @param worklyCategory 类别
     * @param worklyOver 截至时间
     * @param worklyMain 周报内容
     * @param worklyScore 分数
     * @param worklyRemark 教师评语int
     */
     int addWorkly(@Param("stuProdId") long stuProdId,
                   @Param("worklyCategory") String worklyCategory,
                   @Param("worklyOver") String worklyOver,
                   @Param("worklyMain") String worklyMain,
                   @Param("worklyScore") String worklyScore,
                   @Param("worklyRemark") String worklyRemark);

    /**
     * 根据stuProdId删除周报信息
     * @param stuProdId
     */
    void deleteWeekByStuProdId(@Param("stuProdId") long stuProdId);

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
     *根据学生ID获取周报信息
     * @param stuId
     * @return
     */
    List<Map<String,Object>> selectWeekBystuId(@Param("stuId") long stuId,
                                               @Param("worklyId") long worklyId,
                                               @Param("productionId") long productionId,
                                               @Param("worklyCategory") String worklyCategory);

    /**
     * 获取全部生产实习周报信息
     * @param productionId
     * @param studentId
     * @param worklyCategory
     * @return
     */
    List<Map<String,Object>> selectWeeks(@Param(value = "productionId") long productionId,
                                         @Param(value = "studentId") long studentId,
                                         @Param(value = "worklyCategory") String worklyCategory);

    /**
     * 根据周报ID获取周报信息
     * @param worklyId
     * @return
     */
    ProdWeek selectWeekById(@Param("worklyId") long worklyId);
}
