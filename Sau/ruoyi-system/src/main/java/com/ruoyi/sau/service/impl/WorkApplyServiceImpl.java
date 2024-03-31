package com.ruoyi.sau.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.sau.mapper.CourseReplaceMapper;
import com.ruoyi.sau.mapper.StuWorkMapper;
import com.ruoyi.sau.mapper.WorkMapper;
import com.ruoyi.sau.service.WorkApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Maxj
 */
@Service
public class WorkApplyServiceImpl implements WorkApplyService {

    @Autowired
    private WorkMapper workMapper;

    @Autowired
    private StuWorkMapper stuWorkMapper;

    @Autowired
    private CourseReplaceMapper courseReplaceMapper;

    /**
     * 添加学生申请顶岗实习信息
     * @param userId
     * @return
     */
    public AjaxResult addWorkApply(long userId, HashMap<String,Object> map){
        ArrayList<Long> selectWorks = workMapper.getWorkByStuId(userId);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Long workId = Long.parseLong(map.get("workId").toString());
        String[] courses = map.get("courses").toString().split(",");
        long stuWorkId = stuWorkMapper.getId();
        Date worklyOver = new Date();
        Date today = new Date();
        Date beginTime = new Date();
        Date endTime = new Date();
        Date time1 = new Date();
        Date time2 = new Date();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        int year = calendar.get(Calendar.YEAR);
        for(Long num : selectWorks){
            if(num.longValue() == workId){
                return AjaxResult.warn("请不要重复申请！");
            }
        }
        try {
            worklyOver = df.parse(workMapper.selectWorkById(workId).getWorkOver());
            beginTime = df.parse(map.get("begining").toString());
            endTime = df.parse(map.get("ending").toString());
            time1 = df.parse(map.get("workBegin").toString());
            time2 = df.parse(map.get("workEnd").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(worklyOver.before(today)) {
            return AjaxResult.warn("超时无法申请！");
        }
        if(endTime.getTime() - beginTime.getTime() < 1000L*3600L*24L*70L){
            return AjaxResult.warn("外出顶岗实习天数不足！");
        }
        if(beginTime.getTime() - time1.getTime() > 0){
            return AjaxResult.warn("外出顶岗实习开始日期需早于" + map.get("workBegin"));
        }
        if(time2.getTime() - endTime.getTime() > 0){
            return AjaxResult.warn("外出顶岗实习结束日期需晚于" + map.get("workEnd"));
        }
        map.put("userId",userId);
        map.put("stuWorkId",stuWorkId);
        stuWorkMapper.addWorkApply(map);
        for(String course : courses){
            courseReplaceMapper.addCourseReplace(stuWorkId,course);
        }
        return AjaxResult.success("申请成功");
    }
}
