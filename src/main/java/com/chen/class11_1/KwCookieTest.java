package com.chen.class11_1;

import org.aspectj.weaver.tools.cache.AsynchronousFileCacheBacking.ClearCommand;

import com.testing.common.AutoLogger;
import com.testing.inter.HttpClientKw;

public class KwCookieTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpClientKw kw=new HttpClientKw();
		//如果调用了clearCookie，client创建时不会带上cookieStore沿用之前的cookie.
		//如果清理了cookie，需要再带上cookieStore，就调用saveCookie()方法。
		kw.clearCookie();
		String result1=kw.doPost("http://localhost:8080/VIP05LoginMaven/Login", "User=Roy&pwd=123456");
		AutoLogger.log.info("result1="+result1);
		String result2=kw.doPost("http://localhost:8080/VIP05LoginMaven/Login", "User=Roy&pwd=123456");
		AutoLogger.log.info("result2="+result2);
		kw.saveCookie();
		String result3=kw.doPost("http://localhost:8080/VIP05LoginMaven/Login", "User=Roy&pwd=123456");
		AutoLogger.log.info("result3="+result3);
		String result4=kw.doPost("http://localhost:8080/VIP05LoginMaven/Login", "User=Roy&pwd=123456");
		AutoLogger.log.info("result4="+result4);
		
	}

}
