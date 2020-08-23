package com.testing.classt10;

import com.testing.common.AutoLogger;
import com.testing.inter.HttpClientKw;

public class KecommentKw {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpClientKw key=new HttpClientKw();
		String result=key.doGet("https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php","query=.1.1.1.1&resource_id=6006&ie=utf8&oe=gbk");
		AutoLogger.log.info(result);
	
	}

}
