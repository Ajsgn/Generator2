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

/**
 * <p>db文件名称创建</p>
 * @ClassName: PackageNameCreator
 * @Description: db文件名称创建
 * @author Ajsgn@foxmail.com
 * @date 2018年9月30日 下午5:26:19
 */
public interface PackageNameCreator {
	
	/**
	 * <p>通用抽象包结构<p>
	 * @Title: absCommonPackage
	 * @Description: 通用抽象包结构
	 * @param basePackage 基础包结构
	 * @author Ajsgn@foxmail.com
	 * @date 2018年9月30日 下午5:27:15
	 * @return String 抽象接口包路径
	 */
	public abstract String absCommonPackage(String basePackage);
	
	/**
	 * <p>表对象存放包路径<p>
	 * @Title: baseModelPackage
	 * @Description: 表对象存放包路径
	 * @param basePackage 基础包结构
	 * @author Ajsgn@foxmail.com
	 * @date 2018年9月30日 下午5:27:12
	 * @return String 表对象包路径
	 */
	public abstract String baseModelPackage(String basePackage);

	/**
	 * <p>表对象存放的包路径<p>
	 * @Title: modelPackage
	 * @Description: 表对象存放的包路径
	 * @param basePackage 基础包结构
	 * @author Ajsgn@foxmail.com
	 * @date 2018年9月30日 下午5:27:09
	 * @return String 表对象存放的包路径
	 */
	public abstract String modelPackage(String basePackage);
	
	/**
	 * <p>daoCondition存放的包路径<p>
	 * @Title: baseDaoConditionPackage
	 * @Description: daoCondition存放的包路径
	 * @param basePackage 基础包结构
	 * @author Ajsgn@foxmail.com
	 * @date 2018年9月30日 下午5:27:05
	 * @return String dao存放的包路径
	 */
	public abstract String baseDaoConditionPackage(String basePackage);

	/**
	 * <p>daoCondition存放的包路径<p>
	 * @Title: daoConditionPackage
	 * @Description: daoCondition存放的包路径
	 * @param basePackage 基础包结构
	 * @author Ajsgn@foxmail.com
	 * @date 2018年9月30日 下午5:27:01
	 * @return String dao存放的包路径
	 */
	public abstract String daoConditionPackage(String basePackage);
	
	/**
	 * <p>mybatis mapper 存放包路径<p>
	 * @Title: mapperPackage
	 * @Description: mybatis mapper 存放包路径
	 * @param basePackage 基础包结构
	 * @author Ajsgn@foxmail.com
	 * @date 2018年9月30日 下午5:26:59
	 * @return String mybatis mapper 存放包路径
	 */
	public abstract String mapperPackage(String basePackage);
	
	/**
	 * <p>mybatis mapper xml 存放包路径<p>
	 * @Title: mapperXmlPackage
	 * @Description: mybatis mapper xml 存放包路径
	 * @param basePackage 基础包结构
	 * @author Ajsgn@foxmail.com
	 * @date 2018年9月30日 下午5:26:56
	 * @return String mybatis mapper xml 存放包路径
	 */
	public abstract String mapperXmlPackage(String basePackage);

	/**
	 * <p>mybatis mapper xml 存放包路径<p>
	 * @Title: baseMapperXmlPackage
	 * @Description: mybatis mapper xml 存放包路径
	 * @param basePackage 基础包结构
	 * @author Ajsgn@foxmail.com
	 * @date 2018年9月30日 下午5:26:53
	 * @return String 
	 */
	public abstract String baseMapperXmlPackage(String basePackage);
	
	/**
	 * <p>dao存放包路径<p>
	 * @Title: daoPackage
	 * @Description: dao存放包路径
	 * @param basePackage 基础包结构
	 * @author Ajsgn@foxmail.com
	 * @date 2018年9月30日 下午5:26:51
	 * @return String dao存放包路径
	 */
	public abstract String daoPackage(String basePackage);
	
	/**
	 * <p>dao实现存放包路径<p>
	 * @Title: daoImplPackage
	 * @Description: dao实现存放包路径
	 * @param basePackage 基础包结构
	 * @author Ajsgn@foxmail.com
	 * @date 2018年9月30日 下午5:26:46
	 * @return String dao实现存放包路径
	 */
	public abstract String daoImplPackage(String basePackage);

}
