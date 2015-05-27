package cn.thinkjoy.common.restful.apigen.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记协议描述
 * Created by zxxiao on 14/12/25.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiDesc {

    /**
     * 功能所属模块
     * @return
     */
    String module() default "";

    /**
     * 协议描述
     * @return
     */
    String value() default "";

    /**
     * 责任人
     * @return
     */
    String owner() default "";

    String returnDesc() default "";
}
