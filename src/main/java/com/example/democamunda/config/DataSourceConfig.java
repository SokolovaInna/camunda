package com.example.democamunda.config;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


@Configuration
public class DataSourceConfig {



    @Primary
    @Bean
    @ConfigurationProperties(prefix = "datasource.card")
    public  DataSource cardDataSource(){
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "camundaBpmnDataSource")
    @ConfigurationProperties(prefix = "datasource.camunda")
    public DataSource camundaDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    PlatformTransactionManager transactionManager(@Qualifier("camundaBpmnDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }


}
