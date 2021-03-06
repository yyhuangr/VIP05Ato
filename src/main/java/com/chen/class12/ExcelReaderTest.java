package com.chen.class12;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.testing.common.AutoLogger;
import com.testing.common.ExcelReader;
import com.testing.common.ExcelWriter;
import com.testing.inter.KeywordOfInter;

public class ExcelReaderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//第一步：打开excel文件，读取为workbook对象
		ExcelReader cases=new ExcelReader("cases/HTTPLogin.xlsx");
		
		//实例化
		KeywordOfInter inter=new KeywordOfInter();
		//第二步：遍历sheet页，基于sheet页个数进行遍历
		for(int sheetIndex=0;sheetIndex<cases.getTotalSheetNo();sheetIndex++) {
			//指定使用当前的sheet页
			cases.useSheetByIndex(sheetIndex);
			System.out.println("---------当前使用的sheet页是："+cases.getSheetName(sheetIndex)+"-----------------");
			//读取当前sheet页中的每一行信息
			for(int rowIndex=0;rowIndex<cases.rows;rowIndex++) {
				//读取的每一行信息使用list存起来
				List<String> rowContent=cases.readLine(rowIndex);
				System.out.println(rowContent);
				if(rowContent.get(3)!=null && rowContent.get(3).length()>0) {
				//基于每个list的第四个元素也就是每一行的第四个单元格内容决定执行哪个关键字
				switch(rowContent.get(3)) {
				//实现关键字（post,saveParam,addHeader,clearHeader）调用
				case "post":
					inter.testPost(rowContent.get(4), rowContent.get(5));
					break;
				case "saveParam	":	
					inter.saveParam(rowContent.get(4), rowContent.get(5));
					break;
				case "addHeader":
					inter.addHeader(rowContent.get(4));
					break;
				case "clearHeader":
					inter.clearHeader();
					break;
				case "测试json接口":
					inter.testPostJson(rowContent.get(4), rowContent.get(5));
					break;
					default:
						AutoLogger.log.info("没有匹配到关键字，请检查！");
					break;
				}
				//执行完关键字调用方法，进行断言调用，断言的关键字在第8列。
				/*	*/
				switch(rowContent.get(7)) {
				case "equal":
					boolean result=inter.assertSame(rowContent.get(8), rowContent.get(9));
					if(result) {
						AutoLogger.log.info("外部断言通过！");
					}
					else { 
						AutoLogger.log.info("外部断言失败！");
					}
					break;
				}
				
			}
		}
			
		}
		
	}

}
