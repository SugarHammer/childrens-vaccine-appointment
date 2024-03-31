package com.ruoyi.web.controller.sau;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.sau.service.MyProdService;
import com.ruoyi.sau.service.MyWorkService;
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
@Api("学生端我的顶岗实习")
@RequestMapping("/sau/mywork")
public class MyWorkController extends BaseController {

    private String prefix = "sau/work";

    @Autowired
    private MyWorkService myWorkService;


    @RequiresPermissions("system:mywork:view")
    @GetMapping()
    public String myProd(){
        return prefix + "/myWork";
    }

    /**
     * 修改生产实习信息
     */
    @GetMapping("/edit/{worklyId}")
    public String edit(@PathVariable("worklyId") long worklyId, ModelMap mmap)
    {
        mmap.put("workly", myWorkService.selectWeekById(worklyId));
        return prefix + "/weekEdit";
    }

    @PostMapping("/updateWeekly")
    @RequiresPermissions("system:mywork:edit")
    @ApiOperation(value = "修改周报内容")
    @Transactional
    @ResponseBody
    public AjaxResult updateWeekly(@RequestParam HashMap<String,Object> map){
        return myWorkService.updateWeekly(map);
    }


    @PostMapping("/selectWeekBystuId")
    @RequiresPermissions("system:mywork:list")
    @ApiOperation(value = "根据学生ID查找该用户顶岗实习周报")
    @ResponseBody
    public TableDataInfo selectWeekBystuId(@RequestParam(value = "worklyId",defaultValue = "0") long worklyId,
                                           @RequestParam(value = "workId",defaultValue = "0") long workId,
                                           @RequestParam(value = "worklyCategory",defaultValue = "") String worklyCategory){
        startPage();
        long stuId = ShiroUtils.getUserId();
        List<Map<String,Object>> list = myWorkService.selectWeekBystuId(stuId,worklyId,workId,worklyCategory);
        return getDataTable(list);
    }
}
