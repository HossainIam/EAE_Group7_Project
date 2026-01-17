package de.hnu;


import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

// The class AppConfig is the configuration.
// The objects created inside it are called Beans.

@Configuration // Spring configuration.
@EnableJpaRepositories // Enable all UserRepository, RideRepository, etc.
@EnableTransactionManagement // Enable all UserRepository, RideRepository, etc. Please manage database transactions for me automatically.
public class AppConfig {
  // A bean is an object that is managed by Spring .
  @Bean
  @ConfigurationProperties("app.datasource")
  public DataSourceProperties dataSourceProperties() {
      return new DataSourceProperties();
  }

  @Bean  
  @ConfigurationProperties("app.datasource")
  public DataSource dataSource() {
    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create().type(HikariDataSource.class); 
    return dataSourceBuilder.build();
  }

  @Bean
  @ConfigurationProperties("app.jpa")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    EclipseLinkJpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
    vendorAdapter.setGenerateDdl(true);
    vendorAdapter.setShowSql(true);
  
    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan("de.hnu.data", "de.hnu.rides");
    factory.setDataSource(dataSource());
    return factory;
  }

  @Bean // Okay, I found the transaction manager. Now I can control transactions automatically.
  public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
    JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(entityManagerFactory.getObject());
    return txManager;
  } 

}