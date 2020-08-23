package com.testing.class11;

import com.testing.common.AutoLogger;
import com.testing.inter.HttpClientKw;

public class CookieWithKw {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpClientKw key=new HttpClientKw();
		//如果调用了clearcookie之后，client创建时不会带上cookiestore沿用之前的cookie
		//如果清理之后，需要再带上cookieStore的话，调用saveCookie()方法。
		key.clearCookie();
		String result1=key.doPost("http://localhost:8080/VIP05LoginMaven/Login", "User=Roy&pwd=123456");
		AutoLogger.log.info(result1);
		String result2=key.doPost("http://localhost:8080/VIP05LoginMaven/Login", "User=Roy&pwd=123456");
		AutoLogger.log.info(result2);
		key.saveCookie();
		String result3=key.doPost("http://localhost:8080/VIP05LoginMaven/Login", "User=Roy&pwd=123456");
		AutoLogger.log.info(result3);
		String result4=key.doPost("http://localhost:8080/VIP05LoginMaven/Login", "User=Roy&pwd=123456");
		AutoLogger.log.info(result4);
	}

}
