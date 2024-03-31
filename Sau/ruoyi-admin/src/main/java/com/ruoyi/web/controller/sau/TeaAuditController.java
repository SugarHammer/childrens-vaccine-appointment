package com.ruoyi.web.controller.sau;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.sau.service.MyProdService;
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
@Api("教师端生产实习过程管理")
@RequestMapping("/sau/teaaudit")
public class TeaAuditController extends BaseController {

    private String prefix = "sau/production";

    @Autowired
    private MyProdService myProdService;


    @RequiresPermissions("system:teaaudit:view")
    @GetMapping()
    public String myProd(){
        return prefix + "/teaAudit";
    }


    @GetMapping("/edit/{worklyId}")
    public String edit(@PathVariable("worklyId") long worklyId, ModelMap mmap)
    {
        mmap.put("workly", myProdService.selectWeekById(worklyId));
        return prefix + "/teaEdit";
    }

    @PostMapping("/updateWeeklyByTea")
    @RequiresPermissions("system:teaaudit:edit")
    @ApiOperation(value = "修改分数或教师评语")
    @Transactional
    @ResponseBody
    public AjaxResult updateWeeklyByTea(@RequestParam HashMap<String,Object> map){
        return myProdService.updateWeeklyByTea(map);
    }


    @PostMapping("/selectWeeks")
    @RequiresPermissions("system:teaaudit:list")
    @ApiOperation(value = "查找所有生产实习周报")
    @ResponseBody
    public TableDataInfo selectWeeks(@RequestParam(value = "productionId",defaultValue = "0") long productionId,
                                     @RequestParam(value = "studentId",defaultValue = "0") long studentId,
                                     @RequestParam(value = "worklyCategory",defaultValue = "") String worklyCategory){
        startPage();
        List<Map<String,Object>> list = myProdService.selectWeeks(productionId,studentId,worklyCategory);
        return getDataTable(list);
    }
}
