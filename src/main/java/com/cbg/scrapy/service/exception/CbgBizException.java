package com.cbg.scrapy.service.exception;

public class CbgBizException extends RuntimeException {

    public CbgBizException(String msg) {
        super(msg);
    }

    public static CbgBizException fly(String msg) {
        throw new CbgBizException(msg);
    }
}
