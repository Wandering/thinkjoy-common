package cn.thinkjoy.common.restful.apigen.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 参数注解
 * <p/>
 * 创建时间: 15/4/5 下午7:13<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiParam {
    String param() default "";

    String desc() default "";

    boolean required() default false;
}
