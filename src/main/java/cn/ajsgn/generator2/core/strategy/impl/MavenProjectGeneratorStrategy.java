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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;

import cn.ajsgn.generator2.constant.Constant;
import cn.ajsgn.generator2.core.strategy.abs.GeneratorStrategy;
import cn.ajsgn.generator2.core.template.abs.FileTemplate;
import cn.ajsgn.generator2.core.template.impl.CommonFileTemplate;
import cn.ajsgn.generator2.core.template.impl.PackageFileTemplate;
import cn.ajsgn.generator2.util.FileUtil;
import cn.ajsgn.generator2.vm.VolecityInstance;

/**
 * <p>maven工程生成策略</p>
 * @ClassName: MavenProjectGeneratorStrategy
 * @Description: maven工程生成策略
 * @author Ajsgn@foxmail.com
 * @date 2018年9月30日 下午4:47:38
 */
public class MavenProjectGeneratorStrategy implements GeneratorStrategy {
	//输出文件路径
	private String baseOutFolderPath;
	
	//工程名称
	private String projectName;
	//工程中pom文件资源路径
	private String pomFileTemplateResource;
	// >> pom 参数
	private String groupId;
	private String artifactId;
	private String version;
	private String packaging;
	private MavenPackagingType mavenPackageType;
	private String name;
	private String url;
	private String encode;
	
	//是否创建src文件夹路径
	private boolean withSrcFolder;
	
	//需要生成的文件模板集合
	private List<FileTemplate> files = new ArrayList<FileTemplate>();
	//子工程生成策略
	private List<MavenProjectGeneratorStrategy> generatorStrategys = new ArrayList<MavenProjectGeneratorStrategy>();
	//扩展参数
	private Map<String, Object> extTemplateParam = new HashMap<String, Object>();
	
	//构造器
	private MavenProjectGeneratorStrategy(Builder builder) {
		String temp = FileUtil.filePathCompatible(builder.baseOutFolderPath);
		this.baseOutFolderPath = FileUtil.pathEndWithFileSeparator(temp);
		this.projectName = builder.projectName;
		this.pomFileTemplateResource = builder.pomFileTemplateResource;
		this.groupId = builder.groupId;
		this.artifactId = builder.artifactId;
		this.version = builder.version;
		this.packaging = builder.packaging;
		this.mavenPackageType = builder.mavenPackageType;
		this.name = builder.name;
		this.url = builder.url;
		this.encode = builder.encode;
		this.withSrcFolder = builder.withSrcFolder;
		
		extTemplateParam.putAll(builder.extTemplateParam);
	}
	
	/**
	 * <p>在src/main/resource目录下创建一个packagefile<p>
	 * @Title: withResourceFile
	 * @Description: 在src/main/resource目录下创建一个packagefile
	 * @param resourceFileTemplateResource 模板资源文件
	 * @param packageName 包名称
	 * @param className 类名称
	 * @param params 模板中的参数
	 * @author Ajsgn@foxmail.com
	 * @date 2018年9月30日 下午5:05:51
	 * @return MavenProjectGeneratorStrategy 
	 */
	public MavenProjectGeneratorStrategy withResourceFile(String resourceFileTemplateResource, String packageName, String className, Map<String, Object> params) {
		StringBuilder basePath = new StringBuilder();
		basePath.append(baseOutFolderPath)	//
			.append(projectName).append(File.separator)	//
			.append("src").append(File.separator)	//
			.append("main").append(File.separator)	//
			.append("resources").append(File.separator);
		
		files.add(new PackageFileTemplate(basePath.toString(), packageName, className, resourceFileTemplateResource, params));
		return this;
	}
	
