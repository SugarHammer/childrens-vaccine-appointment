package com.ruoyi.sau.service;

import com.ruoyi.project.domain.Course;

import java.util.List;
import java.util.Map;

/**
 * 我的申请（疫苗信息）
 * @author Maxj
 */
public interface MyApplyService {

    /**
     * 根据学生Id查找已申请的顶岗实习信息
     * @return
     */
    List<Map<String,Object>> selectProdBystuId(long stuId,long productionId,String productionName,String productionCompany);

    /**
     * 学生退选生产实习
     * @param stuProdId
     */
    int deleteById(long stuProdId);

    /**
     * 查找所有申请通过的生产实习的成绩
     * @return
     */
    List<Map<String,Object>> selectAllGrade(long stuProdId,String productionName,String studentName,String productionCompany);

    /**
     * 根据学生ID查找该用户生产实习的成绩
     * @return
     */
    List<Map<String,Object>> selectMyGrade(long stuId,long productionId,String productionName,String productionCompany);
}
