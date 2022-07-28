package com.th.vhr.controller.system.basic;

import com.th.vhr.bean.Department;
import com.th.vhr.bean.RespBean;
import com.th.vhr.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: vhr
 * @description:
 * @author: xiaokaixin
 **/
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {


    @Autowired
    DepartmentService departmentService;

    /**
     * 递归获取部门
     *
     * @return
     */
    @GetMapping("/")
    public List<Department> getAllDepartments() {

        return departmentService.getAllDepartments();
    }

    /**
     * 添加部门
     *
     * @param dep
     * @return
     */
    @PostMapping("/")
    public RespBean addDep(@RequestBody Department dep) {
        departmentService.addDep(dep);
        if (dep.getResult() == 1) {
            return RespBean.ok("添加成功", dep);
        }
        return RespBean.error("添加失败");
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public RespBean deleteDepById(@PathVariable Integer id) {
        Department department = new Department();
        department.setId(id);
        departmentService.deleteDepById(department);
        if (department.getResult() == -2) {
            return RespBean.error("该部门有子部门，删除失败");
        }else if(department.getResult()==-1){
            return RespBean.error("该部门下有员工,删除失败");

        }else if(department.getResult()==1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

}
