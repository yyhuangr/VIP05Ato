package com.testing.classt10;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.testing.common.AutoLogger;
import com.testing.inter.HttpClientKw;

public class ShopLogin {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		CloseableHttpClient client=HttpClients.createDefault();
		HttpPost loginPost=new HttpPost("http://www.testingedu.com.cn:8000/index.php?m=Home&c=User&a=do_login&t=0.7799940710114899");
		StringEntity loginParam=new StringEntity("username=13800138006&password=123456&verify_code=1");
		loginParam.setContentEncoding("utf-8");
		loginParam.setContentType("application/x-www-form-urlencoded");
		loginPost.setEntity(loginParam);
		CloseableHttpResponse  loginResp=client.execute(loginPost);
		String loginResult=EntityUtils.toString(loginResp.getEntity(),"utf-8");
		AutoLogger.log.info(loginResult);
		String loginUnicode=HttpClientKw.DeCode(loginResult);
		AutoLogger.log.info(loginUnicode);
		
		
		
	}

}
