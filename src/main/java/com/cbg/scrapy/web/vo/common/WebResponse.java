package com.cbg.scrapy.web.vo.common;

import com.cbg.scrapy.web.enu.WebResponseStatus;
import lombok.Data;

import java.io.Serializable;

@Data
public class WebResponse<T> implements Serializable {

    /**
     * 状态码
     * @see WebResponseStatus#getStatus()
     */
    private int code;

    /**
     * 失败信息
     * @see  WebResponseStatus#getDesc()
     */
    private String msg;

    /**
     * 具体返回数据
     */
    private T data;

    public WebResponse(WebResponseStatus webResponseStatus, T data) {
        this.code = webResponseStatus.getStatus();
        this.msg = webResponseStatus.getDesc();
        this.data = data;
    }

    public WebResponse(WebResponseStatus webResponseStatus, String msg) {
        this.code = webResponseStatus.getStatus();
        this.msg = msg;
    }

    public static <T> WebResponse<T> buildData(T data) {
        return new WebResponse<>(WebResponseStatus.SUCCESS, data);
    }

    public static <T> WebResponse<T> buildError(WebResponseStatus webResponseStatus) {
        return new WebResponse<>(webResponseStatus, null);
    }

    public static <T> WebResponse<T> buildError(WebResponseStatus webResponseStatus, String msg) {
        return new WebResponse<>(webResponseStatus, msg);
    }
}
