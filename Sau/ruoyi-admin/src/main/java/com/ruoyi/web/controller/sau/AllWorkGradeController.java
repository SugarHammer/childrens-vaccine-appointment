package com.ruoyi.web.controller.sau;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.sau.service.AllWorkGradeService;
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
@Api("教师端顶岗实习成绩统计")
@RequestMapping("/sau/allworkgrade")
public class AllWorkGradeController extends BaseController {

    private String prefix = "sau/work";

    @Autowired
    private AllWorkGradeService allWorkGradeService;



    @RequiresPermissions("system:allworkgrade:view")
    @GetMapping()
    public String allGrade(){
        return prefix + "/allGrade";
    }

    @PostMapping("/selectAllGrade")
    @RequiresPermissions("system:allworkgrade:list")
    @ApiOperation(value = "根据学生ID查找该用户顶岗实习信息")
    @ResponseBody
    public TableDataInfo selectAllGrade(@RequestParam(value = "stuWorkId",defaultValue = "0") long stuWorkId,
                                        @RequestParam(value = "workName",defaultValue = "") String workName,
                                        @RequestParam(value = "studentName",defaultValue = "") String studentName,
                                        @RequestParam(value = "company",defaultValue = "") String company){
        startPage();
        List<Map<String,Object>> list = allWorkGradeService.selectAllGrade(stuWorkId,workName,studentName,company);
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
