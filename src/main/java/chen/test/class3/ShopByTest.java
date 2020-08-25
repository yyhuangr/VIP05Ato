package chen.test.class3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import chen.test.webkeyword.WebKeyWord;

public class ShopByTest {
	/*
	 * 该类是：打开chrome谷歌浏览器
	 * 调用封装好的登陆方法，登陆
	 * 调用封装好的商品购买方法，购买
	 * 断言，订单是否提交成功
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebKeyWord web=new WebKeyWord();
		//选择谷歌浏览器
		web.openBrowser("chrome");
		shopLogin(web);
		//强制等待1秒钟
		web.halt("1");
		//购买商品
		buyGoods(web);
		//断言 订单提交成功
		web.assertEleContainsText("//div[@class='erhuh']/h3", "提交成功");
	}
	
	
	public static void shopLogin(WebKeyWord web) {
		//打开网址
		web.visitWeb("http://testingedu.com.cn:8000");
		//登陆
		web.shopLogin("791077118@qq.com", "123456");
	}
	//购买商品的页面功能方法。
	public static void buyGoods(WebKeyWord web) {
		//点击返回商城首页
		web.click("//a[text()='返回商城首页']");
		//强制等待1秒钟
		web.halt("1");
		//鼠标悬停到‘全部商品分类’
		web.hover("//a[text()='全部商品分类']");
		//强制等待1秒钟
		web.halt("1");
		//鼠标悬停到‘手机数码’
		web.hover("//a[text()='手机数码']");
		web.halt("5");
		//点击手机
		web.click("//a[text()='手机' and not(@class)]");
		//切换窗口
		web.switchWindowByTitile("商品列表");
		//强制等待1秒钟
		web.halt("2");
		//点击第二个商品
		web.click("//div[@class='shop-list-splb p']/ul/li[2]//div[@class='shop_name2']");
		//点击 加入购物车
		web.click("//a[@id='join_cart']");
		//切换窗口
		//由于iframe的id,name属性中包含了一个自动生成的编号，所以id和name是变化的，选择不变的父元素来辅助定位iframe元素，进行窗口切换。
		web.switchIframeAsEle("//div[@class='layui-layer-content']/iframe");
		//由于iframe中的元素一直在页面中存在，所以不会触发隐式等待。点击加入购物车之后，会有一个加载js脚本的过程，所以需要执行一下等待。
		web.halt("5");
		//点击去购物车结算
		web.click("//a[text()='去购物车结算']");
		//点击 去结算 按钮
//		web.switchToRoot();
		web.click("//a[@class='paytotal']");
		//页面滚动条，使用xpath的$x执行的地方Console 输入 window.scrollTo(0,1500),window.scrollTo(0,document.body.scrollHeight)
		//滚动 滚动条 到最下
		web.scrollToEnd();
		//强制等待5秒钟，等待收货地址加载完成
		web.halt("7");
		//点击 提交按钮
		//基于传参的方式，先通过xpath定位到元素，然后用arguments[0]调用，进行js脚本执行
		web.runJsWithArg("arguments[0].click()", "//button[@type='submit']");
	}



}
