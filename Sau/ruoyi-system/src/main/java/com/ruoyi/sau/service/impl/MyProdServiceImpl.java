package com.ruoyi.sau.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.sau.domain.ProdWeek;
import com.ruoyi.sau.domain.Production;
import com.ruoyi.sau.mapper.ProdWeekMapper;
import com.ruoyi.sau.mapper.ProductionMapper;
import com.ruoyi.sau.mapper.StuProdMapper;
import com.ruoyi.sau.service.MyProdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
@Service
public class MyProdServiceImpl implements MyProdService {

    @Autowired
    private ProdWeekMapper prodWeekMapper;

    @Autowired
    private StuProdMapper stuProdMapper;

    @Autowired
    private ProductionMapper productionMapper;

    /**
     * 根据学生ID获取周报信息
     * @param stuId
     * @return
     */
    public List<Map<String,Object>> selectWeekBystuId(long stuId,long worklyId,long productionId,String worklyCategory){
        return prodWeekMapper.selectWeekBystuId(stuId,worklyId,productionId,worklyCategory);
    }

    /**
     * 获取全部生产实习周报信息
     * @param productionId
     * @param studentId
     * @param worklyCategory
     * @return
     */
    public List<Map<String,Object>> selectWeeks(long productionId,long studentId,String worklyCategory){
        return prodWeekMapper.selectWeeks(productionId,studentId,worklyCategory);
    }

    /**
     * 根据周报ID获取周报信息
     * @param worklyId
     * @return
     */
    public ProdWeek selectWeekById(long worklyId){
        return prodWeekMapper.selectWeekById(worklyId);
    }

    /**
     * 更新周报信息
     * @param map
     * @return
     */
    public AjaxResult updateWeekly(HashMap<String,Object> map){
        ProdWeek prodWeek = prodWeekMapper.selectWeekById(Long.parseLong(map.get("worklyId").toString()));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date worklyOver = new Date();
        Date today = new Date();
        try {
            worklyOver = df.parse(prodWeek.getWorklyOver());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(worklyOver.before(today)){
            return AjaxResult.warn("超时无法修改！");
        }
        if(!(prodWeek.getWorklyActScore() == null || (prodWeek.getWorklyActScore() == ""))){
            return AjaxResult.warn("教师已经批阅，无法修改");
        }
        int Count = prodWeekMapper.updateWeekly(map);
        if(Count > 0){
            return AjaxResult.success("周报编辑完成");
        }
        return AjaxResult.warn("周报编辑失败！");
    }

    /**
     * 添加教师批阅学生周报信息
     * @param map
     * @return
     */
    public AjaxResult updateWeeklyByTea(HashMap<String,Object> map){
        if(!map.get("worklyActScore").toString().matches("-?[0-9]+.?[0-9]*")){
            return AjaxResult.warn("输入的成绩不是数字");
        }
        if(Double.parseDouble(map.get("worklyActScore").toString()) < 0 ||
                Double.parseDouble(map.get("worklyActScore").toString()) > Double.parseDouble(map.get("worklyScore").toString())){
            return AjaxResult.warn("请输入的成绩在（0~" + Double.parseDouble(map.get("worklyScore").toString()) + "）之间");
        }
        ProdWeek prodWeek = prodWeekMapper.selectWeekById(Long.parseLong(map.get("worklyId").toString()));
        BigDecimal oldGrade;
        if(prodWeek.getWorklyActScore() == null || prodWeek.getWorklyActScore() == ""){
            oldGrade = new BigDecimal("0");
        }
        else{
            oldGrade = new BigDecimal(prodWeek.getWorklyActScore());
        }
        int Count = prodWeekMapper.updateWeeklyByTea(map);
        if(Count > 0){
            Map<String,Object> stuAndProd = stuProdMapper.selectByStuProdId(Long.parseLong(map.get("stuProdId").toString()));
            Production production = productionMapper.selectProductionByStuProdId(Long.parseLong(map.get("stuProdId").toString()));
            //周报成绩占比
            BigDecimal pacific = new BigDecimal(production.getPacific());
            //总结成绩占比
            BigDecimal exam = new BigDecimal(production.getExam());
            //周报成绩
            BigDecimal pacificScore;
            //总结成绩
            BigDecimal examScore;
            //总成绩
            BigDecimal score;
            if(stuAndProd.get("pacificScore") == null || stuAndProd.get("pacificScore") == ""){
                pacificScore = new BigDecimal(0);
            }else{
                pacificScore = new BigDecimal(stuAndProd.get("pacificScore").toString());
            }
            if(stuAndProd.get("examScore") == null || stuAndProd.get("examScore") == ""){
                examScore = new BigDecimal(0);
            }else{
                examScore = new BigDecimal(stuAndProd.get("examScore").toString());
            }
            if(stuAndProd.get("score") == null || stuAndProd.get("score") == ""){
                score = new BigDecimal(0);
            }else{
                score = new BigDecimal(stuAndProd.get("score").toString());
            }
            if(map.get("worklyCategory").toString().equals("总结")){
                examScore = examScore.subtract(oldGrade).add(new BigDecimal(map.get("worklyActScore").toString()));
            }else{
                pacificScore = pacificScore.subtract(oldGrade).add(new BigDecimal(map.get("worklyActScore").toString()));
            }
            score = pacificScore.multiply(pacific.divide(new BigDecimal("100.00"),2,BigDecimal.ROUND_HALF_UP))
                    .add(examScore.multiply(exam.divide(new BigDecimal("100.00"),2,BigDecimal.ROUND_HALF_UP)));
            //修改周报成绩、总结成绩、总成绩
            stuProdMapper.grade(Long.parseLong(map.get("stuProdId").toString()),
                    pacificScore.toString(),examScore.toString(),score.toString());
            return AjaxResult.success("成绩录入成功");
        }
        return AjaxResult.warn("成绩录入失败！");
    }
}
