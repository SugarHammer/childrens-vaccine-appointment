package com.ruoyi.sau.service;

import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
public interface AllWorkGradeService {
    /**
     * 查找所有申请通过的顶岗实习的成绩
     * @return
     */
    List<Map<String,Object>> selectAllGrade(long stuWorkId, String workName, String studentName, String company);
}
