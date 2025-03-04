package com.cbg.scrapy.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.cbg.scrapy.AbstractBastTest;
import com.cbg.scrapy.web.vo.business.TaskAddVo;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;

class TaskControllerTest extends AbstractBastTest {

    @Resource
    private TaskController taskController;

    @Test
    public void createTaskException1() {
        TaskAddVo taskAddVo = new TaskAddVo();
        taskAddVo.setUrl("www");
        taskController.createTask(taskAddVo);
    }

    @Test
    public void listAllTask() {
        System.out.println(JSONObject.toJSONString(taskController.listTask()));
    }
}