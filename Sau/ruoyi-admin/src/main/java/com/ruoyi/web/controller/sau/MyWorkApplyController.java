package com.ruoyi.web.controller.sau;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.sau.service.MyWorkApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * @author Maxj
 */
@Controller()
@Api("学生端我的申请")
@RequestMapping("/sau/myworkapply")
public class MyWorkApplyController extends BaseController {

    private String prefix = "sau/work";

    @Autowired
    private MyWorkApplyService myWorkApplyService;


    @RequiresPermissions("system:myworkapply:view")
    @GetMapping()
    public String workApply(){
        return prefix + "/myWorkApply";
    }


    @PostMapping("/selectApplyBystudentId")
    @RequiresPermissions("system:myworkapply:list")
    @ApiOperation(value = "根据学生ID查找该用户申请的顶岗实习信息")
    @ResponseBody
    public TableDataInfo selectApplyBystudentId(@RequestParam(value = "stuWorkId",defaultValue = "0") long stuWorkId,
                                                @RequestParam(value = "workName",defaultValue = "") String workName,
                                                @RequestParam(value = "company",defaultValue = "") String company){
        startPage();
        long stuId = ShiroUtils.getUserId();
        List<Map<String,Object>> list = myWorkApplyService.selectApplyBystudentId(stuId,stuWorkId,workName,company);
        return getDataTable(list);
    }

    @PostMapping("/deleteById")
    @RequiresPermissions("system:myworkapply:remove")
    @ApiOperation(value = "学生退选顶岗实习")
    @ResponseBody
    public AjaxResult deleteById(@RequestParam(value = "ids") long stuWorkId){
        int Count = myWorkApplyService.deleteById(stuWorkId);
        if(Count > 0){
            return AjaxResult.success("退选成功");
        }
        return AjaxResult.warn("退选失败");
    }
}
