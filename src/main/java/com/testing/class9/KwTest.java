package com.testing.class9;

import com.testing.inter.HttpClientKw;

public class KwTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpClientKw kw=new HttpClientKw();
		String result=kw.doPost("http://localhost:8080/VIP05LoginMaven/Login", "User=Roy&pwd=123456");
		System.out.println("结果是"+result);
	}

}
