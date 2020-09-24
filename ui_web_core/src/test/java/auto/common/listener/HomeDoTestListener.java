package auto.common.listener;

import auto.common.control.BaseTest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import auto.service.utils.HomeDoUtils;

/**
 * Created by haomingjian ,
 * Date ： 2019/11/7 9:46
 * Desc ： 用例执行监听器，用例成功，失败，跳过时，会执行其中对应的方法
 */
public class HomeDoTestListener extends TestListenerAdapter {
    
//    public Logger logger = LoggerFactory.getLogger(HomeDoTestListener.class);
    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    HomeDoUtils homeDoUtils;

    /**
     * @Author : haomingjian , 2019/11/26 16:32
     * @param tr
     * @retrun :
     * @Description : 用例成功后，输出日志
     */
    @Override
    public void onTestSuccess(ITestResult tr) {
        logger.info(tr.getMethod().getDescription()+"   -------   " + "执行通过\n");
        super.onTestSuccess(tr);
    }

    /**
     * @Author : haomingjian , 2019/11/26 16:32
     * @param tr
     * @retrun :
     * @Description : 用例被跳过，输出日志
     */
    @Override
    public void onTestSkipped(ITestResult tr) {
        logger.info(tr.getMethod().getDescription()+"   -------   " + " 用例被跳过\n");
        super.onTestSkipped(tr);
    }

    /**
     * @Author : haomingjian , 2019/11/26 16:33
     * @param tr
     * @retrun :
     * @Description : 用例失败时，截取当前屏幕截图并保存到screen路径下
     */
    @Override
    public void onTestFailure(ITestResult tr) {
//        获取父类，拿到driver，执行截图
        BaseTest baseTest = (BaseTest)tr.getInstance();
        homeDoUtils.screenByDriver(baseTest.getDriver());
        logger.info(tr.getMethod().getDescription()+"   -------   " + "执行失败，已截图\n");
    }


}
