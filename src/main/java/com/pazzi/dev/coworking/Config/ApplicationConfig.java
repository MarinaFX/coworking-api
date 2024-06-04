package com.pazzi.dev.coworking.Config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.pazzi.dev.coworking")
@EnableTransactionManagement
public class ApplicationConfig {


}

//@Configuration
//@ComponentScan(basePackages = "com.pazzi.dev.coworking")
//@EnableTransactionManagement
//public class DatabaseConfig {
//
//    protected static final String PROPERTY_NAME_DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
//    protected static final String PROPERTY_NAME_DATABASE_PASSWORD = "dev213@";
//    protected static final String PROPERTY_NAME_DATABASE_URL = "jdbc:mysql://localhost:3306/coworking";
//    protected static final String PROPERTY_NAME_DATABASE_USERNAME = "dev";
//
//    private static final String PROPERTY_PACKAGES_TO_SCAN = "com.pazzi.dev.coworking";
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter){
//        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
//        entityManagerFactoryBean.setDataSource(dataSource);
//        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
//        entityManagerFactoryBean.setPackagesToScan(PROPERTY_PACKAGES_TO_SCAN);
//        return entityManagerFactoryBean;
//    }
//
//    @Bean
//    public BasicDataSource dataSource(){
//        BasicDataSource ds = new BasicDataSource();
//        ds.setDriverClassName(PROPERTY_NAME_DATABASE_DRIVER);
//        ds.setUrl(PROPERTY_NAME_DATABASE_URL);
//        ds.setUsername(PROPERTY_NAME_DATABASE_USERNAME);
//        ds.setPassword(PROPERTY_NAME_DATABASE_PASSWORD);
//        ds.setInitialSize(5);
//        return ds;
//    }
//
//    @Bean
//    public JpaVendorAdapter jpaVendorAdapter(){
//        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
//        adapter.setDatabase(Database.MYSQL);
//        adapter.setShowSql(true);
//        adapter.setGenerateDdl(true);
//
//        //I'm using MySQL5InnoDBDialect to make my tables support foreign keys
//        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");
//        return adapter;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }
//}