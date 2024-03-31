package com.ruoyi.web.controller.sau;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.sau.service.ProdAuditService;
import com.ruoyi.sau.service.WorkAuditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * @author Maxj
 */
@Controller()
@Api("教师端顶岗实习审批")
@RequestMapping("/sau/workaudit")
public class WorkAuditController extends BaseController {

    private String prefix = "sau/work";

    @Autowired
    private WorkAuditService workAuditService;


    @RequiresPermissions("system:workaudit:view")
    @GetMapping()
    public String prodAudit(){
        return prefix + "/workAudit";
    }

    /**
     * 查看顶岗实习信息详情
     */
    @GetMapping("/see/{stuWorkId}")
    public String edit(@PathVariable("stuWorkId") long stuWorkId, ModelMap mmap)
    {
        mmap.put("stuAndWork", workAuditService.selectByStuWorkId(stuWorkId));
        mmap.put("courses", workAuditService.selectCourseByStuWorkId(stuWorkId));
        return prefix + "/workDetails";
    }


    @PostMapping("/selectWorkApply")
    @RequiresPermissions("system:workaudit:list")
    @ApiOperation(value = "查询顶岗实习申请")
    @ResponseBody
    public TableDataInfo selectProdApply(@RequestParam(value = "studentId",defaultValue = "0") long studentId,
                                        @RequestParam(value = "studentName",defaultValue = "") String studentName,
                                        @RequestParam(value = "workId",defaultValue = "0") long workId,
                                        @RequestParam(value = "auditState",defaultValue = "0") int auditState){
        startPage();
        List<Map<String,Object>> list = workAuditService.selectWorkApply(studentId,studentName,workId,auditState);
        return getDataTable(list);
    }

    @PostMapping("/agree")
    @RequiresPermissions("system:workaudit:agree")
    @ApiOperation(value = "同意顶岗实习申请")
    @Transactional
    @ResponseBody
    public AjaxResult agree(@RequestParam(value = "ids") String stuWorkId){
        return workAuditService.agree(stuWorkId);
    }

    @PostMapping("/reject")
    @RequiresPermissions("system:workaudit:reject")
    @ApiOperation(value = "拒绝顶岗实习申请")
    @Transactional
    @ResponseBody
    public AjaxResult reject(@RequestParam(value = "ids") String stuWorkId){
        return workAuditService.reject(stuWorkId);
    }
}
