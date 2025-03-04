package com.cbg.scrapy.service.facade;

import com.cbg.scrapy.service.dal.dao.AccountDao;
import com.cbg.scrapy.service.dal.dao.TaskDao;
import com.cbg.scrapy.service.dal.entity.Task;
import com.cbg.scrapy.service.dto.EquipParseDto;
import com.cbg.scrapy.service.exception.CbgBizException;
import com.cbg.scrapy.service.scrapy.CbgScrapyService;
import com.cbg.scrapy.service.util.TaskUtil;
import com.cbg.scrapy.web.vo.business.TaskAddVo;
import com.cbg.scrapy.web.vo.business.TaskDetailVo;
import com.cbg.scrapy.web.vo.business.TaskUpdateVo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TaskFacade {

    @Resource
    private TaskDao taskDao;
    @Resource
    private CbgScrapyService cbgScrapyService;


    /**
     * 根据用户商品链接获取商品详情信息
     * @param url         商品链接
     * @return            商品详情信息
     */
    public EquipParseDto getSellInfo(String url) {
        return cbgScrapyService.getSellEquipInfo(url);
    }

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

    /**
     * 查询所有任务
     * @return 任务详情列表
     */
    public List<TaskDetailVo> listTask() {
        List<Task> taskList = taskDao.listAllTasks();
        return taskList.stream().map(TaskUtil::convertDetailVo).collect(Collectors.toList());
    }

    /**
     * 更新任务
     * @param taskUpdateVo 待更新的任务详情
     * @return             是否更新成功
     */
    public Boolean updateTask(TaskUpdateVo taskUpdateVo) {
        // TODO
        return false;
    }
}
