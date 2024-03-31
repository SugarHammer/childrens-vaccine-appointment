package com.ruoyi.web.controller.sau;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.sau.domain.Production;
import com.ruoyi.sau.service.ProductionService;
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
@Api("生产实习信息管理")
@RequestMapping("/sau/production")
public class ProductionController extends BaseController {

    private String prefix = "sau/production";

    @Autowired
    private ProductionService productionService;


    @RequiresPermissions("system:production:view")
    @GetMapping()
    public String production(){
        return prefix + "/production";
    }

    /**
     * 发布生产实习信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 发布生产实习信息
     * @return
     */
    @PostMapping("/addProduction")
    @Transactional
    @RequiresPermissions("system:production:add")
    @ApiOperation(value = "发布生产实习信息")
    @ResponseBody
    public AjaxResult addProduction(Production production) {
        if(!production.getProductionWeeks().matches("-?[0-9]+")){
            return AjaxResult.warn("输入的周报数量不是整数或不是数字");
        }
        if(!production.getPacific().matches("-?[0-9]+.?[0-9]*")){
            return AjaxResult.warn("输入的周报成绩占比不是数字");
        }
        if(!production.getExam().matches("-?[0-9]+.?[0-9]*")){
            return AjaxResult.warn("输入的总结成绩占比不是数字");
        }
        double pacific = Double.parseDouble(production.getPacific());
        double exam = Double.parseDouble(production.getExam());
        if(pacific< 0 || pacific > 100){
            return AjaxResult.warn("请输入的周报成绩占比在（0~100）之间");
        }
        if(exam < 0 || exam > 100){
            return AjaxResult.warn("请输入的总结成绩占比在（0~100）之间");
        }
        if(pacific + exam != 100){
            return AjaxResult.warn("周报成绩+总结成绩占比不等于100%");
        }
        int Count = productionService.addProduction(production);
        if(Count > 0){
            return AjaxResult.success("发布生产实习信息成功");
        }
        return AjaxResult.warn("发布生产实习信息失败");
    }

    /**
     * 修改生产实习信息
     */
    @GetMapping("/edit/{productionId}")
    public String edit(@PathVariable("productionId") long productionId, ModelMap mmap)
    {
        mmap.put("production", productionService.selectProductionById(productionId));
        return prefix + "/edit";
    }

    @PostMapping("/updateProduction")
    @RequiresPermissions("system:production:edit")
    @ApiOperation(value = "修改生产实习信息")
    @Transactional
    @ResponseBody
    public AjaxResult updateProduction(@RequestParam HashMap<String,Object> map){
        if(!map.get("productionWeeks").toString().matches("-?[0-9]+")){
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
        int Count = productionService.updateProduction(map);
        if(Count > 0){
            return AjaxResult.success("修改生产实习信息成功");
        }
        return AjaxResult.warn("修改生产实习信息失败");
    }

    @PostMapping("/selectProduction")
    @RequiresPermissions("system:production:list")
    @ApiOperation(value = "查找生产实习信息")
    @Transactional
    @ResponseBody
    public TableDataInfo selectProduction(@RequestParam(value = "productionId",defaultValue = "0") long productionId,
                                          @RequestParam(value = "productionName",defaultValue = "") String productionName,
                                          @RequestParam(value = "productionCompany",defaultValue = "") String productionCompany){
        startPage();
        List<Map<String,Object>> list = productionService.selectProduction(productionId,productionName,productionCompany);
        return getDataTable(list);
    }

    @PostMapping("/deleteProduction")
    @RequiresPermissions("system:production:remove")
    @ApiOperation(value = "删除生产实习信息")
    @Transactional
    @ResponseBody
    public AjaxResult deleteProduction(@RequestParam(value = "ids") String productionId){

        productionService.deleteProduction(productionId);
        return AjaxResult.success("删除生产实习信息成功");
    }
}
