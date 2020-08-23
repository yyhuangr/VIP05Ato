package com.chen.class11_1;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONPath;
import com.testing.common.AutoLogger;
import com.testing.inter.HttpClientKw;
/*
 * 在执行之前需要把注册接口register接口的参数username修改一下，否则会重复注册不成功。
 */
public class TestingLoginToken {
	//用于存储参数的map
	public static Map<String, String> paramMap;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpClientKw kw=new HttpClientKw();
		paramMap=new HashMap<String, String>();
		
		//第一步 通过调用auth接口获取token值
		String authResult=kw.doPost("http://www.testingedu.com.cn:8081/inter/HTTP/auth", "");
		AutoLogger.log.info("auth接口的返回信息是auth="+authResult);
//		String TokenValue=JSONPath.read(authResult, "$.token").toString();
		//将获取到的token值放在map中
		paramMap.put("token", JSONPath.read(authResult, "$.token").toString());
		AutoLogger.log.info("token的值是="+paramMap.get("token"));
		//创建一个map用于添加头域
		Map<String,String> headerMap=new HashMap<String,String>();
		headerMap.put("token", paramMap.get("token"));
		//添加headerMap
		kw.addHeader(headerMap);
		
		//第二步，注册，调注册接口
		String RegisterResult=kw.doPost("http://www.testingedu.com.cn:8081/inter/HTTP/register", "username=chen2&pwd=123456&nickname=chenhi122&describe=chenhi1222");
		AutoLogger.log.info("register接口的返回信息是="+RegisterResult);
		//第三步  登录 调用登录接口
		String LoginResult=kw.doPost("http://www.testingedu.com.cn:8081/inter/HTTP/login", "username=chen2&password=123456");
		AutoLogger.log.info("login接口的返回信息是="+LoginResult);
		//解析登录接口反回的json
//		String IdValue=JSONPath.read(LoginResult, "$.userid").toString();
		//将获取到的id值放进mapz
		paramMap.put("IdValue", JSONPath.read(LoginResult, "$.userid").toString());
		String userParam="id= {IdValue}";
		userParam=toParam(userParam);
		System.out.println("替换后的userparam参数："+userParam);
		
//		//第四部，调用userinfor 接口，需要的参数id值是从登录接口Login返回的。
		String UserInfResult=kw.doPost("http://www.testingedu.com.cn:8081/inter/HTTP/getUserInfo", userParam);
		AutoLogger.log.info("getUserInfo接口的返回信息是="+UserInfResult);
		String msg=JSONPath.read(UserInfResult, "$.msg").toString();
		
		//断言 （在login,auth,logout,register每一个接口都可以添加断言）
		if(new String("查询成功").contentEquals(msg)) {
			AutoLogger.log.info("测试成功！");
		}else {
			AutoLogger.log.info("测试失败！");
		}
//		//第五步 退出登录  调用LogOut接口
		String LoginOutResult=kw.doPost("http://www.testingedu.com.cn:8081/inter/HTTP/logout", "");
		AutoLogger.log.info("logout接口的返回信息是="+LoginOutResult);
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
