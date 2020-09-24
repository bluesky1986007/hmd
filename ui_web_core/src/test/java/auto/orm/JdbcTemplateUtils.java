package auto.orm;

import auto.common.control.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
/**
 * Created by haomingjian ,
 * Date ： 2019/11/14 16:31
 * Desc ： 数据库工具类
 */
@Component
public class JdbcTemplateUtils extends BaseTest {

//    public Logger logger = LoggerFactory.getLogger(JdbcTemplateUtils.class);
    //    分别注入两个JdbcTemplate

    @Resource(name = "fatJdbcTemplate")
    private JdbcTemplate fatJdbcTemplate;

    @Resource(name = "uatJdbcTemplate")
    private JdbcTemplate uatJdbcTemplate;

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return this.jdbcTemplate;
    }

    public void setJdbcTemplate() {
        if (env.equals("fat")){
            jdbcTemplate = fatJdbcTemplate;
        }else if (env.equals("uat")){
            jdbcTemplate = uatJdbcTemplate;
        }else {
            logger.info("线上BD未配置");
        }
    }


    /**
     * @Author : haomingjian , 2019/11/14 16:42
     * @param sql
     * @retrun : {@link Object}
     * @Description : 取一行数据，如果有多行，也只返回第一行
     */
    public  Map<String,Object> getObjectBySql(String sql){
        setJdbcTemplate();
//        logger.info("{}环境查询开始,查询SQL : {}",BaseTest.env,sql);
        logger.info(env+"环境查询开始,查询SQL :"+sql);
        return jdbcTemplate.queryForList(sql).get(0);
    }


    /**
     * @Author : haomingjian , 2019/11/15 10:23
     * @param sql
     * @retrun : {@link List< Map< String, Object>>}
     * @Description : 查询多行数据
     */
    public List<Map<String,Object>> getObjectsBySql(String sql){
        setJdbcTemplate();
//        logger.info("{}环境查询开始,查询SQL : {}",BaseTest.env,sql);
        logger.info(env+"环境查询开始,查询SQL :"+sql);
        return jdbcTemplate.queryForList(sql);
    }


    /**
     * @Author : haomingjian , 2019/11/15 11:27
     * @param sql
     * @retrun : {@link Object}
     * @Description : 获取单一字段
     */
    public Object getOne(String sql){
        setJdbcTemplate();
//        logger.info("{}环境查询开始,查询SQL : {}",BaseTest.env,sql);
        logger.info(env+"环境查询开始,查询SQL :"+sql);
        return jdbcTemplate.queryForObject(sql,Object.class);
    }


}
