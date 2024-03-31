package com.ruoyi.web.controller.sau;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.sau.service.MyApplyService;
import com.ruoyi.sau.service.MyProdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
@Controller
@Api("教师端生产实习成绩统计")
@RequestMapping("/sau/allgrade")
public class AllGradeController extends BaseController {

    private String prefix = "sau/production";

    @Autowired
    private MyApplyService myApplyService;

    @Autowired
    private MyProdService myProdService;

    @RequiresPermissions("system:allgrade:view")
    @GetMapping()
    public String allGrade(){
        return prefix + "/allGrade";
    }

    @PostMapping("/selectAllGrade")
    @RequiresPermissions("system:allgrade:list")
    @ApiOperation(value = "根据学生ID查找该用户生产实习信息")
    @ResponseBody
    public TableDataInfo selectAllGrade(@RequestParam(value = "stuProdId",defaultValue = "0") long stuProdId,
                                        @RequestParam(value = "productionName",defaultValue = "") String productionName,
                                        @RequestParam(value = "studentName",defaultValue = "") String studentName,
                                        @RequestParam(value = "productionCompany",defaultValue = "") String productionCompany){
        startPage();
        List<Map<String,Object>> list = myApplyService.selectAllGrade(stuProdId,productionName,studentName,productionCompany);
        for(Map map : list){
            if(map.get("score") == null){
                continue;
            }
            List<Map<String,Object>> li = myProdService.selectWeeks(Long.parseLong(map.get("productionId").toString()),Long.parseLong(map.get("studentId").toString()),"");
            boolean flag = false;
            for(Map<String,Object> m : li){
                if(m.get("worklyActScore") == null){
                    map.put("ownScore","无");
                    flag = true;
                    break;
                }
            }
            if(!flag){
                Double score = Double.parseDouble(map.get("score").toString());
                if(score < 60){
                    map.put("ownScore","不及格");
                }else if(score >=60 && score < 70){
                    map.put("ownScore","及格");
                }else if(score >=70 && score < 80){
                    map.put("ownScore","中等");
                }else if(score >=80 && score < 90){
                    map.put("ownScore","良好");
                }else {
                    map.put("ownScore","优秀");
                }
            }

        }
        return getDataTable(list);
    }
}
