package com.ruoyi.web.controller.sau;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.sau.service.MyApplyService;
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
@Api("学生端我的生产实习")
@RequestMapping("/sau/myprod")
public class MyProdController extends BaseController {

    private String prefix = "sau/production";

    @Autowired
    private MyProdService myProdService;


    @RequiresPermissions("system:myprod:view")
    @GetMapping()
    public String myProd(){
        return prefix + "/myProd";
    }

    /**
     * 修改生产实习信息
     */
    @GetMapping("/edit/{worklyId}")
    public String edit(@PathVariable("worklyId") long worklyId, ModelMap mmap)
    {
        mmap.put("workly", myProdService.selectWeekById(worklyId));
        return prefix + "/weekEdit";
    }

    @PostMapping("/updateWeekly")
    @RequiresPermissions("system:myprod:edit")
    @ApiOperation(value = "修改周报内容")
    @Transactional
    @ResponseBody
    public AjaxResult updateWeekly(@RequestParam HashMap<String,Object> map){
        return myProdService.updateWeekly(map);
    }


    @PostMapping("/selectWeekBystuId")
    @RequiresPermissions("system:myprod:list")
    @ApiOperation(value = "根据学生ID查找该用户生产实习周报")
    @ResponseBody
    public TableDataInfo selectWeekBystuId(@RequestParam(value = "worklyId",defaultValue = "0") long worklyId,
                                           @RequestParam(value = "productionId",defaultValue = "0") long productionId,
                                           @RequestParam(value = "worklyCategory",defaultValue = "") String worklyCategory){
        startPage();
        long stuId = ShiroUtils.getUserId();
        List<Map<String,Object>> list = myProdService.selectWeekBystuId(stuId,worklyId,productionId,worklyCategory);
        return getDataTable(list);
    }
}
