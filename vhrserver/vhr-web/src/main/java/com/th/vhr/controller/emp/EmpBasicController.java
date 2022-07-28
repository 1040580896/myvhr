package com.th.vhr.controller.emp;

import com.th.vhr.bean.*;
import com.th.vhr.bean.bo.RespPageBean;
import com.th.vhr.service.*;
import com.th.vhr.utils.POIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.BindingType;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @program: vhr
 * @description:
 **/
@RestController
@RequestMapping("/employee/basic")
public class EmpBasicController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    NationService nationService;

    @Autowired
    PoliticsstatusService politicsstatusService;

    @Autowired
    JobLevelService jobLevelService;

    @Autowired
    PositionService positionService;

    @Autowired
    DepartmentService departmentService;

    /**
     * 分页查询
     *
     * @param page
     * @param size
     * @return
     */


    @GetMapping("/")
    public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, Employee employee, Date[] beginDateScope) {
        return employeeService.getEmployeeByPage(page, size, employee,beginDateScope);
    }
    // @GetMapping("/")
    // public RespPageBean getEmployeePage(@RequestParam(defaultValue = "1") Integer page,
    //                                     @RequestParam(defaultValue = "10") Integer size,
    //                                     String keyWords) {
    //     return employeeService.getEmployeePage(page, size, keyWords);
    // }

    /**
     * 添加员工
     *
     * @param employee
     * @return
     */
    @PostMapping("/")
    public RespBean addEmp(@RequestBody Employee employee) {
        if (employeeService.addEmp(employee) == 1) {
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    /**
     * 查询所有民族
     *
     * @return
     */
    @GetMapping("/nations")
    public List<Nation> getAllNations() {

        return nationService.getAllNations();
    }

    /**
     * 获取所有政治面貌
     *
     * @return
     */
    @GetMapping("/politicsstatus")
    public List<Politicsstatus> getAllPoliticsstatus() {

        return politicsstatusService.getAllPoliticsstatus();
    }

    /**
     * 获取所有职称
     *
     * @return
     */
    @GetMapping("/joblevels")
    public List<JobLevel> getAllJobLevels() {
        return jobLevelService.getALlJobLevels();
    }

    /**
     * 获取职位
     *
     * @return
     */
    @GetMapping("/positions")
    public List<Position> getALlPositions() {
        return positionService.getAllPositions();
    }

    /**
     * 获取最大工号
     */
    @GetMapping("/maxWorkId")
    public RespBean maxWorkId() {

        // return RespBean.ok("", String.format("%08d", employeeService.maxWorkId() + 1));
        return RespBean.build().setStatus(200).setObj(String.format("%08d", employeeService.maxWorkId() + 1));
    }

    /**
     * 获取所有部门
     * @return
     */
    @GetMapping("/deps")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    /**
     * 删除员工
     */
    @DeleteMapping("/{id}")
    public RespBean deleteEmpById(@PathVariable Integer id){
        if(employeeService.deleteEmpById(id)==1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    /**
     * 更新员工
     */
    @PutMapping("/")
    public RespBean updateEmp(@RequestBody Employee employee){
        if(employeeService.updateEmp(employee)==1){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportData(){
        List<Employee> list = (List<Employee>) employeeService.getEmployeeByPage(null, null, new Employee(),null).getData();

        return POIUtils.employee2Excel(list);
    }

    @PostMapping("/import")
    public RespBean importData(MultipartFile file) throws IOException {
        List<Employee> list = POIUtils.excel2Employee(file, nationService.getAllNations(), politicsstatusService.getAllPoliticsstatus(), departmentService.getAllDepartmentsWithOutChildren(), positionService.getAllPositions(), jobLevelService.getALlJobLevels());
        if (employeeService.addEmps(list) == list.size()) {
            return RespBean.ok("上传成功");
        }
        return RespBean.error("上传失败");
    }
}
