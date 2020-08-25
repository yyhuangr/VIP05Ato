package com.chen.class13;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.testing.common.AutoLogger;
import com.testing.inter.KeywordOfInter;

public class SoapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KeywordOfInter key =new KeywordOfInter();
		//auth接口
		String authResult=key.testxmlPost("http://www.testingedu.com.cn:8081/inter/SOAP?wsdl", "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://soap.testingedu.com/\"><soapenv:Header/><soapenv:Body><soap:auth></soap:auth></soapenv:Body></soapenv:Envelope>");
		//由于keywordofinter需要调用json格式的返回来进行解析，所以将下面这段正则表达式处理放到textxmlPost方法中，用于更新response成员变量。
		//正则表达式读取返回xml中的json内容，return这个元素，是wsdl文档中定义，根据返回实际情况编写。
//		Pattern returnPattern=Pattern.compile("<return>(.*?)</return>");
//		Matcher returnM=returnPattern.matcher(authResult);
//		while(returnM.find()) {
//			AutoLogger.log.info(returnM.group(0));
//			AutoLogger.log.info(returnM.group(1));
//		}
		
		key.saveParam("tokenValue", "$.token");
		key.addHeader("{\"token\":\"{tokenValue}\"}");
		
		//login/登录接口
		key.testxmlPost("http://www.testingedu.com.cn:8081/inter/SOAP?wsdl", "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://soap.testingedu.com/\"><soapenv:Header/><soapenv:Body><soap:login><arg0>chen</arg0><arg1>123456</arg1></soap:login></soapenv:Body></soapenv:Envelope>");
		key.saveParam("idValue", "$.userid");
		
		//getUserInfo接口
		key.testxmlPost("http://www.testingedu.com.cn:8081/inter/SOAP?wsdl", "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://soap.testingedu.com/\"><soapenv:Header/><soapenv:Body><soap:getUserInfo><arg0>{idValue}</arg0></soap:getUserInfo></soapenv:Body></soapenv:Envelope>");
		
		//logout接口
		key.testxmlPost("http://www.testingedu.com.cn:8081/inter/SOAP?wsdl", "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://soap.testingedu.com/\"><soapenv:Header/><soapenv:Body><soap:logout></soap:logout></soapenv:Body></soapenv:Envelope>");
		
	
	}

}
