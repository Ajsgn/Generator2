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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import cn.ajsgn.generator2.bc.names.ArtifactIdCreator;
import cn.ajsgn.generator2.bc.names.DefaultArtifactIdCreator;
import cn.ajsgn.generator2.constant.Constant;
import cn.ajsgn.generator2.core.strategy.abs.GeneratorStrategy;
import cn.ajsgn.generator2.util.FileUtil;
import cn.ajsgn.generator2.vm.VolecityInstance;

/**
 * <p>bc工程生成策略</p>
 * @ClassName: BcProjectGeneratorStrategy
 * @Description: bc工程生成策略
 * @author Ajsgn@foxmail.com
 * @date 2018年9月30日 下午4:14:54
 */
public class BcProjectGeneratorStrategy implements GeneratorStrategy {
	
	//基础输出目录路径
	private String baseOutFolderPath;
	//基础包结构
	private String basePackage;
	
	//工程名称
	private String projectName;
	//pom groupId
	private String groupId;
	//pom artifactId
	private String artifactId;
	//pom version
	private String version;
	//pom name
	private String name;
	//pom url
	private String url;
	//pom encode
	private String encode;
	
	//ArtifactId 的生成器
	private ArtifactIdCreator artifactIdCreator;
	
	private BcProjectGeneratorStrategy(Builder builder) {
		this.baseOutFolderPath = builder.baseOutFolderPath;
		this.basePackage = builder.basePackage;
		
		this.projectName = builder.projectName;
		this.groupId = builder.groupId;
		this.artifactId = builder.artifactId;
		this.version = builder.version;
		this.name = builder.name;
		this.url = builder.url;
		this.encode = builder.encode;
		
		this.artifactIdCreator = builder.artifactIdCreator;
	}

	@Override 
	public void generator(VolecityInstance vm) {
		String modelArtifactId = artifactIdCreator.modelArtifactId(this.projectName);
		String utilArtifactId = artifactIdCreator.utilArtifactId(this.projectName);
		String daoArtifactId = artifactIdCreator.daoArtifactId(this.projectName);
		String serviceArtifactId = artifactIdCreator.serviceArtifactId(this.projectName);
		String webArtifactId = artifactIdCreator.webArtifactId(this.projectName);
		
		String modelPath = FileUtil.pathEndWithFileSeparator(baseOutFolderPath).concat(this.projectName);
		modelPath = FileUtil.filePathCompatible(modelPath);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("_projectName", this.projectName);
		params.put("_parentArtifactId", this.artifactId);
		params.put("_parentGroupId", this.groupId);
		params.put("_parentVersion", this.version);
		
		params.put("_modelArtifactId", modelArtifactId);
		params.put("_utilArtifactId", utilArtifactId);
		params.put("_daoArtifactId", daoArtifactId);
		params.put("_serviceArtifactId", serviceArtifactId);
		params.put("_webArtifactId", webArtifactId);
		
		String nowFormatDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		params.put("_createDate", nowFormatDate);
		
		//project 
		MavenProjectGeneratorStrategy project = new MavenProjectGeneratorStrategy.Builder(projectName, "cn/ajsgn/generator2/template/maven/bc/pom/bc-project-pom.xml.vm")
				.artifactId(artifactId)	//
				.baseOutFolderPath(baseOutFolderPath)	//
				.encode(encode)	//
				.groupId(groupId)	//
				.name(name)	//
				.packaging(MavenProjectGeneratorStrategy.MavenPackagingType.POM)	//
				.url(url)	//
				.version(version)	//
				.withSrcFolder(false)	//
				.extTemplateParam(params)	//
				.build();
		
		//project-model
		MavenProjectGeneratorStrategy model = buildMavenModelGeneratorStrategy(modelArtifactId, modelPath, "cn/ajsgn/generator2/template/maven/bc/pom/bc-project-model.xml.vm", params);
		//project-util
		MavenProjectGeneratorStrategy util = buildMavenModelGeneratorStrategy(utilArtifactId, modelPath, "cn/ajsgn/generator2/template/maven/bc/pom/bc-project-util.xml.vm", params);
		//project-dao
		MavenProjectGeneratorStrategy dao = buildMavenModelGeneratorStrategy(daoArtifactId, modelPath, "cn/ajsgn/generator2/template/maven/bc/pom/bc-project-dao.xml.vm", params);
		//dao.config
		String daoConfigPackageName = basePackage.concat(".dao.config");
		Map<String, Object> daoFileParam = new HashMap<String, Object>();
		daoFileParam.put("_configPackage", daoConfigPackageName);
		daoFileParam.put("_basePackage", basePackage);
		daoFileParam.put("_createDate", nowFormatDate);
		dao.withJavaFile("cn/ajsgn/generator2/template/maven/bc/java/MyBatisConfig.java.vm", daoConfigPackageName, "MyBatisConfig.java", daoFileParam);
		dao.withJavaFile("cn/ajsgn/generator2/template/maven/bc/java/MyBatisMapperScannerConfig.java.vm", daoConfigPackageName, "MyBatisMapperScannerConfig.java", daoFileParam);
		dao.withResourceFile("cn/ajsgn/generator2/template/maven/bc/txt/MapperReadme.txt.vm", "mybatis", "Readme.txt", new HashMap<String, Object>());
		//project-service
		MavenProjectGeneratorStrategy service = buildMavenModelGeneratorStrategy(serviceArtifactId, modelPath, "cn/ajsgn/generator2/template/maven/bc/pom/bc-project-service.xml.vm", params);
		//project-web
		MavenProjectGeneratorStrategy web = buildMavenModelGeneratorStrategy(webArtifactId, modelPath, "cn/ajsgn/generator2/template/maven/bc/pom/bc-project-web.xml.vm", params);
		Map<String, Object> webFileParam = new HashMap<String, Object>();
		String webPackage = basePackage.concat(".web");
		webFileParam.put("_basePackage", basePackage);
		webFileParam.put("_webPackage", basePackage.concat(".web"));
		webFileParam.put("_createDate", nowFormatDate);
		web.withJavaFile("cn/ajsgn/generator2/template/maven/bc/java/Application.java.vm", webPackage, "Application.java", webFileParam);
		
		//generator
		project.withMavenModelProjectGeneratorStrategy(model)	//
			.withMavenModelProjectGeneratorStrategy(util)	//
			.withMavenModelProjectGeneratorStrategy(dao)	//
			.withMavenModelProjectGeneratorStrategy(service)	//
			.withMavenModelProjectGeneratorStrategy(web);
		project.generator(vm);
		
	}
	
