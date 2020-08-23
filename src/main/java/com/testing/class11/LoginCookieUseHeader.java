package com.testing.class11;

import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.testing.common.AutoLogger;

public class LoginCookieUseHeader {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		CloseableHttpClient client=HttpClients.createDefault();
		//创建http方法和填写url
		HttpPost loginPost1st=new HttpPost("http://localhost:8080/VIP05LoginMaven/Login");
		//设置请求体,直接用原始的字符串形式，再指定content-type格式就可以
		StringEntity loginParam=new StringEntity("User=Roy&pwd=123456");
		//在请求体编写时就制定好请求的content-type和编码。
		loginParam.setContentType("application/x-www-form-urlencoded");
		loginParam.setContentEncoding("UTF-8");
		loginPost1st.setEntity(loginParam);
		//获取返回体
		CloseableHttpResponse loginResp=client.execute(loginPost1st);
		//获取set-cookie头域的值，通过getFirstHeader拿到第一个set-cookie的值
		//如果有多个，用getHeaders获取数组。
		Header cookieHeader=loginResp.getFirstHeader("Set-Cookie");
		AutoLogger.log.info(cookieHeader);
		//获取头域的值，也就是cookie的值
		String cookieValue= cookieHeader.getValue();
		AutoLogger.log.info(cookieValue);
		String loginResult=EntityUtils.toString(loginResp.getEntity());
		AutoLogger.log.info("返回结果是"+loginResult);
		
		
		AutoLogger.log.info("=====================第二次发包==========================");
		CloseableHttpClient clientNoCookie=HttpClients.createDefault();
		HttpPost loginPost2nd=new HttpPost("http://localhost:8080/VIP05LoginMaven/Login");
		//设置请求体,直接用原始的字符串形式，再指定content-type格式就可以
		StringEntity loginParam2=new StringEntity("User=Roy&pwd=123456");
		//在请求体编写时就制定好请求的content-type和编码。
		loginParam2.setContentType("application/x-www-form-urlencoded");
		loginParam2.setContentEncoding("UTF-8");
		loginPost2nd.setEntity(loginParam2);
		//在请求中添加cookie头域，值就是上一步通过set-cookie获取到的cookie值。
//		loginPost2nd.setHeader("Cookie",cookieValue);
		//获取返回体
		CloseableHttpResponse loginResp2=clientNoCookie.execute(loginPost2nd);
		String loginReuslt2= EntityUtils.toString(loginResp2.getEntity());
		AutoLogger.log.info("第二次请求的结果："+loginReuslt2);
	}

}
