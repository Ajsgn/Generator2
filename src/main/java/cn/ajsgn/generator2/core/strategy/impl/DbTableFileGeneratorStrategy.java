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

import cn.ajsgn.generator2.constant.Constant;
import cn.ajsgn.generator2.core.strategy.abs.GeneratorStrategy;
import cn.ajsgn.generator2.core.template.abs.FileTemplate;
import cn.ajsgn.generator2.core.template.impl.PackageFileTemplate;
import cn.ajsgn.generator2.db.column.ColumnInfo;
import cn.ajsgn.generator2.db.column.mapping.ColumnMapping;
import cn.ajsgn.generator2.db.column.mapping.ColumnMappingFactory;
import cn.ajsgn.generator2.db.config.ColumnTypeEnum;
import cn.ajsgn.generator2.db.names.DefaultPackageNameCreator;
import cn.ajsgn.generator2.db.names.PackageNameCreator;
import cn.ajsgn.generator2.vm.VolecityInstance;

public class DbTableFileGeneratorStrategy implements GeneratorStrategy {
	
	private String user;
	private String password;
	private String jdbcUrl;
	private String driverClass;
	
	private Connection dbConnection;
	
	private String baseOutFolderPath;
	
	private String basePackage;
	
	private String absCommonPackage;
	private String baseModelPackage;
	private String modelPackage;
	private String baseDaoConditionPackage;
	private String daoConditionPackage;
	private String mapperPackage;
	private String mapperXmlPackage;
	private String baseMapperPackage;
	private String baseMapperXmlPackage;
	private String daoPackage;
	private String daoImplPackage;
	
	private ColumnMapping columnMapping;
	
	private List<FileTemplate> files = new ArrayList<FileTemplate>();
	
