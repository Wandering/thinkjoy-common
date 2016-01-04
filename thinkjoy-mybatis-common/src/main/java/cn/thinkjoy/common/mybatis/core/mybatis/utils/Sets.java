package cn.thinkjoy.common.mybatis.core.mybatis.utils;

import org.apache.commons.collections4.SetUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * set 工具集
 * @author Chujiang
 *
 */
public class Sets extends SetUtils {
	public static <T>Set<T> newSet() {
		return new HashSet<T>();
	}
}
