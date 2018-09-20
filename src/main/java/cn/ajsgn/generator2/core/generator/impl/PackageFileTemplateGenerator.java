package cn.ajsgn.generator2.core.generator.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.ajsgn.generator2.core.generator.abs.AbstractFileTemplateGenerator;
import cn.ajsgn.generator2.util.FileUtil;

public class PackageFileTemplateGenerator extends AbstractFileTemplateGenerator {
	
	private String baseOutFolderPath;
	private String templateResourcePath;
	private Map<String, Object> params = new HashMap<String, Object>();
	private File outFile;
	
	public PackageFileTemplateGenerator(String baseOutFolderPath, String packageName, String fileName, String templateResourcePath, Map<String, Object> params) {
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
		System.out.println(String.format("【准备文件】 : %s", outFile.getAbsolutePath()));
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
	
	public static void main(String[] args) {
//		String path = "c:/asd/efg/";
//		path = path.replaceAll("/", File.separator+File.separator);
//		System.out.println(StringUtils.endsWith(path, File.separator));
//		System.out.println(path.concat(File.separator));
		
		PackageFileTemplateGenerator gen = new PackageFileTemplateGenerator("d:/generator2/mysql", "cn.ajsgn.model.xxx", "test.java", "asdasd", new HashMap<String, Object>());
		
		System.out.println(gen.targetFile().getAbsolutePath());
		
		
	}
	

}
