package chen.test.class5;

import chen.test.class3.ShopByTest;
import chen.test.class4.ShopManagerTest;
import chen.test.webkeyword.WebKeyWord;

public class POBuyAndAdd {
	/*
	 * 该类是：采用PageObject模式。
	 * 调用chen.test.webkeyword.WebKeyWord.java类中封装好的关键字方法。
	 * 打开同一个浏览器（谷歌浏览器）
	 * 先登录商城后台，商城后台添加商品，断言：添加商品是否成功。
	 * 再登陆商城前台，商城前台购买商品，断言：订单是否购买成功。
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//首先实例化关键字类，也就是所有的页面都要用的BasePage
		WebKeyWord web=new WebKeyWord();
		//所有页面都使用同一个浏览器
		web.openBrowser("chrome");
		
		String goodsName="第五课测试商品22";
		//测试商城后台登陆功能页面。
		ShopManagerTest.adminLogin(web);
		//测试商城后台添加商品功能。
		ShopManagerTest.addGoods(web, goodsName);
		//断言，添加商品是否成功。
		web.assertPageContains(goodsName);
		
		//测试前台页面登陆功能。
		ShopByTest.shopLogin(web);
		//测试前台页面中的购买测试
		ShopByTest.buyGoods(web);
		//断言 订单是否购买成功。
		web.assertEleContainsText("//div[@class='erhuh']/h3", "提交成功");
		web.closeBrowser();
	}

}
