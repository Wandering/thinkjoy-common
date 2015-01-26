package org.jeecgframework.poi.excel.annotation;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 15/1/19 下午8:10<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public @interface ExcelVerify {
    boolean interHandler() default true;

    boolean isEmail() default false;

    boolean isMobile() default false;

    boolean isTel() default false;

    int maxLength() default -1;

    int minLength() default -1;

    boolean notNull() default false;

    String regex() default "";

    String regexTip() default "数据不符合规范";
}
