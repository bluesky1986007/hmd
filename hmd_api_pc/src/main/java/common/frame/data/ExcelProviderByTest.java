package common.frame.data;

import java.util.Iterator;

public class ExcelProviderByTest{
	
	private String envTestID;

	public Iterator<Object[]> excelProvider(Object aa, String sheetName) {
	   return new ExcelProvider(aa, sheetName, this.envTestID);
    }

	public String getEnvTestID() {
		return envTestID;
	}

	public void setEnvTestID(String envTestID) {
		this.envTestID = envTestID;
	}

}
