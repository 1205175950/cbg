package com.cbg.scrapy.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseParseDto implements Serializable {

    /**
     * 网易返回的业务响应状态码,1/0
     */
    private Integer status;

    /**
     * 网易返回的业务响应状态描述，OK/ERR
     */
    private String status_code;

    /**
     * 错误信息的中文描述
     */
    private String msg;

    /**
     * 装备详情
     */
    private EquipParseDto equip;

}
