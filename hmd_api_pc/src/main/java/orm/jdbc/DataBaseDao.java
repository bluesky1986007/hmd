package orm.jdbc;

import shelper.db.MySql;

public interface DataBaseDao {
	
	/**
	 * mysql数据库初始化
	 * @param environment 环境
	 * @param dataBaseName 库名
	 * @return
	 */
	public MySql getInstanceOfMySql(String environment, String dataBaseName);


}
