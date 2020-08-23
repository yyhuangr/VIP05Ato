package com.chen.class11_1;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.testing.common.AutoLogger;
/*
 * 获取头域， 设置头域   让第一次登陆的cookie可以  沿用  给第二次登陆，可以让第二次登陆的时候提示您已经登陆过了。
 */
public class TLoginCookieUseHeader {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
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
		//获取头域     ：返回头域里面 的Set-cookie
		Header cookieHeader=loginResp.getFirstHeader("Set-Cookie");
		AutoLogger.log.info("返回头域的Set-Cookie---- "+cookieHeader);
		String cookieValue=cookieHeader.getValue();
		AutoLogger.log.info("返回cookieValue: "+cookieValue);
		
		String loginResult=EntityUtils.toString(loginResp.getEntity());
		AutoLogger.log.info("第一次请求的结果是"+loginResult);
		//如果关闭了第一个Client，那么第二次请求因无法获取第一次登陆的cookie，而也会登陆成功。
//		client.close();
		
		AutoLogger.log.info("+++++第二次请求+++++++++++++++++++++++++++");
		
		CloseableHttpClient clientNoCookie=HttpClients.createDefault();
		HttpPost loginFirstPost2=new HttpPost("http://localhost:8080/VIP05LoginMaven/Login");
		//设置请求体,直接用原始的字符串形式，再指定content-type格式就可以
		StringEntity loginParam2=new StringEntity("User=Roy&pwd=123456");
		//方式一：在请求体编写时就制定好请求的content-type和编码。
		loginParam2.setContentType("application/x-www-form-urlencoded");
		loginParam2.setContentEncoding("UTF-8");
		loginFirstPost2.setEntity(loginParam2);
		//设置头域：   设置请求头中的cookie,值是上面的第一次请求中通过set-cookie取到的 cookie值。
		loginFirstPost2.setHeader("Cookie","cookieValue");
		CloseableHttpResponse loginResp2=clientNoCookie.execute(loginFirstPost2);
		String loginResult2=EntityUtils.toString(loginResp2.getEntity());
		AutoLogger.log.info("第二次请求的结果是"+loginResult2);
	}

}
