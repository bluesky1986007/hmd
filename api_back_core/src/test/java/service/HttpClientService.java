package service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.net.MalformedURLException;

public interface HttpClientService {

	/**
	 * doGet
	 * @param url
	 * @return
	 * @throws MalformedURLException
	 */
	public JSONArray doGet(String url) throws MalformedURLException, Exception;


	/**
	 * doPost
	 * @param data
	 * @param dataType
	 * @param url
	 * @return
	 * @throws MalformedURLException
	 */
	public JSONObject doPost(String url, String data, String dataType) throws MalformedURLException, Exception;


	/**
	 * doPostWithCookie
	 * @param data
	 * @param dataType
	 * @param url
	 * @return
	 * @throws MalformedURLException
	 */
	public JSONObject doPostWithCookie(String url, String data, String dataType, String cookie) throws MalformedURLException, Exception;

}
