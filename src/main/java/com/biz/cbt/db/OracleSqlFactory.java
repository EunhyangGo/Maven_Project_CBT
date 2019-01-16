package com.biz.cbt.db;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;


import com.biz.cbt.dao.CBTDao;

public class OracleSqlFactory {
	
	SqlSessionFactory sessionFactory;
	
	public SqlSessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
	
	public OracleSqlFactory() {
		
		Properties props = new Properties();
		
		props.put("DRIVER",	DBContract.oracle_profile.Driver);
		props.put("URL", DBContract.oracle_profile.url);
		props.put("USER", DBContract.oracle_profile.user);
		props.put("PASSWORD", DBContract.oracle_profile.password);
		
		// datasourceFactory 만들기
		DataSourceFactory dataSourceFactory = new CBTDataSourceFactory();
		
		dataSourceFactory.setProperties(props);
		
		// datasourcefactory로부터 datasource를 요청
		DataSource dataSource = dataSourceFactory.getDataSource();
		
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		
		Environment environment = new Environment("CBTEnv", transactionFactory, dataSource);
		
		Configuration config = new Configuration(environment);
		config.addMapper(CBTDao.class);
		
		
		this.sessionFactory = new SqlSessionFactoryBuilder().build(config);
		
		
	}

}
