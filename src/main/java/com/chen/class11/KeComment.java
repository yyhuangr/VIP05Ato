package com.chen.class11;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONPath;
import com.testing.common.AutoLogger;

/*
 * JsonPath解析 Json包含数组
 */
public class KeComment {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		CloseableHttpClient client=HttpClients.createDefault();
		HttpGet KeGet=new HttpGet("https://ke.qq.com/cgi-bin/comment_new/course_comment_list?cid=315793&count=10&page=0&filter_rating=0&bkn=&r=0.014349771803511713");
		// 直接请求的时候会有referer错误，添加referer头域
		KeGet.addHeader("referer","https://ke.qq.com/course/315793?taid=9631386852184465");
		CloseableHttpResponse respon=client.execute(KeGet);
		String strRulst=EntityUtils.toString(respon.getEntity(),"utf-8");
		AutoLogger.log.info("strRulst="+strRulst);
		
		//解析 课程的评论 数组
		for(int i=0;i<10;i++) {
			String first_comment=JSONPath.read(strRulst, "$.result.items["+i+"].first_comment").toString();
			AutoLogger.log.info("first_comment"+i+"="+first_comment);
		}
		
	}

}
