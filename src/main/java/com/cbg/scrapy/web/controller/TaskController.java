package com.cbg.scrapy.web.controller;

import com.cbg.scrapy.service.dal.entity.Task;
import com.cbg.scrapy.service.facade.TaskFacade;
import com.cbg.scrapy.service.util.TaskUtil;
import com.cbg.scrapy.web.vo.business.TaskAddVo;
import com.cbg.scrapy.web.vo.business.TaskDetailVo;
import com.cbg.scrapy.web.vo.business.TaskUpdateVo;
import com.cbg.scrapy.web.vo.common.WebResponse;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Resource
    private TaskFacade taskFacade;

    @PostMapping("/add")
    public WebResponse<Boolean> createTask(@Valid @RequestBody TaskAddVo taskAddVo) {
        return WebResponse.buildData(taskFacade.createTask(taskAddVo));
    }

    @GetMapping("/list")
    public WebResponse<List<TaskDetailVo>> listTask() {
        return WebResponse.buildData(taskFacade.listTask());
    }

    @PostMapping("/update")
    public WebResponse<Boolean> updateTask(@Valid @RequestBody TaskUpdateVo taskUpdateVo) {
        return WebResponse.buildData(taskFacade.updateTask(taskUpdateVo));
    }


}
