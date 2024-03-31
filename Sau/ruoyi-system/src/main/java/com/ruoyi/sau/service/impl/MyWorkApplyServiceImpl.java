package com.ruoyi.sau.service.impl;

import com.ruoyi.sau.mapper.CourseReplaceMapper;
import com.ruoyi.sau.mapper.StuWorkMapper;
import com.ruoyi.sau.mapper.WorkWeekMapper;
import com.ruoyi.sau.service.MyWorkApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
@Service
public class MyWorkApplyServiceImpl implements MyWorkApplyService {

    @Autowired
    private StuWorkMapper stuWorkMapper;

    @Autowired
    private WorkWeekMapper workWeekMapper;

    @Autowired
    private CourseReplaceMapper courseReplaceMapper;

    /**
     * 根据学生Id查找已申请的顶岗实习信息
     * @return
     */
    public List<Map<String,Object>> selectApplyBystudentId(long stuId,long stuWorkId,String workName,String company){
        return stuWorkMapper.selectApplyBystudentId(stuId,stuWorkId,workName,company);
    }

    /**
     * 学生退选顶岗实习
     * @param stuWorkId
     */
    public int deleteById(long stuWorkId){
        workWeekMapper.deleteWeekByStuWorkId(stuWorkId);
        courseReplaceMapper.deleteCourseByStuWorkId(stuWorkId);
        return stuWorkMapper.deleteById(stuWorkId);
    }

}
