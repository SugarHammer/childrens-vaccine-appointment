package com.ruoyi.web.controller.sau;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.sau.service.CommentService;
import com.ruoyi.sau.service.MyTalkService;
import com.ruoyi.sau.service.TalkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * @author Maxj
 */
@Controller()
@Api("新讨论区")
@RequestMapping("/sau/talk")
public class TalkController extends BaseController {

    private String prefix = "sau/talk";

    @Autowired
    private TalkService talkService;

    @Autowired
    private MyTalkService myTalkService;

    @Autowired
    private CommentService commentService;

    @RequiresPermissions("system:talk:view")
    @GetMapping()
    public String talk(){
        return prefix + "/talk";
    }

    /**
     * 查看帖子详情
     */
    @RequiresPermissions("system:talk:see")
    @GetMapping("/see/{talkId}")
    public String see(@PathVariable("talkId") long talkId, ModelMap mmap)
    {
        mmap.put("talk", myTalkService.selectTalkByTalkId(talkId));
        mmap.put("commentDtos",commentService.selectCommentByTalkId(talkId));
        return prefix + "/talkDetails";
    }


    @PostMapping("/selectTalk")
    @RequiresPermissions("system:talk:list")
    @ApiOperation(value = "查询所有帖子")
    @ResponseBody
    public TableDataInfo selectTalk(@RequestParam(value = "userId",defaultValue = "0") long userId,
                                    @RequestParam(value = "author",defaultValue = "") String author,
                                    @RequestParam(value = "talkCenter",defaultValue = "") String talkCenter){
        startPage();
        List<Map<String,Object>> list = talkService.selectTalk(userId,author,talkCenter);
        return getDataTable(list);
    }

    /**
     * 获取回复下的评论
     *
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("/comment/{id}")
    public List<Map<Object,Object>> getComments(@PathVariable("id") Long id){
        List<Map<Object,Object>> commentDtos = commentService.getComments(id);
        return commentDtos;
    }

    /**
     * 添加评论
     * @param parentId
     * @param content
     * @return
     */
    @PostMapping("/insertComment")
    @ResponseBody
    public AjaxResult insertComment(@RequestParam("parentId") long parentId,
                                    @RequestParam("content") String content) {

        long userId = ShiroUtils.getUserId();
        return commentService.insertComment(parentId,content,userId);
    }

}
