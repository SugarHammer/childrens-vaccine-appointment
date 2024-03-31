package com.ruoyi.web.controller.sau;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.sau.domain.Work;
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
import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
@Controller()
@Api("顶岗实习信息管理")
@RequestMapping("/sau/work")
public class WorkController extends BaseController {

    private String prefix = "sau/work";

    @Autowired
    private WorkService workService;

    @RequiresPermissions("system:work:view")
    @GetMapping()
    public String production(){
        return prefix + "/work";
    }

    @PostMapping("/selectWork")
    @RequiresPermissions("system:work:list")
    @ApiOperation(value = "查找顶岗实习信息")
    @Transactional
    @ResponseBody
    public TableDataInfo selectWork(@RequestParam(value = "workId",defaultValue = "0")long workId,
                                    @RequestParam(value = "workName",defaultValue = "")String workName){
        startPage();
        List<Map<String,Object>> list = workService.selectWork(workId,workName);
        return getDataTable(list);
    }

    /**
     * 发布顶岗实习信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 发布顶岗实习信息
     * @return
     */
    @PostMapping("/addWork")
    @Transactional
    @RequiresPermissions("system:work:add")
    @ApiOperation(value = "发布顶岗实习信息")
    @ResponseBody
    public AjaxResult addWork(Work work) {
        if(!work.getWorkWeeks().matches("-?[0-9]+")){
            return AjaxResult.warn("输入的周报数量不是整数或不是数字");
        }
        if(!work.getPacific().matches("-?[0-9]+.?[0-9]*")){
            return AjaxResult.warn("输入的周报成绩占比不是数字");
        }
        if(!work.getExam().matches("-?[0-9]+.?[0-9]*")){
            return AjaxResult.warn("输入的总结成绩占比不是数字");
        }
        double pacific = Double.parseDouble(work.getPacific());
        double exam = Double.parseDouble(work.getExam());
        if(pacific< 0 || pacific > 100){
            return AjaxResult.warn("请输入的周报成绩占比在（0~100）之间");
        }
        if(exam < 0 || exam > 100){
            return AjaxResult.warn("请输入的总结成绩占比在（0~100）之间");
        }
        if(pacific + exam != 100){
            return AjaxResult.warn("周报成绩+总结成绩占比不等于100%");
        }
        int Count = workService.addWork(work);
        if(Count > 0){
            return AjaxResult.success("发布顶岗实习信息成功");
        }
        return AjaxResult.warn("发布顶岗实习信息失败");
    }

    /**
     * 修改顶岗实习信息
     */
    @GetMapping("/edit/{workId}")
    public String edit(@PathVariable("workId") long workId, ModelMap mmap)
    {
        mmap.put("work", workService.selectWorkById(workId));
        return prefix + "/edit";
    }

    @PostMapping("/updateWork")
    @RequiresPermissions("system:work:edit")
    @ApiOperation(value = "修改顶岗实习信息")
    @Transactional
    @ResponseBody
    public AjaxResult updateWork(@RequestParam HashMap<String,Object> map){
        if(!map.get("workWeeks").toString().matches("-?[0-9]+")){
            return AjaxResult.warn("输入的周报数量不是整数或不是数字");
        }
        if(!map.get("pacific").toString().matches("-?[0-9]+.?[0-9]*")){
            return AjaxResult.warn("输入的周报成绩占比不是数字");
        }
        if(!map.get("exam").toString().matches("-?[0-9]+.?[0-9]*")){
            return AjaxResult.warn("输入的总结成绩占比不是数字");
        }
        double pacific = Double.parseDouble(map.get("pacific").toString());
        double exam = Double.parseDouble(map.get("exam").toString());
        if(pacific< 0 || pacific > 100){
            return AjaxResult.warn("请输入的周报成绩占比在（0~100）之间");
        }
        if(exam < 0 || exam > 100){
            return AjaxResult.warn("请输入的总结成绩占比在（0~100）之间");
        }
        if(pacific + exam != 100){
            return AjaxResult.warn("周报成绩+总结成绩占比不等于100%");
        }
        int Count = workService.updateWork(map);
        if(Count > 0){
            return AjaxResult.success("修改顶岗实习信息成功");
        }
        return AjaxResult.warn("修改顶岗实习信息失败");
    }

    @PostMapping("/deleteWork")
    @RequiresPermissions("system:work:remove")
    @ApiOperation(value = "删除顶岗实习信息")
    @Transactional
    @ResponseBody
    public AjaxResult deleteWork(@RequestParam(value = "ids") String workId){

        workService.deleteWork(workId);
        return AjaxResult.success("删除顶岗实习信息成功");
    }
}
