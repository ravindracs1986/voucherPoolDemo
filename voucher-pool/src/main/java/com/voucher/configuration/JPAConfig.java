package com.voucher.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.*;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author Ravindra kumar
 * @email rv.kumar1401@gmail.com
 *
 */

@Configuration
@EnableTransactionManagement
@ComponentScan("com.voucher")
@EnableJpaRepositories(basePackages = { "com.voucher.repo"},transactionManagerRef="transactionManager")
@PropertySource(value = { "classpath:voucher.properties" })
public class JPAConfig {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
    private Environment environment;
	
	@Value("${" + ConfigConstants.DB_CONF_DRIVER + "}")
	private String dbDriver;
	
	@Value("${" + ConfigConstants.DB_CONF_URL + "}")
	private String dbUrl;

	@Value("${" + ConfigConstants.DB_CONF_UNAME + "}")
	private String dbUserName;

	@Value("${" + ConfigConstants.DB_CONF_PWORD + "}")
	private String dbPassword;
	
	
    @Bean(name = "dataSource")
    public DataSource dataSource() {
      
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
    	logger.info("driver::"+environment.getRequiredProperty("mysql.db.driver"));
        dataSource.setDriverClassName(environment.getRequiredProperty("mysql.db.driver"));
        dataSource.setUrl(environment.getRequiredProperty("mysql.db.url"));
        logger.info("URL::"+environment.getRequiredProperty("mysql.db.url"));
        dataSource.setUsername(environment.getRequiredProperty("mysql.db.uname"));
        logger.info("user::"+environment.getRequiredProperty("mysql.db.uname"));
        dataSource.setPassword(environment.getRequiredProperty("mysql.db.pword"));
        logger.info("pass::"+environment.getRequiredProperty("mysql.db.pword"));
        return dataSource;
    	
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan(new String[] { "com.voucher.entity" });
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setJpaProperties(additionalProperties());
        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
       // transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

       Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
         
        return properties;
    }
    @Bean
    public DozerBeanMapper getMapper() {
        return new DozerBeanMapper();
    }

}
