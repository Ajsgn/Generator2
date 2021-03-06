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

import cn.ajsgn.generator2.core.strategy.impl.MavenProjectGeneratorStrategy;

public class MavenProjectGenerator2Test {
	
	public static void main(String[] args) {
		
		MavenProjectGeneratorStrategy mavenProject = new MavenProjectGeneratorStrategy.Builder("bc-project-test", "cn/ajsgn/generator2/template/maven/blankPom.xml.vm")//
				.baseOutFolderPath("d:/generator2/project")	//
				.artifactId("bc.project.test")	//
				.groupId("bc.project.test")	//
				.encode("UTF-8")	//
				.name("generator project for testing")	//
				.packaging(MavenProjectGeneratorStrategy.MavenPackagingType.WAR)	//
				.url("http://www.apache.org")	//
				.version("0.0.1-SNATSHOT")	//
				.withSrcFolder(true)	//
				.build();
		
		
		Generator2.generator(mavenProject);
		
		
		
	}
	
	
	
}
