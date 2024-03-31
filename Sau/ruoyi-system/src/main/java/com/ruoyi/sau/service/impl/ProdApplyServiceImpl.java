package com.ruoyi.sau.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.sau.mapper.ProductionMapper;
import com.ruoyi.sau.mapper.StuProdMapper;
import com.ruoyi.sau.service.ProdApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Maxj
 */
@Service
public class ProdApplyServiceImpl implements ProdApplyService {

   @Autowired
   private StuProdMapper stuProdMapper;

    @Autowired
    private ProductionMapper productionMapper;

    /**
     * 添加学生申请生产实习信息
     * @param productionId
     * @return
     */
    public AjaxResult addProdApply(String productionId, long userId){
        ArrayList<Long> selectProductions = productionMapper.getProductionByStuId(userId);
        Long[] productionIds = Convert.toLongArray(productionId);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date worklyOver = new Date();
        Date today = new Date();
        for(Long id : productionIds){
            Boolean flag = false;
            for(Long num : selectProductions){
                if(num.longValue() == id.longValue()){
                    flag = true;
                    break;
                }
            }
            if(flag){
                return AjaxResult.warn("请不要重复申请！");
            }
            try {
                worklyOver = df.parse(productionMapper.selectProductionById(id).getProductionOver());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(worklyOver.before(today)) {
                return AjaxResult.warn("超时无法申请！");
            }
            stuProdMapper.addProdApply(id,userId);
        }
        return AjaxResult.success("申请成功");
    }
}
