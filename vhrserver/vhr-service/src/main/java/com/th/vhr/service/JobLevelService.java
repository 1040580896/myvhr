package com.th.vhr.service;

import com.th.vhr.bean.JobLevel;
import com.th.vhr.mapper.JobLevelMapper;
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
public class JobLevelService {

    @Autowired
    JobLevelMapper jobLevelMapper;

    /**
     * 获取所以职称等级
     * @return
     */
    public List<JobLevel> getALlJobLevels() {
        return jobLevelMapper.getALlJobLevels();
    }

    /**
     * 添加职称等级
     * @param jobLevel
     * @return
     */
    public int addJobLevel(JobLevel jobLevel) {
        jobLevel.setCreateDate(new Date());
        jobLevel.setEnabled(true);
        return jobLevelMapper.insertSelective(jobLevel);
    }

    /**
     * 更新职称等级
     * @param jobLevel
     * @return
     */
    public int updateJobLevelById(JobLevel jobLevel) {
        return jobLevelMapper.updateByPrimaryKeySelective(jobLevel);
    }

    /**
     * 删除职称等级
     * @param id
     * @return
     */
    public int deleteJobLevelById(Integer id) {
        return jobLevelMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    public int deleteJobLevelsByIds(Integer[] ids) {
        return jobLevelMapper.deleteJobLevelsByIds(ids);
    }

}
