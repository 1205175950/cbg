package com.cbg.scrapy.service.dal.dao;

import ch.qos.logback.core.util.StringUtil;
import com.cbg.scrapy.service.dal.entity.Task;
import com.cbg.scrapy.service.dal.entity.TaskExample;
import com.cbg.scrapy.service.dal.mapper.TaskMapper;
import com.cbg.scrapy.service.util.TaskUtil;
import com.cbg.scrapy.web.vo.business.TaskAddVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Repository
public class TaskDao {

    @Resource
    private TaskMapper taskMapper;

    /**
     * 根据md5获取任务详情，用于防止创建重复任务
     * @param md5   url加密之后的md5值
     * @return      查询到的任务详情
     */
    public Task selectTaskByMd5(String md5) {
        if (StringUtil.isNullOrEmpty(md5)) {
            return null;
        }
        TaskExample example = new TaskExample();
        TaskExample.Criteria criteria = example.createCriteria();
        criteria.andMd5EqualTo(md5);
        List<Task> taskList = taskMapper.selectByExample(example);
        return CollectionUtils.isEmpty(taskList) ? null : taskList.get(0);
    }

    /**
     * 新增一个检测任务
     * @param taskAddVo 任务详情信息
     * @param md5       对应的md5值
     */
    public void insertTask(TaskAddVo taskAddVo, String md5) {
        Task task = TaskUtil.buildTask(taskAddVo, md5);
        taskMapper.insert(task);
    }

    /**
     * 查询所有库内的检测任务，不做分页
     * @return 任务详情
     */
    public List<Task> listAllTasks() {
        TaskExample example = new TaskExample();
        TaskExample.Criteria criteria = example.createCriteria();
        criteria.andIdGreaterThan(0L);
        example.setOrderByClause("add_time desc");
        return taskMapper.selectByExample(example);
    }
}
