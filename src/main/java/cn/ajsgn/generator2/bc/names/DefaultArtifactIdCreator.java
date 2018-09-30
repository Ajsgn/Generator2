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
 * <p>ArtifactId 的默认生成策略</p>
 * @ClassName: DefaultArtifactIdCreator
 * @Description: ArtifactId 的默认生成策略
 * @author Ajsgn@foxmail.com
 * @date 2018年9月30日 下午4:05:11
 */
public class DefaultArtifactIdCreator implements ArtifactIdCreator {
	
	private static final ArtifactIdCreator ME = new DefaultArtifactIdCreator();
	
	private DefaultArtifactIdCreator() {
	}

	public static ArtifactIdCreator singletonInstance() {
		return ME;
	}

	@Override
	public String modelArtifactId(String projectName) {
		return String.valueOf(projectName).concat("-model");
	}

	@Override
	public String utilArtifactId(String projectName) {
		return String.valueOf(projectName).concat("-util");
	}

	@Override
	public String daoArtifactId(String projectName) {
		return String.valueOf(projectName).concat("-dao");
	}

	@Override
	public String serviceArtifactId(String projectName) {
		return String.valueOf(projectName).concat("-service");
	}

	@Override
	public String webArtifactId(String projectName) {
		return String.valueOf(projectName).concat("-web");
	}

}
