package com.th.vhr.controller.salay;

import com.th.vhr.bean.RespBean;
import com.th.vhr.bean.Salary;
import com.th.vhr.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: vhr
 * @description:
 * @author: xiaokaixin
 **/
@RestController
@RequestMapping("/salary/sob")
public class SalaryController {

    @Autowired
    SalaryService salaryService;

    /**
     * 获取所有帐套
     * @return
     */
    @GetMapping("/")
    public List<Salary> getAllSalaries(){
        return salaryService.getAllSalaries();
    }

    /**
     * 添加账套
     */
    @PostMapping("/")
    public RespBean addSalary(@RequestBody Salary salary){
        if(salaryService.addSalary(salary)==1){
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    /**
     * 删除账套
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public RespBean deleteSalaryById(@PathVariable Integer id){
        if(salaryService.deleteSalaryById(id)==1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    /**
     * 更新账套
     */
    @PutMapping("/")
    public RespBean updateSalaryById(@RequestBody Salary salary){
        if(salaryService.updateSalaryById(salary)==1){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }
}
