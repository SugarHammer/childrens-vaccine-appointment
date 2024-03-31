package com.ruoyi.sau.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.sau.domain.ProdWeek;
import com.ruoyi.sau.domain.WorkWeek;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教师端顶岗实习过程管理
 * @author Maxj
 */
public interface WorkTeaAuditService {
    /**
     * 获取全部顶岗实习周报信息
     * @param workId
     * @param studentId
     * @param worklyCategory
     * @return
     */
    List<Map<String,Object>> selectWeeks(long workId, long studentId, String worklyCategory);

    /**
     * 根据周报ID获取周报信息
     * @param worklyId
     * @return
     */
    WorkWeek selectWeekById(long worklyId);

    /**
     * 教师批阅学生周报
     * @param map
     * @return
     */
    AjaxResult updateWeeklyByTea(HashMap<String,Object> map);
}
