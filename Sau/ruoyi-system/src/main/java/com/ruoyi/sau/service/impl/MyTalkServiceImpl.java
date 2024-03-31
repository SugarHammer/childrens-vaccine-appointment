package com.ruoyi.sau.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.sau.domain.Talk;
import com.ruoyi.sau.mapper.TalkMapper;
import com.ruoyi.sau.service.MyTalkService;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
@Service
public class MyTalkServiceImpl implements MyTalkService {
    @Autowired
    private TalkMapper talkMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 发帖
     * @param map
     * @return
     */
    public AjaxResult addMyTalk(Map<String,Object> map){
        List<SysRole> roles = sysRoleMapper.selectRolesByUserId(Long.parseLong(map.get("userId").toString()));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        map.put("talkTime",df.format(new Date()));
        map.put("auditState",1);
        for(SysRole role : roles){
            if(role.getRoleName().equals("管理员") || role.getRoleName().equals("教师")){
                map.put("auditState",2);
                break;
            }
        }
        int Count = talkMapper.addMyTalk(map);
        if(Count > 0){
            return AjaxResult.success("发帖成功，等待管理员审核");
        }
        return AjaxResult.warn("发帖失败");
    }

    /**
     * 删帖
     * @param talkId
     * @return
     */
    public AjaxResult deleteMyWork(String talkId){
        Long[] ids = Convert.toLongArray(talkId);
        int Count = talkMapper.deleteMyWork(ids);
        if(Count > 0){
            return AjaxResult.success("删帖成功");
        }
        return AjaxResult.warn("删帖失败");
    }

    /**
     * 编辑帖子
     * @param map
     * @return
     */
    public AjaxResult updateMyTalk(Map<String,Object> map){
        List<SysRole> roles = sysRoleMapper.selectRolesByUserId(Long.parseLong(map.get("userId").toString()));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        map.put("talkTime",df.format(new Date()));
        map.put("auditState",1);
        for(SysRole role : roles){
            if(role.getRoleName().equals("管理员") || role.getRoleName().equals("教师")){
                map.put("auditState",2);
                break;
            }
        }
        int Count = talkMapper.updateMyTalk(map);
        if(Count > 0){
            return AjaxResult.success("修改帖子成功，等待管理员审核");
        }
        return AjaxResult.warn("修改帖子失败");
    }

    /**
     * 查找我的所有贴子
     * @param talkId
     * @param talkCenter
     * @return
     */
    public List<Map<String,Object>> selectMyTalk(long talkId, String talkCenter){
        return talkMapper.selectMyTalk(talkId,talkCenter);
    }

    /**
     * 根据talkId查找帖子
     * @param talkId
     * @return
     */
    public Talk selectTalkByTalkId(long talkId){
        return talkMapper.selectTalkByTalkId(talkId);
    }
}
