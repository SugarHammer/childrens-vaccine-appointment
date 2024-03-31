package com.ruoyi.sau.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.sau.domain.ProdWeek;
import com.ruoyi.sau.domain.WorkWeek;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 我的顶岗实习
 * @author Maxj
 */
public interface MyWorkService {

    /**
     * 根据学生ID获取周报信息
     * @param stuId
     * @return
     */
    List<Map<String,Object>> selectWeekBystuId(long stuId, long worklyId, long workId, String worklyCategory);

    /**
     * 根据周报ID获取周报信息
     * @param worklyId
     * @return
     */
    WorkWeek selectWeekById(long worklyId);

    /**
     * 更新周报信息
     * @param map
     * @return
     */
    AjaxResult updateWeekly(HashMap<String, Object> map);

}
