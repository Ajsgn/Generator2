package cn.ajsgn.generator2.core.strategy.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
import cn.ajsgn.generator2.db.column.ColumnInfo;
import cn.ajsgn.generator2.db.column.mapping.ColumnMapping;
import cn.ajsgn.generator2.db.column.mapping.ColumnMappingFactory;
import cn.ajsgn.generator2.db.config.ColumnTypeEnum;
import cn.ajsgn.generator2.db.names.DefaultPackageNameCreator;
import cn.ajsgn.generator2.db.names.PackageNameCreator;
import cn.ajsgn.generator2.util.StrKit;
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
	
	private ColumnMapping columnMapping;
	
	private List<FileTemplateGenerator> files = new ArrayList<FileTemplateGenerator>();
	
	private DbGeneratorStrategy(Builder builder) {
		this.user = builder.user;
		this.password = builder.password;
		this.jdbcUrl = builder.jdbcUrl;
		this.driverClass = builder.driverClass;
		//文件输出目录
		this.baseOutFolderPath = builder.baseOutFolderPath;
		//实例化连接
		init();
		//实例化包级目录
		setBasePackage("cn.ajsgn.generator2", DefaultPackageNameCreator.singletonInstance());
		//数据库示例的列类型映射
		this.columnMapping = ColumnMappingFactory.instanceOf(driverClass);
	}
	
	private void init() {
		try {
			//尝试建立链接
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
	
	public DbGeneratorStrategy withTable(String schemaName, String tableName, String beanName){
		try {
			DatabaseMetaData databaseMetaData = this.dbConnection.getMetaData();
			List<ColumnInfo> columnInfos = columnInfos(databaseMetaData, schemaName, tableName);
			
			
			generatorModel(tableName, beanName, columnInfos);
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	private void generatorModel(String tableName, String beanName, List<ColumnInfo> columnInfos) {
		tableName = String.valueOf(tableName);
		tableName = String.valueOf(beanName);
		String abstractBeanName = "Abstract".concat(beanName);
		
		String fileName = String.format("%s.java", String.valueOf(beanName));
		String AbstractFileName = String.format("%s.java", String.valueOf(abstractBeanName));
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("_basePackage", this.absCommonPackage);
		params.put("_createDate", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		params.put("_absModelPackage", this.absModelPackage);
		params.put("_abstractBeanName", abstractBeanName);
		params.put("_toStringBeanName", abstractBeanName);
		params.put("columnInfos", columnInfos);
		params.put("columnMapping", this.columnMapping);
		params.put("_modelPackage", this.modelPackage);
		params.put("_beanName", beanName);
		
		files.add(new PackageFileTemplateGenerator(this.baseOutFolderPath, this.absModelPackage, AbstractFileName, "cn/ajsgn/generator2/template/db/model/abs/AbstractModel.vm", params));
		files.add(new PackageFileTemplateGenerator(this.baseOutFolderPath, this.modelPackage, fileName, "cn/ajsgn/generator2/template/db/model/Model.vm", params));
		
		
		
		
		
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
	
	private List<ColumnInfo> columnInfos(DatabaseMetaData databaseMetaData, String schemaName, String tableName) throws SQLException{
		schemaName = StringUtils.isBlank(schemaName) ? user : schemaName;
		List<ColumnInfo> list = new ArrayList<ColumnInfo>(23);
		ResultSet rs = databaseMetaData.getColumns(null, schemaName.toUpperCase(), tableName, "%");
		while(rs.next()){
			ColumnInfo columnInfo = new ColumnInfo();
			columnInfo.setBufferLength(rs.getString(ColumnTypeEnum.BUFFER_LENGTH.getValue()));
			columnInfo.setCharOctetLength(rs.getInt(ColumnTypeEnum.CHAR_OCTET_LENGTH.getValue()));
			// FIXME 表中如果有默认值，此处会报  java.sql.SQLException: 流已被关闭
//			columnInfo.setColumnDef(rs.getString(ColumnTypeEnum.COLUMN_DEF.getValue()));
			columnInfo.setColumnName(rs.getString(ColumnTypeEnum.COLUMN_NAME.getValue()));
			columnInfo.setColumnSize(rs.getInt(ColumnTypeEnum.COLUMN_SIZE.getValue()));
			columnInfo.setDataType(rs.getInt(ColumnTypeEnum.DATA_TYPE.getValue()));
			columnInfo.setDecimalDigits(rs.getInt(ColumnTypeEnum.DECIMAL_DIGITS.getValue()));
			columnInfo.setIsAutoincrement(rs.getString(ColumnTypeEnum.IS_AUTOINCREMENT.getValue()));
			columnInfo.setIsNullable(rs.getString(ColumnTypeEnum.IS_NULLABLE.getValue()));
			columnInfo.setNullable(rs.getInt(ColumnTypeEnum.NULLABLE.getValue()));
			columnInfo.setNumPrecRadix(rs.getInt(ColumnTypeEnum.NUM_PREC_RADIX.getValue()));
			columnInfo.setOrdinalPosition(rs.getInt(ColumnTypeEnum.ORDINAL_POSITION.getValue()));
			columnInfo.setRemarks(rs.getString(ColumnTypeEnum.REMARKS.getValue()));
			columnInfo.setScopeCatlog(rs.getString(ColumnTypeEnum.SCOPE_CATLOG.getValue()));
			columnInfo.setScopeSchema(rs.getString(ColumnTypeEnum.SCOPE_SCHEMA.getValue()));
			columnInfo.setScopeTable(rs.getString(ColumnTypeEnum.SCOPE_TABLE.getValue()));
			columnInfo.setSourceDataType(rs.getInt(ColumnTypeEnum.SOURCE_DATA_TYPE.getValue()));
			columnInfo.setSqlDataType(rs.getInt(ColumnTypeEnum.SQL_DATA_TYPE.getValue()));
			columnInfo.setSqlDatetimeSub(rs.getInt(ColumnTypeEnum.SQL_DATETIME_SUB.getValue()));
			columnInfo.setTableCat(rs.getString(ColumnTypeEnum.TABLE_CAT.getValue()));
			columnInfo.setTableName(rs.getString(ColumnTypeEnum.TABLE_NAME.getValue()));
			columnInfo.setTableSchem(rs.getString(ColumnTypeEnum.TABLE_SCHEM.getValue()));
			columnInfo.setTypeName(rs.getString(ColumnTypeEnum.TYPE_NAME.getValue()));
			list.add(columnInfo);
		}
		return list;
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
		releaseConnection();
	}
	
	private void releaseConnection() {
		if(null != this.dbConnection) {
			try {
				this.dbConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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

