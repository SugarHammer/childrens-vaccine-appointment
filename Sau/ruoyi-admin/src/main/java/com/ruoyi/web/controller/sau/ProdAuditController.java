package com.ruoyi.web.controller.sau;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.sau.service.ProdApplyService;
import com.ruoyi.sau.service.ProdAuditService;
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
@Api("教师端生产实习审批")
@RequestMapping("/sau/prodaudit")
public class ProdAuditController extends BaseController {

    private String prefix = "sau/production";

    @Autowired
    private ProdAuditService prodAuditService;


    @RequiresPermissions("system:prodaudit:view")
    @GetMapping()
    public String prodAudit(){
        return prefix + "/prodAudit";
    }


    @PostMapping("/selectProdApply")
    @RequiresPermissions("system:prodaudit:list")
    @ApiOperation(value = "查询生产实习申请")
    @ResponseBody
    public TableDataInfo ProdApply(@RequestParam(value = "studentId",defaultValue = "0") long studentId,
                                   @RequestParam(value = "studentName",defaultValue = "") String studentName,
                                   @RequestParam(value = "productionId",defaultValue = "0") long productionId,
                                   @RequestParam(value = "auditState",defaultValue = "0") int auditState){
        startPage();
        List<Map<String,Object>> list = prodAuditService.selectProdApply(studentId,studentName,productionId,auditState);
        return getDataTable(list);
    }

    @PostMapping("/agree")
    @RequiresPermissions("system:prodaudit:agree")
    @ApiOperation(value = "同意生产实习申请")
    @Transactional
    @ResponseBody
    public AjaxResult agree(@RequestParam(value = "ids") String stuProdId){
        return prodAuditService.agree(stuProdId);
    }

    @PostMapping("/reject")
    @RequiresPermissions("system:prodaudit:reject")
    @ApiOperation(value = "拒绝生产实习申请")
    @Transactional
    @ResponseBody
    public AjaxResult reject(@RequestParam(value = "ids") String stuProdId){
        return prodAuditService.reject(stuProdId);
    }
}
