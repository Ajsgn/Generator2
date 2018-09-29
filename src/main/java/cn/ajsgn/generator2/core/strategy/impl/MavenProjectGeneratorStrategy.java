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

public class MavenProjectGeneratorStrategy implements GeneratorStrategy {
	
	private String baseOutFolderPath;
	
	private String projectName;
	private String pomFileTemplateResource;
	private String groupId;
	private String artifactId;
	private String version;
	private String packaging;
	private MavenPackagingType mavenPackageType;
	private String name;
	private String url;
	private String encode;
	
	private boolean withSrcFolder;
	
	private List<FileTemplate> files = new ArrayList<FileTemplate>();
	private List<MavenProjectGeneratorStrategy> generatorStrategys = new ArrayList<MavenProjectGeneratorStrategy>();
	
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
	}
	
	public MavenProjectGeneratorStrategy withResourceFile(String resourceFileTemplateResource, String packageName, String className, Map<String, Object> params) {
		StringBuilder basePath = new StringBuilder();
		basePath.append(baseOutFolderPath)	//
			.append(projectName).append(File.separator)	//
			.append("src").append(File.separator)	//
			.append("main").append(File.separator)	//
			.append("resource").append(File.separator);
		
		files.add(new PackageFileTemplate(basePath.toString(), packageName, className, resourceFileTemplateResource, params));
		return this;
	}
	
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
	
	public MavenProjectGeneratorStrategy withTestResourceFile(String resourceFileTemplateResource, String packageName, String className, Map<String, Object> params) {
		StringBuilder basePath = new StringBuilder();
		basePath.append(baseOutFolderPath)	//
			.append(projectName).append(File.separator)	//
			.append("src").append(File.separator)	//
			.append("test").append(File.separator)	//
			.append("resource").append(File.separator);
		
		files.add(new PackageFileTemplate(basePath.toString(), packageName, className, resourceFileTemplateResource, params));
		return this;
	}
	
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
	
	public MavenProjectGeneratorStrategy withMavenModelProjectGeneratorStrategy(MavenProjectGeneratorStrategy mavenModelStrategy) {
		if(null != mavenModelStrategy)
			generatorStrategys.add(mavenModelStrategy);
		return this;
	}

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
				.append("resource").append(File.separator);
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
				.append("resource").append(File.separator);
		files.add(CommonFileTemplate.folderTemplate(srcTestResourceSb.toString()));
	}

	public static enum MavenPackagingType {
		JAR("jar"), WAR("war");
		
		private String value;
		
		MavenPackagingType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
		
	}
	
	public static void main(String[] args) {
		
		System.out.println(new Date(1538208000000L).toLocaleString());
		
	}

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
		
		private boolean withSrcFolder = Constant.DEFAULT_PROJECT_WITH_SRC_FOLDER;
		
		public Builder(String projectName, String pomFileTemplateResource) {
			this.projectName = projectName;
			this.pomFileTemplateResource = pomFileTemplateResource;
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
		
		public Builder packaging(MavenPackagingType mavenPackageType) {
			mavenPackageType = null == mavenPackageType ? MavenPackagingType.JAR : mavenPackageType;
			this.packaging = mavenPackageType.getValue();
			this.mavenPackageType = mavenPackageType;
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
		
		public Builder withSrcFolder(boolean withSrcFolder) {
			this.withSrcFolder = withSrcFolder;
			return this;
		}
		
		public MavenProjectGeneratorStrategy build() {
			return new MavenProjectGeneratorStrategy(this);
		}
		
	}

}
