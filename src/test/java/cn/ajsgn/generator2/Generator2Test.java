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

import cn.ajsgn.generator2.core.strategy.impl.DbGeneratorStrategy;
import cn.ajsgn.generator2.db.names.DefaultPackageNameCreator;

public class Generator2Test {

	@Test
	public void test() { 
		
		DbGeneratorStrategy.Builder builder = new DbGeneratorStrategy.Builder("bcpay", "bcpay", "jdbc:oracle:thin:@192.168.0.161:1521:orcl", "oracle.jdbc.driver.OracleDriver");
		builder.baseOutFolderPath("d:/generator2");
		
		DbGeneratorStrategy strategy = builder.build();
		
		strategy.setBasePackage("cn.ajsgn.generator2.test", DefaultPackageNameCreator.singletonInstance())
			.withAbstract()
			.withTable("bcpay", "ZYPAYMENT", "ZyPayment", new String[] {"ORDER_NO"})
			.withTable("bcpay", "CITICPAYMENT", "Citicpayment", new String[] {"BATCHNO", "SERIALNO"});
		
		
		Generator2.generator(strategy);
		
		
		
		
	}

}
