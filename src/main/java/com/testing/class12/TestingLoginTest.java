package com.testing.class12;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONPath;
import com.testing.common.AutoLogger;
import com.testing.inter.HttpClientKw;

public class TestingLoginTest {
	//用于存储参数的map
	public static Map<String, String> paramMap;
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpClientKw key=new HttpClientKw();
		paramMap=new HashMap<String,String>();
		//第一步通过调用auth接口获取token值
		String authResult=key.doPost("http://www.testingedu.com.cn/inter/HTTP/auth", "");
		AutoLogger.log.info("auth接口的返回信息"+authResult);
		paramMap.put("token", JSONPath.read(authResult, "$.token").toString());
		AutoLogger.log.info("token的值是"+paramMap.get("token"));
		//创建一个map用于添加头域
		Map<String, String> headerMap=new HashMap<String,String>();
		//添加token和它的值作为键值对存储到map中
		headerMap.put("token", paramMap.get("token"));
		//添加headermap
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
		//将获取到的id的值，存到参数map中。
		paramMap.put("idValue", JSONPath.read(loginResult, "$.userid").toString());
		String userParam="id={idValue}";
		userParam=toParam(userParam);
		System.out.println("替换后的user参数是"+userParam);
		//调用getUserInfo接口，需要的参数id，从login接口中返回，参数传递。
		String userInfoResult=key.doPost("http://www.testingedu.com.cn/inter/HTTP/getUserInfo",userParam );
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
	//替换参数的方法
	public static String toParam(String origin) {
		for(String key:paramMap.keySet()) {
			//替换键所对应的值
			origin=origin.replaceAll("\\{"+key+"\\}", paramMap.get(key));
		}
		return origin;
	}
	
}
