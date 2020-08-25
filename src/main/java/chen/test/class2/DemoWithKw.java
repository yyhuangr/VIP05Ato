package chen.test.class2;

import com.testing.webKeyword.WebkeyWord;

public class DemoWithKw {
	/*
	 * 该类：是调用chen.test.webkeyword.WebKeyWord 里面的封装的关键字方法。
	 * 调用封装后的关键字方法，完成 打开浏览器（IE,chrome,firefox）
	 * 输入 www.baidu.com 网址
	 * 输入 cheese点击百度一下
	 * 显式等待：等待页面标题变成以小写cheese开头，如果超过10秒，依然没有符合条件，则报错。
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebkeyWord web=new WebkeyWord();
		web.openBrowser("chrome");
		web.visitWeb("https://www.baidu.com");
//		web.driver.findElement(by.)
		System.out.println("titile1---"+web.getTitle());
//		这一句会出现异常，没有name属性为s的元素  wd
		web.inputAndSubmitByName("s", "cheese!");
//		web.inputAndSubmitByName("s", "cheese!");
		//这一句汇报异常，预期等待事件没有发生。
		web.explicitlyWaitTitle();
		System.out.println("titile2---"+web.getTitle());
		web.closeBrowser();
	}

}
