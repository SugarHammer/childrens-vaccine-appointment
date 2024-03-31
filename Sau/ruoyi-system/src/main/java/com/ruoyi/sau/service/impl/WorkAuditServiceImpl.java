package com.ruoyi.sau.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.sau.domain.Production;
import com.ruoyi.sau.domain.StuAndWork;
import com.ruoyi.sau.domain.Work;
import com.ruoyi.sau.mapper.*;
import com.ruoyi.sau.service.ProdAuditService;
import com.ruoyi.sau.service.WorkAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Maxj
 */
@Service
public class WorkAuditServiceImpl implements WorkAuditService {

    @Autowired
    private StuWorkMapper stuWorkMapper;

    @Autowired
    private WorkMapper workMapper;

    @Autowired
    private WorkWeekMapper workWeekMapper;

    @Autowired
    private CourseReplaceMapper courseReplaceMapper;

    /**
     * 同意顶岗实习申请
     * @param stuWorkId
     * @return
     */
    public AjaxResult agree(@RequestParam(value = "ids") String stuWorkId){
        Long[] stuWorkIds = Convert.toLongArray(stuWorkId);
        for(Long id : stuWorkIds){
            Map<String,Object> map = stuWorkMapper.selectByStuWorkId(id);
            if(Integer.parseInt(map.get("auditState").toString()) != 1){
                return AjaxResult.warn("请勿重复操作！");
            }
            List<String> courseName = courseReplaceMapper.selectCourseByStuWorkId(id);
            Work work = workMapper.selectWorkByStuWorkId(id);
            int weekNums = Integer.parseInt(work.getWorkWeeks());
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            DecimalFormat df2 =new DecimalFormat("#0.00");
            Date start = new Date();
            Date end = new Date();
            try {
                start = df.parse(work.getWorkBegin());
                end = df.parse(work.getWorkEnd());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //周报间隔时间
            long  interval= (end.getTime() - start.getTime()) /  (1000L*3600L*24L) / weekNums;
            double worklyScore = 100.0 / (weekNums*1.0);
            for(int index = 1; index <= weekNums; index++){
                workWeekMapper.addWorkly(Long.parseLong(map.get("stuWorkId").toString()),
                        "第"+index+"次周报",
                        df.format(new Date(start.getTime()+(interval*index*1000L*3600L*24L))),
                        "",df2.format(worklyScore),"");
            }
            if(courseName.size() != 0){
                for(String name : courseName){
                    workWeekMapper.addWorkly(Long.parseLong(map.get("stuWorkId").toString()),
                            name + "总结",
                            df.format(new Date(end.getTime())),
                            "","100","");
                }
            }else{
                workWeekMapper.addWorkly(Long.parseLong(map.get("stuWorkId").toString()),
                        "总结",
                        df.format(new Date(end.getTime())),
                        "","100","");
            }
        }
        int Count = stuWorkMapper.agree(stuWorkIds);
        if(Count > 0){
            return AjaxResult.success("操作成功");
        }
        return AjaxResult.warn("操作成功");
    }


    /**
     * 拒绝顶岗实习申请
     * @param stuWorkId
     * @return
     */
    public AjaxResult reject(@RequestParam(value = "ids") String stuWorkId){
        Long[] stuWorkIds = Convert.toLongArray(stuWorkId);
        for(Long id : stuWorkIds){
            Map<String,Object> map = stuWorkMapper.selectByStuWorkId(id);
            if(Integer.parseInt(map.get("auditState").toString()) != 1){
                return AjaxResult.warn("请勿重复操作！");
            }
        }
        int Count = stuWorkMapper.reject(stuWorkIds);
        if(Count > 0){
            return AjaxResult.success("操作成功");
        }
        return AjaxResult.warn("操作成功");
    }

    /**
     * 获取学生申请的生产实习信息
     * @param studentId 学生ID
     * @param studentName 学生姓名
     * @param workId 顶岗实习ID
     * @param auditState 审批状态
     * @return
     */
    public List<Map<String,Object>> selectWorkApply(long studentId, String studentName, long workId, int auditState){
        return stuWorkMapper.selectWorkApply(studentId,studentName,workId,auditState);
    }


    /**
     *根据stuWorkId获取顶岗实习申请信息
     * @param stuWorkId
     * @return
     */
    public StuAndWork selectByStuWorkId(long stuWorkId){
        return stuWorkMapper.selectStuAndWorkByStuWorkId(stuWorkId);
    }

    /**
     * 根据stuWorkId查询置换课程名称
     */
    public List<String> selectCourseByStuWorkId(long stuWorkId){
        return courseReplaceMapper.selectCourseByStuWorkId(stuWorkId);
    }
}
