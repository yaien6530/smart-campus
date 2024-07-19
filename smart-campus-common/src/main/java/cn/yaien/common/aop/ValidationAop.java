package cn.yaien.common.aop;

import cn.yaien.common.aop.validated.NotBlank;
import cn.yaien.common.aop.validated.NotEmpty;
import cn.yaien.common.aop.validated.NotNull;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 参数校验注解
 *
 * @author yanggl
 * @since 2024/7/15/11:50
 */
//@Aspect
//@Component
public class ValidationAop {

    @Pointcut("@annotation(cn.yaien.common.aop.validated.NotNull)")
    private void notNullAop() {
    }

    @Pointcut("@annotation(cn.yaien.common.aop.validated.NotBlank)")
    private void notBlankAop() {
    }

    @Pointcut("@annotation(cn.yaien.common.aop.validated.NotEmpty)")
    private void notEmptyAop() {
    }

    @Pointcut("notNullAop() || notBlankAop() || notEmptyAop()")
    private void validateAop() {
    }

    @Before("validateAop()")
    public void validateParameters(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();

        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            for (Annotation annotation : parameterAnnotations[i]) {
                Object arg = args[i];
                if (annotation instanceof NotNull) {
                    if (arg == null) {
                        throw new IllegalArgumentException(((NotNull) annotation).message());
                    }
                } else if (annotation instanceof NotBlank) {
                    if (arg == null || arg.toString().trim().isEmpty()) {
                        throw new IllegalArgumentException(((NotBlank) annotation).message());
                    }
                } else if (annotation instanceof NotEmpty) {
                    if (arg == null || arg.toString().isEmpty()) {
                        throw new IllegalArgumentException(((NotEmpty) annotation).message());
                    }
                }
            }
        }
    }


}
