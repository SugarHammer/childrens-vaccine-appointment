package com.ruoyi.sau.service.impl;

import com.ruoyi.sau.mapper.StuWorkMapper;
import com.ruoyi.sau.service.MyWorkGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
@Service
public class MyWorkGradeServiceImpl implements MyWorkGradeService {

    @Autowired
    private StuWorkMapper stuWorkMapper;

    /**
     * 根据学生ID查找该用户顶岗实习的成绩
     * @return
     */
    public List<Map<String,Object>> selectMyGrade(long stuId, long workId, String workName, String company){
        return stuWorkMapper.selectMyGrade(stuId,workId,workName,company);
    }
}
