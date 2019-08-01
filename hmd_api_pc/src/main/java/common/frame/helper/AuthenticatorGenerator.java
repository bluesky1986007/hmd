package common.frame.helper;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


/**
 * 生成发送邮件需要的Authenticator
 * 
 * @author ALLen
 *
 */
public final class AuthenticatorGenerator {

	/**
	 * 根据用户名和密码生成Authenticator
	 * @param userName
	 * @param password
	 * @return
	 */
	public static Authenticator getAuthenticator(final String userName, final String password) {
		
		return new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication(userName, password);
			}			
		};			
	}

}
