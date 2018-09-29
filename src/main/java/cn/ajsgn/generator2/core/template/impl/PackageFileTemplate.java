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
package cn.ajsgn.generator2.core.template.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.ajsgn.generator2.core.template.abs.AbstractFileTemplate;
import cn.ajsgn.generator2.util.FileUtil;

/**
 * <p>classpath 包文件生成器</p>
 * @ClassName: PackageFileTemplateGenerator
 * @Description: classpath 包文件生成器
 * @author Ajsgn@foxmail.com
 * @date 2018年9月26日 下午8:23:12
 */
public class PackageFileTemplate extends AbstractFileTemplate {
	
	//文件基本输出路径
	private String baseOutFolderPath;
	//模板资源路径
	private String templateResourcePath;
	//参数集合
	private Map<String, Object> params = new HashMap<String, Object>();
	//输出文件
	private File outFile;
	
	public PackageFileTemplate(String baseOutFolderPath, String packageName, String fileName, String templateResourcePath, Map<String, Object> params) {
		if(true == StringUtils.isBlank(templateResourcePath))
			throw new IllegalArgumentException("模板资源文件不能为空...");
		this.templateResourcePath = templateResourcePath;
		this.baseOutFolderPath = StringUtils.defaultIfBlank(baseOutFolderPath, "");
		this.params = null == params ? new HashMap<String, Object>() : params;
		
		//创建文件，兼容文件的目录分隔符
		String separator = File.separator;
		if(StringUtils.equals("\\", separator))
			separator += separator;
		
		this.baseOutFolderPath = FileUtil.filePathCompatible(baseOutFolderPath);
		this.baseOutFolderPath = FileUtil.pathEndWithFileSeparator(this.baseOutFolderPath);
		
		StringBuilder sb = new StringBuilder().append(this.baseOutFolderPath)	//
				.append(packageName.replaceAll("\\.", separator))	//
				.append(separator)	//
				.append(fileName);
		
		outFile = new File(String.valueOf(sb));
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
		System.out.println(String.format("【准备文件】 : %s", outFile.getAbsolutePath()));
		return outFile;
	}

}
