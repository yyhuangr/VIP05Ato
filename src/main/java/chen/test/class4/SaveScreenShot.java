package chen.test.class4;

import chen.test.common.AutoLogger;
import chen.test.webkeyword.WebKeyWord;

public class SaveScreenShot {
	/*
	 * 该类：调用hen.test.webkeyword.WebKeyWord类中封装的关键字方法。
	 * 打开谷歌浏览器，输入url：https://www.baidu.com
	 * 通过ssw使用xpath定位百度首页的输入框（）。
	 * 是测试一下hen.test.webkeyword.WebKeyWord类中的saveScrShot（）方法是否能在自动化测试
	 * 过程中出错的时候截图。
	 * 测试AutoLogger（）方法能否在日志中记录
	 */
	public static void main(String[] args) {
		WebKeyWord web=new WebKeyWord();
		AutoLogger.log.info("+++++++++++测试开始++++++1++++++");
		web.openBrowser("chrome");
		web.visitWeb("http://www.baidu.com");
		web.saveScrShot("自己想截图");
		//正确的应该是在 wd这里输入：//input[@name='wd']
		web.input("ssw", "chen");
		web.halt("8");
		web.closeBrowser();
	}
}
