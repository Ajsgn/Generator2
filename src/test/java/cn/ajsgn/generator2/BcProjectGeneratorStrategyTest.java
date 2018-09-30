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

import cn.ajsgn.generator2.core.strategy.impl.BcProjectGeneratorStrategy;

public class BcProjectGeneratorStrategyTest {
	
	public static void main(String[] args) {
		
		BcProjectGeneratorStrategy bcProject = new BcProjectGeneratorStrategy.Builder("bc-project-test")//
				.artifactId("bc.project.test")	//
				.baseOutFolderPath("d:/generator2/project")	//
				.encode("UTF-8")	//
				.groupId("bc.project.test")	//
				.name("generator project for testing")	//
				.url("http://www.apache.org")	//
				.version("0.0.1-SNATSHOT")	//
				.basePackage("cn.ajsgn.generator2.test")
				.build();
		
		
		Generator2.generator(bcProject);
		
		
		
	}
	
	
}
