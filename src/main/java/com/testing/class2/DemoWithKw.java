package com.testing.class2;

import com.testing.webKeyword.WebkeyWord;

public class DemoWithKw {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebkeyWord web=new WebkeyWord();
		web.openBrowser("chrome");
		web.visitWeb("https://www.baidu.com");
		System.out.println(web.getTitle());
//		这一句会出现异常，没有name属性为s的元素
		web.inputAndSubmitByName("s", "cheese!");
		//这一句汇报异常，预期等待事件没有发生。
		web.explicitlyWaitTitle();
		System.out.println(web.getTitle());
		web.closeBrowser();
	}

}
