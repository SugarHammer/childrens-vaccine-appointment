package com.ruoyi.sau.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 课程置换
 * @author Maxj
 */
@Mapper
public interface CourseReplaceMapper {
    /**
     * 添加学生顶岗实习课程置换
     * @param stuWorkId
     * @param course
     */
    void addCourseReplace(@Param("stuWorkId") long stuWorkId,
                          @Param("course") String course);

    /**
     * 删除与本顶岗实习相关的学生课程置换
     * @param workIds
     */
    void deleteByWorkId(Long[] workIds);

    /**
     * 根据stuWorkId删除置换的课程
     */
    void deleteCourseByStuWorkId(@Param("stuWorkId") long stuWorkId);

    /**
     * 根据stuWorkId查询置换课程名称
     * @param stuWorkId
     * @return
     */
    List<String> selectCourseByStuWorkId(@Param("stuWorkId") long stuWorkId);
}
