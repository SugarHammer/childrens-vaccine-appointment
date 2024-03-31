package com.ruoyi.sau.service.impl;

import com.ruoyi.sau.mapper.StuWorkMapper;
import com.ruoyi.sau.service.AllWorkGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
@Service
public class AllWorkGradeServiceImpl implements AllWorkGradeService {
    @Autowired
    private StuWorkMapper stuWorkMapper;

    /**
     * 查找所有申请通过的顶岗实习的成绩
     * @return
     */
    public List<Map<String,Object>> selectAllGrade(long stuWorkId, String workName, String studentName, String company){
        return stuWorkMapper.selectAllGrade(stuWorkId,workName,studentName,company);
    }
}
