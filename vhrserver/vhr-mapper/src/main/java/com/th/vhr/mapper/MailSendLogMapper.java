package com.th.vhr.mapper;

import com.th.vhr.bean.MailSendLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MailSendLogMapper {


    // 修改数据库中的记录，消息投递成功
    Integer updateMailSendLogStatus(@Param("msgId") String msgId, @Param("status") Integer status);

    Integer insert(MailSendLog mailSendLog);

    // 获取在一定时间内没有发送成功的
    List<MailSendLog> getMailSendLogsByStatus();

    // 更新发送次数
    Integer updateCount(@Param("msgId") String msgId, @Param("date") Date date);
}
