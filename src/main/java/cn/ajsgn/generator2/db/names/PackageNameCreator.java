package cn.ajsgn.generator2.db.names;

public interface PackageNameCreator {

	public abstract String modelPackage(String basePackage);

	public abstract String absModelPackage(String basePackage);

	public abstract String mapperXmlPackage(String basePackage);

	public abstract String mapperPackage(String basePackage);

	public abstract String daoPackage(String basePackage);

	public abstract String daoImplPackage(String basePackage);

	public abstract String daoConditionPackage(String basePackage);

	public abstract String absDaoConditionPackage(String basePackage);
	
	public abstract String absCommonPackage(String basePackage);

}
