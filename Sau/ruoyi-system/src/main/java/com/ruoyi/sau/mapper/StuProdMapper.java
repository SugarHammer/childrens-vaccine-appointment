package com.ruoyi.sau.mapper;

import com.ruoyi.sau.domain.Production;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 学生生产实习信息
 * @author Maxj
 */
@Mapper
public interface StuProdMapper {
    /**
     * 同意学生申请的生产实习
     * @param stuProdIds
     * @return
     */
    int agree(Long[] stuProdIds);

    /**
     * 拒绝学生申请的生产实习
     * @param stuProdIds
     * @return
     */
    int reject(Long[] stuProdIds);

    /**
     * 根据教师评分录入计算后的成绩
     * @param pacificScore 周报成绩
     * @param examScore 总结成绩
     * @param score 总成绩
     */
    void grade(@Param(value = "stuProdId") long stuProdId,
               @Param(value = "pacificScore") String pacificScore,
               @Param(value = "examScore") String examScore,
               @Param(value = "score") String score);

    /**
     * 添加学生申请生产实习信息
     */
    void addProdApply(@Param("productionId")Long productionId, @Param("userId")long userId);

    /**
     * 学生退选生产实习
     * @param stuProdId
     * @return
     */
    int deleteById(@Param("stuProdId") long stuProdId);

    /**
     * 根据stuProdId查询生产实习信息
     * @param stuProdId
     * @return
     */
    Map<String,Object> selectByStuProdId(Long stuProdId);

    /**
     * 查找全部申请的生产实习信息
     * @param studentId
     * @param studentName
     * @param productionId
     * @param auditState
     * @return
     */
    List<Map<String,Object>> selectProdApply(@Param(value = "studentId") long studentId,
                                             @Param(value = "studentName") String studentName,
                                             @Param(value = "productionId") long productionId,
                                             @Param(value = "auditState") int auditState);

    /**
     * 根据学生ID获得该用户申请的生产实习信息
     * @param stuId
     * @param productionId
     * @param productionName
     * @param productionCompany
     * @return
     */
    List<Map<String,Object>> selectApplyBystudentId(@Param(value = "stuId") long stuId,
                                               @Param("productionId") long productionId,
                                               @Param("productionName") String productionName,
                                               @Param("productionCompany") String productionCompany);

    /**
     * 查找所有顶岗实习的成绩
     * @param stuProdId
     * @param productionName
     * @param studentName
     * @param productionCompany
     * @return
     */
    List<Map<String,Object>> selectAllGrade(@Param("stuProdId") long stuProdId,
                                            @Param("productionName") String productionName,
                                            @Param("studentName") String studentName,
                                            @Param("productionCompany") String productionCompany);

    /**
     * 根据学生ID查找该用户顶岗实习的成绩
     * @param stuId
     * @param productionId
     * @param productionName
     * @param productionCompany
     * @return
     */
    List<Map<String,Object>> selectMyGrade(@Param("stuId") long stuId,
                                           @Param("productionId") long productionId,
                                           @Param("productionName") String productionName,
                                           @Param("productionCompany") String productionCompany);

}
