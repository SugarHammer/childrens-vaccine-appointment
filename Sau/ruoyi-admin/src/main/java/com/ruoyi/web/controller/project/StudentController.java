package com.ruoyi.web.controller.project;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.project.domain.Student;
import com.ruoyi.project.service.StudentService;
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
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Array;
import java.util.*;

import static com.github.pagehelper.page.PageMethod.startPage;

/**
 * @author Maxj
 */
@Controller
@Api("学生信息管理")
@RequestMapping("/project/student")
public class StudentController extends BaseController {

    private String prefix = "project/student";

    @Autowired
    private StudentService studentService;

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

    @RequiresPermissions("system:student:view")
    @GetMapping()
    public String student()
    {
        return prefix + "/student";
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
     * 添加学生信息
     * @return
     */
    @PostMapping("/addStudent")
    @Transactional
    @RequiresPermissions("system:student:add")
    @ApiOperation(value = "添加学生信息")
    @ResponseBody
    public AjaxResult addStudent(@RequestParam HashMap<String,Object> map){
        SysUser user = new SysUser();
        user.setDeptId(Long.parseLong(map.get("treeId").toString()));
        Long[] roleIds = (Long[]) ConvertUtils.convert(map.get("roleIds").toString().split(","),Long.class);
        user.setRoleIds(roleIds);
        user.setLoginName(map.get("loginName").toString());
        user.setUserName(map.get("studentName").toString());
        user.setPassword(map.get("password").toString());
        user.setSex(map.get("studentSex").toString());
        if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(user.getLoginName())))
        {
            return error("新增用户'" + user.getLoginName() + "'失败，登录账号已存在");
        }
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        user.setCreateBy(ShiroUtils.getLoginName());

        Student student = new Student();
        student.setStudentId(userService.getId());
        student.setDepartmentId(Long.parseLong(map.get("treeId").toString()));
        student.setStudentBirthday(map.get("studentBirthday").toString());
        student.setStudentGrade(Double.parseDouble(map.get("studentGrade").toString()));
        student.setStudentSex(Integer.parseInt(map.get("studentSex").toString()));
        student.setStudentName(map.get("studentName").toString());
        userService.insertUser(user);
        int Count = studentService.addStudent(student);
        if(Count > 0){
            return AjaxResult.success("添加学生信息成功");
        }
        return AjaxResult.warn("添加学生信息失败");
    }

    @PostMapping("/deleteStudent")
    @RequiresPermissions("system:student:remove")
    @ApiOperation(value = "删除学生信息")
    @Transactional
    @ResponseBody
    public AjaxResult deleteStudent(@RequestParam(value = "ids") String studentId){
        try
        {
            userService.deleteUserByIds(studentId);
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
        int Count = studentService.deleteStudent(studentId);
        studentService.deleteStudent2(studentId);
        if(Count > 0){
            return AjaxResult.success("删除学生信息成功");
        }
        return AjaxResult.warn("删除学生信息失败");
    }


    /**
     * 修改角色
     */
    @GetMapping("/edit/{studentId}")
    public String edit(@PathVariable("studentId") Long studentId, ModelMap mmap)
    {
        mmap.put("student", studentService.selectStudentById(studentId));
        mmap.put("roles", roleService.selectRoleAll());
        return prefix + "/edit";
    }



    @PostMapping("/updateStudent")
    @RequiresPermissions("system:student:edit")
    @ApiOperation(value = "修改学生信息")
    @Transactional
    @ResponseBody
    public AjaxResult updateStudent(@RequestParam HashMap<String,Object> map){
        Student student = new Student();
        student.setStudentId(Long.parseLong(map.get("studentId").toString()));
        student.setStudentName(map.get("studentName").toString());
        student.setStudentGrade(Double.parseDouble(map.get("studentGrade").toString()));
        student.setStudentBirthday(map.get("studentBirthday").toString());
        student.setStudentSex(Integer.parseInt(map.get("studentSex").toString()));
        student.setDepartmentId(Long.parseLong(map.get("departmentId").toString()));

        int Count = studentService.updateStudent(student);
        SysUser user = new SysUser();
        user.setUserId(student.getStudentId());
        user.setUserName(student.getStudentName());
        user.setDeptId(student.getDepartmentId());
        user.setSex(map.get("studentSex").toString());
        List<Long> list = roleService.selectRolesIdByUserId(student.getStudentId());
        Long[] roleIds = new Long[list.size()];
        for(int i=0; i<list.size(); i++){
            roleIds[i] = list.get(i);
        }
        user.setRoleIds(roleIds);
        userService.checkUserAllowed(user);
        user.setUpdateBy(ShiroUtils.getLoginName());
        userService.updateUser(user);
        if(Count > 0){
            return AjaxResult.success("修改学生信息成功");
        }
        return AjaxResult.warn("修改学生信息失败");
    }


    @PostMapping("/selectStudent")
    @RequiresPermissions("system:student:list")
    @ApiOperation(value = "查找学生信息")
    @Transactional
    @ResponseBody
    public TableDataInfo selectStudent(@RequestParam(value = "studentId",defaultValue = "0")long studentId,
                                       @RequestParam(value = "studentName",defaultValue = "") String studentName,
                                       @RequestParam(value = "departmentId",defaultValue = "0") long departmentId){
        startPage();
        List<Map<String,Object>> list = studentService.selectStudent(studentId,studentName,departmentId);
        for(Map<String,Object> map : list){
            if(map.containsKey("departmentId")){
                map.put("departmentId",deptService.getDeptName(Long.parseLong(map.get("departmentId").toString())));
            }
        }
        return getDataTable(list);
    }

    @Log(title = "学生信息管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:student:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Student user)
    {
        List<Student> list = studentService.selectStudentList(user);
        ExcelUtil<Student> util = new ExcelUtil<Student>(Student.class);
        return util.exportExcel(list, "学生信息");
    }

    @Log(title = "学生信息管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:student:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<Student> util = new ExcelUtil<Student>(Student.class);
        List<Student> userList = util.importExcel(file.getInputStream());
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message = studentService.importUser(userList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    @RequiresPermissions("system:student:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<Student> util = new ExcelUtil<Student>(Student.class);
        return util.importTemplateExcel("学生信息");
    }
}
