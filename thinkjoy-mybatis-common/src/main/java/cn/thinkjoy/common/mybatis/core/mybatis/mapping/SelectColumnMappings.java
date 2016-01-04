package cn.thinkjoy.common.mybatis.core.mybatis.mapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 查询列与实体类属性映射集
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface SelectColumnMappings {

	SelectColumnMapping[] value();

}
