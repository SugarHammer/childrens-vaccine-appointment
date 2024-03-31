package com.ruoyi.sau.service;

import com.ruoyi.sau.domain.Production;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
public interface ProductionService {

    /**
     * 查找生产实习信息
     * @return
     */
    List<Map<String,Object>> selectProduction(long productionId,String productionName,String productionCompany);

    /**
     *根据ID查询生产实习信息
     * @param productionId
     * @return
     */
    Production selectProductionById(long productionId);

    /**
     * 发布生产实习信息
     * @param production
     * @return
     */
    int addProduction(Production production);

    /**
     * 更新生产实习信息
     * @param map
     * @return
     */
    int updateProduction(HashMap<String,Object> map);

    /**
     * 删除生产实习信息
     * @return
     */
    void deleteProduction(String productionId);


}
