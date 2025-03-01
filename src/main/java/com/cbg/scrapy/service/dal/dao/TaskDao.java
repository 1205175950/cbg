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

    public int insertTask(TaskAddVo taskAddVo, String md5) {
        Task task = TaskUtil.buildTask(taskAddVo, md5);
        return taskMapper.insert(task);
    }
}
