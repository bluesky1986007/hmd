package orm.jdbc.impl;

import org.testng.Reporter;

import orm.DBType;
import orm.jdbc.DataBaseDao;
import shelper.db.MySql;

public class DataBaseDaoImpl implements DataBaseDao{
	
	private MySql mysql_general;

	private String mysqlPreConnect;
	private String mysqlPreUser;
	private String mysqlPrePwd;
	

	@Override
	public MySql getInstanceOfMySql(String environment, String dataBaseName) {

		if (environment.equals(DBType.MYSQL_PRE)) {
			
			String mysqlPreUrl = mysqlPreConnect+"/"+dataBaseName;	
			Reporter.log("mysql预发环境数据库连接信息为："+mysqlPreConnect, true);
			
			mysql_general = null;
			mysql_general = new MySql(mysqlPreUrl, mysqlPreUser, mysqlPrePwd);
			
			Reporter.log("mysql预发环境数据库初始化成功！", true);

			return mysql_general;
		}

		Reporter.log("未正确获取mysql数据信息", true);
		return null;
	}


	public String getMysqlPreConnect() {
		return mysqlPreConnect;
	}


	public void setMysqlPreConnect(String mysqlPreConnect) {
		this.mysqlPreConnect = mysqlPreConnect;
	}


	public String getMysqlPreUser() {
		return mysqlPreUser;
	}


	public void setMysqlPreUser(String mysqlPreUser) {
		this.mysqlPreUser = mysqlPreUser;
	}


	public String getMysqlPrePwd() {
		return mysqlPrePwd;
	}


	public void setMysqlPrePwd(String mysqlPrePwd) {
		this.mysqlPrePwd = mysqlPrePwd;
	}


}
