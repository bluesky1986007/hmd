package auto;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * Created by haomingjian ,
 * Date ： 2019/11/11 13:48
 * Desc : 启动类，scanBasePackages扫描其中报下的所有依赖注入注解
 */
//@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class},
//        scanBasePackages = {
//                "auto.common.*",
//                "auto.config",
//                "auto.page",
//                "auto.pojo",
//                "auto.service",
//                "auto.utils",
//                "auto.orm"
//        })

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class}, scanBasePackages = {"auto"})
public class HomeDoAutoTestApplication extends AbstractTestNGSpringContextTests {


}
