package com.testing.class12;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

public class ParamTest {
	
	public static Map<String, String> paramMap=new HashedMap<String, String>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		paramMap.put("tokenValue", "6f9f117d86994f61b66d157cd6aa6cb7");
		paramMap.put("password", "123456");
		System.out.println(paramMap);
//		String origin="{\"Roy\":\"{password}\",\"Content-Type\":\"{tokenValue}\",\"token\":\"{tokenValue}\"}";
		String origin="http://www.testingedu.com.cn/inter/HTTP/logout";
		//		String paramResult=origin;
		System.out.println("原字符串："+origin);
		//遍历map中所有的键值对，对字符串中的{参数键}格式直接替换所有的{参数键}为参数值
		for(String key:paramMap.keySet()) {
			//替换键所对应的值
			origin=origin.replaceAll("\\{"+key+"\\}", paramMap.get(key));
		}
		System.out.println("替换后的字符串"+origin);
	
	}

}
