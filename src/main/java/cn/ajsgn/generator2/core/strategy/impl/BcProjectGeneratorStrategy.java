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
package cn.ajsgn.generator2.core.strategy.impl;

import cn.ajsgn.generator2.constant.Constant;
import cn.ajsgn.generator2.core.strategy.abs.GeneratorStrategy;
import cn.ajsgn.generator2.core.strategy.impl.MavenProjectGeneratorStrategy.MavenPackagingType;
import cn.ajsgn.generator2.vm.VolecityInstance;

public class BcProjectGeneratorStrategy implements GeneratorStrategy {
	
	private String baseOutFolderPath;
	
	private String projectName;
	private String groupId;
	private String artifactId;
	private String version;
	private String name;
	private String url;
	private String encode;
	
	private BcProjectGeneratorStrategy(Builder builder) {
		this.baseOutFolderPath = builder.baseOutFolderPath;
		
		this.projectName = builder.projectName;
		this.groupId = builder.groupId;
		this.artifactId = builder.artifactId;
		this.version = builder.version;
		this.name = builder.name;
		this.url = builder.url;
		this.encode = builder.encode;
	}

	@Override 
	public void generator(VolecityInstance vm) {
		//project 
		MavenProjectGeneratorStrategy project = new MavenProjectGeneratorStrategy.Builder(projectName, "cn/ajsgn/generator2/template/maven/bc/pom/bc-project-pom.xml.vm")
				.artifactId(artifactId)
				.baseOutFolderPath(baseOutFolderPath)
				.encode(encode)
				.groupId(groupId)
				.name(name)
				.packaging(MavenProjectGeneratorStrategy.MavenPackagingType.JAR)
				.url(url)
				.version(version)
				.withSrcFolder(false)
				.build();
		
		//project-util
		
		//project-model
		
		//project-dao
		
		//project-service
		
		//project-web
		
	}
	
	public static final class Builder {
		
		private String baseOutFolderPath = Constant.DEFAULT_OUTPUT_BASE_FOLDER;
		
		private String projectName;
		private String groupId = Constant.DEFAULT_POM_GROUP_ID;
		private String artifactId = Constant.DEFAULT_ARTIFACT_ID;
		private String version = Constant.DEFAULT_POM_VERSION;
		private String name = Constant.DEFAULT_POM_NAME;
		private String url = Constant.DEFAULT_POM_URL;
		private String encode = Constant.DEFAULT_POM_ENCODE;
		
		public Builder(String projectName) {
			this.projectName = projectName;
		}
		
		public Builder baseOutFolderPath(String baseOutFolderPath) {
			this.baseOutFolderPath = baseOutFolderPath;
			return this;
		}
		
		public Builder groupId(String groupId) {
			this.groupId = groupId;
			return this;
		}
		
		public Builder artifactId(String artifactId) {
			this.artifactId = artifactId;
			return this;
		}
		
		public Builder version(String version) {
			this.version = version;
			return this;
		}
		
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		
		public Builder url(String url) {
			this.url = url;
			return this;
		}
		
		public Builder encode(String encode) {
			this.encode = encode;
			return this;
		}
		
		public BcProjectGeneratorStrategy build() {
			return new BcProjectGeneratorStrategy(this);
		}
		
	}
	
}
