package com.chen.class12;

import com.testing.inter.KeywordOfInter;

public class LoginTestWithKw {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KeywordOfInter inter=new KeywordOfInter();
		//auth接口
		inter.testPost("http://www.testingedu.com.cn:8081/inter/HTTP/auth", "");
		inter.saveParam("tokenValue", "$.token");
		System.out.println("auth接口后paramMap中的内容："+inter.paramMap);
		//添加头域信息到httpclientkw的map中，接下来所有的请求都能够使用这个头域
		inter.addHeader("{\"token\":\"{tokenValue}\"}");
		
		//login接口
		String respon=inter.testPost("http://www.testingedu.com.cn:8081/inter/HTTP/login", "username=chen&password=123456");
		//断言
		System.out.println("respon："+respon);
		inter.assertSame("恭喜您，登录成功", "$.msg");
		inter.saveParam("idValue", "$.userid");//将登陆的id获取到，后面会用到
		System.out.println("auth,login接口后paramMap:"+inter.paramMap);
		
		
		//getUserInfo接口
		String reult=inter.testPost("http://www.testingedu.com.cn:8081/inter/HTTP/getUserInfo", "id={idValue}");
		//断言
		System.out.println("reult:"+reult);
		inter.assertContains("查询成功", "$.msg");
		
		
		//logout接口
		String reslut=inter.testPost("http://www.testingedu.com.cn:8081/inter/HTTP//logout", "");
		System.out.println("reslut:"+reslut);
		//断言
		inter.assertResponseContains("退出");
	}

}
