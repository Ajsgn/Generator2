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

import org.apache.commons.lang3.StringUtils;

/**
 * <p>列类型映射Factory</p>
 * <li>oracle</li>
 * <li>mysql</li>
 * @ClassName: ColumnMappingFactory
 * @Description: 列类型映射Factory
 * @author Ajsgn@foxmail.com
 * @date 2017年10月22日 下午8:15:29
 */
public class ColumnMappingFactory {
	
	/**
	 * <p>通过DriverClassName获取当前的列类型映射</p>
	 * @Title: instanceOf
	 * @Description: 通过DriverClassName获取当前的列类型映射
	 * @param driverClassName 驱动类名称
	 * @return ColumnMapping    返回类型
	 * @author Ajsgn@foxmail.com
	 * @date 2017年10月22日 下午8:16:35
	 * @throws
	 */
	public static ColumnMapping instanceOf(String driverClassName) {
		ColumnMapping cm = null;
		if(StringUtils.containsIgnoreCase(driverClassName, "ORACLE")){
			cm = OracleColumnMapping.singletonInstance();
		} else if(StringUtils.containsIgnoreCase(driverClassName, "MYSQL")){
			cm = MysqlColumnMapping.singletonInstance();
		} else {
			throw new RuntimeException("当前不支持的数据库驱动类型：".concat(driverClassName));
		}
		return cm;
	}
	
}
