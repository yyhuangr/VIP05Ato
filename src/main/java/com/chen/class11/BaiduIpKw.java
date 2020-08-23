package com.chen.class11;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.testing.common.AutoLogger;
import com.testing.inter.HttpClientKw;

public class BaiduIpKw {
//有点问题fiddler无法抓取https的包
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		CloseableHttpClient client=HttpClients.createDefault();
		HttpClientKw kw=new HttpClientKw();
		 String st=kw.doGet("https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php","query=.1.1.1.1&resource_id=6006&ie=utf8&oe=gbk");
		 AutoLogger.log.info(st);
	}

}
