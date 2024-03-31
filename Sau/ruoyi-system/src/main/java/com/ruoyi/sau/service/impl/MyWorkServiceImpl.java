package com.ruoyi.sau.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.sau.domain.ProdWeek;
import com.ruoyi.sau.domain.WorkWeek;
import com.ruoyi.sau.mapper.WorkWeekMapper;
import com.ruoyi.sau.service.MyWorkService;
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
public class MyWorkServiceImpl implements MyWorkService {

   @Autowired
   private WorkWeekMapper workWeekMapper;

    /**
     * 根据学生ID获取周报信息
     * @param stuId
     * @return
     */
    public List<Map<String,Object>> selectWeekBystuId(long stuId,long worklyId,long workId,String worklyCategory){
        return workWeekMapper.selectWeekBystuId(stuId,worklyId,workId,worklyCategory);
    }

    /**
     * 根据周报ID获取周报信息
     * @param worklyId
     * @return
     */
    public WorkWeek selectWeekById(long worklyId){
        return workWeekMapper.selectWeekById(worklyId);
    }

    /**
     * 更新周报信息
     * @param map
     * @return
     */
    public AjaxResult updateWeekly(HashMap<String,Object> map){
        WorkWeek workWeek = workWeekMapper.selectWeekById(Long.parseLong(map.get("worklyId").toString()));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date worklyOver = new Date();
        Date today = new Date();
        try {
            worklyOver = df.parse(workWeek.getWorklyOver());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(worklyOver.before(today)){
            return AjaxResult.warn("超时无法修改！");
        }
        if(!(workWeek.getWorklyActScore() == null || (workWeek.getWorklyActScore() == ""))){
            return AjaxResult.warn("教师已经批阅，无法修改");
        }
        int Count = workWeekMapper.updateWeekly(map);
        if(Count > 0){
            return AjaxResult.success("周报编辑完成");
        }
        return AjaxResult.warn("周报编辑失败！");
    }
}
