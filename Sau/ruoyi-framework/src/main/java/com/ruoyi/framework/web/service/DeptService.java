package com.ruoyi.framework.web.service;

import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RuoYi首创 html调用 thymeleaf 实现字典读取
 * 
 * @author ruoyi
 */
@Service("dept")
public class DeptService
{
    @Autowired
    private ISysDeptService deptService;

    /**
     * 根据sys_dept表查询院系信息
     * @return
     */
    public List<SysDept> getDepartmentId(){
        return deptService.getDepartmentId();
    }
}
