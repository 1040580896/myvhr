package com.th.vhr.service;

import com.th.vhr.bean.Department;
import com.th.vhr.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: vhr
 * @description:
 * @author: xiaokaixin
 **/
@Service
public class DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    /**
     * 递归获取部门
     * @return
     */
    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartmentsByParentId(-1);
    }

    /**
     * 添加部门
     * @param dep
     */
    public void addDep(Department dep) {
        dep.setEnabled(true);
        departmentMapper.addDep(dep);

    }

    /**
     * 删除部门
     * @param department
     */
    public void deleteDepById(Department department) {
        departmentMapper.deleteDepById(department);
    }

    public List<Department> getAllDepartmentsWithOutChildren() {
        return departmentMapper.getAllDepartmentsWithOutChildren();
    }
}
