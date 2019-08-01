package common.frame.test;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import common.frame.data.ExcelProviderByTest;
import service.HttpClientService;

@ContextConfiguration(locations={"classpath:/qacontext/applicationContext.xml","classpath:/qacontext/envPropertyLoader.xml"})
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
