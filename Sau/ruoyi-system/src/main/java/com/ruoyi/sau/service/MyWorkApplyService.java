package com.ruoyi.sau.service;

import java.util.List;
import java.util.Map;

/**
 * 我的申请（顶岗实习）
 * @author Maxj
 */
public interface MyWorkApplyService {

    /**
     * 根据学生Id查找已申请的顶岗实习信息
     * @return
     */
    List<Map<String,Object>> selectApplyBystudentId(long stuId,long stuWorkId,String workName,String company);

    /**
     * 学生退选顶岗实习
     * @param stuWorkId
     */
    int deleteById(long stuWorkId);

}
