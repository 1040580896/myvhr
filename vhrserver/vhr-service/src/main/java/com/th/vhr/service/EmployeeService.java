package com.th.vhr.service;

import com.th.vhr.bean.Employee;
import com.th.vhr.bean.MailConstants;
import com.th.vhr.bean.MailSendLog;
import com.th.vhr.bean.bo.RespPageBean;
import com.th.vhr.mapper.EmployeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @program: vhr
 * @description:
 * @author: xiaokaixin
 **/
@Service
public class EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    MailSendLogService mailSendLogService;

    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    DecimalFormat decimalFormat = new DecimalFormat("##.00");

    public RespPageBean getEmployeeByPage(Integer page, Integer size, Employee employee, Date[] beginDateScope) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Employee> data = employeeMapper.getEmployeeByPage(page, size, employee, beginDateScope);
        Long total = employeeMapper.getTotal(employee, beginDateScope);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

    // /**
    //  * 分页查询
    //  *
    //  * @param page
    //  * @param size
    //  * @param keyWords
    //  * @return
    //  */
    // public RespPageBean getEmployeePage(Integer page, Integer size, String keyWords) {
    //     if (page != null && size != null) {
    //         page = (page - 1) * size;
    //     }
    //     List<Employee> date = employeeMapper.getEmployeePage(page, size, keyWords);
    //     Long total = employeeMapper.getTotal(keyWords);
    //     RespPageBean respPageBean = new RespPageBean();
    //     respPageBean.setData(date);
    //     respPageBean.setTotal(total);
    //     return respPageBean;
    // }

    /**
     * 添加员工
     *
     * @param employee
     * @return
     */
    public int addEmp(Employee employee) {
        Date beginContract = employee.getBeginContract();
        Date endContract = employee.getEndContract();
        double month = (Double.parseDouble(yearFormat.format(endContract)) - Double.parseDouble(yearFormat.format(beginContract))) * 12 + (Double.parseDouble(monthFormat.format(endContract)) - Double.parseDouble(monthFormat.format(beginContract)));
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(month / 12)));
        int res = employeeMapper.insertSelective(employee);

        //  发送邮件
        if(res==1){
            Employee emp =  employeeMapper.getEmployeeById(employee.getId());
            // log.info(emp.toString());
            //生成消息的唯一id
            String msgId = UUID.randomUUID().toString();
            MailSendLog mailSendLog = new MailSendLog();

            mailSendLog.setMsgId(msgId);
            mailSendLog.setCreateTime(new Date());
            mailSendLog.setExchange(MailConstants.MAIL_EXCHANGE_NAME);
            mailSendLog.setRouteKey(MailConstants.MAIL_ROUTING_KEY_NAME);
            mailSendLog.setEmpId(emp.getId());
            mailSendLog.setTryTime(new Date(System.currentTimeMillis() + 1000 * 60 * MailConstants.MSG_TIMEOUT));
            mailSendLogService.insert(mailSendLog);
            // rabbitTemplate.convertAndSend("xiaokaixin.mail.welcome",emp);
            // 3.添加callback
            CorrelationData correlationData = new CorrelationData(msgId);
            // 2.2.准备ConfirmCallback
            correlationData.getFuture().addCallback(result -> {
                // 判断结果
                if (result.isAck()) {
                    // ACK
                    System.out.println("消息成功投递到交换机！消息ID");
                    log.debug("消息成功投递到交换机！消息ID: {}", correlationData.getId());
                    mailSendLogService.updateMailSendLogStatus(msgId, 1);//修改数据库中的记录，消息投递成功
                } else {
                    // NACK
                    log.error("消息投递到交换机失败！消息ID：{}", correlationData.getId());
                    // 重发消息
                }
            }, ex -> {
                // 记录日志
                log.error("消息发送失败！", ex);
                // 重发消息
            });
            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME, MailConstants.MAIL_ROUTING_KEY_NAME, emp, correlationData);
        }

        return res;
    }

    /**
     * 获取最大工号
     *
     * @return
     */
    public Integer maxWorkId() {
        return employeeMapper.maxWorkId();
    }

    /**
     *  删除员工
     * @param id
     * @return
     */
    public Integer deleteEmpById(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新员工
     * @param employee
     * @return
     */
    public int updateEmp(Employee employee) {
        return employeeMapper.updateByPrimaryKeySelective(employee);
    }

    public int addEmps(List<Employee> list) {
        return employeeMapper.addEmps(list);
    }

    public RespPageBean getEmployeeByPageWithSalary(Integer page, Integer size) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Employee> list = employeeMapper.getEmployeeByPageWithSalary(page, size);
        RespPageBean respPageBean = new RespPageBean();
        respPageBean.setData(list);
        respPageBean.setTotal(employeeMapper.getTotal(null, null));
        return respPageBean;
    }

    public Integer updateEmployeeSalaryById(Integer eid, Integer sid) {
        return employeeMapper.updateEmployeeSalaryById(eid, sid);
    }

    public Employee getEmployeeById(Integer empId) {
        return employeeMapper.getEmployeeById(empId);
    }
}
