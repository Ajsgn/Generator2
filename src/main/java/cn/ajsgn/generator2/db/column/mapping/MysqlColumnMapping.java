/*
 * Copyright (c) 2018, Ajsgn 杨光 (Ajsgn@foxmail.com).
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

/**
 * <p>mysql列类型映射</p>
 * @ClassName: MysqlColumnMapping
 * @Description: <p>mysql列类型映射</p>
 * @author Ajsgn@foxmail.com
 * @date 2017年10月22日 下午8:18:02
 */
class MysqlColumnMapping extends AbstractColumnMapping {
	
	private static final MysqlColumnMapping ME = new MysqlColumnMapping();
	
	private MysqlColumnMapping() {
		
	}
	
	public static ColumnMapping singletonInstance() {
		return ME;
	}
	
	@Override
	protected void javaTypeMappingInit(){
		addColumnClassName("INT","java.lang.Integer");
		addColumnClassName("INT UNSIGNED","java.lang.Integer");
		addColumnClassName("BIGINT","java.lang.Long");
		addColumnClassName("BIGINT UNSIGNED","java.lang.Long");
		addColumnClassName("BINARY","java.lang.Byte[]");
		addColumnClassName("BIT","java.lang.Boolean");
		addColumnClassName("BLOB","java.lang.Byte[]");
		addColumnClassName("BIT","java.lang.Boolean");
		addColumnClassName("CHAR","java.lang.String");
		addColumnClassName("DATE","java.util.Date");
		addColumnClassName("DATETIME","java.util.Date");
		addColumnClassName("DECIMAL","java.math.BigDecimal");
		addColumnClassName("DOUBLE","java.lang.Double");
		addColumnClassName("ENUM","java.lang.String");
		addColumnClassName("FLOAT","java.lang.Float");
		addColumnClassName("INT","java.lang.Integer");
		addColumnClassName("LONGBLOB","java.lang.Byte[]");
		addColumnClassName("LONGTEXT","java.lang.String");
		addColumnClassName("MEDIUMBLOB","java.lang.Byte[]");
		addColumnClassName("MEDIUMINT","java.lang.Integer");
		addColumnClassName("MEDIUMTEXT","java.lang.String");
		addColumnClassName("DECIMAL","java.math.BigDecimal");
		addColumnClassName("DOUBLE","java.lang.Double");
		addColumnClassName("SET","java.lang.String");
		addColumnClassName("SMALLINT","java.lang.Integer");
		addColumnClassName("TEXT","java.lang.String");
		addColumnClassName("TIME","java.util.Date");
		addColumnClassName("TIMESTAMP","java.util.Date");
		addColumnClassName("TINYBLOB","java.lang.Byte[]");
		addColumnClassName("TINYINT","java.lang.Integer");
		addColumnClassName("TINYTEXT","java.lang.String");
		addColumnClassName("VARBINARY","java.lang.Byte[]");
		addColumnClassName("VARCHAR","java.lang.String");
		addColumnClassName("YEAR","java.util.Date");
	}
	
	@Override
	protected void jdbcTypeMappingInit(){
		addDbColumnType("INT","INTEGER");
		addDbColumnType("INT UNSIGNED","INTEGER");
		addDbColumnType("BIGINT","BIGINT");
		addDbColumnType("BINARY","BINARY");
		addDbColumnType("BIT","BIT");
		addDbColumnType("BLOB","LONGVARBINARY");
		addDbColumnType("BIT","BIT");
		addDbColumnType("BIT","BIT");
		addDbColumnType("CHAR","CHAR");
		addDbColumnType("DATE","DATE");
		addDbColumnType("DATETIME","TIMESTAMP");
		addDbColumnType("DECIMAL","DECIMAL");
		addDbColumnType("DOUBLE","DOUBLE");
		addDbColumnType("ENUM","CHAR");
		addDbColumnType("FLOAT","FLOAT");
		addDbColumnType("INT","INTEGER");
		addDbColumnType("LONGBLOB","LONGVARBINARY");
		addDbColumnType("LONGTEXT","LONGVARCHAR");
		addDbColumnType("MEDIUMBLOB","LONGVARBINARY");
		addDbColumnType("MEDIUMINT","INTEGER");
		addDbColumnType("MEDIUMTEXT","LONGVARCHAR");
		addDbColumnType("DECIMAL","DECIMAL");
		addDbColumnType("DOUBLE","DOUBLE");
		addDbColumnType("SET","CHAR");
		addDbColumnType("SMALLINT","SMALLINT");
		addDbColumnType("TEXT","LONGVARCHAR");
		addDbColumnType("TIME","TIME");
		addDbColumnType("TIMESTAMP","TIMESTAMP");
		addDbColumnType("TINYBLOB","BINARY");
		addDbColumnType("TINYINT","TINYINT");
		addDbColumnType("TINYTEXT","VARCHAR");
		addDbColumnType("VARBINARY","VARBINARY");
		addDbColumnType("VARCHAR","VARCHAR");
		addDbColumnType("YEAR","DATE");
	}

}
