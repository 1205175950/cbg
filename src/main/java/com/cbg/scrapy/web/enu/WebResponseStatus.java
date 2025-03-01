package com.cbg.scrapy.web.enu;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WebResponseStatus {

    SUCCESS(200, "成功"),
    ERROR(400, "系统错误");

    private final int status;
    private final String desc;
}
