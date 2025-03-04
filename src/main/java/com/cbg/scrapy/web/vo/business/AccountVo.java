package com.cbg.scrapy.web.vo.business;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class AccountVo implements Serializable {

    /**
     * 账号id
     */
    private Long id;

    /**
     * 账号名称
     */
    @NotBlank(message = "账号名称不能为空")
    private String userName;

    /**
     * 密码
     */
    @NotBlank(message = "账号密码不能为空")
    private String password;

    /**
     * 当前账号状态
     */
    @NotNull
    private Integer status;
}
