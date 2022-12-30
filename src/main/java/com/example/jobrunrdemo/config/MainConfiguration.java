package com.example.jobrunrdemo.config;


import org.jobrunr.jobs.mappers.JobMapper;
import org.jobrunr.storage.InMemoryStorageProvider;
import org.jobrunr.storage.StorageProvider;
import org.jobrunr.storage.sql.mysql.MySqlStorageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class MainConfiguration {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/jobrunrdemo?serverTimezone=Asia/Taipei&useLegacyDatetimeCode=false");
        dataSource.setUsername("root");
        dataSource.setPassword("SYSTEM");
        return dataSource;
    }

    @Bean
    public StorageProvider storageProvider(DataSource dataSource, JobMapper jobMapper) {
        final MySqlStorageProvider mySqlStorageProvider= new MySqlStorageProvider(dataSource);
        mySqlStorageProvider.setJobMapper(jobMapper);
        return mySqlStorageProvider;
    }

}
