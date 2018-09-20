package cn.ajsgn.generator2.core.generator.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.ajsgn.generator2.core.generator.abs.AbstractFileTemplateGenerator;

public class CommonFileTemplateGenerator extends AbstractFileTemplateGenerator {
	
	private String templateResourcePath;
	private Map<String, Object> params = new HashMap<String, Object>();
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
