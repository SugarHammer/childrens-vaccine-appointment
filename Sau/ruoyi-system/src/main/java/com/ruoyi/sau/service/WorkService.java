package com.ruoyi.sau.service;

import com.ruoyi.sau.domain.Production;
import com.ruoyi.sau.domain.Work;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
public interface WorkService {

    /**
     * 查找顶岗实习信息
     * @return
     */
    List<Map<String,Object>> selectWork(long workId,String workName);

    /**
     *根据ID查询顶岗实习信息
     * @param workId
     * @return
     */
    Work selectWorkById(long workId);

    /**
     * 发布顶岗实习信息
     * @param work
     * @return
     */
    int addWork(Work work);

    /**
     * 更新顶岗实习信息
     * @param map
     * @return
     */
    int updateWork(HashMap<String,Object> map);

    /**
     * 删除顶岗实习信息
     * @return
     */
    void deleteWork(String workId);
}
