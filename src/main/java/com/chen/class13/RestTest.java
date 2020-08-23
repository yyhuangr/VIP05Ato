package com.chen.class13;

import com.testing.common.AutoLogger;
import com.testing.inter.KeywordOfInter;

public class RestTest {
/**
 * rest接口测试
 * @param args
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KeywordOfInter key=new KeywordOfInter();
		//auth接口的rest风格
		key.testPost("http://www.testingedu.com.cn:8081/inter/REST/auth", "");
		key.saveParam("tokenValue", "$.token");
		key.addHeader("{\"token\":\"{tokenValue}\"}");
		//register接口的rest风格
		String registerResult=key.testPostJson("http://www.testingedu.com.cn:8081/inter/REST/user/register", "{\"username\":\"chen3\",\"pwd\":\"123456\",\"nickname\":\"chen321\",\"describe\":\"test\"}");		
		AutoLogger.log.info("registerResult="+registerResult);
		//login接口的rest风格(url中login后带两层路径也就是2个参数，调用的是login)
		key.testPost("http://www.testingedu.com.cn:8081/inter/REST/login/chen3/123456", "");
		key.saveParam("idValue", "$.userid");
		//getUserInfo接口的rest风格(url中login后带一层路径也就是一个参数，调用的是getUserInfo)
		key.testPost("http://www.testingedu.com.cn:8081/inter/REST/login/{idValue}", "");
		//Logout接口的rest风格(url中login后不带路径，调用的是logout)
		key.testPost("http://www.testingedu.com.cn:8081/inter/REST/login", "");
		
		
		
		
		
		
		
		
	}

}
