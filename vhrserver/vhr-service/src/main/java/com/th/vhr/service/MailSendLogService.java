package com.th.vhr.service;

import com.th.vhr.bean.MailSendLog;
import com.th.vhr.mapper.MailSendLogMapper;
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
public class MailSendLogService {


    @Autowired
    MailSendLogMapper mailSendLogMapper;

    // 修改数据库中的记录，消息投递成功
    public Integer updateMailSendLogStatus(String msgId, Integer status) {
        return mailSendLogMapper.updateMailSendLogStatus(msgId, status);
    }


    // 添加
    public Integer insert(MailSendLog mailSendLog) {
        return mailSendLogMapper.insert(mailSendLog);
    }

    // 获取在一定时间内没有发送成功的
    public List<MailSendLog> getMailSendLogsByStatus() {
        return mailSendLogMapper.getMailSendLogsByStatus();
    }

    // 更新发送次数
    public Integer updateCount(String msgId, Date date) {
        return mailSendLogMapper.updateCount(msgId,date);
    }
}
