package com.ruoyi.web.controller.sau;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.framework.web.domain.server.Sys;
import com.ruoyi.sau.service.ProdApplyService;
import com.ruoyi.sau.service.WorkApplyService;
import com.ruoyi.sau.service.WorkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Maxj
 */
@Controller()
@Api("学生端顶岗实习申请")
@RequestMapping("/sau/workapply")
public class WorkApplyController extends BaseController {

    private String prefix = "sau/work";

    @Autowired
    private WorkApplyService workApplyService;

    @Autowired
    private WorkService workService;


    @RequiresPermissions("system:workapply:view")
    @GetMapping()
    public String prodApply(){
        return prefix + "/workApply";
    }

    @GetMapping("/add/{workId}")
    public String add(@PathVariable("workId") long workId, ModelMap mmap){
        mmap.put("work", workService.selectWorkById(workId));
        return prefix + "/addApply";
    }

    @PostMapping("/addWorkApply")
    @RequiresPermissions("system:workapply:add")
    @ApiOperation(value = "申请顶岗实习")
    @Transactional
    @ResponseBody
    public AjaxResult addWorkApply(@RequestParam HashMap<String,Object> map){
        long userId = ShiroUtils.getUserId();
        return workApplyService.addWorkApply(userId,map);
    }
}
