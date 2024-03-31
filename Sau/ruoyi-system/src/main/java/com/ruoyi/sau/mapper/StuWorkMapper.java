package com.ruoyi.sau.mapper;

import com.ruoyi.sau.domain.StuAndWork;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学生顶岗实习信息
 * @author Maxj
 */
@Mapper
public interface StuWorkMapper {
    /**
     * 获取下一个自增的ID
     * @return
     */
    long getId();

    /**
     * 拒绝学生顶岗实习申请
     * @param stuWorkIds
     * @return
     */
    int reject(Long[] stuWorkIds);

    /**
     * 同意学生顶岗实习申请
     * @param stuWorkIds
     * @return
     */
    int agree(Long[] stuWorkIds);

    /**
     * 根据教师评分录入计算后的成绩
     * @param pacificScore 周报成绩
     * @param examScore 总结成绩
     * @param score 总成绩
     */
    void grade(@Param(value = "stuWorkId") long stuWorkId,
               @Param(value = "pacificScore") String pacificScore,
               @Param(value = "examScore") String examScore,
               @Param(value = "score") String score);

    /**
     * 添加学生申请顶岗实习信息
     * @param map
     */
    void addWorkApply(HashMap<String,Object> map);

    /**
     * 学生退选顶岗实习
     * @param stuWorkId
     * @return
     */
    int deleteById(@Param("stuWorkId") long stuWorkId);


    /**
     * 根据学生ID获得该用户申请的顶岗实习信息
     * @param stuId
     * @param stuWorkId
     * @param workName
     * @param company
     * @return
     */
    List<Map<String,Object>> selectApplyBystudentId(@Param("stuId") long stuId,
                                                    @Param("stuWorkId") long stuWorkId,
                                                    @Param("workName") String workName,
                                                    @Param("company") String company);

    /**
     * 查找学生申请的顶岗实习信息
     * @param studentId
     * @param studentName
     * @param workId
     * @param auditState
     * @return
     */
    List<Map<String,Object>> selectWorkApply(@Param("studentId") long studentId,
                                             @Param("studentName") String studentName,
                                             @Param("workId") long workId,
                                             @Param("auditState") int auditState);

    /**
     * 根据ID查询每条顶岗实习申请
     * @param stuWorkId
     * @return
     */
    Map<String,Object> selectByStuWorkId(@Param("stuWorkId") long stuWorkId);

    /**
     * 根据ID查询StuAndWork对象
     * @param stuWorkId
     * @return
     */
    StuAndWork selectStuAndWorkByStuWorkId(@Param("stuWorkId") long stuWorkId);

    /**
     * 根据学生ID查找该用户顶岗实习的成绩
     * @param stuId
     * @param workId
     * @param workName
     * @param company
     * @return
     */
    List<Map<String,Object>> selectMyGrade(@Param("stuId") long stuId,
                                           @Param("workId") long workId,
                                           @Param("workName") String workName,
                                           @Param("company") String company);

    /**
     * 查找所有顶岗实习的成绩
     * @param stuWorkId
     * @param workName
     * @param studentName
     * @param company
     * @return
     */
    List<Map<String,Object>> selectAllGrade(@Param("stuWorkId") long stuWorkId,
                                            @Param("workName") String workName,
                                            @Param("studentName") String studentName,
                                            @Param("company") String company);
}
