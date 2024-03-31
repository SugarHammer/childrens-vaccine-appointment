package com.ruoyi.web.controller.sau;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.sau.service.ProdApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


/**
 * @author Maxj
 */
@Controller()
@Api("学生端生产实习申请")
@RequestMapping("/sau/prodapply")
public class ProdApplyController extends BaseController {

    private String prefix = "sau/production";

    @Autowired
    private ProdApplyService prodApplyService;


    @RequiresPermissions("system:prodapply:view")
    @GetMapping()
    public String prodApply(){
        return prefix + "/prodApply";
    }


    @PostMapping("/addProdApply")
    @RequiresPermissions("system:prodapply:add")
    @ApiOperation(value = "申请生产实习")
    @ResponseBody
    public AjaxResult addProdApply(@RequestParam(value = "ids") String productionId){
        long userId = ShiroUtils.getUserId();
        return prodApplyService.addProdApply(productionId,userId);
    }
}
