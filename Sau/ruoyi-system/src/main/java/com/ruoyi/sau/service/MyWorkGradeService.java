package com.ruoyi.sau.service;

import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
public interface MyWorkGradeService {
    /**
     * 根据学生ID查找该用户顶岗实习的成绩
     * @param stuId
     * @param workId
     * @param workName
     * @param company
     * @return
     */
    List<Map<String,Object>> selectMyGrade(long stuId,long workId,String workName,String company);
}
