package com.testing.class13;

import com.testing.common.AutoLogger;
import com.testing.inter.HttpClientKw;
import com.testing.inter.KeywordOfInter;

public class RestTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KeywordOfInter key=new KeywordOfInter();
		key.testPost("http://www.testingedu.com.cn/inter/REST/auth", "");
		key.saveParam("tokenValue", "$.token");
		key.addHeader("{\"token\":\"{tokenValue}\"}");
		String registerResult=key.testPostJson("http://www.testingedu.com.cn/inter/REST/user/register", "{\"username\":\"roy9\",\"pwd\":\"123456\",\"nickname\":\"roysensei\",\"describe\":\"teacher\"}");
		AutoLogger.log.info(registerResult);
		//url中login后带两层路径也就是2个参数，调用的是login
		key.testPost("http://www.testingedu.com.cn/inter/REST/login/roy9/123456", "");
		key.saveParam("idValue", "$.userid");
		//url中login后带一层路径也就是一个参数，调用的是getUserInfo
		key.testPost("http://www.testingedu.com.cn/inter/REST/login/{idValue}", "");
		//url中login后不带路径，调用的是logout
		key.testPost("http://www.testingedu.com.cn/inter/REST/login", "");
		
		
		
	}

}
