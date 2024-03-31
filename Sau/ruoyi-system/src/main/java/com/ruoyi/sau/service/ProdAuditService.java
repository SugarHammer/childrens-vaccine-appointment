package com.ruoyi.sau.service;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
public interface ProdAuditService {

    /**
     * 获取学生申请的生产实习信息
     * @param studentId 学生ID
     * @param studentName 学生姓名
     * @param productionId 生产实习ID
     * @param auditState 审批状态
     * @return
     */
    List<Map<String,Object>> selectProdApply(long studentId, String studentName, long productionId, int auditState);

    /**
     * 同意生产实习申请
     * @param stuProdId
     * @return
     */
    AjaxResult agree(@RequestParam(value = "ids") String stuProdId);

    /**
     * 拒绝生产实习申请
     * @param stuProdId
     * @return
     */
    AjaxResult reject(@RequestParam(value = "ids") String stuProdId);

    /**
     * 根据教师评分录入计算后的成绩
     * @param pacificScore 周报成绩
     * @param examScore 总结成绩
     * @param score 总成绩
     */
    void grade(long stuProdId,String pacificScore,String examScore,String score);
}
