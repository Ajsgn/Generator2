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
 * <p>通用文件生成器</p>
 * @ClassName: CommonFileTemplateGenerator
 * @Description: 通用文件生成器
 * @author Ajsgn@foxmail.com
 * @date 2018年9月26日 下午8:21:47
 */
public class CommonFileTemplate extends AbstractFileTemplate {
	
	//模板文件资源
	private String templateResourcePath;
	//模板文件中的参数集合
	private Map<String, Object> params = new HashMap<String, Object>();
	//输出文件对象
	private File outFile;
	//标识当前是否为文件夹
	private boolean isDirectory = false;
	
	//私有化构造
	private CommonFileTemplate(String templateResourcePath, Map<String, Object> params, File outFile) {
		this.templateResourcePath = templateResourcePath;
		this.params = params;
		this.outFile = outFile;
	}
	
	//私有化构造
	private CommonFileTemplate(File outFile) {
		this.outFile = outFile;
	}
	
	/**
	 * <p>文件模板创建<p>
	 * @Title: fileTemplate
	 * @Description: 文件模板创建
	 * @param baseOutFolderPath 输出文件目录
	 * @param templateResourcePath 模板文件资源路径
	 * @param params 模板文件中所需参数
	 * @param fileName 文件名称
	 * @author Ajsgn@foxmail.com
	 * @date 2018年9月30日 下午5:22:10
	 * @return CommonFileTemplate 通用文件模板对象
	 */
	public static final CommonFileTemplate fileTemplate(String baseOutFolderPath, String templateResourcePath, Map<String, Object> params, String fileName) {
		if(true == StringUtils.isBlank(baseOutFolderPath) || true == StringUtils.isBlank(fileName))
			throw new IllegalArgumentException("输出文件路径与文件名称不能为空...");
		if(true == StringUtils.isBlank(templateResourcePath))
			throw new IllegalArgumentException("模板资源文件路径不能为空...");
		params = params == null ? new HashMap<String, Object>() : params;
		
		baseOutFolderPath = FileUtil.filePathCompatible(baseOutFolderPath);
		baseOutFolderPath = FileUtil.pathEndWithFileSeparator(baseOutFolderPath);
		
		fileName = baseOutFolderPath.concat(fileName);
		File targetFile = new File(fileName);
		CommonFileTemplate fileTemplate = new CommonFileTemplate(templateResourcePath, params, targetFile);
		return fileTemplate;
	}
	
	/**
	 * <p>文件夹模板创建<p>
	 * @Title: folderTemplate
	 * @Description: 文件夹模板创建
	 * @param folderPath 文件夹路径
	 * @author g.yang@i-vpoints.com
	 * @date 2018年9月30日 下午5:23:26
	 * @return CommonFileTemplate 通用文件模板对象
	 */
	public static final CommonFileTemplate folderTemplate(String folderPath) {
		if(StringUtils.isBlank(folderPath)) {
			throw new IllegalArgumentException("folder path 不能为空");
		}
		folderPath = FileUtil.filePathCompatible(folderPath);
		File outFile = new File(folderPath);
		CommonFileTemplate fileTemplate = new CommonFileTemplate(outFile);
		fileTemplate.isDirectory = true;
		return fileTemplate;
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
		if(true == isDirectory) {
			if(false == outFile.exists())
				outFile.mkdirs();
			return null;
		} else {
			return outFile;
		}
	}

}
