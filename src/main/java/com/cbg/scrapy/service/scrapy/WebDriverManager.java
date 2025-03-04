package com.cbg.scrapy.service.scrapy;

import com.cbg.scrapy.service.dal.dao.AccountDao;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebDriverManager {

    /**
     * 藏宝阁登录链接
     */
    private static final String LOGIN_URL = "https://xyq-m.cbg.163.com/cgi/mweb/show_login";

    /**
     * webDriver句柄池
     */
    private static List<WebDriver> webDriverPool;
    /**
     * 空闲webDriver句柄池
     */
    private static List<WebDriver> noTaskWebDriverPool;

    @Resource
    private AccountDao accountDao;

    @PostConstruct
    public void initPool() {
        webDriverPool = new ArrayList<>();
        noTaskWebDriverPool = new ArrayList<>();
        doAllAccountLogin();
    }

    /**
     * 尝试所有账户进行登录
     */
    private void doAllAccountLogin() {

    }
}
