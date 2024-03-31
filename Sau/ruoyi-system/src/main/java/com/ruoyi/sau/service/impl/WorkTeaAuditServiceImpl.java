package com.ruoyi.sau.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.sau.domain.ProdWeek;
import com.ruoyi.sau.domain.Production;
import com.ruoyi.sau.domain.Work;
import com.ruoyi.sau.domain.WorkWeek;
import com.ruoyi.sau.mapper.CourseReplaceMapper;
import com.ruoyi.sau.mapper.StuWorkMapper;
import com.ruoyi.sau.mapper.WorkMapper;
import com.ruoyi.sau.mapper.WorkWeekMapper;
import com.ruoyi.sau.service.WorkTeaAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
@Service
public class WorkTeaAuditServiceImpl implements WorkTeaAuditService {

    @Autowired
    private WorkWeekMapper workWeekMapper;

    @Autowired
    private StuWorkMapper stuWorkMapper;

    @Autowired
    private WorkMapper workMapper;

    @Autowired
    private CourseReplaceMapper courseReplaceMapper;

    /**
     * 获取全部顶岗实习周报信息
     * @param workId
     * @param studentId
     * @param worklyCategory
     * @return
     */
    public List<Map<String,Object>> selectWeeks(long workId, long studentId, String worklyCategory){
        return workWeekMapper.selectWeeks(workId,studentId,worklyCategory);
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
        WorkWeek workWeek = workWeekMapper.selectWeekById(Long.parseLong(map.get("worklyId").toString()));
        BigDecimal oldGrade;
        if(workWeek.getWorklyActScore() == null || workWeek.getWorklyActScore() == ""){
            oldGrade = new BigDecimal("0");
        }
        else{
            oldGrade = new BigDecimal(workWeek.getWorklyActScore());
        }
        int Count = workWeekMapper.updateWeeklyByTea(map);
        if(Count > 0){
            Map<String,Object> stuAndWork = stuWorkMapper.selectByStuWorkId(Long.parseLong(map.get("stuWorkId").toString()));
            Work work = workMapper.selectWorkByStuWorkId(Long.parseLong(map.get("stuWorkId").toString()));
            List<String> list = courseReplaceMapper.selectCourseByStuWorkId(Long.parseLong(map.get("stuWorkId").toString()));
            //周报成绩占比
            BigDecimal pacific = new BigDecimal(work.getPacific());
            //总结成绩占比
            BigDecimal exam = new BigDecimal(work.getExam());
            //周报成绩
            BigDecimal pacificScore;
            //总结成绩
            BigDecimal examScore;
            //总成绩
            BigDecimal score;
            //置换课程数量
            BigDecimal courseCount = new BigDecimal(list.size()*1.00);
            if(stuAndWork.get("pacificScore") == null || stuAndWork.get("pacificScore") == ""){
                pacificScore = new BigDecimal(0);
            }else{
                pacificScore = new BigDecimal(stuAndWork.get("pacificScore").toString());
            }
            if(stuAndWork.get("examScore") == null || stuAndWork.get("examScore") == ""){
                examScore = new BigDecimal(0);
            }else{
                examScore = new BigDecimal(stuAndWork.get("examScore").toString());
            }
            if(stuAndWork.get("score") == null || stuAndWork.get("score") == ""){
                score = new BigDecimal(0);
            }else{
                score = new BigDecimal(stuAndWork.get("score").toString());
            }
            if(map.get("worklyCategory").toString().endsWith("总结")){
                if(list.size() == 0){
                    examScore = examScore.subtract(oldGrade).add(new BigDecimal(map.get("worklyActScore").toString()));
                }else{
                    examScore = examScore.add((new BigDecimal(map.get("worklyActScore").toString()).subtract(oldGrade)).divide(courseCount,2,BigDecimal.ROUND_HALF_UP));
                }
            }else{
                pacificScore = pacificScore.subtract(oldGrade).add(new BigDecimal(map.get("worklyActScore").toString()));
            }
            score = pacificScore.multiply(pacific.divide(new BigDecimal("100.00"),2,BigDecimal.ROUND_HALF_UP))
                    .add(examScore.multiply(exam.divide(new BigDecimal("100.00"),2,BigDecimal.ROUND_HALF_UP)));
            //修改周报成绩、总结成绩、总成绩
            stuWorkMapper.grade(Long.parseLong(map.get("stuWorkId").toString()),
                    pacificScore.toString(),examScore.toString(),score.toString());
            return AjaxResult.success("成绩录入成功");
        }
        return AjaxResult.warn("成绩录入失败！");
    }
}
