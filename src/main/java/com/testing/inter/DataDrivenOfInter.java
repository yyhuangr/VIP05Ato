package com.testing.inter;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.testing.common.AutoLogger;
import com.testing.common.ExcelWriter;

public class DataDrivenOfInter {
	public HttpClientKw client;
	public Map<String, String> paramMap;
	//加入成员变量，方便在每一行用例调用时，统一操作的行数、返回结果的断言、excel的写入。
	public String tmpResponse;
	public int line = 0; // 成员变量行数，用于在用例执行时保持执行行和写入行一致
	public ExcelWriter outExcel;//操作的excelwriter
	//构造方法，传递用例文件和结果文件路径，在构造方法中实例化excelwriter对象
	public DataDrivenOfInter(String casePath,String resultPath) {
		client=new HttpClientKw();
		paramMap=new HashMap<String,String>();
		//实例化excelwriter对象，让关键字知道操作哪个文件
		outExcel =new ExcelWriter(casePath, resultPath);
	}
	//外部创建好excelwriter对象之后，直接传递给构造方法使用。
	public DataDrivenOfInter(ExcelWriter excel) {
		client=new HttpClientKw();
		paramMap=new HashMap<String,String>();
		outExcel=excel;
	}
	
	public void setline(int rowNo) {
		line=rowNo;
	}
	
	public String testGet(String url,String param) {
			url=toParam(url);
			param=toParam(param);
			String response =client.doGet(url, param);
			tmpResponse=response;
			outExcel.writeCell(line, 11, response);
			return response;
	}
	
	public String testPost(String url,String param) {
			url=toParam(url);
			param=toParam(param);
			String response =client.doPost(url, param);
			tmpResponse=response;
			outExcel.writeCell(line, 11, response);
			return response;
	}
	

	public String testPostJson(String url, String jsonParam) {
		url = toParam(url);
		jsonParam = toParam(jsonParam);
		tmpResponse = client.doPostJson(url, jsonParam);
		AutoLogger.log.info(tmpResponse);
		outExcel.writeCell(line, 11, tmpResponse);
		return tmpResponse;
	}
	public void saveCookie() {
		try {
			client.saveCookie();
			outExcel.writeCell(line, 10, "PASS");
		} catch (Exception e) {
			outExcel.writeFailCell(line, 10, "FAIL");
			outExcel.writeFailCell(line, 11, e.fillInStackTrace().toString());
		}
	}

	public void clearCookie() {
		try {
			client.clearCookie();
			outExcel.writeCell(line, 10, "PASS");
		} catch (Exception e) {
			outExcel.writeFailCell(line, 10, "FAIL");
			outExcel.writeFailCell(line, 11, e.fillInStackTrace().toString());
		}
	}
	
	public void addHeader(String headerJson) {
		try {
			// 在解析为map之前，先替换{参数名}为参数值
			headerJson = toParam(headerJson);
			// 创建map
			Map<String, String> headerMap = new HashMap<String, String>();
			// 以json格式的头域列表为基础，创建一个json类型的对象
			JSONObject json = JSON.parseObject(headerJson);
			for (String key : json.keySet()) {
				headerMap.put(key, json.get(key).toString());
			}
			// 转换出的map作为addheader所使用的map，来进行添加头域的操作。
			client.addHeader(headerMap);
			outExcel.writeCell(line, 10, "PASS");
		} catch (Exception e) {
			AutoLogger.log.error("头域参数格式错误，请检查");
			AutoLogger.log.error(e,e.fillInStackTrace());
			outExcel.writeFailCell(line, 10, "FAIL");
			outExcel.writeFailCell(line, 11, e.fillInStackTrace().toString());
		}
	}
	
	public void clearHeader() {
		try {
			client.clearHeader();
			outExcel.writeCell(line, 10, "PASS");
		} catch (Exception e) {
			outExcel.writeFailCell(line, 10, "FAIL");
			outExcel.writeFailCell(line, 11, e.fillInStackTrace().toString());
		}
	}
	
	public void saveParam(String key,String jsonPath) {
		String value;
		try {
			value = JSONPath.read(tmpResponse,jsonPath).toString();
			paramMap.put(key, value);
			outExcel.writeCell(line, 10, "PASS");
		} catch (Exception e) {
			AutoLogger.log.error("保存参数失败");
			AutoLogger.log.error(e,e.fillInStackTrace());
			outExcel.writeFailCell(line, 10, "FAIL");
			outExcel.writeFailCell(line, 11, e.fillInStackTrace().toString());
		}
	}
	
	public String toParam(String origin) {
		String param=origin;
		for(String key:paramMap.keySet()) {
		param=param.replaceAll("\\{"+key+"\\}", paramMap.get(key));
		}
		return param;
	}

	public void assertSame(String jsonPath,String expect) {
		try {
			String actual=JSONPath.read(tmpResponse,jsonPath).toString();
			if(actual!=null&&actual.equals(expect)) {
				AutoLogger.log.info("测试通过！");
				outExcel.writeCell(line, 10, "PASS");
			}
			else {
				AutoLogger.log.info("测试失败！");
				outExcel.writeFailCell(line, 10, "FAIL");
				outExcel.writeFailCell(line, 11, "预期值"+expect+"不等于结果"+actual);
			}
		} catch (Exception e) {
			AutoLogger.log.error("解析失败，请检查jsonPath表达式");
			AutoLogger.log.error(e,e.fillInStackTrace());
			outExcel.writeFailCell(line, 10, "FAIL");
			outExcel.writeFailCell(line, 11, e.fillInStackTrace().toString());
		}
	}
	
	public void assertContains(String jsonPath,String expect) {
		try {
			String actual=JSONPath.read(tmpResponse,jsonPath).toString();
			if(actual!=null&&actual.contains(expect)) {
				AutoLogger.log.info("测试通过！");
				outExcel.writeCell(line, 10, "PASS");
			}
			else {
				AutoLogger.log.info("测试失败！");
				outExcel.writeFailCell(line, 10, "FAIL");
			}
		} catch (Exception e) {
			AutoLogger.log.error("解析失败，请检查jsonPath表达式");
			AutoLogger.log.error(e,e.fillInStackTrace());
			outExcel.writeFailCell(line, 10, "FAIL");
			outExcel.writeFailCell(line, 11, e.fillInStackTrace().toString());
		}
	}
}
