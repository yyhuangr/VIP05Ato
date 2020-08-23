package com.testing.class12;

import com.testing.app.KeywordOfApp;
import com.testing.inter.KeywordOfInter;

public class LoginTestWithKw {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KeywordOfInter inter=new KeywordOfInter();
		//auth接口
		inter.testPost("http://www.testingedu.com.cn/inter/HTTP/auth", "");
		inter.saveParam("tokenValue", "$.token");
		//添加头域信息到httpclientkw的map中，接下来所有的请求都能够使用这个头域
//		inter.addHeader("{\"token\":\"{tokenValue}\"}");
		//login请求
		inter.testPost("http://www.testingedu.com.cn/inter/HTTP/login", "username=roy3&password=123456");
		inter.assertContains("登录成功","$.msg");
		inter.saveParam("idValue", "$.userid");
		//userinfo请求
		inter.testPost("http://www.testingedu.com.cn/inter/HTTP/getUserInfo", "id={idValue}");
		inter.assertSame("查询成功", "$.msg");
		inter.testPost("http://www.testingedu.com.cn/inter/HTTP/logout", "");
	}

}
