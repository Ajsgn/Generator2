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
package cn.ajsgn.generator2.bc.names;

/**
 * <p>bc工程中ArtifactId的生成策略</p>
 * @ClassName: ArtifactIdCreator
 * @Description: bc工程中ArtifactId的生成策略
 * @author Ajsgn@foxmail.com
 * @date 2018年9月30日 下午4:00:49
 */
public interface ArtifactIdCreator {
	
	/**
	 * <p>model ArtifactId的生成策略<p>
	 * @Title: modelArtifactId
	 * @Description: modelArtifactId的生成策略
	 * @param projectName 工程名称
	 * @author Ajsgn@foxmail.com
	 * @date 2018年9月30日 下午4:01:52
	 * @return String 
	 */
	public String modelArtifactId(String projectName);
	
	/**
	 * <p>util ArtifactId的生成策略<p>
	 * @Title: utilArtifactId
	 * @Description: util ArtifactId的生成策略
	 * @param projectName 工程名称
	 * @author Ajsgn@foxmail.com
	 * @date 2018年9月30日 下午4:02:29
	 * @return String 
	 */
	public String utilArtifactId(String projectName);
	
	/**
	 * <p>dao ArtifactId的生成策略<p>
	 * @Title: daoArtifactId
	 * @Description: dao ArtifactId的生成策略
	 * @param projectName 工程名称
	 * @author Ajsgn@foxmail.com
	 * @date 2018年9月30日 下午4:02:36
	 * @return String 
	 */
	public String daoArtifactId(String projectName);
	
	/**
	 * <p>service ArtifactId的生成策略<p>
	 * @Title: serviceArtifactId
	 * @Description: service ArtifactId的生成策略
	 * @param projectName 工程名称
	 * @author Ajsgn@foxmail.com
	 * @date 2018年9月30日 下午4:02:41
	 * @return String 
	 */
	public String serviceArtifactId(String projectName);
	
	/**
	 * <p>web ArtifactId 的生成方式<p>
	 * @Title: webArtifactId
	 * @Description: web ArtifactId 的生成方式
	 * @param projectName 工程名称
	 * @author Ajsgn@foxmail.com
	 * @date 2018年9月30日 下午4:02:46
	 * @return String 
	 */
	public String webArtifactId(String projectName);
	
}
