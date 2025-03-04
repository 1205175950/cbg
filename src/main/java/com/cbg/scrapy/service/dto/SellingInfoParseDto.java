package com.cbg.scrapy.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SellingInfoParseDto implements Serializable {

    /**
     * 上架状态
     */
    private String status;

    /**
     * 过期时间
     */
    private String expire_time;
}
