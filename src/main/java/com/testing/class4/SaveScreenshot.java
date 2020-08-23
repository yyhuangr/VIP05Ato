package com.testing.class4;

import com.testing.common.AutoLogger;
import com.testing.webKeyword.WebkeyWord;

public class SaveScreenshot {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebkeyWord web=new WebkeyWord();
		AutoLogger.log.info("+++++++++++++++++++测试开始+++++++++++++++");
		web.openBrowser("chrome");
		web.visitWeb("https://www.baidu.com");
		web.saveScrShot("自己想截图");
		web.input("ssw", "roy");
		
	}

}
