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
package cn.ajsgn.generator2.db.config;

/**
 * <p>列类型枚举，用于 java.sql.DatabaseMetaData 获取类类型参数值</p>
 * @ClassName: ColumnTypeEnum
 * @Description: 列类型枚举，用于 java.sql.DatabaseMetaData 获取类类型参数值
 * @author Ajsgn@foxmail.com
 * @date 2017年10月22日 下午8:19:42
 */
public enum ColumnTypeEnum {
	
	/**
	 *  String => 表类别（可为 null） 
	 */
	TABLE_CAT(1),
	/**
	 *  String => 表模式（可为 null）
	 */
	TABLE_SCHEM(2),
	/**
	 *  String => 表名称 
	 */
	TABLE_NAME(3),
	/**
	 *  String => 列名称 
	 */
	COLUMN_NAME(4),
	/**
	 *  int => 来自 java.sql.Types 的 SQL 类型 
	 */
	DATA_TYPE(5),
	/**
	 *  String => 数据源依赖的类型名称，对于 UDT，该类型名称是完全限定的 
	 */
	TYPE_NAME(6),
	/**
	 *  int => 列的大小。 
	 */
	COLUMN_SIZE(7),
	/**
	 *  未被使用。 
	 */
	BUFFER_LENGTH(8),
	/**
	 *  int => 小数部分的位数。对于 DECIMAL_DIGITS 不适用的数据类型，则返回 Null。 
	 */
	DECIMAL_DIGITS(9),
	/**
	 *  int => 基数（通常为 10 或 2） 
	 */
	NUM_PREC_RADIX(10),
	/**
	 *  int => 是否允许使用 NULL。 
	 *  	columnNoNulls - 可能不允许使用 NULL 值 
	 *		columnNullable - 明确允许使用 NULL 值 
	 *		columnNullableUnknown - 不知道是否可使用 null 
	 */
	NULLABLE(11),
	/**
	 *  String => 描述列的注释（可为 null） 
	 */
	REMARKS(12),
	/**
	 *  String => 该列的默认值，当值在单引号内时应被解释为一个字符串（可为 null） 
	 */
	COLUMN_DEF(13),
	/**
	 *  int => 未使用 
	 */
	SQL_DATA_TYPE(14),
	/**
	 *  int => 未使用 
	 */
	SQL_DATETIME_SUB(15),
	/**
	 *  int => 对于 char 类型，该长度是列中的最大字节数 
	 */
	CHAR_OCTET_LENGTH(16),
	/**
	 *  int => 表中的列的索引（从 1 开始） 
	 */
	ORDINAL_POSITION(17),
	/**
	 *  String => ISO 规则用于确定列是否包括 null。 
	 *		YES --- 如果参数可以包括 NULL 
	 *		NO --- 如果参数不可以包括 NULL 
	 *		空字符串 --- 如果不知道参数是否可以包括 null 
	 */
	IS_NULLABLE(18),
	/**
	 *  String => 表的类别，它是引用属性的作用域（如果 DATA_TYPE 不是 REF，则为 null） 
	 */
	SCOPE_CATLOG(19),
	/**
	 *  String => 表的模式，它是引用属性的作用域（如果 DATA_TYPE 不是 REF，则为 null） 
	 */
	SCOPE_SCHEMA(20),
	/**
	 *  String => 表名称，它是引用属性的作用域（如果 DATA_TYPE 不是 REF，则为 null） 
	 */
	SCOPE_TABLE(21),
	/**
	 *  short => 不同类型或用户生成 Ref 类型、来自 java.sql.Types 的 SQL 类型的源类型（如果 DATA_TYPE 不是 DISTINCT 或用户生成的 REF，则为 null） 
	 */
	SOURCE_DATA_TYPE(22),
	/**
	 *  String => 指示此列是否自动增加 
	 *		YES --- 如果该列自动增加 
	 *		NO --- 如果该列不自动增加 
	 *		空字符串 --- 如果不能确定该列是否是自动增加参数 
	 */
	IS_AUTOINCREMENT(23);

	private int value;
	
	ColumnTypeEnum(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
	
}
