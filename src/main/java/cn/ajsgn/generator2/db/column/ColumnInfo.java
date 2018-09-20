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
package cn.ajsgn.generator2.db.column;

import cn.ajsgn.generator2.util.StrKit;

/**
 * <p>类类型信息对象</p>
 * @ClassName: ColumnInfo
 * @Description: 类类型信息对象
 * @author Ajsgn@foxmail.com
 * @date 2017年10月22日 下午4:09:45
 */
public class ColumnInfo {

	private String tableCat;
	private String tableSchem;
	private String tableName;
	private String columnName;
	private int dataType;
	private String typeName;
	private int columnSize;
	private String bufferLength;
	private int decimalDigits;
	private int numPrecRadix;
	private int nullable;
	private String remarks;
	private String columnDef;
	private int sqlDataType;
	private int sqlDatetimeSub;
	private int charOctetLength;
	private int ordinalPosition;
	private String isNullable;
	private String scopeCatlog;
	private String scopeSchema;
	private String scopeTable;
	private int sourceDataType;
	private String isAutoincrement;

	/**
	 * String => 表类别（可为 null）
	 */
	public String getTableCat() {
		return tableCat;
	}
	
	/**
	 * String => 表模式（可为 null）
	 */
	public String getTableSchem() {
		return tableSchem;
	}

	/**
	 * String => 表名称
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * String => 列名称
	 */
	public String getColumnName() {
		return columnName;
	}
	
	public String getColumnCamelName(){
		return StrKit.toCamelCase(getColumnName().toLowerCase());
	}
	
	public String getColumnEntityName(){
		return StrKit.firstCharToUpperCase(getColumnCamelName());
	}

	/**
	 * int => 来自 java.sql.Types 的 SQL 类型
	 */
	public int getDataType() {
		return dataType;
	}

	/**
	 * String => 数据源依赖的类型名称，对于 UDT，该类型名称是完全限定的
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * int => 列的大小。
	 */
	public int getColumnSize() {
		return columnSize;
	}

	/**
	 * 未被使用。
	 */
	public String getBufferLength() {
		return bufferLength;
	}

	/**
	 * int => 小数部分的位数。对于 DECIMAL_DIGITS 不适用的数据类型，则返回 Null。
	 */
	public int getDecimalDigits() {
		return decimalDigits;
	}

	/**
	 * int => 基数（通常为 10 或 2）
	 */
	public int getNumPrecRadix() {
		return numPrecRadix;
	}

	/**
	 * int => 是否允许使用 NULL。 columnNoNulls - 可能不允许使用 NULL 值 columnNullable -
	 * 明确允许使用 NULL 值 columnNullableUnknown - 不知道是否可使用 null
	 */
	public int getNullable() {
		return nullable;
	}

	/**
	 * String => 描述列的注释（可为 null）
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * String => 该列的默认值，当值在单引号内时应被解释为一个字符串（可为 null）
	 */
	public String getColumnDef() {
		return columnDef;
	}

	/**
	 * int => 未使用
	 */
	public int getSqlDataType() {
		return sqlDataType;
	}

	/**
	 * int => 未使用
	 */
	public int getSqlDatetimeSub() {
		return sqlDatetimeSub;
	}

	/**
	 * int => 对于 char 类型，该长度是列中的最大字节数
	 */
	public int getCharOctetLength() {
		return charOctetLength;
	}

	/**
	 * int => 表中的列的索引（从 1 开始）
	 */
	public int getOrdinalPosition() {
		return ordinalPosition;
	}

	/**
	 * String => ISO 规则用于确定列是否包括 null。 YES --- 如果参数可以包括 NULL NO --- 如果参数不可以包括
	 * NULL 空字符串 --- 如果不知道参数是否可以包括 null
	 */
	public String getIsNullable() {
		return isNullable;
	}

