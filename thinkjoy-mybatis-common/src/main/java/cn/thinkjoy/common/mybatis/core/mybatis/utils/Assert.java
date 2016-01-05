package cn.thinkjoy.common.mybatis.core.mybatis.utils;

import org.apache.commons.lang3.Validate;

/**
 * 断言工具类，用于检查传入的参数是否合法等功能
 * @author Chujiang
 *
 */
public abstract class Assert extends Validate {
	
	public static void noneNull(Object...objs) {
		for (Object obj : objs) {
			notNull(obj);
		}
	}
}
