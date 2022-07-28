package com.th.vhr.mapper;

import com.th.vhr.bean.Department;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    /**
     * 递归获取部门
     * @param pid
     * @return
     */
    List<Department> getAllDepartmentsByParentId(Integer pid);

    /**
     * 添加部门
     * @param dep
     */
    void addDep(Department dep);

    /**
     * 删除部门
     * @param department
     */
    void deleteDepById(Department department);

    List<Department> getAllDepartmentsWithOutChildren();
}