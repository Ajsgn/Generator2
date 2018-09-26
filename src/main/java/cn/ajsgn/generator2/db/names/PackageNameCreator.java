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

public interface PackageNameCreator {
	
	public abstract String absCommonPackage(String basePackage);
	
	public abstract String baseModelPackage(String basePackage);

	public abstract String modelPackage(String basePackage);
	
	public abstract String baseDaoConditionPackage(String basePackage);

	public abstract String daoConditionPackage(String basePackage);
	
	public abstract String mapperPackage(String basePackage);
	
	public abstract String mapperXmlPackage(String basePackage);

	public abstract String baseMapperPackage(String basePackage);

	public abstract String baseMapperXmlPackage(String basePackage);
	
	public abstract String daoPackage(String basePackage);
	
	public abstract String daoImplPackage(String basePackage);

}
