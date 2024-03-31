package com.ruoyi.sau.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.sau.domain.Production;
import com.ruoyi.sau.mapper.ProductionMapper;
import com.ruoyi.sau.service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
@Service
public class ProductionServiceImpl implements ProductionService {

    @Autowired
    private ProductionMapper productionMapper;

    /**
     * 查找生产实习信息
     * @return
     */
    public List<Map<String,Object>> selectProduction(long productionId,String productionName,String productionCompany){
        return productionMapper.selectProduction(productionId,productionName,productionCompany);
    }

    /**
     *根据ID查询生产实习信息
     * @param productionId
     * @return
     */
    public Production selectProductionById(long productionId){
        return productionMapper.selectProductionById(productionId);
    }

    /**
     * 发布生产实习信息
     * @param production
     * @return
     */
    public int addProduction(Production production){
        return productionMapper.addProduction(production);
    }

    /**
     * 更新生产实习信息
     * @param map
     * @return
     */
    public int updateProduction(HashMap<String,Object> map){
        return productionMapper.updateProduction(map);
    }

    /**
     * 删除生产实习信息
     * @return
     */
    public void deleteProduction(String productionId){
        Long[] productionIds = Convert.toLongArray(productionId);
        productionMapper.deleteProduction2(productionIds);
        productionMapper.deleteProduction3(productionIds);
        productionMapper.deleteProduction(productionIds);
    }
}
