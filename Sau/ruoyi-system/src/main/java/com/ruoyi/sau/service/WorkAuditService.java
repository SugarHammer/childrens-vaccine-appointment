package com.ruoyi.sau.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.sau.domain.StuAndWork;
import com.ruoyi.sau.domain.WorkWeek;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
public interface WorkAuditService {
    /**
     * 同意顶岗实习申请
     * @param stuWorkId
     * @return
     */
    AjaxResult agree(@RequestParam(value = "ids") String stuWorkId);

    /**
     * 拒绝顶岗实习申请
     * @param stuWorkId
     * @return
     */
    AjaxResult reject(@RequestParam(value = "ids") String stuWorkId);

    /**
     * 获取学生申请的顶岗实习信息
     * @param studentId 学生ID
     * @param studentName 学生姓名
     * @param workId 顶岗实习ID
     * @param auditState 审批状态
     * @return
     */
    List<Map<String,Object>> selectWorkApply(long studentId, String studentName, long workId, int auditState);

    /**
     *根据stuWorkId获取顶岗实习申请信息
     * @param stuWorkId
     * @return
     */
    StuAndWork selectByStuWorkId(long stuWorkId);

    /**
     * 根据stuWorkId查询置换课程名称
     */
    List<String> selectCourseByStuWorkId(long stuWorkId);
}
