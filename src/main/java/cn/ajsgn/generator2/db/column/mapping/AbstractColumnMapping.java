/*
 * Copyright (c) 2017, Ajsgn 杨光 (Ajsgn@foxmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.ajsgn.generator2.db.column.mapping;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>抽象的列类型映射</p>
 * @ClassName: AbstractColumnMapping
 * @Description: 抽象的列类型映射
 * @author Ajsgn@foxmail.com
 * @date 2017年10月22日 下午8:11:14
 */
abstract class AbstractColumnMapping implements ColumnMapping{
	
	/**
	 * ColumnTypeName -> javaType 
	 */
	private Map<String,String> javaTypeMapping = new HashMap<String,String>();
	
	/**
	 * ColumnTypeName -> jdbcType
	 */
	private Map<String,String> jdbcTypeMapping = new HashMap<String,String>();
	
	/**
	 * 构造函数
	 */
	public AbstractColumnMapping(){
		// javaType 初始化
		javaTypeMappingInit();
		// jdbcType 初始化
		jdbcTypeMappingInit();
	}
	
	@Override
	public String getColumnClassName(String columnTypeName) {
		return javaTypeMapping.get(StringUtils.defaultIfBlank(columnTypeName, "").toUpperCase());
	}
	
	@Override
	public void addColumnClassName(String columnTypeName, String javaType) {
		javaTypeMapping.put(StringUtils.defaultIfBlank(columnTypeName, "").toUpperCase(), StringUtils.defaultIfBlank(javaType, ""));
	}

	@Override
	public String getDbColumnType(String columnTypeName) {
		return jdbcTypeMapping.get(StringUtils.defaultIfBlank(columnTypeName, "").toUpperCase());
	}

	@Override
	public void addDbColumnType(String columnTypeName, String jdbcType) {
		jdbcTypeMapping.put(StringUtils.defaultIfBlank(columnTypeName, "").toUpperCase(), StringUtils.defaultIfBlank(jdbcType, ""));
	}
	
	/**
	 * <p>jdbcType初始化</p>
	 * @Title: jdbcTypeMappingInit
	 * @Description: jdbcType 初始化
	 * @author Ajsgn@foxmail.com
	 * @date 2017年10月22日 下午8:14:23
	 */
	protected abstract void jdbcTypeMappingInit();
	
	/**
	 * <p>javaType初始化</p>
	 * @Title: javaTypeMappingInit
	 * @Description: javaType初始化
	 * @author Ajsgn@foxmail.com
	 * @date 2017年10月22日 下午8:14:46
	 */
	protected abstract void javaTypeMappingInit();
	
	/**
	 * toString()
	 */
	@Override
	public String toString() {
		return "AbstractColumnMapping [javaTypeMapping=" + javaTypeMapping + ", jdbcTypeMapping=" + jdbcTypeMapping + "]";
	}
	
}
