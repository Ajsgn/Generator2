package cn.ajsgn.generator2.constant;

import cn.ajsgn.generator2.core.strategy.impl.MavenProjectGeneratorStrategy.MavenPackagingType;

public class Constant {
	
	public static final String BASE_FILE_PREFIX = "Base";
	public static final String DAO_CONDITION_SUFFIX = "DaoCondition";
	public static final String MAPPER_SUFFIX = "Mapper";
	public static final String DAO_SUFFIX = "Dao";
	public static final String IMPLEMENT_SUFFIX = "Impl";
	
	public static final String JAVA_FILE_SUFFIX = ".java";
	public static final String XML_FILE_SUFFIX = ".xml";
	
	public static final String DEFAULT_OUTPUT_BASE_FOLDER = "d:/generator2";
	public static final String DEFAULT_BASE_PACKAGE = "cn.ajsgn.generator2";
	
	public static final String DEFAULT_POM_GROUP_ID = "cn.ajsgn.generator2.xxx";
	public static final String DEFAULT_ARTIFACT_ID = "cn.ajsgn.generator2";
	public static final String DEFAULT_POM_VERSION = "0.0.1-SNAPSHOT";
	public static final MavenPackagingType DEFAULT_PACKAGING = MavenPackagingType.JAR;
	public static final String DEFAULT_POM_NAME = "cn.ajsgn.generator2.xxx";
	public static final String DEFAULT_POM_URL = "http://maven.apache.org";
	public static final String DEFAULT_POM_ENCODE = "UTF-8";
	public static final boolean DEFAULT_PROJECT_WITH_SRC_FOLDER = true;
	
	
}
