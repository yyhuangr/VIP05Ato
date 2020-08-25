package chen.test.class2;

//import com.testing.webKeyword.WebkeyWord;

import chen.test.webkeyword.WebKeyWord;

public class LoginTest {
/*
 * 该类是：打开谷歌浏览器
 * 输入url:testingedu.com.cn:8000 
 * 登陆，返回商城首页，点击 手机数码 
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebKeyWord web=new WebKeyWord();
		//打开谷歌浏览器
		web.openBrowser("chrome");
		//打开目标网站
		web.visitWeb("testingedu.com.cn:8000");
		//获取货架类别(并输出到控制台)
//		web.getAllgoodsType("//div[@class='cata-nav-wrap']/a");

		//点击登陆的超链接位置
		web.shopLogin("791077118@qq.com","123456");
		
		//点击 返回商城 首页
		web.click("//a[@href='/Home/Index/index.html']");
		//手机数码
		web.click("//a[text()='手机数码']");
		//手机数码->手机
//		web.click("//a[@href='/Home/Goods/goodsList/id/62.html']");
		//关闭浏览器 已经驱动进程
		web.closeBrowser();
	}
	public static void shopLogin(WebKeyWord web) {
		web.click("//a[text()='登录']");
		web.input("//input[@id='username']", "13800138006");
		web.input("//input[@id='password']", "123456");
		web.input("//input[@id='verify_code']", "1");
		web.click("//a[@name='sbtbutton']");
	}

}
