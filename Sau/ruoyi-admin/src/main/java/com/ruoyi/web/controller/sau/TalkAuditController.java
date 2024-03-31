package com.ruoyi.web.controller.sau;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.sau.service.MyTalkService;
import com.ruoyi.sau.service.TalkAuditService;
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
@Api("教师端信息审核")
@RequestMapping("/sau/talkaudit")
public class TalkAuditController extends BaseController {

    private String prefix = "sau/talk";

    @Autowired
    private TalkAuditService talkAuditService;

    @Autowired
    private MyTalkService myTalkService;

    @RequiresPermissions("system:talkaudit:view")
    @GetMapping()
    public String prodAudit(){
        return prefix + "/talkAudit";
    }

    /**
     * 查看帖子详情
     */
    @GetMapping("/see/{talkId}")
    public String edit(@PathVariable("talkId") long talkId, ModelMap mmap)
    {
        mmap.put("talk", myTalkService.selectTalkByTalkId(talkId));
        return prefix + "/talkDetails";
    }


    @PostMapping("/selectAllTalk")
    @RequiresPermissions("system:talkaudit:list")
    @ApiOperation(value = "查询顶岗实习申请")
    @ResponseBody
    public TableDataInfo selectAllTalk(@RequestParam(value = "userId",defaultValue = "0") long userId,
                                        @RequestParam(value = "author",defaultValue = "") String author,
                                        @RequestParam(value = "talkCenter",defaultValue = "") String talkCenter,
                                        @RequestParam(value = "auditState",defaultValue = "0") int auditState){
        startPage();
        List<Map<String,Object>> list = talkAuditService.selectAllTalk(userId,author,talkCenter,auditState);
        return getDataTable(list);
    }

    @PostMapping("/agree")
    @RequiresPermissions("system:talkaudit:agree")
    @ApiOperation(value = "通过")
    @Transactional
    @ResponseBody
    public AjaxResult agree(@RequestParam(value = "ids") String talkId){
        return talkAuditService.agree(talkId);
    }

    @PostMapping("/reject")
    @RequiresPermissions("system:workaudit:reject")
    @ApiOperation(value = "拒绝")
    @Transactional
    @ResponseBody
    public AjaxResult reject(@RequestParam(value = "ids") String talkId){
        return talkAuditService.reject(talkId);
    }
}
