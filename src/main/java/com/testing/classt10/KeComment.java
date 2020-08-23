package com.testing.classt10;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONPath;
import com.testing.common.AutoLogger;
/*
 * JsonPath解析 Json包含数组
 */
public class KeComment {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(
				"https://ke.qq.com/cgi-bin/comment_new/course_comment_list?cid=315793&count=10&page=0&filter_rating=0&bkn=1098103277&r=0.45023804110424415");
		// 直接请求的时候会有referer错误，添加referer头域
		get.addHeader("referer", "https://ke.qq.com/course/315793?taid=5073666341917073&tuin=227706b0");
		CloseableHttpResponse resp = client.execute(get);

		String jsonResult = EntityUtils.toString(resp.getEntity(), "utf-8");

		AutoLogger.log.info(jsonResult);
		String comment="";
		for (int i = 0; i < 10; i++) {
			comment=JSONPath.read(jsonResult, "$.result.items["+i+"].first_comment").toString();
			AutoLogger.log.info(comment);
		}
		

	}

}
