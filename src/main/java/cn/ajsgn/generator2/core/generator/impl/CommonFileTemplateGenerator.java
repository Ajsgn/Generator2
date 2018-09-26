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
package cn.ajsgn.generator2.core.generator.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.ajsgn.generator2.core.generator.abs.AbstractFileTemplateGenerator;

/**
 * <p>通用文件生成器</p>
 * @ClassName: CommonFileTemplateGenerator
 * @Description: 通用文件生成器
 * @author Ajsgn@foxmail.com
 * @date 2018年9月26日 下午8:21:47
 */
public class CommonFileTemplateGenerator extends AbstractFileTemplateGenerator {
	
	//模板文件资源
	private String templateResourcePath;
	//模板文件中的参数集合
	private Map<String, Object> params = new HashMap<String, Object>();
	//输出文件对象
	private File outFile;
	
	public CommonFileTemplateGenerator(String templateResourcePath, Map<String, Object> params, File outFile) {
		if(true == StringUtils.isBlank(templateResourcePath))
			throw new IllegalArgumentException("模板资源文件不能为空...");
		if(null == outFile)
			throw new IllegalArgumentException("输出文件路径不能为null...");
		params = params == null ? new HashMap<String, Object>() : params;
		
		this.templateResourcePath = templateResourcePath;
		this.params = params;
		this.outFile = outFile;
	}

	@Override
	public Map<String, Object> targetParam() {
		return params;
	}

	@Override
	public String templateResource() {
		return templateResourcePath;
	}

	@Override
	protected File targetFile() {
		return outFile;
	}

}
