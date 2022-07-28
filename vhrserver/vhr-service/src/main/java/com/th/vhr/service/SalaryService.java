package com.th.vhr.service;

import com.th.vhr.bean.Salary;
import com.th.vhr.mapper.SalaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: vhr
 * @description:
 * @author: xiaokaixin
 **/
@Service
public class SalaryService {

    @Autowired
    SalaryMapper salaryMapper;

    /**
     * 获取所有帐套
     * @return
     */
    public List<Salary> getAllSalaries() {
        return salaryMapper.getAllSalaries();
    }

    /**
     * 添加账套
     * @param salary
     * @return
     */
    public int addSalary(Salary salary) {
        salary.setCreateDate(new Date());
        return salaryMapper.insertSelective(salary);
    }

    /**
     * 删除账套
     * @param id
     * @return
     */
    public int deleteSalaryById(Integer id) {
        return salaryMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新账套
     * @param salary
     * @return
     */
    public int updateSalaryById(Salary salary) {
        return salaryMapper.updateByPrimaryKeySelective(salary);
    }
}
