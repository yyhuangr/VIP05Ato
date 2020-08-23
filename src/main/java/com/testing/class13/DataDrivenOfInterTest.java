package com.testing.class13;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.testing.common.ExcelReader;
import com.testing.common.ExcelWriter;
import com.testing.inter.DataDrivenOfInter;

public class DataDrivenOfInterTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date date = new Date();
		//将时间转换为指定的格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd+HH-mm-ss");
		// 将当前时间转换为上一步指定格式的字符串
		String createdate = sdf.format(date);
		
		ExcelReader readerTest = new ExcelReader("cases/HttpLogin.xlsx");
		ExcelWriter writerTest= new ExcelWriter("cases/HttpLogin.xlsx", "cases/ResultOfHttpLogin"+createdate+".xlsx");
		DataDrivenOfInter kw = new DataDrivenOfInter(writerTest);
		//		System.out.println(readerTest.readLine(11));
		// 循环操作读取所有sheet页
		for (int sheetNo = 0; sheetNo < readerTest.getTotalSheetNo(); sheetNo++) {
			// 使用当前序号的sheet页
			readerTest.useSheetByIndex(sheetNo);
			writerTest.useSheetByIndex(sheetNo);
			System.out.println("-----------------当前所在的sheet页是：" + readerTest.getSheetName(sheetNo));
			// 循环读取每一行
			for (int rowNo = 0; rowNo < readerTest.rows; rowNo++) {
				//指定当前执行的行号
				kw.setline(rowNo);
				System.out.println(readerTest.readLine(rowNo));
				// 这是每一行读取到的内容
				List<String> rowContent = readerTest.readLine(rowNo);
				//可以判断一下，只有前面两列为空的才是用例行，需要switch关键字
				if((rowContent.get(0).length()==0&&rowContent.get(1).length()==0)||
						rowContent.get(0)==null&&rowContent.get(1)==null) {
				//通过第4列先判断关键字的类型以决定调用什么关键字进行操作。
				switch (rowContent.get(3)) {
				case "post":
					kw.testPost(rowContent.get(4), rowContent.get(5));
					break;
				case "saveParam":
					kw.saveParam(rowContent.get(4), rowContent.get(5));
					break;
				case "addHeader":
					kw.addHeader(rowContent.get(4));
					break;
				}
				//第二步，通过第8列，判断需要调用的断言方法进行操作
				switch(rowContent.get(7)) {
				case "equal":
					kw.assertSame(rowContent.get(8), rowContent.get(9));
				case "contains":
					kw.assertContains(rowContent.get(8), rowContent.get(9));
					break;
				}
				}
			}
		}

		//保存写入结果
		writerTest.save();
	}

}
