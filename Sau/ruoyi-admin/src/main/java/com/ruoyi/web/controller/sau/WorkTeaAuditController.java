package com.ruoyi.web.controller.sau;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.sau.service.MyProdService;
import com.ruoyi.sau.service.WorkTeaAuditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Maxj
 */
@Controller()
@Api("教师端顶岗实习过程管理")
@RequestMapping("/sau/workteaaudit")
public class WorkTeaAuditController extends BaseController {

    private String prefix = "sau/work";

   @Autowired
   private WorkTeaAuditService workTeaAuditService;

    @RequiresPermissions("system:workteaaudit:view")
    @GetMapping()
    public String myProd(){
        return prefix + "/teaAudit";
    }


    @GetMapping("/edit/{worklyId}")
    public String edit(@PathVariable("worklyId") long worklyId, ModelMap mmap)
    {
        mmap.put("workly", workTeaAuditService.selectWeekById(worklyId));
        return prefix + "/teaEdit";
    }

    @PostMapping("/updateWeeklyByTea")
    @RequiresPermissions("system:workteaaudit:edit")
    @ApiOperation(value = "修改分数或教师评语")
    @Transactional
    @ResponseBody
    public AjaxResult updateWeeklyByTea(@RequestParam HashMap<String,Object> map){
        return workTeaAuditService.updateWeeklyByTea(map);
    }


    @PostMapping("/selectWeeks")
    @RequiresPermissions("system:workteaaudit:list")
    @ApiOperation(value = "查找所有顶岗实习周报")
    @ResponseBody
    public TableDataInfo selectWeeks(@RequestParam(value = "workId",defaultValue = "0") long workId,
                                     @RequestParam(value = "studentId",defaultValue = "0") long studentId,
                                     @RequestParam(value = "worklyCategory",defaultValue = "") String worklyCategory){
        startPage();
        List<Map<String,Object>> list = workTeaAuditService.selectWeeks(workId,studentId,worklyCategory);
        return getDataTable(list);
    }
}
