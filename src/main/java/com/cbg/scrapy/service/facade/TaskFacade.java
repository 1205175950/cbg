package com.cbg.scrapy.service.facade;

import com.cbg.scrapy.service.dal.dao.TaskDao;
import com.cbg.scrapy.service.dal.entity.Task;
import com.cbg.scrapy.service.exception.CbgBizException;
import com.cbg.scrapy.service.util.TaskUtil;
import com.cbg.scrapy.web.vo.business.TaskAddVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class TaskFacade {

    @Resource
    private TaskDao taskDao;

    /**
     * 创建爬虫任务
     * @param taskAddVo 任务详情
     * @return          是否创建成功
     */
    public Boolean createTask(TaskAddVo taskAddVo) {
        if (null == taskAddVo) {
            CbgBizException.fly("任务信息为空");
        }
        String md5 = TaskUtil.getMD5Hash(taskAddVo.getUrl());
        Task taskInDb = taskDao.selectTaskByMd5(md5);
        if (null != taskInDb) {
            CbgBizException.fly("不允许新增重复任务");
        }
        taskDao.insertTask(taskAddVo, md5);
        return true;
    }
}
