package cn.ajsgn.generator2.core.strategy.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import cn.ajsgn.generator2.core.generator.abs.FileTemplateGenerator;
import cn.ajsgn.generator2.core.generator.impl.PackageFileTemplateGenerator;
import cn.ajsgn.generator2.core.strategy.abs.GeneratorStrategy;
import cn.ajsgn.generator2.db.names.DefaultPackageNameCreator;
import cn.ajsgn.generator2.db.names.PackageNameCreator;
import cn.ajsgn.generator2.vm.VolecityInstance;

public class DbGeneratorStrategy implements GeneratorStrategy {
	
	private String user;
	private String password;
	private String jdbcUrl;
	private String driverClass;
	
	private Connection dbConnection;
	
	private String baseOutFolderPath;
	
	private String basePackage;
	
	private String modelPackage;
	private String absModelPackage;
	private String mapperPackage;
	private String mapperXmlPackage;
	private String daoPackage;
	private String daoImplPackage;
	private String daoConditionPackage;
	private String absDaoConditionPackage;
	private String absCommonPackage;
	
	private List<FileTemplateGenerator> files = new ArrayList<FileTemplateGenerator>();
	
	private DbGeneratorStrategy(Builder builder) {
		this.user = builder.user;
		this.password = builder.password;
		this.jdbcUrl = builder.jdbcUrl;
		this.driverClass = builder.driverClass;
		
		this.baseOutFolderPath = builder.baseOutFolderPath;
		
		init();
	}
	
	private void init() {
		try {
			Class.forName(driverClass);
			Properties props =new Properties();
			props.put("remarksReporting", "true");
			props.put("user", user);
			props.put("password", password);
			this.dbConnection = DriverManager.getConnection(jdbcUrl, props);
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public DbGeneratorStrategy setBasePackage(String basePackage, PackageNameCreator packageNameCreator) {
		packageNameCreator = packageNameCreator == null ? DefaultPackageNameCreator.singletonInstance() : packageNameCreator;
		this.basePackage = StringUtils.defaultIfBlank(basePackage, "");
		
		this.modelPackage = packageNameCreator.modelPackage(this.basePackage);
		this.absModelPackage = packageNameCreator.absModelPackage(this.basePackage);
		this.mapperPackage = packageNameCreator.mapperPackage(this.basePackage);
		this.mapperXmlPackage = packageNameCreator.mapperXmlPackage(this.basePackage);
		this.daoPackage = packageNameCreator.daoPackage(this.basePackage);
		this.daoImplPackage = packageNameCreator.daoImplPackage(this.basePackage);
		this.daoConditionPackage = packageNameCreator.daoConditionPackage(this.basePackage);
		this.absDaoConditionPackage = packageNameCreator.absDaoConditionPackage(this.basePackage);
		this.absCommonPackage = packageNameCreator.absCommonPackage(this.basePackage);
		
		return this;
	}
	
	public DbGeneratorStrategy withAbstract() {
		generatorAbstract();
		return this;
	}
	
	public DbGeneratorStrategy withTable(String tableName) {
		
		return this;
	}
	
	private void generatorAbstract() {
		generatorAbstractDaoImpl();
		generatorBaseDao();
		generatorBaseMapper();
		generatorDaoCondition();
	}
	
	private void generatorAbstractDaoImpl() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("_basePackage", this.absCommonPackage);
		params.put("_createDate", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		files.add(new PackageFileTemplateGenerator(this.baseOutFolderPath, this.absCommonPackage, "AbstractDaoImpl.java", "cn/ajsgn/generator2/template/db/abs/AbstractDaoImpl.vm", params));
	}
	
	private void generatorBaseDao() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("_basePackage", this.absCommonPackage);
		params.put("_createDate", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		files.add(new PackageFileTemplateGenerator(this.baseOutFolderPath, this.absCommonPackage, "BaseDao.java", "cn/ajsgn/generator2/template/db/abs/BaseDao.vm", params));
	}
	
	private void generatorBaseMapper() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("_basePackage", this.absCommonPackage);
		params.put("_createDate", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		files.add(new PackageFileTemplateGenerator(this.baseOutFolderPath, this.absCommonPackage, "BaseMapper.java", "cn/ajsgn/generator2/template/db/abs/BaseMapper.vm", params));
	}
	
	private void generatorDaoCondition() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("_basePackage", this.absCommonPackage);
		params.put("_createDate", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		files.add(new PackageFileTemplateGenerator(this.baseOutFolderPath, this.absCommonPackage, "DaoCondition.java", "cn/ajsgn/generator2/template/db/abs/DaoCondition.vm", params));
	}

	@Override
	public void generator(VolecityInstance vm) {
		this.files.forEach(file -> {
			try {
				vm.flush(file.templateResource(), file.targetParam(), file.targetFileWriter());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	
	public static final class Builder {
		
		private String user;
		private String password;
		private String jdbcUrl;
		private String driverClass;
		
		private String baseOutFolderPath = "d:/Generator2";
		
		public Builder(String user, String password, String jdbcUrl, String driverClass) {
			if(true == StringUtils.isAnyBlank(user, password, jdbcUrl, driverClass))
				throw new IllegalArgumentException("数据库连接参数不能为空...");
			this.user = user;
			this.password = password;
			this.jdbcUrl = jdbcUrl;
			this.driverClass = driverClass;
		}
		
		public Builder baseOutFolderPath(String baseOutFolderPath) {
			if(false == StringUtils.isBlank(baseOutFolderPath)) {
				this.baseOutFolderPath = baseOutFolderPath;
			}
			return this;
		}
		
		public DbGeneratorStrategy build() {
			DbGeneratorStrategy strategy = new DbGeneratorStrategy(this);
			return strategy;
		}
		
		
	}

}

