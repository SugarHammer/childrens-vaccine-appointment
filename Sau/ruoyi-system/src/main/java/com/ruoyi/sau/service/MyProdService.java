package com.ruoyi.sau.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.sau.domain.ProdWeek;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 我的生产实习
 * @author Maxj
 */
public interface MyProdService {

    /**
     * 根据学生ID获取周报信息
     * @param stuId
     * @return
     */
    List<Map<String,Object>> selectWeekBystuId(long stuId,long worklyId,long productionId,String worklyCategory);

    /**
     * 根据周报ID获取周报信息
     * @param worklyId
     * @return
     */
    ProdWeek selectWeekById(long worklyId);

    /**
     * 获取全部生产实习周报信息
     * @param productionId
     * @param studentId
     * @param worklyCategory
     * @return
     */
    List<Map<String,Object>> selectWeeks(long productionId,long studentId,String worklyCategory);

    /**
     * 更新周报信息
     * @param map
     * @return
     */
    AjaxResult updateWeekly(HashMap<String,Object> map);

    /**
     * 教师批阅学生周报
     * @param map
     * @return
     */
    AjaxResult updateWeeklyByTea(HashMap<String,Object> map);
}
