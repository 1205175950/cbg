package com.cbg.scrapy.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.openqa.selenium.WebDriver;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class WebDriverWrapperDto implements Serializable {

    /**
     * 驱动对象
     */
    private WebDriver webDriver;

    /**
     * 网易登录错误信息
     */
    private String errMsg;
}
