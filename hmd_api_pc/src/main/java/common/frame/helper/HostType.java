package common.frame.helper;

import java.util.Properties;

/**
 * 每种类型服务器properties初始化
 * @author ALLen
 *
 */
public enum HostType {

	/**
	 * 网易邮箱服务器
	 * @author ALLen
	 *
	 */
	NETEASE {
		
		@Override
		public Properties getProperties(){
			
			Properties properties = new Properties();
			properties.put("mail.smtp.host", "smtp.qiye.163.com");
			properties.put("mail.smtp.auth", "true");//这个必须设置后才能通过验证
			
			return properties;
			
		}
	},
	TENCENT {
			
			@Override
			public Properties getProperties(){
				
				Properties properties = new Properties();
				properties.put("mail.pop3.host", "pop.exmail.qq.com");
				properties.put("mail.imap.host", "imap.exmail.qq.com");
				properties.put("mail.store.protocol", "pop3");// 默认使用pop3收取邮件
				
				return properties;			
		}
	};
	
	public abstract Properties getProperties();

}
