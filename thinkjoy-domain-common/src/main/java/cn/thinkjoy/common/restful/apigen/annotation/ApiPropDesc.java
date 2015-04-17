package cn.thinkjoy.common.restful.apigen.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 15/4/4 下午7:38<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiPropDesc {
    /**
     * 属性描述
     * @return
     */
    String value() default "";
}
