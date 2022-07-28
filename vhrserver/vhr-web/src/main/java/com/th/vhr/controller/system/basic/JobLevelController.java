package com.th.vhr.controller.system.basic;

import com.th.vhr.bean.JobLevel;
import com.th.vhr.bean.RespBean;
import com.th.vhr.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: vhr
 * @description:
 **/
@RestController
@RequestMapping("/system/basic/jobLevel")
public class JobLevelController {


    @Autowired
    JobLevelService jobLevelService;

    /**
     * 获取所以职称等级
     * @return
     */
    @GetMapping("/")
    public List<JobLevel> getALlJobLevels(){

        return jobLevelService.getALlJobLevels();
    }


    /**
     * 添加职称等级
     * @param jobLevel
     * @return
     */
    @PostMapping("/")
    public RespBean addJobLevel(@RequestBody JobLevel jobLevel){
        if(jobLevelService.addJobLevel(jobLevel)==1){
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    /**
     * 更新职称等级
     * @param jobLevel
     * @return
     */
    @PutMapping("/")
    public RespBean updateJobLevelById(@RequestBody JobLevel jobLevel){
        if(jobLevelService.updateJobLevelById(jobLevel)==1){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    /**
     * 删除职称等级
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public RespBean deleteJobLevelById(@PathVariable("id") Integer id){
        if(jobLevelService.deleteJobLevelById(id)==1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @DeleteMapping("/")
    public RespBean deleteJobLevelsByIds(Integer[] ids){
        if(jobLevelService.deleteJobLevelsByIds(ids)==ids.length){
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败");
    }
}
