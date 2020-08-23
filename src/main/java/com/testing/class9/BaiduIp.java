package com.testing.class9;

import java.io.IOException;

import javax.xml.ws.Response;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONPath;
import com.testing.common.AutoLogger;

public class BaiduIp {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		//创建httpclient实例化对象，作为客户端，完成发包
		CloseableHttpClient client=HttpClients.createDefault();
		//创建httpget方法，填写url
		HttpGet ipGet=new HttpGet("https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query=.1.1.1.1&resource_id=6006&ie=utf8&oe=gbk");
		//执行发包
		CloseableHttpResponse response= client.execute(ipGet);
		AutoLogger.log.info("返回内容是"+response);
		//将返回报文中的返回体内容获取出来
		HttpEntity ipEN= response.getEntity();
		AutoLogger.log.info("返回体是"+ipEN);
		//返回体解析成为字符串
		String ipresult=EntityUtils.toString(ipEN);
		AutoLogger.log.info("返回字符串结果是"+ipresult);
		
		String location=JSONPath.read(ipresult,"$.data[0].location").toString();
		AutoLogger.log.info("location字段的值是"+location);
		
		
		if(location.equals("澳大利亚")) {
			AutoLogger.log.info("测试成功");
		}
		else {
			AutoLogger.log.info("测试失败");
		}
		response.close();
		client.close();
		
		
		
	}

}
