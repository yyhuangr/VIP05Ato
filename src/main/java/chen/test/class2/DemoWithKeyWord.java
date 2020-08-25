package chen.test.class2;

import chen.test.webkeyword.WebKeyWord;

public class DemoWithKeyWord {
	/*
	 * 该类：是调用chen.test.webkeyword.WebKeyWord 里面的封装的关键字方法。
	 * 调用封装后的关键字方法，完成 打开浏览器（IE,chrome,firefox）
	 * 输入 www.baidu.com 网址
	 * 输入 cheese点击百度一下
	 * 显式等待：等待页面标题变成以小写cheese开头，如果超过10秒，依然没有符合条件，则报错。
	 */
	public static void main(String[] args) {
		WebKeyWord ww=new WebKeyWord();
//		ww.openBrowser("ie");//打开ie浏览器
//		ww.openBrowser("chrome");//打开谷歌浏览器
		ww.openBrowser("firefox");//打开火狐浏览器
		ww.visitWeb("http://www.baidu.com");
		System.out.println("titile1---"+ww.getTitle());
		ww.inputAndSubmitByName("wd", "cheese");
		//显式等待
		ww.explicitlyWaitTitle();
		System.out.println("titile2---"+ww.getTitle());
		//关闭浏览器以及浏览器驱动
		ww.closeBrowser();
	}
	
}
