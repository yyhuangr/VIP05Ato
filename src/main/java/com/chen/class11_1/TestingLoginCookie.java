package com.chen.class11_1;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.testing.common.AutoLogger;

public class TestingLoginCookie {

	public static void main(String[] args) throws Exception {
		CloseableHttpClient client=HttpClients.createDefault();
		//创建http方法和填写url
		HttpPost loginFirstPost=new HttpPost("http://localhost:8080/VIP05LoginMaven/Login");
		//设置请求体,直接用原始的字符串形式，再指定content-type格式就可以
		StringEntity loginParam=new StringEntity("User=Roy&pwd=123456");
		//方式一：在请求体编写时就制定好请求的content-type和编码。
		loginParam.setContentType("application/x-www-form-urlencoded");
		loginParam.setContentEncoding("UTF-8");
		loginFirstPost.setEntity(loginParam);
		//方式二：设置请求头content-type
//		loginPost.setHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
		CloseableHttpResponse loginResp=client.execute(loginFirstPost);
		String loginResult=EntityUtils.toString(loginResp.getEntity());
		AutoLogger.log.info("返回结果是"+loginResult);
		
		AutoLogger.log.info("+++++++++++++++++第二次发包+++++++两次发包使用不同的client+++++++++++++");
		CloseableHttpClient client2=HttpClients.createDefault();
		HttpPost loginPost2=new HttpPost("http://localhost:8080/VIP05LoginMaven/Login");
		//设置请求体,直接用原始的字符串形式，再指定content-type格式就可以
		StringEntity loginParam2=new StringEntity("User=Roy&pwd=123456");
		//方式一：在请求体编写时就制定好请求的content-type和编码。
		loginParam2.setContentType("application/x-www-form-urlencoded");
		loginParam2.setContentEncoding("UTF-8");
		loginPost2.setEntity(loginParam2);
		//方式二：设置请求头content-type
//		loginPost.setHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
		CloseableHttpResponse loginResp2=client2.execute(loginPost2);
		String loginResult2=EntityUtils.toString(loginResp2.getEntity());
		AutoLogger.log.info("返回结果是"+loginResult2);
	}

}
