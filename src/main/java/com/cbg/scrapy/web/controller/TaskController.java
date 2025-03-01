package com.cbg.scrapy.web.controller;

import com.cbg.scrapy.service.facade.TaskFacade;
import com.cbg.scrapy.web.vo.business.TaskAddVo;
import com.cbg.scrapy.web.vo.common.WebResponse;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Resource
    private TaskFacade taskFacade;

    @PostMapping("/add")
    public WebResponse<Boolean> createTask(@Valid @RequestBody TaskAddVo taskAddVo) {
        return WebResponse.buildData(taskFacade.createTask(taskAddVo));
    }
}
