package com.ruoyi.web.controller.sau;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.sau.service.MyApplyService;
import com.ruoyi.sau.service.MyWorkGradeService;
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
@RequestMapping("/sau/myworkgrade")
public class MyWorkGradeController extends BaseController {

    private String prefix = "sau/work";

    @Autowired
    private MyWorkGradeService myWorkGradeService;

    @Autowired
    private WorkAuditService workAuditService;

    @RequiresPermissions("system:myworkgrade:view")
    @GetMapping()
    public String myGrade(){
        return prefix + "/myGrade";
    }

    /**
     * 查看顶岗实习信息详情
     */
    @GetMapping("/see/{stuWorkId}")
    public String edit(@PathVariable("stuWorkId") long stuWorkId, ModelMap mmap)
    {
        mmap.put("stuAndWork", workAuditService.selectByStuWorkId(stuWorkId));
        mmap.put("courses", workAuditService.selectCourseByStuWorkId(stuWorkId));
        return prefix + "/myGradeDetails";
    }

    @PostMapping("/selectMyGrade")
    @RequiresPermissions("system:myworkgrade:list")
    @ApiOperation(value = "根据学生ID查找该用户生产实习信息")
    @ResponseBody
    public TableDataInfo selectMyGrade(@RequestParam(value = "workId",defaultValue = "0") long workId,
                                       @RequestParam(value = "workName",defaultValue = "") String workName,
                                       @RequestParam(value = "company",defaultValue = "") String company){
        startPage();
        long stuId = ShiroUtils.getUserId();
        List<Map<String,Object>> list = myWorkGradeService.selectMyGrade(stuId,workId,workName,company);
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
