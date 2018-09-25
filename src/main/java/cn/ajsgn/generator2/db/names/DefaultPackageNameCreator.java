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
	public String modelPackage(String basePackage) {
		return StringUtils.defaultIfBlank(basePackage, DEFAULT_PACKAGE).concat(".model");
	}

	@Override
	public String absModelPackage(String basePackage) {
		return StringUtils.defaultIfBlank(basePackage, DEFAULT_PACKAGE).concat(".model.abs");
	}

	@Override
	public String mapperXmlPackage(String basePackage) {
		return StringUtils.defaultIfBlank(basePackage, DEFAULT_PACKAGE).concat(".mapper.xml");
	}

	@Override
	public String mapperPackage(String basePackage) {
		return StringUtils.defaultIfBlank(basePackage, DEFAULT_PACKAGE).concat(".mapper");
	}

	@Override
	public String daoPackage(String basePackage) {
		return StringUtils.defaultIfBlank(basePackage, DEFAULT_PACKAGE).concat(".dao");
	}

	@Override
	public String daoImplPackage(String basePackage) {
		return StringUtils.defaultIfBlank(basePackage, DEFAULT_PACKAGE).concat(".dao.impl");
	}

	@Override
	public String daoConditionPackage(String basePackage) {
		return StringUtils.defaultIfBlank(basePackage, DEFAULT_PACKAGE).concat(".condition");
	}

	@Override
	public String absDaoConditionPackage(String basePackage) {
		return StringUtils.defaultIfBlank(basePackage, DEFAULT_PACKAGE).concat(".condition.impl");
	}

	@Override
	public String absCommonPackage(String basePackage) {
		return StringUtils.defaultIfBlank(basePackage, DEFAULT_PACKAGE).concat(".abs");
	}

}
