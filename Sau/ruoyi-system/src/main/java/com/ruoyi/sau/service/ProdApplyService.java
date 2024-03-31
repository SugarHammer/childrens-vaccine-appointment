package com.ruoyi.sau.service;

import com.ruoyi.common.core.domain.AjaxResult;

/**
 * @author Maxj
 */
public interface ProdApplyService {

    /**
     * 添加学生申请生产实习信息
     * @param productionId
     * @return
     */
    public AjaxResult addProdApply(String productionId, long userId);
}
