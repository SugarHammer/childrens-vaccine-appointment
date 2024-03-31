package com.ruoyi.web.controller.sau;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.sau.service.MyTalkService;
import com.ruoyi.sau.service.TalkService;
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
@Api("我的分享")
@RequestMapping("/sau/mytalk")
public class MyTalkController extends BaseController {

    private String prefix = "sau/talk";

    @Autowired
    private MyTalkService myTalkService;

    @RequiresPermissions("system:mytalk:view")
    @GetMapping()
    public String myTalk(){
        return prefix + "/myTalk";
    }

    /**
     * 发布帖子
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 修改帖子信息
     */
    @GetMapping("/edit/{talkId}")
    public String edit(@PathVariable("talkId") long talkId, ModelMap mmap)
    {
        mmap.put("talk", myTalkService.selectTalkByTalkId(talkId));
        return prefix + "/edit";
    }

    /**
     * 发布帖子
     * @return
     */
    @PostMapping("/addMyTalk")
    @Transactional
    @RequiresPermissions("system:mytalk:add")
    @ApiOperation(value = "发帖")
    @ResponseBody
    public AjaxResult addMyTalk(@RequestParam Map<String,Object> map) {
        map.put("userId",ShiroUtils.getUserId());
        return myTalkService.addMyTalk(map);
    }

    @PostMapping("/deleteMyTalk")
    @RequiresPermissions("system:mytalk:remove")
    @ApiOperation(value = "删贴")
    @Transactional
    @ResponseBody
    public AjaxResult deleteMyWork(@RequestParam(value = "ids") String talkId){
        return myTalkService.deleteMyWork(talkId);
    }


    @PostMapping("/updateMyTalk")
    @RequiresPermissions("system:mytalk:edit")
    @ApiOperation(value = "修改帖子")
    @Transactional
    @ResponseBody
    public AjaxResult updateMyTalk(@RequestParam HashMap<String,Object> map){
        return myTalkService.updateMyTalk(map);
    }



    @PostMapping("/selectMyTalk")
    @RequiresPermissions("system:mytalk:list")
    @ApiOperation(value = "查询我的所有帖子")
    @ResponseBody
    public TableDataInfo selectTalk(@RequestParam(value = "talkId",defaultValue = "0") long talkId,
                                    @RequestParam(value = "talkCenter",defaultValue = "") String talkCenter){
        startPage();
        List<Map<String,Object>> list = myTalkService.selectMyTalk(talkId,talkCenter);
        return getDataTable(list);
    }

}
