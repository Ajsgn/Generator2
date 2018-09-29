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
package cn.ajsgn.generator2;

import org.junit.Test;

import cn.ajsgn.generator2.core.strategy.impl.DbTableFileGeneratorStrategy;
import cn.ajsgn.generator2.db.names.DefaultPackageNameCreator;
public class DbGenerator2Test {

	@Test
	public void test() { 
		
		DbTableFileGeneratorStrategy mysql = new DbTableFileGeneratorStrategy.Builder("root", "123456", "jdbc:mysql://localhost:3306/bc_eleme_platform", "com.mysql.jdbc.Driver")
				.baseOutFolderPath("d:/generator2/mysql/xxx")	//
				.basePackage("cn.ajsgn.generator2", DefaultPackageNameCreator.singletonInstance())	//
				.build()	//
				.withAbstract()	//
				.withTable("test", "table1", "Table1", new String[] {"ID"})	//
//				.withTable("test", "table2", "Table2", new String[] {"ID"})	//
//				.withTable("test", "table3", "Table3", new String[] {"ID"})	//
				;
		
		Generator2.generator(mysql);
		
		
		
		
	}

}
