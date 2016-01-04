package cn.thinkjoy.common.mybatis.core.mybatis.criteria.impl;

import cn.thinkjoy.common.mybatis.core.mybatis.mapping.SelectColumnMapping;
import cn.thinkjoy.common.mybatis.core.mybatis.mapping.SelectColumnMappings;
import cn.thinkjoy.common.mybatis.core.mybatis.utils.Arrays;
import cn.thinkjoy.common.mybatis.core.mybatis.utils.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 
 * 基于Annotation配置的列<->属性映射器
 * 
 */
public class AnnotationColumnMapper implements ColumnMapper {

	public Column getColumn(Class<?> entityClass, String property) {
		Map<String, SelectColumnMapping> mappings = getMappings(entityClass);
		if (mappings != null && mappings.get(property) != null) {
			SelectColumnMapping scm = mappings.get(property);
			return new Column(scm.column(), StringUtils.defaultIfEmpty(scm.tableAlias(), scm.table()), scm.type());
		}
		return null;
	}

	private Map<String, SelectColumnMapping> getMappings(Class<?> entityClass) {
		Map<String, SelectColumnMapping> cachedMapping = cachedPool.get(entityClass);
		if (cachedMapping == null) {
			cachedMapping = Maps.newMap();
			SelectColumnMappings mappings = entityClass.getAnnotation(SelectColumnMappings.class);
			if(mappings != null){
				SelectColumnMapping[] mps = mappings.value();
				if (Arrays.isNotEmpty(mps)) {
					for (SelectColumnMapping m : mps) {
						cachedMapping.put(m.property(), m);
					}
				}
				cachedPool.put(entityClass, cachedMapping);
			}
		}
		return cachedMapping;
	}

	private static Map<Class<?>, Map<String, SelectColumnMapping>> cachedPool = Maps.newMap();

}
