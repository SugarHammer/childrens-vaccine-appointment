package com.ruoyi.web.controller.sau;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.sau.service.MyApplyService;
import com.ruoyi.sau.service.WorkAuditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
@Controller
@Api("学生端我的成绩")
@RequestMapping("/sau/mygrade")
public class MyGradeController extends BaseController {

    private String prefix = "sau/production";

    @Autowired
    private MyApplyService myApplyService;

    @RequiresPermissions("system:mygrade:view")
    @GetMapping()
    public String myGrade(){
        return prefix + "/myGrade";
    }


    @PostMapping("/selectMyGrade")
    @RequiresPermissions("system:mygrade:list")
    @ApiOperation(value = "根据学生ID查找该用户生产实习信息")
    @ResponseBody
    public TableDataInfo selectMyGrade(@RequestParam(value = "productionId",defaultValue = "0") long productionId,
                                       @RequestParam(value = "productionName",defaultValue = "") String productionName,
                                       @RequestParam(value = "productionCompany",defaultValue = "") String productionCompany){
        startPage();
        long stuId = ShiroUtils.getUserId();
        List<Map<String,Object>> list = myApplyService.selectMyGrade(stuId,productionId,productionName,productionCompany);
        for(Map map : list){
            if(map.get("score") == null){
                continue;
            }
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
        return getDataTable(list);
    }
}
