package com.cbg.scrapy.web.vo.business;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class TaskAddVo implements Serializable {

        /**
         * 商品连接
         */
        @NotBlank(message = "任务链接不能为空")
        @Pattern(regexp = "^(https|http)+.*cbg.163.com.*$", message = "任务链接必须是藏宝阁链接")
        private String url;

        /**
         * 商品角色
         */
        private String roleName;

        /**
         * 卖家摆价
         */
        private BigDecimal salesPrice;

        /**
         * 用户还价价格
         */
        @NotNull(message = "用户还价价格不能为空")
        @Min(value = 1, message = "用户还价价格不能小于等于0")
        private BigDecimal userCounterPrice;

        /**
         * 用户备注
         */
        private String userComment;

        /**
         * 是否作为昨天角色
         */
        private boolean markAsYestRole;

}
