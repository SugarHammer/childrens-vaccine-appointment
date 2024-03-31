package com.ruoyi.web.controller.sau;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.sau.service.MyApplyService;
import com.ruoyi.sau.service.ProdApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * @author Maxj
 */
@Controller()
@Api("学生端我的申请")
@RequestMapping("/sau/myprodapply")
public class MyProdApplyController extends BaseController {

    private String prefix = "sau/production";

    @Autowired
    private MyApplyService myApplyService;


    @RequiresPermissions("system:myprodapply:view")
    @GetMapping()
    public String prodApply(){
        return prefix + "/myProdApply";
    }


    @PostMapping("/selectProdBystuId")
    @RequiresPermissions("system:myprodapply:list")
    @ApiOperation(value = "根据学生ID查找该用户申请的生产实习信息")
    @ResponseBody
    public TableDataInfo selectProdBystuId(@RequestParam(value = "productionId",defaultValue = "0") long productionId,
                                           @RequestParam(value = "productionName",defaultValue = "") String productionName,
                                           @RequestParam(value = "productionCompany",defaultValue = "") String productionCompany){
        startPage();
        long stuId = ShiroUtils.getUserId();
        List<Map<String,Object>> list = myApplyService.selectProdBystuId(stuId,productionId,productionName,productionCompany);
        return getDataTable(list);
    }

    @PostMapping("/deleteById")
    @RequiresPermissions("system:myprodapply:remove")
    @ApiOperation(value = "学生退选生产实习")
    @Transactional
    @ResponseBody
    public AjaxResult deleteById(@RequestParam(value = "ids") long stuProdId){
        int Count = myApplyService.deleteById(stuProdId);
        if(Count > 0){
            return AjaxResult.success("退选成功");
        }
        return AjaxResult.warn("退选失败");
    }
}
