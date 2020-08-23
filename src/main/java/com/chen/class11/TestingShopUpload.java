package com.chen.class11;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.testing.common.AutoLogger;
import com.testing.inter.HttpClientKw;

public class TestingShopUpload {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		CloseableHttpClient client=HttpClients.createDefault();
		HttpPost UploadPost=new HttpPost("http://testingedu.com.cn:8000/index.php/home/Uploadify/imageUp/savepath/head_pic/pictitle/banner/dir/images.html");
	   //http://testingedu.com.cn:8000/index.php?m=Home&c=Uploadify&a=upload&num=1&input=head_pic&path=head_pic&func=add_img 
		MultipartEntityBuilder multi=MultipartEntityBuilder.create();
		
	    multi.addTextBody("id", "WU_FILE_0");
	    multi.addBinaryBody("file", new File("f:\\1.jpg"));
	    HttpEntity hpen=multi.build();
	    
	    UploadPost.setEntity(hpen);
	    //注意由于multipartentitybuild自动完成multipart的拼接，以及boundry分割线的生成所以，不要自己再去指定头域content-type了。
//		UploadPost.addHeader("Content-Type","multipart/form-data; boundary=----WebKitFormBoundaryZWCBqDCUN1ZsWum6");
	    
	    CloseableHttpResponse respon=client.execute(UploadPost) ;
	    String reslut=EntityUtils.toString(respon.getEntity(),"utf-8");
	    AutoLogger.log.info(reslut);
	    
	    String uploadUnicode=HttpClientKw.DeCode(reslut);
		AutoLogger.log.info("转码前返回："+reslut);
		AutoLogger.log.info("转码后返回："+uploadUnicode);
	    

	    
	}

}
