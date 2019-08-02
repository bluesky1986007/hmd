package common.frame.test;

import common.frame.data.ExcelProviderByTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import service.HttpClientService;

import java.util.Iterator;

@ContextConfiguration(locations={"classpath:/context/applicationContext.xml"})
public abstract class BaseTest extends AbstractTestNGSpringContextTests{
	
	@Autowired
	public ExcelProviderByTest excelProviderByTest;
	@Autowired
	public HttpClientService httpClientService;
	
	public Object getBean(String name)
  {
	   return this.applicationContext.getBean(name);
  }
	
	
   public Iterator<Object[]> ExcelProviderByEnv(Object aa, String sheetName) {
	   
	     return this.excelProviderByTest.excelProvider(aa, sheetName);
   }

}
