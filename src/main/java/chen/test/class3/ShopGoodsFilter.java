package chen.test.class3;

import chen.test.webkeyword.WebKeyWord;

public class ShopGoodsFilter {
	/*
	 * 该类是：调用com.testing.webKeyword.WebkeyWord类中封装好的关键字方法
	 * 打开谷歌浏览器，输入 http://www.testingedu.com.cn:8000/
	 * 登陆，返回商城首页，悬停的全部商品分类，悬停到手机数码，点击手机，切换到商品列表窗口
	 * 勾选筛选项，
	 * 断言：第一个商品是不是畅享8
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebKeyWord web = new WebKeyWord();
		// 选择谷歌浏览器
		web.openBrowser("chrome");
		// 打开网址
		web.visitWeb("http://testingedu.com.cn:8000");
		// 登陆
		web.shopLogin("791077118@qq.com", "123456");
		// 点击返回商城首页
		web.click("//a[text()='返回商城首页']");
		// 强制等待1秒钟
		web.halt("1");
		// 鼠标悬停到‘全部商品分类’
		web.hover("//a[text()='全部商品分类']");
		// 强制等待1秒钟
		web.halt("1");
		// 鼠标悬停到‘手机数码’
		web.hover("//a[text()='手机数码']");
		//点击手机
		web.click("//a[text()='手机' and not(@class)]");
		//切换浏览器窗口
		web.switchWindowByTitile("商品列表");
//		web.switchByName("商品列表");//该方法用不了
		//勾选一些筛选项
		web.click("//span[text()='全网通3G+32G']");
		//选择颜色
		web.click("//dt[text()='选择颜色']/following-sibling::dd//span[text()='红色']");
		//断言第一个商品元素中是否包含畅享8
		web.assertEleContainsText("//div[@class='shop-list-splb p']/ul/li[1]//div[@class='shop_name2']/a", "畅享8");
		web.closeBrowser();
	
	}

}
