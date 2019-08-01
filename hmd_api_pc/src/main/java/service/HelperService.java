package service;

public interface HelperService {
	

	/**
	 * 发送测试报告邮件
	 * @param messageSubject
	 * @param attachPath
	 */
	public void sendMail(String messageSubject, String attachPath);
	

}
