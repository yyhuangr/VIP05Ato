package com.testing.class11;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONPath;
import com.testing.common.AutoLogger;
import com.testing.inter.HttpClientKw;

public class TestingLoginTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpClientKw key=new HttpClientKw();
		//第一步通过调用auth接口获取token值
		String authResult=key.doPost("http://www.testingedu.com.cn/inter/HTTP/auth", "");
		AutoLogger.log.info("auth接口的返回信息"+authResult);
		String tokenValue=JSONPath.read(authResult, "$.token").toString();
		AutoLogger.log.info("token的值是"+tokenValue);
		//创建一个map用于添加头域
		Map<String,String> headerMap=new HashMap<String,String>();
		headerMap.put("token", tokenValue);
		//添加headerMap
		key.addHeader(headerMap);
		
		//调用register注册接口
		String registerResult=key.doPost("http://www.testingedu.com.cn/inter/HTTP/register", "username=roy3&pwd=123456&nickname=roy12&describe=roysense");
		AutoLogger.log.info("register接口的返回信息"+registerResult);
		if(new String("用户已被注册").equals(JSONPath.read(registerResult, "$.msg").toString()))
		{AutoLogger.log.info("测试成功");}
		else {
			AutoLogger.log.info("测试失败");
		}
		//调用login接口
		String loginResult=key.doPost("http://www.testingedu.com.cn/inter/HTTP/login", "username=roy3&password=123456");
		AutoLogger.log.info("login接口的返回信息"+loginResult);
		String idValue=JSONPath.read(loginResult, "$.userid").toString();
		//调用getUserInfo接口，需要的参数id，从login接口中返回，参数传递。
		String userInfoResult=key.doPost("http://www.testingedu.com.cn/inter/HTTP/getUserInfo", "id="+idValue);
		AutoLogger.log.info(userInfoResult);
		if(new String("查询成功").equals(JSONPath.read(userInfoResult, "$.msg").toString()))
		{AutoLogger.log.info("测试成功");}
		else {
			AutoLogger.log.info("测试失败");
		}
		//调用注销logout接口
		String logOutResult=key.doPost("http://www.testingedu.com.cn/inter/HTTP/logout", "");
		AutoLogger.log.info(logOutResult);
	}

}
