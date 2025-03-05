package com.cbg.scrapy.service.scrapy;

import com.cbg.scrapy.service.dal.dao.AccountDao;
import com.cbg.scrapy.service.dal.entity.ScrapyAccount;
import com.cbg.scrapy.service.dto.WebDriverWrapperDto;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class WebDriverManager {

    /**
     * webDriver句柄池
     */
    private static Map<String, WebDriver> webDriverPool;
    /**
     * 空闲webDriver句柄池
     */
    private static Map<String, WebDriver> noTaskWebDriverPool;

    /**
     * 藏宝阁登录链接
     */
    private static final String LOGIN_URL = "https://xyq-m.cbg.163.com/cgi/mweb/show_login";
    /**
     * 浏览器驱动设置
     */
    private static final ChromeOptions options = new ChromeOptions();

    @Resource
    private AccountDao accountDao;

    static {
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--user-agent=\"Android\"");
        options.setBinary("C:\\Users\\Administrator\\Desktop\\chrome-win64\\chrome.exe");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\Downloads\\jdk11\\bin\\chromedriver.exe");
    }

    @PostConstruct
    public void initPool() {
        try {
            // 连接池配置
            webDriverPool = new ConcurrentHashMap<>();
            noTaskWebDriverPool = new ConcurrentHashMap<>();
            doAllAccountLogin();
        } catch (Exception e) {
            log.error("初始化webDriver连接池失败", e);
        }
    }

    /**
     * 尝试所有账户进行登录
     */
    private void doAllAccountLogin() throws InterruptedException {
        List<ScrapyAccount> allActiveAccounts = accountDao.getAllActiveAccount();
        if (CollectionUtils.isEmpty(allActiveAccounts)) {
            return;
        }
        for (ScrapyAccount account : allActiveAccounts) {
            String userName = account.getAccount();
            String password = account.getPassword();
            tryLogin(userName, password);
        }
    }

    /**
     * 具体登录逻辑
     * @param userName   账户名
     * @param password   登录密码
     * @return           登录结果，包装了登录失败原因&登录成功后的句柄池
     */
    public WebDriverWrapperDto tryLogin(String userName, String password) {

        try {
            WebDriver webDriver = new ChromeDriver(options);
            webDriver.get(LOGIN_URL);
            Thread.sleep(2000);
            WebElement iframe = webDriver.findElement(By.tagName("iframe"));
            webDriver.switchTo().frame(iframe);
            webDriver.findElement(By.name("email")).clear();
            webDriver.findElement(By.name("email")).sendKeys(userName);
            webDriver.findElement(By.name("password")).clear();
            webDriver.findElement(By.name("password")).sendKeys(password);
            webDriver.findElement(By.id("dologin")).click();
            Thread.sleep(5000);
            String pageSource = webDriver.getPageSource();
            // 登录成功后页面会跳转，且新页面有一个特定元素
            if (pageSource.contains("选择角色")) {
                log.info("登录成功，跳转到新页面，账户名：{}", userName);
                webDriverPool.put(userName, webDriver);
                noTaskWebDriverPool.put(userName, webDriver);
                accountDao.updateAccountStatus(userName, 1);
                return new WebDriverWrapperDto(webDriver, null);
            } else {
                String pattern = "<div class=\"ferrorhead\">(.*)</div>";
                Pattern regexp = Pattern.compile(pattern);
                Matcher matcher = regexp.matcher(pageSource);
                if (matcher.find()) {
                    String errMsg = matcher.group(1);
                    errMsg = errMsg.contains("密码错误次数过多") ? "密码错误次数过多请稍后再试" : errMsg;
                    log.error("登录失败，账户名：{}, 错误信息:{}", userName, errMsg);
                    accountDao.updateAccountStatus(userName, 0);
                    webDriverPool.remove(userName);
                    noTaskWebDriverPool.remove(userName);
                    return new WebDriverWrapperDto(null, "登录失败！ " + errMsg);
                }
            }
        } catch (Exception e) {
            log.error("未知状态，既没有登录成功也没有登录失败，账户名：{}", userName, e);
        }
        return new WebDriverWrapperDto(null, "登录失败！" + "未知原因");
    }
}
