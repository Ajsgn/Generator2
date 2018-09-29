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
package cn.ajsgn.generator2.db.names;

import org.apache.commons.lang3.StringUtils;

public class DefaultPackageNameCreator implements PackageNameCreator {
	
	private static final DefaultPackageNameCreator ME = new DefaultPackageNameCreator();
	private static final String DEFAULT_PACKAGE = "cn.ajsgn.generator2";
	
	private DefaultPackageNameCreator() {
		
	}
	
	public static DefaultPackageNameCreator singletonInstance() {
		return ME;
	}

	@Override
	public String absCommonPackage(String basePackage) {
		return StringUtils.defaultIfBlank(basePackage, DEFAULT_PACKAGE).concat(".abs");
	}

	@Override
	public String baseModelPackage(String basePackage) {
		return StringUtils.defaultIfBlank(basePackage, DEFAULT_PACKAGE).concat(".model.base");
	}

	@Override
	public String modelPackage(String basePackage) {
		return StringUtils.defaultIfBlank(basePackage, DEFAULT_PACKAGE).concat(".model");
	}

	@Override
	public String baseDaoConditionPackage(String basePackage) {
		return StringUtils.defaultIfBlank(basePackage, DEFAULT_PACKAGE).concat(".condition.base");
	}

	@Override
	public String daoConditionPackage(String basePackage) {
		return StringUtils.defaultIfBlank(basePackage, DEFAULT_PACKAGE).concat(".condition");
	}

	@Override
	public String mapperPackage(String basePackage) {
		return StringUtils.defaultIfBlank(basePackage, DEFAULT_PACKAGE).concat(".mapper");
	}

	@Override
	public String mapperXmlPackage(String basePackage) {
		return StringUtils.defaultIfBlank(basePackage, DEFAULT_PACKAGE).concat(".mapper.xml");
	}

	@Override
	public String baseMapperXmlPackage(String basePackage) {
		return StringUtils.defaultIfBlank(basePackage, DEFAULT_PACKAGE).concat(".mapper.base.xml");
	}

	@Override
	public String daoPackage(String basePackage) {
		return StringUtils.defaultIfBlank(basePackage, DEFAULT_PACKAGE).concat(".dao");
	}

	@Override
	public String daoImplPackage(String basePackage) {
		return StringUtils.defaultIfBlank(basePackage, DEFAULT_PACKAGE).concat(".dao.impl");
	}

}
