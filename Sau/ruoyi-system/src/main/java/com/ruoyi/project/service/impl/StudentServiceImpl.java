package com.ruoyi.project.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.Md5Utils;
import com.ruoyi.project.domain.Student;
import com.ruoyi.project.mapper.StudentMapper;
import com.ruoyi.project.service.StudentService;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.impl.SysUserServiceImpl;
import org.apache.commons.beanutils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private SysUserServiceImpl userService;

    /**
     * 添加学生信息
     * @param student
     * @return
     */
    @Override
    public int addStudent(Student student) {
        return studentMapper.addStudent(student);
    }

    /**
     * 删除学生信息
     * @param studentId
     * @return
     */
    @Override
    public int deleteStudent(String studentId) {
        Long[] userIds = Convert.toLongArray(studentId);
        for (Long userId : userIds)
        {
            checkUserAllowed(new SysUser(userId));
        }
        sysUserMapper.deleteUserByIds(userIds);
        return studentMapper.deleteStudent(userIds);
    }

    /**
     * 删除与该学生相关的课程信息
     * @param studentId
     */
    @Override
    public void deleteStudent2(String studentId) {
        Long[] userIds = Convert.toLongArray(studentId);
        for (Long userId : userIds)
        {
            checkUserAllowed(new SysUser(userId));
        }
        studentMapper.deleteStudent2(userIds);
    }

    /**
     * 更新学生信息
     * @param student
     * @return
     */
    @Override
    public int updateStudent(Student student) {
        return studentMapper.updateStudent(student);
    }

    /**
     * 查询学生信息
     * @param studentId
     * @param studentName
     * @param departmentId
     * @return
     */
    @Override
    public List<Map<String,Object>> selectStudent(long studentId, String studentName, long departmentId) {
        return studentMapper.selectStudent(studentId,studentName,departmentId);
    }

    @Override
    public Student selectStudentById(long studentId) {
        return studentMapper.selectStudentById(studentId);
    }

    /**
     * 根据查找学生信息列表
     * @return
     */
    public List<Student> selectStudentList(Student student){
        return studentMapper.selectStudentList(student);
    }


    /**
     * 校验用户是否允许操作
     *
     * @param user 用户信息
     */
    public void checkUserAllowed(SysUser user)
    {
        if (StringUtils.isNotNull(user.getUserId()) && user.isAdmin())
        {
            throw new BusinessException("不允许操作超级管理员用户");
        }
    }

    /**
     * 导入用户数据
     *
     * @param userList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    @Override
    public String importUser(List<Student> userList, Boolean isUpdateSupport, String operName)
    {
        if (StringUtils.isNull(userList) || userList.size() == 0)
        {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String password = configService.selectConfigByKey("sys.user.initPassword");
        for (Student student : userList)
        {
            try
            {
                // 验证是否存在这个用户
                Student u = studentMapper.selectStudentById(student.getStudentId());
                if (StringUtils.isNull(u))
                {
                    SysUser user = new SysUser();
                    user.setDeptId(student.getDepartmentId());
                    Long[] roleIds = {Long.valueOf(2)};
                    user.setRoleIds(roleIds);
                    user.setLoginName(String.valueOf(student.getStudentId()));
                    user.setUserName(student.getStudentName());
                    user.setSex(String.valueOf(student.getStudentSex()));
                    user.setPassword(Md5Utils.hash(student.getStudentName() + password));
                    user.setCreateBy(operName);
                    userService.insertUser(user);
                    this.addStudent(student);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + student.getStudentName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    SysUser user = new SysUser();
                    user.setDeptId(student.getDepartmentId());
                    Long[] roleIds = {Long.valueOf(2)};
                    user.setRoleIds(roleIds);
                    user.setLoginName(String.valueOf(student.getStudentId()));
                    user.setUserName(student.getStudentName());
                    user.setSex(String.valueOf(student.getStudentSex()));
                    user.setPassword(Md5Utils.hash(student.getStudentName() + password));
                    user.setCreateBy(operName);
                    userService.updateUser(user);
                    this.updateStudent(student);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + student.getStudentName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、账号 " + student.getStudentName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + student.getStudentName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}
