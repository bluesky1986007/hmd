package orm.jdbc;

public interface MysqlDataDeal {

	/**
	 * 从数据库中获取活动报名验证码
	 * @param userId 用户名
	 * @return
	 */
	public String getSignUpComfirmCode(String userId);
	
	/**
	 * 更新企业入驻状态
	 * @param enterpriseName 入驻企业名称
	 * @param statusValue  更新的状态
	 */
	public void updateEnterpriseStatus(String enterpriseName, int statusValue);
	
	/**
	 * 删除报名信息
	 * @param activityName 活动名称
	 * @param mobile  报名手机号
	 */
	public void deleteActivitySignUp(String activityName, String mobile);
	
	/**
	 * 校验企业是否入驻
	 * @param enterpriseName 入驻企业名称
	 * @return
	 */
	public String checkEnterIncubator(String enterpriseName);
	
}