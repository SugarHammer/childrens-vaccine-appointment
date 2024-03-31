package com.ruoyi.web.controller.project;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.project.domain.Teacher;
import com.ruoyi.project.service.TeacherService;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysPostService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.ConvertUtils;
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
@Controller
@Api("教师信息管理")
@RequestMapping("/project/teacher")
public class TeacherController extends BaseController {

    private String prefix = "project/teacher";

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysPostService postService;

    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysDeptService deptService;

    @Autowired
    private ISysPostService PostService;


    @GetMapping()
    @RequiresPermissions("system:teacher:view")
    public String role()
    {
        return prefix + "/teacher";
    }

    /**
     * 新增角色
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        mmap.put("roles", roleService.selectRoleAll());
        mmap.put("posts", postService.selectPostAll());
        return prefix + "/add";
    }

    /**
     * 添加职工信息
     * @param
     * @return
     */
    @PostMapping("/addTeacher")
    @RequiresPermissions("system:teacher:add")
    @ApiOperation(value = "添加教师信息")
    @ResponseBody
    public AjaxResult addTeacher(@RequestParam HashMap<String,Object> map){
        SysUser user = new SysUser();
        user.setPassword(map.get("password").toString());
        user.setUserName(map.get("teacherName").toString());
        user.setDeptId(Long.parseLong(map.get("treeId").toString()));
        user.setLoginName(map.get("loginName").toString());
        Long[] roleIds = (Long[]) ConvertUtils.convert(map.get("roleIds").toString().split(","),Long.class);
        user.setRoleIds(roleIds);
        user.setSex(map.get("teacherSex").toString());
        Long[] postIds = (Long[]) ConvertUtils.convert(map.get("postIds").toString().split(","),Long.class);
        user.setPostIds(postIds);
        if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(user.getLoginName())))
        {
            return error("新增用户'" + user.getLoginName() + "'失败，登录账号已存在");
        }
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        user.setCreateBy(ShiroUtils.getLoginName());

        Teacher teacher = new Teacher();
        teacher.setTeacherId(userService.getId());
        teacher.setTeacherName(map.get("teacherName").toString());
        teacher.setTeacherSex(Integer.parseInt(map.get("teacherSex").toString()));
        teacher.setTeacherBirthday(map.get("teacherBirthday").toString());
        teacher.setDepartmentId(Long.parseLong(map.get("treeId").toString()));
        teacher.setTeacherTitle(Integer.parseInt(map.get("postIds").toString()));
        teacher.setTeacherTechnical(map.get("teacherTechnical").toString());

        userService.insertUser(user);
        int Count = teacherService.addTeacher(teacher);
        if(Count > 0){
            return AjaxResult.success("添加教师信息成功");
        }
        return AjaxResult.warn("添加教师信息失败");
    }

    @PostMapping("/deleteTeacher")
    @RequiresPermissions("system:teacher:remove")
    @ApiOperation(value = "删除教师信息")
    @Transactional
    @ResponseBody
    public AjaxResult deleteTeacher(@RequestParam(value = "ids") String teacherId){
        try
        {
            userService.deleteUserByIds(teacherId);
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
        int Count = teacherService.deleteTeacher(teacherId);
        teacherService.deleteTeacher2(teacherId);
        teacherService.deleteTeacher3(teacherId);
        if(Count > 0){
            return AjaxResult.success("删除教师信息成功");
        }
        return AjaxResult.warn("删除教师信息失败");
    }


    /**
     * 修改角色
     */
    @GetMapping("/edit/{teacherId}")
    public String edit(@PathVariable("teacherId") Long teacherId, ModelMap mmap)
    {
        mmap.put("teacher", teacherService.selectTeacherById(teacherId));
        mmap.put("roles", roleService.selectRoleAll());
        mmap.put("posts", postService.selectPostAll());
        return prefix + "/edit";
    }



    @PostMapping("/updateTeacher")
    @RequiresPermissions("system:teacher:edit")
    @ApiOperation(value = "修改教师信息")
    @ResponseBody
    public AjaxResult updateTeacher(Teacher teacher){
        int Count = teacherService.updateTeacher(teacher);
        SysUser user = new SysUser();
        user.setUserId(teacher.getTeacherId());
        user.setUserName(teacher.getTeacherName());
        user.setDeptId(teacher.getDepartmentId());
        user.setSex(String.valueOf(teacher.getTeacherSex()));
        long num = teacher.getTeacherTitle();
        Long[] postIds = new Long[1];
        postIds[0] = num;
        user.setPostIds(postIds);
        List<Long> list = roleService.selectRolesIdByUserId(teacher.getTeacherId());
        Long[] roleIds = new Long[list.size()];
        for(int i=0; i<list.size(); i++){
            roleIds[i] = list.get(i);
        }
        user.setRoleIds(roleIds);
        userService.checkUserAllowed(user);
        user.setUpdateBy(ShiroUtils.getLoginName());
        userService.updateUser(user);
        if(Count > 0){
            return AjaxResult.success("修改教师信息成功");
        }
        return AjaxResult.warn("修改教师信息失败");
    }

    @PostMapping("/selectTeacher")
    @RequiresPermissions("system:teacher:list")
    @ApiOperation(value = "查找教师信息")
    @ResponseBody
    public TableDataInfo selectTeacher(@RequestParam(value = "teacherId",defaultValue = "0")long teacherId,
                                       @RequestParam(value = "teacherName",defaultValue = "") String teacherName,
                                       @RequestParam(value = "departmentId",defaultValue = "0") long departmentId){
        startPage();
        List<Map<String,Object>> list = teacherService.selectTeacher(teacherId,teacherName,departmentId);
        for(Map<String,Object> map : list){
            if(map.containsKey("departmentId")){
                map.put("departmentId",deptService.getDeptName(Long.parseLong(map.get("departmentId").toString())));
            }
            if(map.containsKey("teacherTitle")){
                map.put("teacherTitle",postService.getPostName(Long.parseLong(map.get("teacherTitle").toString())));
            }
        }
        return getDataTable(list);
    }
}
