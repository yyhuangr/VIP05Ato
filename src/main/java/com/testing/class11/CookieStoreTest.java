package com.testing.class11;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.testing.common.AutoLogger;

public class CookieStoreTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//httpclient用cookieStore来保存cookie,用basicCookieStore来完成实例化。
		CookieStore purse=new BasicCookieStore();
		//自定义使用purse这个cookiestore来完成httpclient的创建
		CloseableHttpClient Roy=HttpClients.custom().setDefaultCookieStore(purse).build();
		HttpPost loginRoy=new HttpPost("http://localhost:8080/VIP05LoginMaven/Login");
		//设置请求体,直接用原始的字符串形式，再指定content-type格式就可以
		StringEntity loginRoyParam=new StringEntity("User=Roy&pwd=123456");
		//在请求体编写时就制定好请求的content-type和编码。
		loginRoyParam.setContentType("application/x-www-form-urlencoded");
		loginRoyParam.setContentEncoding("UTF-8");
		loginRoy.setEntity(loginRoyParam);
		CloseableHttpResponse loginResp=Roy.execute(loginRoy);
		String loginResult=EntityUtils.toString(loginResp.getEntity());
		AutoLogger.log.info("Roy自己登陆的返回"+loginResult);
		
		AutoLogger.log.info("第二次请求+++++++++++++++++++++++++roy的老婆带上roy的cookie池去进行请求");
		CloseableHttpClient royWife=HttpClients.custom().setDefaultCookieStore(purse).build();
		HttpPost loginWife=new HttpPost("http://localhost:8080/VIP05LoginMaven/Login");
		StringEntity loginWifeParam=new StringEntity("User=Roy&pwd=123456");
		loginWifeParam.setContentType("application/x-www-form-urlencoded");
		loginWifeParam.setContentEncoding("UTF-8");
		loginWife.setEntity(loginWifeParam);
		//通过新的client执行请求
		CloseableHttpResponse wifeResp= royWife.execute(loginWife);
		String wifeResult=EntityUtils.toString(wifeResp.getEntity());
		AutoLogger.log.info("Roy老婆登陆的返回"+wifeResult);
		
		
		
		
		
		
		
	}

}