	//Maven Model 生成策略
	private MavenProjectGeneratorStrategy buildMavenModelGeneratorStrategy(String artifactId, String modelPath, String pomTemplateResourcePath, Map<String, Object> params) {
		MavenProjectGeneratorStrategy mavenModel = new MavenProjectGeneratorStrategy.Builder(artifactId, pomTemplateResourcePath)
				.artifactId(artifactId)
				.baseOutFolderPath(modelPath)
				.encode(encode)
				.groupId(groupId)
				.name(name)
				.packaging(MavenProjectGeneratorStrategy.MavenPackagingType.JAR)
				.url(url)
				.version(version)
				.withSrcFolder(true)
				.extTemplateParam(params)
				.build();
		return mavenModel;
	}
	
	/**
	 * <p>BcProjectGeneratorStrategy 的构造器</p>
	 * @ClassName: Builder
	 * @Description: BcProjectGeneratorStrategy 的构造器
	 * @author Ajsgn@foxmail.com
	 * @date 2018年9月30日 下午4:22:29
	 */
	public static final class Builder {
		
		//工程基础输出文件目录
		private String baseOutFolderPath = Constant.DEFAULT_OUTPUT_BASE_FOLDER;
		//基础包结构
		private String basePackage = "";
		
		//工程名称
		private String projectName;
		//pom groupId
		private String groupId = Constant.DEFAULT_POM_GROUP_ID;
		//pom artifactId
		private String artifactId = Constant.DEFAULT_ARTIFACT_ID;
		//pom version
		private String version = Constant.DEFAULT_POM_VERSION;
		//pom name
		private String name = Constant.DEFAULT_POM_NAME;
		//pom url
		private String url = Constant.DEFAULT_POM_URL;
		//pom encode
		private String encode = Constant.DEFAULT_POM_ENCODE;
		
		//artifactId 生成器
		private ArtifactIdCreator artifactIdCreator = DefaultArtifactIdCreator.singletonInstance();
		
