package com.cbg.scrapy.web.controller;

import com.cbg.scrapy.service.facade.AccountFacade;
import com.cbg.scrapy.web.vo.business.AccountVo;
import com.cbg.scrapy.web.vo.business.TaskAddVo;
import com.cbg.scrapy.web.vo.common.WebResponse;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Resource
    private AccountFacade accountFacade;

    @GetMapping("/hasAlive")
    public WebResponse<Boolean> hasAliveAccount() {
        return WebResponse.buildData(accountFacade.hasAliveAccount());
    }

    @PostMapping("/create")
    public WebResponse<Boolean> createAccount(@Valid @RequestBody AccountVo accountVo) {
        return WebResponse.buildData(accountFacade.createAccount(accountVo));
    }

    @PostMapping("/update")
    public WebResponse<Boolean> updateAccount(@Valid @RequestBody AccountVo accountVo) {
        return WebResponse.buildData(accountFacade.updateAccount(accountVo));
    }

    @PostMapping("/valid")
    public WebResponse<Boolean> isUsefulAccount(@Valid @RequestBody AccountVo accountVo) {
        return WebResponse.buildData(accountFacade.isUsefulAccount(accountVo));
    }
}