	/**
	 * <p>在src/main/java目录下创建一个packagefile<p>
	 * @Title: withResourceFile
	 * @Description: 在src/main/java目录下创建一个packagefile
	 * @param resourceFileTemplateResource 模板资源文件
	 * @param packageName 包名称
	 * @param className 类名称
	 * @param params 模板中的参数
	 * @author Ajsgn@foxmail.com
	 * @date 2018年9月30日 下午5:05:51
	 * @return MavenProjectGeneratorStrategy 
	 */
	public MavenProjectGeneratorStrategy withJavaFile(String resourceFileTemplateResource, String packageName, String className, Map<String, Object> params) {
		StringBuilder basePath = new StringBuilder();
		basePath.append(baseOutFolderPath)	//
			.append(projectName).append(File.separator)	//
			.append("src").append(File.separator)	//
			.append("main").append(File.separator)	//
			.append("java").append(File.separator);
		
		files.add(new PackageFileTemplate(basePath.toString(), packageName, className, resourceFileTemplateResource, params));
		return this;
	}
	
	/**
	 * <p>在src/test/resource目录下创建一个packagefile<p>
	 * @Title: withResourceFile
	 * @Description: 在src/test/resource目录下创建一个packagefile
	 * @param resourceFileTemplateResource 模板资源文件
	 * @param packageName 包名称
	 * @param className 类名称
	 * @param params 模板中的参数
	 * @author Ajsgn@foxmail.com
	 * @date 2018年9月30日 下午5:05:51
	 * @return MavenProjectGeneratorStrategy 
	 */
	public MavenProjectGeneratorStrategy withTestResourceFile(String resourceFileTemplateResource, String packageName, String className, Map<String, Object> params) {
		StringBuilder basePath = new StringBuilder();
		basePath.append(baseOutFolderPath)	//
			.append(projectName).append(File.separator)	//
			.append("src").append(File.separator)	//
			.append("test").append(File.separator)	//
			.append("resources").append(File.separator);
		
		files.add(new PackageFileTemplate(basePath.toString(), packageName, className, resourceFileTemplateResource, params));
		return this;
	}
	
	/**
	 * <p>在src/test/java目录下创建一个packagefile<p>
	 * @Title: withResourceFile
	 * @Description: 在src/test/java目录下创建一个packagefile
	 * @param resourceFileTemplateResource 模板资源文件
	 * @param packageName 包名称
	 * @param className 类名称
	 * @param params 模板中的参数
	 * @author Ajsgn@foxmail.com
	 * @date 2018年9月30日 下午5:05:51
	 * @return MavenProjectGeneratorStrategy 
	 */
	public MavenProjectGeneratorStrategy withTestJavaFile(String resourceFileTemplateResource, String packageName, String className, Map<String, Object> params) {
		StringBuilder basePath = new StringBuilder();
		basePath.append(baseOutFolderPath)	//
			.append(projectName).append(File.separator)	//
			.append("src").append(File.separator)	//
			.append("test").append(File.separator)	//
			.append("java").append(File.separator);
		
		files.add(new PackageFileTemplate(basePath.toString(), packageName, className, resourceFileTemplateResource, params));
		return this;
	}
	
	/**
	 * <p>添加一个mavenProject工程生成策略<p>
	 * @Title: withMavenModelProjectGeneratorStrategy
	 * @Description: 添加一个mavenProject工程生成策略
	 * @param mavenModelStrategy
	 * @author Ajsgn@foxmail.com 
	 * @date 2018年9月30日 下午5:08:17
	 * @return MavenProjectGeneratorStrategy 
	 */
	public MavenProjectGeneratorStrategy withMavenModelProjectGeneratorStrategy(MavenProjectGeneratorStrategy mavenModelStrategy) {
		if(null != mavenModelStrategy)
			generatorStrategys.add(mavenModelStrategy);
		return this;
	}

