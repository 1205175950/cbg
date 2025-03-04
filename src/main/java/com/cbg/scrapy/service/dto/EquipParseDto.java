package com.cbg.scrapy.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class EquipParseDto implements Serializable {

    /**
     * 角色级别，如：175级
     */
    private String title;

    /**
     * 卖家售价，如：1727000（最后两位为小数后两位）
     */
    private Long price;

    /**
     * 角色名称，如：暮色离人归
     */
    private String seller_name;

    /**
     * 上架状态，如：上架中
     */
    private String status_desc;

    /**
     * 上架过期时间，如：2025-03-16 09:57:10
     */
    private Date expire_time;

    /**
     * 上架状态具体描述信息，
     * {
     *     "status": "上架中",
     *     "expire_time": "13天18时"
     * }
     */
    private SellingInfoParseDto selling_info_data;

}
