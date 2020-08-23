package com.testing.classt10;

import com.testing.common.AutoLogger;
import com.testing.inter.HttpClientKw;

public class ZhuJsonKwTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpClientKw key=new HttpClientKw();
		String result=key.doPostJson("https://b.zhulogic.com/designer_api/account/login_quick_pc", "{\"phone\":\"13412341234\",\"code\":\"\",\"messageType\":3,\"registration_type\":1,\"channel\":\"zhulogic\",\"unionid\":\"\"}");
		AutoLogger.log.info(result);
	}

}
