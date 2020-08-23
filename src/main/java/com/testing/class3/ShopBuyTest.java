package com.testing.class3;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.NeedsLocalLogs;

import com.testing.webKeyword.WebkeyWord;

public class ShopBuyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebkeyWord web=new WebkeyWord();
		web.openBrowser("chrome");
		shopLogin(web);
		buyGoods(web);
		//断言订单提交成功。
		web.assertEleContainsText("//div[@class='erhuh']/h3", "提交成功");
		
	}

	public static void shopLogin(WebkeyWord web) {
		web.visitWeb("http://www.testingedu.com.cn:8000/");
		//登录
		web.shopLogin("13800138006", "123456");
	}

	//购买商品的页面功能方法。
	public static void buyGoods(WebkeyWord web) {
		web.click("//a[text()='返回商城首页']");
		//悬停到全部商品分类
		web.hover("//a[text()='全部商品分类']");
		web.halt("1");
		//悬停到手机数码
		web.hover("//a[text()='手机数码']");
		web.click("//a[text()='手机' and not(@class)]");
		//切换窗口
		web.switchWindowByTitle("商品列表");
		//点击第二个商品
		web.click("//div[@class='shop-list-splb p']/ul/li[2]//div[@class='shop_name2']/a");
		web.click("//a[@id='join_cart']");
		//由于id和name中包含了一个自动生成的编号，所以id和name是变化的，选择不变的父元素来辅助定位iframe元素，进行切换。
		web.switchIframeAsele("//div[@class='layui-layer-content']/iframe");
		//由于iframe中的a元素一直在页面中存在，所以不会触发隐式等待。
		//点击添加购物车之后，会有一个加载js脚本的过程，所以需要执行一下等待。
		web.halt("5");
		web.click("//a[text()='去购物车结算']");
		//点击去结算
//		web.switchToRoot();
		web.click("//a[@class='paytotal']");
		//滚动滑动条到最下
		web.scrollToEnd();
		//等待收货地址加载完成
		web.halt("3");
		//基于传参的方式，先通过xpath定位到元素，然后用arguments[0]调用，进行js脚本执行
		web.runJsWithArg("arguments[0].click()", "//button[@type='submit']");
	}



}
