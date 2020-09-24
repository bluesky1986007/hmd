package auto.service.initial;

import auto.common.control.BaseTest;
import auto.common.data.ExcelProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testng.Assert;
import org.testng.annotations.*;
import auto.service.utils.CommonUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * updated by luyi ,
 * Date ： 2020/3/30 13:54
 */
@Service
public class SuiteTestCase extends BaseTest {

    @Autowired
    CommonUtils commonUtils;
    
//    private static Logger logger = LoggerFactory.getLogger(SuiteTestCase.class);
    ArrayList nodePortNum = new ArrayList();


    @Test(dataProvider = ""+"data",description = "启动node节点服务")
    public void startNodeService(Map<String, String> data) throws Exception {
//        logInfo.info("UI 自动化测试开始...,开始时间：{}",new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
        logger.info("UI 自动化测试开始...,开始时间：{}"+new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
        ResourceBundle resourceBundle = ResourceBundle.getBundle("global");
//        String webdriverWorkMode = resourceBundle.getString("webdriver.work.mode");
        String chromeDriverPath = resourceBundle.getString("webdriver.chrome.driver.path");
        String firefoxDriverPath = resourceBundle.getString("webdriver.firefox.driver.path");
        String ieDriverPath = resourceBundle.getString("webdriver.ie.driver.path");
        String gridServerPath = resourceBundle.getString("grid.server.path");
//        String hubRegisterUrl = resourceBundle.getString("grid.hub.register");
//        String hubUrl = resourceBundle.getString("grid.hub.url");
        String hubPort = resourceBundle.getString("grid.hub.port");

        String nodePort = data.get("nodePort");
        String nodeBrowser = data.get("nodeBrowser");
        String maxSession = data.get("maxSession");
        logger.info("nodePort="+nodePort);
        logger.info("nodeBrowser="+nodeBrowser);
        logger.info("maxSession="+maxSession);
//      先关闭所有远程节点
        commonUtils.killGridPort(hubPort);

//            String startNodeCommand = "java -Dwebdriver.chrome.driver=\"" + chromeDriverPath + "\" " +
//                    "-Dwebdriver.gecko.driver=\"" +  firefoxDriverPath+ "\" " +
//                    "-Dwebdriver.ie.driver=\"" + ieDriverPath + "\" " +
//                    "-jar " + gridJarPath + " -role node -port " + nodePort1 + " -hub "+hubUrl;
//        logger.info("开始启动hub服务");
//        String startHubCommand = "java -jar "+gridServerPath+" -role hub -maxSession 10 -port "+hubPort;
//        Runtime.getRuntime().exec(startHubCommand);
//        logger.info("启动hub中... Command:" + startHubCommand);
//        Thread.sleep(5000);
//        logger.info("共启用{}个服务",getPortNum(true,hubPort));
//        Assert.assertEquals(getPortNum(false,hubPort),1);

        logger.info("开始启动node服务");
        String startNodeCommand=null;
        String hubRegisterUrl = "http://localhost:"+hubPort+"/grid/register";
        if ("chrome".equals(nodeBrowser)){

            startNodeCommand = "java -Dwebdriver.chrome.driver=" + chromeDriverPath + " -jar " + gridServerPath + " -role webdriver -hub " + hubRegisterUrl + " -port " + nodePort + " -maxSession " + maxSession + " -browser browserName=\"chrome\",maxInstances="+maxSession;

        }

        Runtime.getRuntime().exec(startNodeCommand);
        logger.info("启动node节点中... Command:" + startNodeCommand);
//       断言服务是否启动,查看4444端口的服务个数
        Thread.sleep(3000);
//        logInfo.info("共启用{}个服务",getPortNum(true,nodePort));
        logger.info("共启用服务数"+getPortNum(true,nodePort));
        Assert.assertEquals(getPortNum(false,nodePort),1);
        nodePortNum.add(nodePort);
        logger.info("端口" + nodePort + "的node节点服务启动完成！");

    }

    @AfterSuite
    public void endService() throws Exception{
//        ResourceBundle resourceBundle = ResourceBundle.getBundle("global");
//        String webdriverWorkMode = resourceBundle.getString("webdriver.work.mode");
        int allNodePortNum=this.nodePortNum.size();
        logger.info("本次执行启动的node节点数量="+allNodePortNum);

        for (int i = 0; i < allNodePortNum; i++) {

            String currentPort = nodePortNum.get(i).toString();
            commonUtils.killGridPort(currentPort);
            Assert.assertEquals(0,getPortNum(false,currentPort));
            logger.info("端口" + currentPort + "的node节点已经被关闭");

        }

        logger.info("本次启动的所有node节点都已关闭");
//        logInfo.info("UI 自动化测试结束...,结束时间：{}",new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
    }



    @DataProvider(name = "data")
    public Iterator<Object[]> getData() throws IOException {
        env = "pro";
        return new ExcelProvider(env,this, 0);}

    /**
     * @Author : haomingjian , 2019/12/13 14:16
     * @param output
     * @retrun : {@link int}
     * @Description : 获取当前端口号4444 有关的服务个数，参数output：true则输出cmd返回的结果和个数,false则的不输出，只返回个数
     */
    private int getPortNum(boolean output,String port) throws Exception{
        Process proc = Runtime.getRuntime().exec("cmd /c netstat -ano | findstr \""+port+"\"");
        BufferedReader bufferedReader = null;
        bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream(), "gbk"));
        String line = null;
        Set<String> pidList = new HashSet<>();
        while ((line = bufferedReader.readLine()) != null) {
            if (output){
                logger.info(line);
            }
            pidList.add(line.substring(line.lastIndexOf(" "), line.length()));
        }
        return pidList.size();
    }


}