package com.testing.classt10;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONPath;
import com.testing.common.AutoLogger;
import com.testing.inter.HttpClientKw;

public class ShopUploadTest {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		CloseableHttpClient client=HttpClients.createDefault();
		HttpPost post=new HttpPost("http://www.testingedu.com.cn:8000/index.php/home/Uploadify/imageUp/savepath/head_pic/pictitle/banner/dir/images.html");
		//创建一个multipart格式请求体的构造器
		MultipartEntityBuilder meb= MultipartEntityBuilder.create();
//		meb.addTextBody("id", "WU_FILE_0");
		//注意，文件参数的键名，不一定都叫file，根据自己的接口文档信息，来进行编写。
//		meb.addBinaryBody("file", new File("E:\\QSwork\\素材\\图片素材\\疫情.png"));
		meb.addBinaryBody("file", new File("f:\\1.jpg"));
		
		//用构造器完成构造，生成一个组合好的httpentity
		HttpEntity me= meb.build();
		//设置为请求体，因为multipartentitybuilder默认使用content-type就是multipart/form-data
		post.setEntity(me);
		//注意由于multipartentitybuild自动完成multipart的拼接，以及boundry分割线的生成所以，不要自己再去指定头域content-type了。
//		post.addHeader("Content-Type","multipart/form-data; boundary=----WebKitFormBoundaryZWCBqDCUN1ZsWum6");
		
		CloseableHttpResponse uploadResp=client.execute(post);
		String uploadResult=EntityUtils.toString(uploadResp.getEntity(), "utf-8");
		String uploadUnicode=HttpClientKw.DeCode(uploadResult);
		AutoLogger.log.info(uploadResult);
		AutoLogger.log.info(uploadUnicode);
		
		
	}

}
