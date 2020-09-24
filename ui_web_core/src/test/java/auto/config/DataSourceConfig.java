package auto.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by haomingjian ,
 * Date ： 2019/11/14 17:41
 * Desc : 数据源配置，使用配置注解，代替SpringBoot默认注解，添加前缀，去读取配置文件中对应的配置参数
 */
@Configuration
public class DataSourceConfig {

//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource.fat")
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.fat")
    DataSource dsFat() {
        return DruidDataSourceBuilder.create().build();
    }


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.uat")
    DataSource dsUat() {
        return DruidDataSourceBuilder.create().build();
    }

}
