package com.testing.class9;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.testing.common.AutoLogger;

public class VIP05LoginTest {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		CloseableHttpClient client=HttpClients.createDefault();
		//创建http方法和填写url
		HttpPost loginPost=new HttpPost("http://localhost:8080/VIP05LoginMaven/Login");
		//设置请求体,直接用原始的字符串形式，再指定content-type格式就可以
		StringEntity loginParam=new StringEntity("User=Roy&pwd=123456");
		//在请求体编写时就制定好请求的content-type和编码。
		loginParam.setContentType("application/x-www-form-urlencoded");
		loginParam.setContentEncoding("UTF-8");
		loginPost.setEntity(loginParam);
		//设置请求头content-type
//		loginPost.setHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
		CloseableHttpResponse loginResp=client.execute(loginPost);
		String loginResult=EntityUtils.toString(loginResp.getEntity());
		AutoLogger.log.info("返回结果是"+loginResult);
		
	}

}
