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
package cn.ajsgn.generator2.constant;

import cn.ajsgn.generator2.core.strategy.impl.MavenProjectGeneratorStrategy.MavenPackagingType;

/**
 * <p>通用常量</p>
 * @ClassName: Constant
 * @Description: 通用常量
 * @author Ajsgn@foxmail.com
 * @date 2018年9月30日 下午4:05:59
 */
public class Constant {
	
	//base 前缀
	public static final String BASE_FILE_PREFIX = "Base";
	//daoCondition 后缀
	public static final String DAO_CONDITION_SUFFIX = "DaoCondition";
	//Mapper 后缀
	public static final String MAPPER_SUFFIX = "Mapper";
	//Dao 后缀
	public static final String DAO_SUFFIX = "Dao";
	//实现类后缀
	public static final String IMPLEMENT_SUFFIX = "Impl";
	
	//java文件类型
	public static final String JAVA_FILE_SUFFIX = ".java";
	//xml文件类型
	public static final String XML_FILE_SUFFIX = ".xml";
	
	//默认输出文件夹目录
	public static final String DEFAULT_OUTPUT_BASE_FOLDER = "d:/generator2";
	//默认包目录
	public static final String DEFAULT_BASE_PACKAGE = "cn.ajsgn.generator2";
	
	//pom文件默认groupId
	public static final String DEFAULT_POM_GROUP_ID = "cn.ajsgn.generator2.xxx";
	//pom文件默认artifactId
	public static final String DEFAULT_ARTIFACT_ID = "cn.ajsgn.generator2";
	//pom文件默认版本
	public static final String DEFAULT_POM_VERSION = "0.0.1-SNAPSHOT";
	//默认的pom packaging
	public static final MavenPackagingType DEFAULT_PACKAGING = MavenPackagingType.POM;
	//pom文件中name字段的默认值
	public static final String DEFAULT_POM_NAME = "cn.ajsgn.generator2.xxx";
	//pom文件中url字段的默认值
	public static final String DEFAULT_POM_URL = "http://maven.apache.org";
	//pom文件中encode字段的默认值
	public static final String DEFAULT_POM_ENCODE = "UTF-8";
	//maven工程默认生成src文件夹
	public static final boolean DEFAULT_PROJECT_WITH_SRC_FOLDER = true;
	
	
}