	private DbTableFileGeneratorStrategy(Builder builder) {
		this.user = builder.user;
		this.password = builder.password;
		this.jdbcUrl = builder.jdbcUrl;
		this.driverClass = builder.driverClass;
		//文件输出目录
		this.baseOutFolderPath = builder.baseOutFolderPath;
		//实例化连接
		init();
		//实例化包级目录
		setBasePackage(builder.basePackage, builder.packageNameCreator);
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
	
	private DbTableFileGeneratorStrategy setBasePackage(String basePackage, PackageNameCreator packageNameCreator) {
		packageNameCreator = packageNameCreator == null ? DefaultPackageNameCreator.singletonInstance() : packageNameCreator;
		this.basePackage = StringUtils.defaultIfBlank(basePackage, "");
		
		this.absCommonPackage = packageNameCreator.absCommonPackage(this.basePackage);
		this.baseModelPackage = packageNameCreator.baseModelPackage(this.basePackage);
		this.modelPackage = packageNameCreator.modelPackage(this.basePackage);
		this.baseDaoConditionPackage = packageNameCreator.baseDaoConditionPackage(this.basePackage);
		this.daoConditionPackage = packageNameCreator.daoConditionPackage(this.basePackage);
		this.mapperPackage = packageNameCreator.mapperPackage(this.basePackage);
		this.mapperXmlPackage = packageNameCreator.mapperXmlPackage(this.basePackage);
		this.baseMapperXmlPackage = packageNameCreator.baseMapperXmlPackage(this.basePackage);
		this.daoPackage = packageNameCreator.daoPackage(this.basePackage);
		this.daoImplPackage = packageNameCreator.daoImplPackage(this.basePackage);
		return this;
	}
	
	public DbTableFileGeneratorStrategy withAbstract() {
		generatorAbstract();
		return this;
	}
	
	public DbTableFileGeneratorStrategy withTable(String schemaName, String tableName, String className, String[] primaryKeys) {
		try {
			DatabaseMetaData databaseMetaData = this.dbConnection.getMetaData();
			List<ColumnInfo> columnInfos = columnInfos(databaseMetaData, schemaName, tableName);
			tableGeneratory(schemaName, tableName, className, columnInfos, primaryKeys);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	private void tableGeneratory(String schemaName, String tableName, String className, List<ColumnInfo> columnInfos, String[] primaryKeys) {
		//class name
		className = String.valueOf(className);	//Model
		String baseClassName = Constant.BASE_FILE_PREFIX.concat(className);	//BaseModel
		String daoConditionClassName = className.concat(Constant.DAO_CONDITION_SUFFIX);	//DaoCondition
		String baseDaoConditionClassName = Constant.BASE_FILE_PREFIX.concat(daoConditionClassName);	//BaseDaoCondition
		String mapperClassName = className.concat(Constant.MAPPER_SUFFIX);	//ModelMapper
		String baseMapperClassName = Constant.BASE_FILE_PREFIX.concat(mapperClassName);	//BaseModelMapper
		String daoClassName = className.concat(Constant.DAO_SUFFIX);	//Dao
		String daoImplClassName = daoClassName.concat(Constant.IMPLEMENT_SUFFIX);	//DaoImpl
		
		//file name
		String modelFileName = className.concat(Constant.JAVA_FILE_SUFFIX);	//model.java
		String baseModelFileName = baseClassName.concat(Constant.JAVA_FILE_SUFFIX);	//BaseModel.java
		String daoConditionFileName = daoConditionClassName.concat(Constant.JAVA_FILE_SUFFIX);	//DaoCondition.java
		String baseDaoConditionFileName = baseDaoConditionClassName.concat(Constant.JAVA_FILE_SUFFIX);	//BaseDaoCondition.java
		String mapperFileName = mapperClassName.concat(Constant.JAVA_FILE_SUFFIX);	//ModelMapper.java
		String mapperXmlFileName = mapperClassName.concat(Constant.XML_FILE_SUFFIX);	//ModelMapper.xml
		String baseMapperXmlFileName = baseMapperClassName.concat(Constant.XML_FILE_SUFFIX);	//ModelBaseMapper.xml
		String daoFileName = daoClassName.concat(Constant.JAVA_FILE_SUFFIX);	//Dao.java
		String daoImplFileName = daoImplClassName.concat(Constant.JAVA_FILE_SUFFIX);	//DaoImpl.java
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("_createDate", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		params.put("_primaryKeys", primaryKeys(primaryKeys, columnInfos));
		params.put("_schemaName", schemaName);
		params.put("_tableName", tableName);
		//abs
		params.put("_absCommonPackage", this.absCommonPackage);
		//column info & mapping
		params.put("_columnInfos", columnInfos);
		params.put("_columnMapping", this.columnMapping);
		//base model
		params.put("_baseModelPackage", this.baseModelPackage);
		params.put("_baseClassName", baseClassName);
		params.put("_toStringClassName", baseClassName);
		//model 
		params.put("_modelPackage", this.modelPackage);
		params.put("_className", className);
		//base daoCondition
		params.put("_baseDaoConditionPackage", this.baseDaoConditionPackage);
		params.put("_baseDaoConditionClassName", baseDaoConditionClassName);
		//daoCondition
		params.put("_daoConditionPackage", this.daoConditionPackage);
		params.put("_daoConditionClassName", daoConditionClassName);
		//base mapper
		params.put("_mapperPackage", this.mapperPackage);
		params.put("_mapperClassName", mapperClassName);
		params.put("_baseMapperPackage", this.baseMapperPackage);
		params.put("_baseMapperClassName", baseMapperClassName);
		//dao & daoImpl
		params.put("_daoPackage", this.daoPackage);
		params.put("_daoClassName", daoClassName);
		params.put("_daoImplPackage", this.daoImplPackage);
		params.put("_daoImplClassName", daoImplClassName);
		
		//model
		files.add(new PackageFileTemplate(this.baseOutFolderPath, this.modelPackage, modelFileName, "cn/ajsgn/generator2/template/db/model/Model.vm", params));
		files.add(new PackageFileTemplate(this.baseOutFolderPath, this.baseModelPackage, baseModelFileName, "cn/ajsgn/generator2/template/db/model/base/BaseModel.vm", params));
		//daocondition
		files.add(new PackageFileTemplate(this.baseOutFolderPath, this.daoConditionPackage, daoConditionFileName, "cn/ajsgn/generator2/template/db/condition/DaoCondition.vm", params));
		files.add(new PackageFileTemplate(this.baseOutFolderPath, this.baseDaoConditionPackage, baseDaoConditionFileName, "cn/ajsgn/generator2/template/db/condition/base/BaseDaoCondition.vm", params));
		//mapper & baseMapper
		files.add(new PackageFileTemplate(this.baseOutFolderPath, this.mapperPackage, mapperFileName, "cn/ajsgn/generator2/template/db/mapper/Mapper.vm", params));
//		files.add(new PackageFileTemplateGenerator(this.baseOutFolderPath, this.baseMapperPackage, baseMapperFileName, "cn/ajsgn/generator2/template/db/mapper/base/BaseMapper.vm", params));
		//mapper.xml & baseMapper.xml
		files.add(new PackageFileTemplate(this.baseOutFolderPath, this.mapperXmlPackage, mapperXmlFileName, "cn/ajsgn/generator2/template/db/mapper/xml/Mapper.xml.vm", params));
		files.add(new PackageFileTemplate(this.baseOutFolderPath, this.baseMapperXmlPackage, baseMapperXmlFileName, "cn/ajsgn/generator2/template/db/mapper/xml/base/BaseMapper.xml.vm", params));
		//dao & daoImpl
		files.add(new PackageFileTemplate(this.baseOutFolderPath, this.daoPackage, daoFileName, "cn/ajsgn/generator2/template/db/dao/Dao.vm", params));
		files.add(new PackageFileTemplate(this.baseOutFolderPath, this.daoImplPackage, daoImplFileName, "cn/ajsgn/generator2/template/db/dao/impl/DaoImpl.vm", params));
	
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
		files.add(new PackageFileTemplate(this.baseOutFolderPath, this.absCommonPackage, "AbstractDaoImpl.java", "cn/ajsgn/generator2/template/db/abs/AbstractDaoImpl.vm", params));
	}
	
	private void generatorBaseDao() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("_basePackage", this.absCommonPackage);
		params.put("_createDate", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		files.add(new PackageFileTemplate(this.baseOutFolderPath, this.absCommonPackage, "BaseDao.java", "cn/ajsgn/generator2/template/db/abs/BaseDao.vm", params));
	}
	
	private void generatorBaseMapper() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("_basePackage", this.absCommonPackage);
		params.put("_createDate", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		files.add(new PackageFileTemplate(this.baseOutFolderPath, this.absCommonPackage, "BaseMapper.java", "cn/ajsgn/generator2/template/db/abs/BaseMapper.vm", params));
	}
	
	private void generatorDaoCondition() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("_basePackage", this.absCommonPackage);
		params.put("_createDate", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		files.add(new PackageFileTemplate(this.baseOutFolderPath, this.absCommonPackage, "DaoCondition.java", "cn/ajsgn/generator2/template/db/abs/DaoCondition.vm", params));
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
	
	/**
	 * <p>主键描述拼接，用于Mapper</p>
	 * @Title: primaryKeys
	 * @Description: 主键描述拼接，用于Mapper
	 * @author Ajsgn@foxmail.com
	 * @date 2017年10月22日 下午7:35:52
	 */
	private List<ColumnInfo> primaryKeys(String[] primaryKeysArray, List<ColumnInfo> columnInfos){
		if(null == primaryKeysArray || 0 == primaryKeysArray.length) {
			primaryKeysArray = new String[] {"ID"};
		}
		List<ColumnInfo> primaryKeys = new ArrayList<ColumnInfo>();
		for(String pk : primaryKeysArray){
			for(ColumnInfo columnInfo : columnInfos){
				if(StringUtils.equalsIgnoreCase(pk, columnInfo.getColumnName())){
					primaryKeys.add(columnInfo);
				}
			}
		}
		return primaryKeys;
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
		
		private String baseOutFolderPath = Constant.DEFAULT_OUTPUT_BASE_FOLDER;
		private String basePackage = Constant.DEFAULT_BASE_PACKAGE;
		private PackageNameCreator packageNameCreator = DefaultPackageNameCreator.singletonInstance();
		
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
		
		public Builder basePackage(String basePackage, PackageNameCreator packageNameCreator) {
			this.basePackage = basePackage;
			this.packageNameCreator = packageNameCreator;
			return this;
		}
		
		public DbTableFileGeneratorStrategy build() {
			DbTableFileGeneratorStrategy strategy = new DbTableFileGeneratorStrategy(this);
			return strategy;
		}
		
	}

}

