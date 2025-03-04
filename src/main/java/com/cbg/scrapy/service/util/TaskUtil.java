package com.cbg.scrapy.service.util;

import com.cbg.scrapy.service.dal.entity.Task;
import com.cbg.scrapy.web.enu.OperateStatus;
import com.cbg.scrapy.web.vo.business.TaskAddVo;
import com.cbg.scrapy.web.vo.business.TaskDetailVo;
import org.springframework.beans.BeanUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

public class TaskUtil {

    /**
     * 任务创建vo转task
     * @param taskAddVo 任务创建详情
     * @return          表Task数据
     */
    public static Task buildTask(TaskAddVo taskAddVo, String md5) {
        Task task = new Task();
        BeanUtils.copyProperties(taskAddVo, task);
        task.setUserFirstCounterPrice(taskAddVo.getUserCounterPrice());
        task.setUserFirstCounterTime(new Date());
        task.setAddTime(new Date());
        task.setUpdateTime(new Date());
        task.setMd5(md5);
        return task;
    }

    /**
     * 任务详情entity转详情vo
     * @param task 任务详情entity
     * @return     任务详情vo
     */
    public static TaskDetailVo convertDetailVo(Task task) {
        TaskDetailVo taskDetailVo = new TaskDetailVo();
        BeanUtils.copyProperties(task, taskDetailVo);
        taskDetailVo.setOperateStatusDesc(OperateStatus.getDesc(task.getOperateStatus()));
        return taskDetailVo;
    }

    /**
     * 对商品链接做md5加密，用于库内去重
     * @param url 商品链接
     * @return    md5加密真值
     */
    public static String getMD5Hash(String url) {
        try {
            // 创建MessageDigest实例，指定MD5算法
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 对字符串进行编码
            md.update(url.getBytes());
            // 获取MD5编码后的字节数组
            byte[] digest = md.digest();
            // 将字节数组转换为十六进制表示的字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                // 将每个字节转换为两位十六进制数
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e); // 如果找不到MD5算法，抛出运行时异常
        }
    }
}
