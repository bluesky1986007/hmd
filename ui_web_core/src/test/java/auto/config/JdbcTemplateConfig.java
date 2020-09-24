package auto.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by haomingjian ,
 * Date ： 2019/11/14 17:46
 * Desc ：JdbcTemplate配置，使用@Qualifier("数据源中的对应的方法名")指定使用哪一个数据源
 */
@Configuration
public class JdbcTemplateConfig {

    @Bean
    JdbcTemplate fatJdbcTemplate(@Qualifier("dsFat") DataSource dsFat) {
        return new JdbcTemplate(dsFat);
    }

    @Bean
    JdbcTemplate uatJdbcTemplate(@Qualifier("dsUat") DataSource dsUat) {
        return new JdbcTemplate(dsUat);
    }
    
}
