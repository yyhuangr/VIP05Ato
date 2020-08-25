package com.testing.class14;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.testing.common.ExcelReader;
import com.testing.common.ExcelWriter;
import com.testing.inter.DataDrivenOfSoap;

public class DDTofSoap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 记录执行时间
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HH+mm+ss");
		String createdate = sdf.format(date);
		// 创建excelreader对象，打开用例源文件
		ExcelReader oriCase = new ExcelReader("cases/SOAPLogin.xlsx");
		// 创建excelWriter对象，复制一份结果文件，并且打开它进行操作
		ExcelWriter resCase = new ExcelWriter("cases/SOAPLogin.xlsx", "cases/result-" + createdate + "SOAPLogin.xlsx");
		DataDrivenOfSoap soap = new DataDrivenOfSoap(resCase);
		//通过excel用例的遍历读取，每一行执行对应的关键字调用
		for(int sheetNO=0;sheetNO<oriCase.getTotalSheetNo();sheetNO++) {
			//指定用例文件和结果文件使用当前下标的sheet页
			oriCase.useSheetByIndex(sheetNO);
			resCase.useSheetByIndex(sheetNO);
			//循环遍历sheet页中的每一行
			for(int rowNO=0;rowNO<oriCase.rows;rowNO++) {
				//传递line,告知datadriven的方法，当前执行到哪一行，应该往哪一行写结果
				soap.setline(rowNO);
				//调用方法完成测试的执行和结果的写入
				//读取当前行的内容
				List<String> lineContent=oriCase.readLine(rowNO);
				//判断第一列和第二列为空，这才是我们想要执行的用例。
				if((lineContent.get(0).length()==0&&lineContent.get(1).length()==0)
						||(lineContent.get(0)==null&&lineContent.get(0)==null)
						||(lineContent.get(0).trim().equals("")&&(lineContent.get(1).trim().equals("")))){
					//通过第4列，也就是关键字列，判断当前行执行的操作是什么
					switch (lineContent.get(3)) {
					case "createCon":
						soap.createCon(lineContent.get(4));
						break;
					case "testSoap":
						soap.testSoap(lineContent.get(4), lineContent.get(5), lineContent.get(6));
						break;
					case "addHeader":
						soap.addHeader(lineContent.get(4));
						break;
					case "saveParam":
						soap.saveParam(lineContent.get(4), lineContent.get(5));
						break;
					}
					// 第二步，执行校验列对应的断言
					switch (lineContent.get(7)) {
					case "equal":
						soap.assertSame(lineContent.get(8), lineContent.get(9));
						break;
					case "contains":
						soap.assertContains(lineContent.get(8), lineContent.get(9));
						break;
					}
				}
				
				
			}
		}//sheet页遍历结束
		//完成结果文件的保存
		resCase.save();
	}

}
