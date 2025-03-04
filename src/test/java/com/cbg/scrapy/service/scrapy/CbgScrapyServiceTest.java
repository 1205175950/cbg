package com.cbg.scrapy.service.scrapy;

import com.cbg.scrapy.AbstractBastTest;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CbgScrapyServiceTest extends AbstractBastTest {

    @Resource
    private CbgScrapyService cbgScrapyService;

    @Test
    public void getShopInfo() throws Exception {
        String url = "https://xyq.cbg.163.com/equip?s=299&eid=202503020800113-299-6JIKSO5BWFOW&o";
        System.out.println(cbgScrapyService.sendPostRequest(url));
    }

    public static void main(String[] args) throws InterruptedException {
        // 谷歌驱动
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--user-agent=\"Android\"");
        options.setBinary("C:\\Users\\Administrator\\Desktop\\chrome-win64\\chrome.exe");
        WebDriver webDriver = new ChromeDriver(options);
        // 启动需要打开的网页
        webDriver.get("https://xyq.cbg.163.com/equip?s=133&eid=202503021800113-133-ISLOPWNMVBGA&o");
        Thread.sleep(5000);
        WebElement div = webDriver.findElements(By.className("action-other")).get(1);
        // 点击“还价”按钮
        WebElement webElement = div.findElements(By.tagName("a")).get(1);
        System.out.println(webElement.getText());
        webElement.click();

        // 等待一段时间，观察点击后的效果（可选）
        Thread.sleep(3000);
        WebElement iframe = webDriver.findElement(By.tagName("iframe"));
        webDriver.switchTo().frame(iframe);
        System.out.println(1000);
        webDriver.findElement(By.name("email")).clear();
        webDriver.findElement(By.name("email")).sendKeys("17326788164@163.com");
        webDriver.findElement(By.name("password")).clear();
        webDriver.findElement(By.name("password")).sendKeys("13912971461Lzy");
        webDriver.findElement(By.id("dologin")).click();
       Thread.sleep(8000);
        webDriver.switchTo().defaultContent();
        webDriver.get("https://xyq.cbg.163.com/equip?s=133&eid=202503021800113-133-ISLOPWNMVBGA&o");
        Thread.sleep(3000);
        WebElement div2 = webDriver.findElements(By.className("action-other")).get(1);
        // 点击“还价”按钮
        WebElement webElement2 = div2.findElements(By.tagName("a")).get(1);
        System.out.println(webElement2.getText());
        webElement2.click();
        Thread.sleep(2000);
        System.out.println(webDriver.getPageSource());
        webDriver.findElements(By.cssSelector("div.page-loading.vpa-router-view > div > div.request-status-ctrl.content > div > div > div.area-select-scroll > div:nth-child(2) > a")).get(0).click();
        System.out.println(1000);
        System.out.println(webDriver.getPageSource());
    }
}
