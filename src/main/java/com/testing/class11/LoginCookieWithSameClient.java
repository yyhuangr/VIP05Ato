package com.testing.class11;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.testing.common.AutoLogger;

public class LoginCookieWithSameClient {

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
		CloseableHttpResponse loginResp=client.execute(loginPost1st);
		String loginResult=EntityUtils.toString(loginResp.getEntity());
		AutoLogger.log.info("返回结果是"+loginResult);
		//如果关闭了，就没法再使用client对象发包。
		client.close();
		
		AutoLogger.log.info("=====================第二次发包==========================");
		//第一次请求和第二次请求用同一个client完成请求。
		HttpPost loginPost2nd=new HttpPost("http://localhost:8080/VIP05LoginMaven/Login");
		StringEntity loginParam2nd=new StringEntity("User=Will&pwd=123456");
		loginParam2nd.setContentType("application/x-www-form-urlencoded");
		loginParam2nd.setContentEncoding("UTF-8");
		loginPost2nd.setEntity(loginParam2nd);
		CloseableHttpResponse loginResp2nd=client.execute(loginPost2nd);
		String loginResult2nd=EntityUtils.toString(loginResp2nd.getEntity());
		AutoLogger.log.info("返回结果是"+loginResult2nd);
		
		
	}

}
