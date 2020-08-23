package com.chen.class11;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONPath;
import com.testing.common.AutoLogger;
import com.testing.inter.HttpClientKw;

public class TestingLoginTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		CloseableHttpClient client=HttpClients.createDefault();
		HttpPost LoginPost=new HttpPost("http://testingedu.com.cn:8000/index.php?m=Home&c=User&a=do_login&t=0.3469304538686684");
	    StringEntity LoginParam=new StringEntity("username=13800138006&password=123456&verify_code=1");
	    LoginParam.setContentEncoding("utf-8");
	    LoginParam.setContentType("application/x-www-form-urlencoded");
	    LoginPost.setEntity(LoginParam);
	    CloseableHttpResponse LoginResp=client.execute(LoginPost);
	    String result= EntityUtils.toString(LoginResp.getEntity(),"utf-8");
	    
	    AutoLogger.log.info("result="+result);
	    String loginUnicode=HttpClientKw.DeCode(result);
		AutoLogger.log.info(loginUnicode);
		
		String str=JSONPath.read(loginUnicode, "$.msg").toString();
		AutoLogger.log.info("str="+str);
	    if(str.equals("登陆成功")) {
	    	AutoLogger.log.info("测试成功！");
	    }else {
	    	AutoLogger.log.info("测试失败！");
		}
	}

}
