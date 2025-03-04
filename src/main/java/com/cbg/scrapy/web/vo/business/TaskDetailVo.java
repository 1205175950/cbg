package com.cbg.scrapy.web.vo.business;

import com.cbg.scrapy.web.enu.OperateStatus;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

@Data
public class TaskDetailVo implements Serializable {

    /**
     *  任务id
     */
    private Long id;

    /**
     *  商品链接
     */
    private String url;

    /**
     *  角色名
     */
    private String roleName;

    /**
     *  商家出售价格
     */
    private DecimalFormat salesPrice;


    /**
     *  用户首次还价价格
     */
    private BigDecimal userFirstCounterPrice;

    /**
     *  用户首次还价时间
     */
    private Date userFirstCounterTime;

    /**
     *  用户最新跟进价格
     */
    private BigDecimal userLatestFollowUpPrice;

    /**
     *  当前所有买家的最高还价价格
     */
    private BigDecimal currentBuyerHighestCounterPrice;

    /**
     *  当前所有买家的最高被拒绝价格
     */
    private BigDecimal currentBuyerHighestRejectPrice;

    /**
     *  操作状态
     * @see OperateStatus#getStatus()
     */
    private Integer operateStatus;

    /**
     * 操作状态中文描述
     */
    private String operateStatusDesc;

    /**
     *  用户备注
     */
    private String userComment;

    /**
     *  用户作为最高价格被买家拒绝时的时间
     */
    private Date highestRejectTime;

    /**
     *  新增时间
     */
    private Date addTime;

    /**
     *  更新时间
     */
    private Date updateTime;
}
