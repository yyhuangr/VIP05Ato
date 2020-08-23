package com.testing.classt10;

import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.JSONPath;


public class PracticeOfJsonPath {
	public static final String json="{" + 
			"\"testing\": {" + 
			"\"teacher\": [" + 
			"{" + 
			"\"name\": \"roy\"," + 
			"\"age\": \"28\"," + 
			"\"course\": \"java\"," + 
			"\"id\": 1" + 
			"}," + 
			"{" + 
			"\"name\": \"will\"," + 
			"\"age\": \"28\"," + 
			"\"course\": \"python\"," + 
			"\"id\": 2" + 
			"}," + 
			"{" + 
			"\"name\": \"土匪\"," + 
			"\"age\": \"36\"," + 
			"\"course\": \"性能\"," + 
			"\"description\": \"大牛\"," + 
			"\"id\": 3" + 
			"}," + 
			"{" + 
			" \"name\": \"卡卡\"," + 
			"\"age\": \"24\"," + 
			"\"course\": \"All\"," + 
			"\"description\": \"小鲜肉\"," + 
			"\"id\": 4" + 
			"}" + 
			"]," + 
			"\"bicycle\": {" + 
			"\"color\": \"red\"," + 
			"\"id\": 5" + 
			"}" + 
			"}," + 
			"\"ClassID\": 4" + 
			"}";
	
	public static String ipJson ="{\"status\":\"0\",\"t\":\"\",\"set_cache_time\":\"\",\"data\":{\"location\":\"美国\",\"titlecont\":\"IP地址查询\",\"origip\":\"3.3.3.3\",\"origipquery\":\"3.3.3.3\",\"showlamp\":\"1\",\"showLikeShare\":1,\"shareImage\":1,\"ExtendedLocation\":\"\",\"OriginQuery\":\"3.3.3.3\",\"tplt\":\"ip\",\"resourceid\":\"6006\",\"fetchkey\":\"3.3.3.3\",\"appinfo\":\"\",\"role_id\":0,\"disp_type\":0}}";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String dataLo=JSONPath.read(ipJson, "$.data.location").toString();
		System.out.println("地址是："+dataLo);
		
		
		//取所有name键的值，存放为list
//		String jsonpathName="$..name";
//		List<String> nameList=JsonPath.read(json, jsonpathName);	
//		//读取出来的所有name的值的列表
//		String Names=JsonPath.read(json, jsonpathName).toString();
//		System.out.println("任意位置搜索name的所有值"+Names);
//		System.out.println("任意位置搜索name列表中的第3个"+nameList.get(2));
//		//取土匪老师名字的jsonpath表达式
//		String jsonPathtufei="$.testing.teacher[2].name";
//		String tufeiName=JsonPath.read(json,jsonPathtufei).toString();
//		System.out.println("土匪的名字是"+tufeiName);
//		
//		//jsonpath解析时，能够自动判断解析出来的内容的数据类型。
//		int id1=JsonPath.read(json, "$.testing.bicycle.id");
//		System.out.println(id1);
//		//teacher的子元素中，符合id小于3的元素
//		List<Object> teachers=JsonPath.read(json, "$.testing.teacher[?(@.id < 3)]");
//		System.out.println(teachers);
//		for(Iterator<Object> it=teachers.iterator();it.hasNext();) {
//			System.out.println(it.next());
//		}
	}

}
