package com.ruoyi.sau.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.sau.domain.Work;
import com.ruoyi.sau.mapper.CourseReplaceMapper;
import com.ruoyi.sau.mapper.WorkMapper;
import com.ruoyi.sau.mapper.WorkWeekMapper;
import com.ruoyi.sau.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    private WorkMapper workMapper;

    @Autowired
    private CourseReplaceMapper courseReplaceMapper;

    /**
     * 查找顶岗实习信息
     * @return
     */
    public List<Map<String,Object>> selectWork(long workId,String workName){
        return workMapper.selectWork(workId,workName);
    }

    /**
     *根据ID查询顶岗实习信息
     * @param workId
     * @return
     */
    public Work selectWorkById(long workId){
        return workMapper.selectWorkById(workId);
    }

    /**
     * 发布顶岗实习信息
     * @param work
     * @return
     */
    public int addWork(Work work){
        return workMapper.addWork(work);
    }

    /**
     * 更新顶岗实习信息
     * @param map
     * @return
     */
    public int updateWork(HashMap<String,Object> map){
        return workMapper.updateWork(map);
    }

    /**
     * 删除顶岗实习信息
     * @return
     */
    public void deleteWork(String workId){
        Long[] workIds = Convert.toLongArray(workId);
        courseReplaceMapper.deleteByWorkId(workIds);
        workMapper.deleteWork2(workIds);
        workMapper.deleteWork3(workIds);
        workMapper.deleteWork(workIds);
    }
}
