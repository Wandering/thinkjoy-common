package org.jeecgframework.poi.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 15/1/19 下午8:07<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ExcelEntity {
    String id() default "";

    String name() default "";
}
