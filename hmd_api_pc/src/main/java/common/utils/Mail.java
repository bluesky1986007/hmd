package common.utils;

import common.frame.test.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.*;
import service.HelperService;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Mail extends BaseTest {

	private String subject;
	private String attachPath;

	@Autowired
	private HelperService helperService;

	@BeforeClass
	public void beforeClass() {

	}

	@Parameters({ "attachName" })
	@Test(enabled = true)
	public void sendAutomationReports(
			@Optional("AppAutoTestForAndroid") String attachName) throws Exception {
		
		Thread.sleep(3000);

		helperService.sendMail(this.getSubject(attachName), this.getAttachPath(attachName));

	}

	/**
	 * 邮件主题
	 * @param attachString
	 * @return
	 */
	private String getSubject(String attachString) {

		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy kk:mm");

		this.subject = "[AutomationTest]Test Results of " + attachString + " "
				+ sdf.format(c.getTime());

		return subject;
	}

	/**
	 * 附件路径
	 * @param attachNameString
	 * @return
	 */
	private String getAttachPath(String attachNameString) {

		try {
			this.attachPath = String.valueOf(new File("./").getCanonicalPath())
					+ "\\test-output\\Suite_TestResults\\"+attachNameString+".html";//emailable-report.html
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("getAttachPath is wrong!!" + e);
			e.printStackTrace();
		}
		System.out.println("path of reports: " + attachPath);

		return attachPath;
	}

	@AfterClass
	public void afterClass() {
	}

}
