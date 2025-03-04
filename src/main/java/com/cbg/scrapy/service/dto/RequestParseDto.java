package com.cbg.scrapy.service.dto;

import lombok.Data;

@Data
public class RequestParseDto {

    /**
     * 不带请求参数的原始链接
     */
    private String url;

    /**
     * 流水号
     */
    private String eid;

    /**
     * 服务器id
     */
    private Integer serverId;
}
