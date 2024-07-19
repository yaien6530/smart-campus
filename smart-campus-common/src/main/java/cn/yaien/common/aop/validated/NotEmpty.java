package cn.yaien.common.aop.validated;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义@NotEmpty注解，处理请求参数校验
 *
 * @author yanggl
 * @since 2024/7/15/11:45
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmpty {

    /**
     * 校验不通过的异常描述
     *
     * @return 异常描述
     */
    String message() default "Parameter cannot be empty";
}
