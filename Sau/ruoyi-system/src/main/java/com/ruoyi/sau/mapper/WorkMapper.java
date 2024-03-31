package com.ruoyi.sau.mapper;

import com.ruoyi.sau.domain.Work;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
@Mapper
public interface WorkMapper {
    /**
     * 根据学生ID查询已经申请的顶岗实习信息
     * @param userId
     * @return
     */
    ArrayList<Long> getWorkByStuId(@Param("userId") long userId);

    /**
     * 教师添加顶岗实习信息
     * @param work
     * @return
     */
    int addWork(Work work);

    /**
     * 删除顶岗实习信息
     * @param workIds
     */
    void deleteWork(Long[] workIds);

    /**
     * 删除与本顶岗实习相关的学生周报
     * @param workIds
     */
    void deleteWork2(Long[] workIds);

    /**
     * 删除与本顶岗实习相关的学生申请
     * @param workIds
     */
    void deleteWork3(Long[] workIds);

    /**
     * 更新顶岗实习信息
     * @param map
     * @return
     */
    int updateWork(HashMap<String,Object> map);

    /**
     * 获取全部顶岗实习信息
     * @param workId
     * @param workName
     * @return
     */
    List<Map<String,Object>> selectWork(@Param("workId") long workId,
                                        @Param("workName") String workName);

    /**
     * 根据顶岗实习ID插叙顶岗实习信息
     * @param workId
     * @return
     */
    Work selectWorkById(@Param(value = "workId") long workId);

    /**
     * 根据stuWorkId查询顶岗实习信息
     * @param stuWorkId
     * @return
     */
    Work selectWorkByStuWorkId(@Param("stuWorkId") long stuWorkId);

}