	//生成文件
	@Override
	public void generator(VolecityInstance vm) {
		String projectPath = baseOutFolderPath.concat(projectName);
		projectPath = FileUtil.pathEndWithFileSeparator(projectPath);
		//blankPom.xml
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("_groupId", groupId);
		param.put("_artifactId", artifactId);
		param.put("_version", version);
		param.put("_packaging", packaging);
		param.put("_name", name);
		param.put("_url", url);
		param.put("_encode", encode);
		param.put("_createDate", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		
		param.putAll(this.extTemplateParam);
		
		files.add(CommonFileTemplate.fileTemplate(projectPath, pomFileTemplateResource, param, "pom.xml"));
		
		if(true == this.withSrcFolder) {
			buildSrc(projectPath);
			if(MavenPackagingType.WAR == this.mavenPackageType) {
				StringBuilder webXmlPathSb = new StringBuilder()	//
						.append(projectPath).append(File.separator)	//
						.append("src").append(File.separator)	//
						.append("main").append(File.separator)	//
						.append("webapp").append(File.separator)	//
						.append("WEB-INF").append(File.separator)	//
						;
				files.add(CommonFileTemplate.fileTemplate(webXmlPathSb.toString(), "cn/ajsgn/generator2/template/maven/web.xml.vm", param, "web.xml"));
			}
		}
		
		files.forEach(file -> {
			try {
				vm.flush(file.templateResource(), file.targetParam(), file.targetFileWriter());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		generatorStrategys.forEach(gs -> {
			gs.generator(vm);
		});
	}
	
	//创建文件夹
	private void buildSrc(String projectPath) {
		// src/main/java
		StringBuilder srcMainJavaSb = new StringBuilder()	//
				.append(projectPath).append(File.separator)	//
				.append("src").append(File.separator)	//
				.append("main").append(File.separator)	//
				.append("java").append(File.separator);
		files.add(CommonFileTemplate.folderTemplate(srcMainJavaSb.toString()));
		// src/main/resource
		StringBuilder srcMainResourceSb = new StringBuilder()	//
				.append(projectPath).append(File.separator)	//
				.append("src").append(File.separator)	//
				.append("main").append(File.separator)	//
				.append("resources").append(File.separator);
		files.add(CommonFileTemplate.folderTemplate(srcMainResourceSb.toString()));
		// src/test/java
		StringBuilder srcTestJavaSb = new StringBuilder()	//
				.append(projectPath).append(File.separator)	//
				.append("src").append(File.separator)	//
				.append("test").append(File.separator)	//
				.append("java").append(File.separator);
		files.add(CommonFileTemplate.folderTemplate(srcTestJavaSb.toString()));
		// src/test/resource
		StringBuilder srcTestResourceSb = new StringBuilder()	//
				.append(projectPath).append(File.separator)	//
				.append("src").append(File.separator)	//
				.append("test").append(File.separator)	//
				.append("resources").append(File.separator);
		files.add(CommonFileTemplate.folderTemplate(srcTestResourceSb.toString()));
	}

	/**
	 * <p>pom packaging type</p>
	 * @ClassName: MavenPackagingType
	 * @Description: pom packaging type
	 * @author Ajsgn@foxmail.com 
	 * @date 2018年9月30日 下午5:10:16
	 */
	public static enum MavenPackagingType {
		JAR("jar"), WAR("war"), POM("pom");
		
		private String value;
		
		MavenPackagingType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
		
	}
	
	/**
	 * <p>MavenProjectGeneratorStrategy 的建造者</p>
	 * @ClassName: Builder
	 * @Description: MavenProjectGeneratorStrategy 的建造者
	 * @author Ajsgn@foxmail.com 
	 * @date 2018年9月30日 下午5:10:45
	 */
	public static final class Builder {
		
		private String baseOutFolderPath = Constant.DEFAULT_OUTPUT_BASE_FOLDER;
		
		private String projectName;
		private String pomFileTemplateResource;
		private String groupId = Constant.DEFAULT_POM_GROUP_ID;
		private String artifactId = Constant.DEFAULT_ARTIFACT_ID;
		private String version = Constant.DEFAULT_POM_VERSION;
		private String packaging = Constant.DEFAULT_PACKAGING.getValue();
		private MavenPackagingType mavenPackageType = Constant.DEFAULT_PACKAGING;
		private String name = Constant.DEFAULT_POM_NAME;
		private String url = Constant.DEFAULT_POM_URL;
		private String encode = Constant.DEFAULT_POM_ENCODE;
		
		private Map<String, Object> extTemplateParam = new HashMap<String, Object>();
		
		private boolean withSrcFolder = Constant.DEFAULT_PROJECT_WITH_SRC_FOLDER;
		
		/**
		 * <p>构造函数</p>
		 * @param projectName 工程名称
		 * @param pomFileTemplateResource 模板文件资源路径
		 */
		public Builder(String projectName, String pomFileTemplateResource) {
			this.projectName = projectName;
			this.pomFileTemplateResource = pomFileTemplateResource;
		}
		
		/**
		 * <p>文件输出目录<p>
		 * @Title: baseOutFolderPath
		 * @Description: 文件输出目录
		 * @param baseOutFolderPath 文件输出目录
		 * @author Ajsgn@foxmail.com
		 * @date 2018年9月30日 下午5:19:46
		 * @return Builder 
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
		 * @date 2018年9月30日 下午5:16:10
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
		 * @date 2018年9月30日 下午5:16:10
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
		 * @param version
		 * @author Ajsgn@foxmail.com
		 * @date 2018年9月30日 下午5:16:10
		 * @return Builder 当前对象
		 */
		public Builder version(String version) {
			this.version = version;
			return this;
		}
		
		/**
		 * <p>pom packaging<p>
		 * @Title: packaging
		 * @Description: pom packaging
		 * @param mavenPackageType pom packaging
		 * @author Ajsgn@foxmail.com
		 * @date 2018年9月30日 下午5:16:10
		 * @return Builder 当前对象
		 */
		public Builder packaging(MavenPackagingType mavenPackageType) {
			mavenPackageType = null == mavenPackageType ? MavenPackagingType.JAR : mavenPackageType;
			this.packaging = mavenPackageType.getValue();
			this.mavenPackageType = mavenPackageType;
			return this;
		}
		
		/**
		 * <p>pom name<p>
		 * @Title: name
		 * @Description: pom name
		 * @param pom name
		 * @author Ajsgn@foxmail.com
		 * @date 2018年9月30日 下午5:16:10
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
		 * @param url pom url
		 * @author Ajsgn@foxmail.com
		 * @date 2018年9月30日 下午5:16:10
		 * @return Builder 当前对象
		 */
		public Builder url(String url) {
			this.url = url;
			return this;
		}
		
		/**
		 * <p>pom 编码<p>
		 * @Title: encode
		 * @Description: pom 编码
		 * @param encode pom 编码
		 * @author Ajsgn@foxmail.com
		 * @date 2018年9月30日 下午5:16:10
		 * @return Builder 当前对象
		 */
		public Builder encode(String encode) {
			this.encode = encode;
			return this;
		}
		
		/**
		 * <p>标注是否创建src文件夹<p>
		 * @Title: withSrcFolder
		 * @Description: 标注是否创建src文件夹
		 * @param withSrcFolder 是否创建src文件夹
		 * @author Ajsgn@foxmail.com
		 * @date 2018年9月30日 下午5:12:37
		 * @return Builder 当前对象
		 */
		public Builder withSrcFolder(boolean withSrcFolder) {
			this.withSrcFolder = withSrcFolder;
			return this;
		}
		
		/**
		 * <p>设置模板中的扩展参数<p>
		 * @Title: extTemplateParam
		 * @Description: 设置模板中的扩展参数
		 * @param 模板中的扩展参数
		 * @author Ajsgn@foxmail.com
		 * @date 2018年9月30日 下午5:11:53
		 * @return Builder 当前对象
		 */
		public Builder extTemplateParam(Map<String, Object> params) {
			if(null != params)
				extTemplateParam.putAll(params);
			return this;
		}
		
		/**
		 * <p>构造 MavenProjectGeneratorStrategy<p>
		 * @Title: build
		 * @Description: 构造 MavenProjectGeneratorStrategy
		 * @author Ajsgn@foxmail.com
		 * @date 2018年9月30日 下午5:11:15
		 * @return MavenProjectGeneratorStrategy 
		 */
		public MavenProjectGeneratorStrategy build() {
			return new MavenProjectGeneratorStrategy(this);
		}
		
	}

}
