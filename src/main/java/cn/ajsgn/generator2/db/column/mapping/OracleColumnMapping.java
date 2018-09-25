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

/**
 * <p>Oracle列类型映射</p>
 * @ClassName: OracleColumnMapping
 * @Description: Oracle列类型映射
 * @author g.yang@i-vpoints.com
 * @date 2017年10月16日 下午6:32:21
 */
class OracleColumnMapping extends AbstractColumnMapping{
	
	private static final OracleColumnMapping ME = new OracleColumnMapping();
	
	private OracleColumnMapping() {
		
	}
	
	public static ColumnMapping singletonInstance() {
		return ME;
	}
	
	@Override
	protected void javaTypeMappingInit(){
		addColumnClassName("BFILE", "oracle.jdbc.OracleBfile");
		addColumnClassName("BINARY_DOUBLE", "java.lang.Double");
		addColumnClassName("BINARY_FLOAT", "java.lang.Float");
		addColumnClassName("BLOB", "oracle.jdbc.OracleBlob");
		addColumnClassName("CHAR", "java.lang.String");
		addColumnClassName("VARCHAR2", "java.lang.String");
		addColumnClassName("CLOB", "oracle.jdbc.OracleClob");
		addColumnClassName("DATE", "java.sql.Timestamp");
		addColumnClassName("NUMBER", "java.math.BigDecimal");
		addColumnClassName("INTERVALDS", "oracle.sql.INTERVALDS");
		addColumnClassName("INTERVALYM", "oracle.sql.INTERVALYM");
		addColumnClassName("INTERVAL DAY(2) TO SECOND(6)", "java.lang.Object");
		addColumnClassName("INTERVAL YEAR(2) TO MONTH", "java.lang.Object");
		addColumnClassName("FLOAT", "java.lang.Float");
		addColumnClassName("LONG", "java.lang.String");
		addColumnClassName("NCHAR", "java.lang.String");
		addColumnClassName("NVARCHAR2", "java.lang.String");
		addColumnClassName("NCLOB", "oracle.jdbc.OracleNClob");
		addColumnClassName("NVARCHAR2", "java.lang.String");
		addColumnClassName("RAW", "java.lang.Byte[]");
		addColumnClassName("ROWID", "oracle.sql.ROWID");
		addColumnClassName("UROWID", "oracle.sql.ROWID");
		addColumnClassName("TIMESTAMP", "java.sql.Timestamp");
		addColumnClassName("TIMESTAMP(6)", "oracle.sql.TIMESTAMP");
	}
	
	@Override
	protected void jdbcTypeMappingInit(){
		addDbColumnType("BFILE", "OTHER");
		addDbColumnType("BINARY_DOUBLE", "OTHER");
		addDbColumnType("BINARY_FLOAT", "OTHER");
		addDbColumnType("BLOB", "BLOB");
		addDbColumnType("CHAR", "CHAR");
		addDbColumnType("VARCHAR2", "VARCHAR");
		addDbColumnType("CLOB", "CLOB");
		addDbColumnType("DATE", "DATE");
		addDbColumnType("FLOAT", "FLOAT");
		addDbColumnType("NUMBER", "DECIMAL");
		addDbColumnType("INTERVALDS", "OTHER");
		addDbColumnType("INTERVALYM", "OTHER");
		addDbColumnType("INTERVAL DAY(2) TO SECOND(6)", "OTHER");
		addDbColumnType("INTERVAL YEAR(2) TO MONTH", "OTHER");
		addDbColumnType("LONG", "LONG VARCHAR");
		addDbColumnType("NCHAR", "NCHAR");
		addDbColumnType("NVARCHAR2", "OTHER");
		addDbColumnType("NCLOB", "NCLOB");
		addDbColumnType("RAW", "OTHER");
		addDbColumnType("ROWID", "OTHER");
		addDbColumnType("UROWID", "OTHER");
		addDbColumnType("TIMESTAMP", "TIMESTAMP");
		addDbColumnType("TIMESTAMP(6)", "TIMESTAMP");
	}

}
