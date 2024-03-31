package com.ruoyi.sau.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.sau.domain.Production;
import com.ruoyi.sau.mapper.ProdWeekMapper;
import com.ruoyi.sau.mapper.ProductionMapper;
import com.ruoyi.sau.mapper.StuProdMapper;
import com.ruoyi.sau.service.ProdAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
@Service
public class ProdAuditServiceImpl implements ProdAuditService {

    @Autowired
    private StuProdMapper stuProdMapper;

    @Autowired
    private ProdWeekMapper prodWeekMapper;

    @Autowired
    private ProductionMapper productionMapper;

    /**
     * 获取学生申请的生产实习信息
     * @param studentId 学生ID
     * @param studentName 学生姓名
     * @param productionId 生产实习ID
     * @param auditState 审批状态
     * @return
     */
    public List<Map<String,Object>> selectProdApply(long studentId, String studentName, long productionId, int auditState){
        return stuProdMapper.selectProdApply(studentId,studentName,productionId,auditState);
    }

    /**
     * 同意生产实习申请
     * @param stuProdId
     * @return
     */
    public AjaxResult agree(@RequestParam(value = "ids") String stuProdId){
        Long[] stuProdIds = Convert.toLongArray(stuProdId);
        for(Long id : stuProdIds){
            Map<String,Object> map = stuProdMapper.selectByStuProdId(id);
            if(Integer.parseInt(map.get("auditState").toString()) != 1){
                return AjaxResult.warn("请勿重复操作！");
            }
            Production production = productionMapper.selectProductionByStuProdId(id);
            int weekNums = Integer.parseInt(production.getProductionWeeks());
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            DecimalFormat df2 =new DecimalFormat("#0.00");
            Date start = new Date();
            Date end = new Date();
            try {
                start = df.parse(production.getProductionBegin());
                end = df.parse(production.getProductionEnd());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //周报间隔时间
            long  interval= (end.getTime() - start.getTime()) /  (1000L*3600L*24L) / weekNums;
            double worklyScore = 100.0 / (weekNums*1.0);
            for(int index = 1; index <= weekNums; index++){
                prodWeekMapper.addWorkly(Long.parseLong(map.get("stuProdId").toString()),
                        "第"+index+"次周报",
                        df.format(new Date(start.getTime()+(interval*index*1000L*3600L*24L))),
                        "",df2.format(worklyScore),"");
            }
            prodWeekMapper.addWorkly(Long.parseLong(map.get("stuProdId").toString()),
                    "总结",
                    df.format(new Date(end.getTime())),
                    "","100","");
        }
        int Count = stuProdMapper.agree(stuProdIds);
        if(Count > 0){
            return AjaxResult.success("操作成功");
        }
        return AjaxResult.warn("操作成功");
    }

    /**
     * 拒绝生产实习申请
     * @param stuProdId
     * @return
     */
    public AjaxResult reject(@RequestParam(value = "ids") String stuProdId){
        Long[] stuProdIds = Convert.toLongArray(stuProdId);
        for(Long id : stuProdIds){
            Map<String,Object> map = stuProdMapper.selectByStuProdId(id);
            if(Integer.parseInt(map.get("auditState").toString()) != 1){
                return AjaxResult.warn("请勿重复操作！");
            }
        }
        int Count = stuProdMapper.reject(stuProdIds);
        if(Count > 0){
            return AjaxResult.success("操作成功");
        }
        return AjaxResult.warn("操作成功");
    }

    /**
     * 根据教师评分录入计算后的成绩
     * @param pacificScore 周报成绩
     * @param examScore 总结成绩
     * @param score 总成绩
     */
    public void grade(long stuProdId,String pacificScore,String examScore,String score){
        stuProdMapper.grade(stuProdId,pacificScore,examScore,score);
    }
}
