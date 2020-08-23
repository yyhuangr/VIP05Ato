package com.chen.class11_1;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.testing.common.AutoLogger;

public class CookieStoreTest {
/*
 * 不同的client,第一次请求的cookie通过cookiestore将cookie值传递给第二次请求使用，即便是关闭了第一次请求的client，cookie值
 * 仍然可以传给第二次请求使用。
 * 所以第二次请求登录的时候，提示您已经登录过了。
 */
	public static void main(String[] args) throws  Exception {
		// TODO Auto-generated method stub
		CookieStore purse=new BasicCookieStore();
		CloseableHttpClient chen=HttpClients.custom().setDefaultCookieStore(purse).build();
		//创建http方法和填写url
		HttpPost ChenPost=new HttpPost("http://localhost:8080/VIP05LoginMaven/Login");
		//设置请求体,直接用原始的字符串形式，再指定content-type格式就可以
		StringEntity loginChParam=new StringEntity("User=Roy&pwd=123456");
		//方式一：在请求体编写时就制定好请求的content-type和编码。
		loginChParam.setContentType("application/x-www-form-urlencoded");
		loginChParam.setContentEncoding("UTF-8");
		ChenPost.setEntity(loginChParam);
		//方式二：设置请求头content-type
//				loginPost.setHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
		CloseableHttpResponse loginResp=chen.execute(ChenPost);
		String loginResult=EntityUtils.toString(loginResp.getEntity());
		AutoLogger.log.info("chen自己登陆的反回："+loginResult);
		chen.close();
		
		
		AutoLogger.log.info("++++++++第二次请求+++++带着第一次的cookie值");
		//带上第一次请求的cookie值，提示你已经登录过了
//		CloseableHttpClient client=HttpClients.custom().setDefaultCookieStore(purse).build();
		//不带着第一次请求的cookie值 ，可以登录成功
		CloseableHttpClient client=HttpClients.custom().build();
		HttpPost LoginHyy=new HttpPost("http://localhost:8080/VIP05LoginMaven/Login");
		StringEntity HyyParam=new StringEntity("User=Roy&pwd=123456");
		
		HyyParam.setContentType("application/x-www-form-urlencoded");
		HyyParam.setContentEncoding("UTF-8");
		LoginHyy.setEntity(HyyParam);
		CloseableHttpResponse HyyRespon=client.execute(LoginHyy);
		String HyyResult=EntityUtils.toString(HyyRespon.getEntity());
		
		AutoLogger.log.info("Hyy登陆的反回："+HyyResult);
		
	}

}
