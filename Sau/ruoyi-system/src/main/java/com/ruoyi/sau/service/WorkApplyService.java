package com.ruoyi.sau.service;

import com.ruoyi.common.core.domain.AjaxResult;

import java.util.HashMap;

/**
 * 顶岗实习申请
 * @author Maxj
 */
public interface WorkApplyService {

    /**
     * 添加学生申请顶岗实习信息
     * @param userId
     * @return
     */
    AjaxResult addWorkApply(long userId,HashMap<String,Object> map);
}