	/**
	 * String => 表的类别，它是引用属性的作用域（如果 DATA_TYPE 不是 REF，则为 null）
	 */
	public String getScopeCatlog() {
		return scopeCatlog;
	}

	/**
	 * String => 表的模式，它是引用属性的作用域（如果 DATA_TYPE 不是 REF，则为 null）
	 */
	public String getScopeSchema() {
		return scopeSchema;
	}

	/**
	 * String => 表名称，它是引用属性的作用域（如果 DATA_TYPE 不是 REF，则为 null）
	 */
	public String getScopeTable() {
		return scopeTable;
	}

	/**
	 * short => 不同类型或用户生成 Ref 类型、来自 java.sql.Types 的 SQL 类型的源类型（如果 DATA_TYPE 不是
	 * DISTINCT 或用户生成的 REF，则为 null）
	 */
	public int getSourceDataType() {
		return sourceDataType;
	}

	/**
	 * String => 指示此列是否自动增加 YES --- 如果该列自动增加 NO --- 如果该列不自动增加 空字符串 ---
	 * 如果不能确定该列是否是自动增加参数
	 */
	public String getIsAutoincrement() {
		return isAutoincrement;
	}

	public void setTableCat(String tableCat) {
		this.tableCat = tableCat;
	}

	public void setTableSchem(String tableSchem) {
		this.tableSchem = tableSchem;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}

	public void setBufferLength(String bufferLength) {
		this.bufferLength = bufferLength;
	}

	public void setDecimalDigits(int decimalDigits) {
		this.decimalDigits = decimalDigits;
	}

	public void setNumPrecRadix(int numPrecRadix) {
		this.numPrecRadix = numPrecRadix;
	}

	public void setNullable(int nullable) {
		this.nullable = nullable;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setColumnDef(String columnDef) {
		this.columnDef = columnDef;
	}

	public void setSqlDataType(int sqlDataType) {
		this.sqlDataType = sqlDataType;
	}

	public void setSqlDatetimeSub(int sqlDatetimeSub) {
		this.sqlDatetimeSub = sqlDatetimeSub;
	}

	public void setCharOctetLength(int charOctetLength) {
		this.charOctetLength = charOctetLength;
	}

	public void setOrdinalPosition(int ordinalPosition) {
		this.ordinalPosition = ordinalPosition;
	}

	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}

	public void setScopeCatlog(String scopeCatlog) {
		this.scopeCatlog = scopeCatlog;
	}

	public void setScopeSchema(String scopeSchema) {
		this.scopeSchema = scopeSchema;
	}

	public void setScopeTable(String scopeTable) {
		this.scopeTable = scopeTable;
	}

	public void setSourceDataType(int sourceDataType) {
		this.sourceDataType = sourceDataType;
	}

	public void setIsAutoincrement(String isAutoincrement) {
		this.isAutoincrement = isAutoincrement;
	}
	
	@Override
	public String toString() {
		return "ColumnMetaData [tableCat=" + tableCat + ", tableSchem=" + tableSchem + ", tableName=" + tableName
				+ ", columnName=" + columnName + ", dataType=" + dataType + ", typeName=" + typeName + ", columnSize="
				+ columnSize + ", bufferLength=" + bufferLength + ", decimalDigits=" + decimalDigits + ", numPrecRadix="
				+ numPrecRadix + ", nullable=" + nullable + ", remarks=" + remarks + ", columnDef=" + columnDef
				+ ", sqlDataType=" + sqlDataType + ", sqlDatetimeSub=" + sqlDatetimeSub + ", charOctetLength="
				+ charOctetLength + ", ordinalPosition=" + ordinalPosition + ", isNullable=" + isNullable
				+ ", scopeCatlog=" + scopeCatlog + ", scopeSchema=" + scopeSchema + ", scopeTable=" + scopeTable
				+ ", sourceDataType=" + sourceDataType + ", isAutoincrement=" + isAutoincrement + "]";
	}

}
