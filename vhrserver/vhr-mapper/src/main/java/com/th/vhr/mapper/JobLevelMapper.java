package com.th.vhr.mapper;

import com.th.vhr.bean.JobLevel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobLevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JobLevel record);

    int insertSelective(JobLevel record);

    JobLevel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JobLevel record);

    int updateByPrimaryKey(JobLevel record);

    /**
     * 获取所以职称等级
     *
     * @return
     */
    List<JobLevel> getALlJobLevels();

    int deleteJobLevelsByIds(@Param("ids") Integer[] ids);


}