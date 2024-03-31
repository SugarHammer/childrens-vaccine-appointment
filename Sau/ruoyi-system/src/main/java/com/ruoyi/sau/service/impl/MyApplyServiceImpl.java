package com.ruoyi.sau.service.impl;

import com.ruoyi.sau.mapper.ProdWeekMapper;
import com.ruoyi.sau.mapper.StuProdMapper;
import com.ruoyi.sau.service.MyApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
@Service
public class MyApplyServiceImpl implements MyApplyService {

    @Autowired
    private ProdWeekMapper prodWeekMapper;

    @Autowired
    private StuProdMapper stuProdMapper;

    /**
     * 根据学生Id查找已申请的顶岗实习信息
     * @return
     */
    public List<Map<String,Object>> selectProdBystuId(long stuId,long productionId,String productionName,String productionCompany){
        return stuProdMapper.selectApplyBystudentId(stuId,productionId,productionName,productionCompany);
    }

    /**
     * 学生退选生产实习
     * @param stuProdId
     */
    public int deleteById(long stuProdId){
        prodWeekMapper.deleteWeekByStuProdId(stuProdId);
        return stuProdMapper.deleteById(stuProdId);
    }

    /**
     * 查找所有申请通过的生产实习的成绩
     * @return
     */
    public List<Map<String,Object>> selectAllGrade(long stuProdId,String productionName,String studentName,String productionCompany){
        return stuProdMapper.selectAllGrade(stuProdId,productionName,studentName,productionCompany);
    }

    /**
     * 根据学生ID查找该用户生产实习的成绩
     * @return
     */
    public List<Map<String,Object>> selectMyGrade(long stuId,long productionId,String productionName,String productionCompany){
        return stuProdMapper.selectMyGrade(stuId,productionId,productionName,productionCompany);
    }
}
