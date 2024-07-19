package cn.yaien.common.error;

import lombok.Getter;
import lombok.Setter;

/**
 * 业务异常
 * 默认code：9999
 *
 * @author yanggl
 * @since 2024/7/16/17:46
 */
@Setter
public class BusinessException extends RuntimeException {
    @Getter
    protected int code;

    protected String message;

    public BusinessException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BusinessException(CodeMsg codeMsg) {
        this.code = codeMsg.getCode();
        this.message = codeMsg.getMessage();
    }

    public BusinessException(String message) {
        this.code = 9999;
        this.message = message;
    }

    public static BusinessException of(String message) {
        return new BusinessException(message);
    }

    public static BusinessException of(int code, String message) {
        return new BusinessException(code, message);
    }

    @Override
    public String getMessage() {
        return message;
    }

}
