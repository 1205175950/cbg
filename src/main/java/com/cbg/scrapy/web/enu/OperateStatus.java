package com.cbg.scrapy.web.enu;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum OperateStatus {

    STAY_TUNED(1, "持续关注"),
    PEND_OPERATE(2, "待处理"),
    FOLLOWED_UP(3, "已跟进"),
    NEED_SPEED_UP(4, "需要加急"),
    ALREADY_EXPIRED(5, "已经过期"),
    ALREADY_BOOKED(6, "已经预定"),
    ;

    private final int status;
    private final String desc;

    public static String getDesc(Integer code) {
        if (null == code) {
            return "";
        }
        for (OperateStatus item : OperateStatus.values()) {
            if (item.getStatus() == code) {
                return item.getDesc();
            }
        }
        return "";
    }
}
