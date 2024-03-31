package com.ruoyi.sau.mapper;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.sau.domain.Production;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
@Mapper
public interface ProductionMapper {
    /**
     * 根据学生ID查询已经申请的顶岗实习信息
     * @param userId
     * @return
     */
    ArrayList<Long> getProductionByStuId(long userId);

    /**
     * 添加生产实习信息
     * @param production
     * @return
     */
    int addProduction(Production production);

    /**
     * 删除生产实习
     * @param productionIds
     */
    void deleteProduction(Long[] productionIds);

    /**
     * 删除与本生产实习相关的学生周报
     * @param productionIds
     */
    void deleteProduction2(Long[] productionIds);

    /**
     * 删除与本生产实习相关的学生申请
     * @param productionIds
     */
    void deleteProduction3(Long[] productionIds);

    /**
     * 删除生产实习信息
     * @param map
     * @return
     */
    int updateProduction(HashMap<String,Object> map);

    /**
     * 插叙生产实习信息
     * @param productionId
     * @param productionName
     * @param productionCompany
     * @return
     */
    List<Map<String,Object>> selectProduction(@Param(value = "productionId") long productionId,
                                              @Param(value = "productionName") String productionName,
                                              @Param(value = "productionCompany") String productionCompany);

    /**
     * 根据生产实习ID查询生产实习信息
     * @param productionId
     * @return
     */
    Production selectProductionById(@Param(value = "productionId") long productionId);

    /**
     * 根据stuProdId查询生产实习信息
     * @param stuProdId
     * @return
     */
    Production selectProductionByStuProdId(@Param("stuProdId") long stuProdId);

}
