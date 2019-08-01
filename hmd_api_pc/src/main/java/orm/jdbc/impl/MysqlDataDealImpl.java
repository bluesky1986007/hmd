package orm.jdbc.impl;

import org.testng.Reporter;

import orm.jdbc.DataBaseDao;
import orm.jdbc.MysqlDataDeal;
import shelper.db.MySql;
import orm.DBType;
import orm.MysqlDBName;

public class MysqlDataDealImpl implements MysqlDataDeal {

	private DataBaseDao dataBaseDao;
	private MySql msql;

	@Override
	public String getSignUpComfirmCode(String userId) {

		Reporter.log("从数据库中获取活动报名验证码...", true);

		msql = dataBaseDao.getInstanceOfMySql(DBType.MYSQL_PRE, MysqlDBName.XLH_SMS);

		String sql = "select substring((select s.content from sms_record s where phone="+userId+" ORDER BY s.id DESC LIMIT 1),11,6)";
		String queryInfo = msql.query(sql);
		
		Reporter.log("数据查询成功！", true);

		msql.closeDBcon();
		// System.out.println(queryInfo);
		return queryInfo;
	}
		
	
	@Override
	public void updateEnterpriseStatus(String enterpriseName, int statusValue) {
		// TODO Auto-generated method stub
		Reporter.log("更新企业状态...", true);

		msql = dataBaseDao.getInstanceOfMySql(DBType.MYSQL_PRE, MysqlDBName.XLH_FHQ);

		String sql = "UPDATE incubator_enterprises SET enterprise_status="+statusValue+" WHERE enterprise_name="+"'"+enterpriseName+"'";
		msql.Update(sql);
		
		Reporter.log("数据更新成功！", true);

		msql.closeDBcon();

	}
	
	
	@Override
	public void deleteActivitySignUp(String activityName, String mobile) {
		// TODO Auto-generated method stub
		Reporter.log("删除报名信息...", true);

		msql = dataBaseDao.getInstanceOfMySql(DBType.MYSQL_PRE, MysqlDBName.XLH_YRT);

		String sql = "delete from yrt_hd_bm where hd_title='" + activityName + "'" + " and mobi=" + mobile;
		msql.Delete(sql);
		
		Reporter.log("数据删除成功！", true);

		msql.closeDBcon();
		
	}
		
		
		@Override
		public String checkEnterIncubator(String enterpriseName) {
			// TODO Auto-generated method stub
			Reporter.log("校验企业是否入驻...", true);

			msql = dataBaseDao.getInstanceOfMySql(DBType.MYSQL_PRE, MysqlDBName.XLH_FHQ);

			String sql = "SELECT COUNT(*) FROM incubator_enterprises WHERE enterprise_name='"+enterpriseName+"' and enterprise_status=0";
			String num = msql.query(sql);
			
			Reporter.log("数据查询成功！", true);

			msql.closeDBcon();
			// System.out.println(num);
			return num;
			
		}


	public DataBaseDao getDataBaseDao() {
		return dataBaseDao;
	}

	public void setDataBaseDao(DataBaseDao dataBaseDao) {
		this.dataBaseDao = dataBaseDao;
	}


}