		/**
		 * @param projectName 工程名称
		 */
		public Builder(String projectName) {
			this.projectName = projectName;
		}
		
		/**
		 * <p>设置基础输出目录路径<p>
		 * @Title: baseOutFolderPath
		 * @Description: 设置基础输出目录路径
		 * @param baseOutFolderPath 基础输出目录路径
		 * @author Ajsgn@foxmail.com
		 * @date 2018年9月30日 下午4:25:11
		 * @return Builder 当前对象
		 */
		public Builder baseOutFolderPath(String baseOutFolderPath) {
			this.baseOutFolderPath = baseOutFolderPath;
			return this;
		}
		
		/**
		 * <p>pom groupId<p>
		 * @Title: groupId
		 * @Description: pom groupId
		 * @param groupId pom groupId
		 * @author Ajsgn@foxmail.com
		 * @date 2018年9月30日 下午4:32:33
		 * @return Builder 当前对象
		 */
		public Builder groupId(String groupId) {
			this.groupId = groupId;
			return this;
		}
		
		/**
		 * <p>pom artifactId<p>
		 * @Title: artifactId
		 * @Description: pom artifactId
		 * @param artifactId pom artifactId
		 * @author Ajsgn@foxmail.com
		 * @date 2018年9月30日 下午4:31:11
		 * @return Builder 当前对象
		 */
		public Builder artifactId(String artifactId) {
			this.artifactId = artifactId;
			return this;
		}
		
		/**
		 * <p>pom version<p>
		 * @Title: version
		 * @Description: pom version
		 * @param version pom version
		 * @author Ajsgn@foxmail.com
		 * @date 2018年9月30日 下午4:29:42
		 * @return Builder 当前对象
		 */
		public Builder version(String version) {
			this.version = version;
			return this;
		}
		
		/**
		 * <p>pom name<p>
		 * @Title: name
		 * @Description: pom name
		 * @param name pom name
		 * @author Ajsgn@foxmail.com
		 * @date 2018年9月30日 下午4:29:17
		 * @return Builder 当前对象
		 */
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		
		/**
		 * <p>pom url<p>
		 * @Title: url
		 * @Description: pom url
		 * @param url url
		 * @author Ajsgn@foxmail.com
		 * @date 2018年9月30日 下午4:28:53
		 * @return Builder 当前对象
		 */
		public Builder url(String url) {
			this.url = url;
			return this;
		}
		
		/**
		 * <p>pom encode<p>
		 * @Title: encode
		 * @Description: pom encode
		 * @param encode 编码类型
		 * @author Ajsgn@foxmail.com
		 * @date 2018年9月30日 下午4:28:28
		 * @return Builder 当前对象
		 */
		public Builder encode(String encode) {
			this.encode = encode;
			return this;
		}
		
		/**
		 * <p>设置pom artifactId生成器<p>
		 * @Title: artifactIdCreator
		 * @Description: 设置pom artifactId生成器
		 * @param artifactIdCreator artifactId生成器
		 * @author Ajsgn@foxmail.com
		 * @date 2018年9月30日 下午4:27:26
		 * @return Builder 当前对象
		 */
		public Builder artifactIdCreator(ArtifactIdCreator artifactIdCreator) {
			if(null != artifactIdCreator)
				this.artifactIdCreator = artifactIdCreator;
			return this;
		}
		
		/**
		 * <p>设置基础包路径结构<p>
		 * @Title: basePackage
		 * @Description: 设置基础包路径结构
		 * @param basePackage 基础包结构
		 * @author Ajsgn@foxmail.com
		 * @date 2018年9月30日 下午4:25:59
		 * @return Builder 当前对象
		 */
		public Builder basePackage(String basePackage) {
			this.basePackage = StringUtils.isBlank(basePackage) ? "" : basePackage;
			return this;
		}
		
		/**
		 * <p>构建bc project生成策略<p>
		 * @Title: build
		 * @Description: 构建bc project生成策略
		 * @author Ajsgn@foxmail.com
		 * @date 2018年9月30日 下午4:25:17
		 * @return BcProjectGeneratorStrategy 
		 */
		public BcProjectGeneratorStrategy build() {
			return new BcProjectGeneratorStrategy(this);
		}
		
	}
	
}
