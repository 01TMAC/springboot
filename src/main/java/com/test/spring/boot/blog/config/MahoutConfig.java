package com.test.spring.boot.blog.config;

import com.mysql.cj.jdbc.MysqlDataSource;
import lombok.Data;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.net.URL;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class MahoutConfig {
//    # DataSource
////    spring.datasource.url=jdbc:mysql://localhost:3306/blog?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC
////    spring.datasource.username=root
////    spring.datasource.password=123456
////    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    private String url;
    private String username;
    private String password;
    @Autowired
    DataSource dataSource;
    private MysqlDataSource getDataSource(){
        MysqlDataSource dataSource=new MysqlDataSource();
        dataSource.setServerName(this.getUrl());
        dataSource.setUser(this.getUsername());
        dataSource.setPassword(this.getPassword());
        dataSource.setDatabaseName("blog");
//        System.out.print("===============================username:"+this.getUsername());
        return dataSource;
    }

    @Bean(autowire = Autowire.BY_NAME,value = "mySQLDataModel")
    public DataModel getMySQLJDBCDataModel() throws  Exception{
        DataModel dataModel=new MySQLJDBCDataModel(this.dataSource,"preference","user_id","movie_id","rating", "rate_time");

        System.out.print("====================datamodle:"+dataModel.toString());
        return dataModel;
    }

//    @Bean(autowire = Autowire.BY_NAME,value = "fileDataModel")
//    public DataModel getDataModel() throws IOException {
//        URL url=MahoutConfig.class.getClassLoader().getResource("mahout/ratings.data");
//        DataModel dataModel = new FileDataModel(new File(url.getFile()));
//        return dataModel;
//    }
}
