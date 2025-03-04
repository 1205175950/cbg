package com.cbg.scrapy.web.vo.business;

import com.cbg.scrapy.web.enu.OperateStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class TaskUpdateVo implements Serializable {

    /**
     * 任务ID
     */
    @NotNull(message = "更新的任务id不能为空")
    private Long id;

    /**
     *  用户最新跟进价格
     */
    private BigDecimal userLatestFollowUpPrice;

    /**
     *  操作状态
     * @see OperateStatus#getStatus()
     */
    private Integer operateStatus;

}
