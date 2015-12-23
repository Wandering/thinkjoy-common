package cn.thinkjoy.cloudstack.router.annotation;

import java.lang.annotation.*;

/**
 * DB annotation 配置
 * <p/>
 * 创建时间: 15/4/28 下午1:03<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface DBShardAnnotation {
    public String ruleKey();

    public String[] ruleProps();

    public boolean isUseThreadLocal() default false;
}
