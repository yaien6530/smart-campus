package cn.yaien.common.error;

import lombok.Getter;

/**
 * 错误码区间定义 #200～成功 #1000～1999 区间 表示参数错误 #2000～2999 区间 表示网络错误 #3000～3999 区间 表示IO错误 #4000～4999 区间 表示用户错误 #5000～5999 区间
 * 表示系统错误
 */
@Getter
public enum CodeMsg {
    // 200 表示成功
    SUCCESS(200, "操作成功"),
    NO_DATA(201, "数据不存在"),


    SUCCESS_ORDER(9999, "下单成功");

    private final Integer code;

    private final String message;

    CodeMsg(Integer errorCode, String errorMsg) {
        code = errorCode;
        message = errorMsg;
    }

}
