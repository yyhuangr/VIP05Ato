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

import com.alibaba.fastjson.JSONPath;
import com.testing.common.AutoLogger;
import com.testing.inter.HttpClientKw;
/*
 * JsonPath解析 Json
 */
public class ZhuJsonTest {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		CloseableHttpClient client =HttpClients.createDefault();
		
		HttpPost jsonPost=new HttpPost("https://b.zhulogic.com/designer_api/account/login_quick_pc");
		//设置好请求体，也就是打包快递。
		StringEntity jsonParam=new StringEntity("{\"phone\":\"13412341234\",\"code\":\"\",\"messageType\":3,\"registration_type\":1,\"channel\":\"zhulogic\",\"unionid\":\"\"}");
		jsonParam.setContentType("application/json");
		jsonParam.setContentEncoding("utf-8");
		//将快递交给快递小哥
		jsonPost.setEntity(jsonParam);
		
		CloseableHttpResponse jsonResp=client.execute(jsonPost);
		String result=EntityUtils.toString(jsonResp.getEntity(),"UTF-8");
	    String uniResult=HttpClientKw.DeCode(result);
		AutoLogger.log.info(uniResult);
		
		//解析 json
		String status_code=JSONPath.read(uniResult, "$.status_code").toString();
		AutoLogger.log.info(status_code);
		
		if((new String("1001")).equals(status_code)) {
			AutoLogger.log.info("测试通过");
		}
		
		
		
		
	}

}
