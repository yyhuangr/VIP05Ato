package com.chen.class11;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.testing.common.AutoLogger;
import com.testing.inter.HttpClientKw;

public class ZhuJsonLoginTest {

	public static void main(String[] args) throws  Exception {
		// TODO Auto-generated method stub
	CloseableHttpClient client =HttpClients.createDefault();
		
		HttpPost jsonPost=new HttpPost("https://b.zhulogic.com/designer_api/account/login_quick_pc");
		//设置好请求体，也就是打包快递。
		StringEntity jsonParam=new StringEntity("{\"phone\":\"13412341234\",\"code\":\"\",\"messageType\":3,\"registration_type\":1,\"channel\":\"zhulogic\",\"unionid\":\"\"}");
        //form不填写与填写与误是完全不同的测试场景
		//StringEntity jsonParam=new StringEntity("{\"code\":\"\",\"messageType\":3,\"registration_type\":1,\"channel\":\"zhulogic\",\"unionid\":\"\"}");
		
		jsonParam.setContentType("application/json");
		jsonParam.setContentEncoding("utf-8");
		//将快递交给快递小哥
		jsonPost.setEntity(jsonParam);
		//发包
		CloseableHttpResponse jsonResp=client.execute(jsonPost);
		//接收请求的返回值
		String result=EntityUtils.toString(jsonResp.getEntity(),"UTF-8");
		//将返回值转码
	    String uniResult=HttpClientKw.DeCode(result);
		AutoLogger.log.info(uniResult);
	}

}
