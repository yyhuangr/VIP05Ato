package chen.test.class4;

import chen.test.webkeyword.WebKeyWord;

public class ShopManagerTest {
/*
 * 该类是：调用chen.test.webkeyword.WebKeyWord.java类中封装的关键字方法。
 * 登陆商城后台，
 * 调用再次封装的添加商品的方法，添加商品
 * 断言：在商品添加成功后，返回到商品列表页面。在商品列表页面内容中是否有新添加的商品。
 * 有，则说明   -测试成功。
 * 没有，则说明 -测试失败。
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebKeyWord web=new WebKeyWord();
		web.openBrowser("chrome");
		//后台登陆页面的登陆测试
		adminLogin(web);
		
		String goodsName="VIP05第5课测试商品44";
		//对于商城后台商品页面进行添加商品的功能测试。
		addGoods(web,goodsName);
		web.halt("8");
		//断言
		web.assertPageContains(goodsName);
		web.halt("30");
		web.closeBrowser();
		
	}
	//针对于首页这个页面进行的测试，所有的需要的定位元素和操作方法都在这里
	public static void adminLogin(WebKeyWord web) {
		web.visitWeb("http://testingedu.com.cn:8000/index.php/Admin/Admin/login");
		web.input("//input[@name='username']", "admin");
		web.input("//input[@name='password']", "123456");
		//验证码随意输入
		web.input("//input[@name='vertify']", "1");
		web.click("//input[@name='submit']");
	}
	//商城页面中的添加商品功能的测试 用到的元素和方法都在这了。
	public static void addGoods(WebKeyWord web,String goodsName) {
		//点击商城
		web.click("//a[text()='商城']");
		//切换到iframe
		web.switchIframe("workspace");
		web.halt("8");
		web.click("//span[text()='添加商品']");
		web.input("//input[@name='goods_name']", goodsName);
		//下拉框选择
//		web.selectByText("//select[@name='cat_id']", "电脑");
		web.selectByText("(//select)[1]", "电脑");
		//由于选择第一个下拉框之后，后面的第二个下拉框需要时间来响应变化，但是select元素一直都能被定位到，所以被会触发隐式等待，
		//因此使用强制等待。
		web.halt("1");
//		web.selectByText("//select[@name='cat_id_2']", "电脑整机");
		web.selectByText("(//select)[2]", "电脑整机");
		web.halt("1");
//		web.selectByText("//select[@name='cat_id_3']", "游戏本");
		web.selectByText("(//select)[3]", "游戏本");
		web.input("//input[@name='shop_price']", "5000");
		web.input("//input[@name='market_price']", "5500");
		//文本上传,先点击按钮，弹出iframe
		web.click("//input[contains(@title,'可查看大图')]");
		//切换到iframe中进行操作
		web.switchIframeAsEle("//iframe[contains(@src,'func=img')]");
		//等待
		web.halt("2");
		//上传文件
		web.uploadFile("//div[@id='filePicker']//input[@type='file']","f:\\google.jpg");
		//点击  确定使用
		web.click("//div[@class='saveBtn']");
		//弹窗操作完成了，切回主html页面层级
		web.switchToRoot();
		//然后在切到网页右侧的iframe中
		web.switchIframe("workspace");
		///
		web.click("//label[contains(string(),'是否包邮')]/../following-sibling::dd[@class='opt']//label[text()='是']");
		//切换到复文本框的iframe中
		web.switchIframe("ueditor_0");
		//通过js往复文本框中输入内容
		//在console里面输入  $x("//p")[0].innerText='chen'
		//document.getElementsByTagName("p")[0].innerText="123456"
//		web.runJsWithArg("arguments[0].innerText=\"这是一个VIP05的测试商品\"", "//p");
		String result=web.getJsReturnWithArg("arguments[0].innerText=\"这是一个VIP05的测试商品\"", "//p");
		System.out.println("返回的，输入在textarea的值："+result);
		
		//弹窗操作完成了，切回主html页面层级
		web.switchToRoot();
		//然后在切到网页右侧的iframe中
		web.switchIframe("workspace");
		web.halt("5");
		//确认提交//a[text()='确认提交']  //a[@id='submit']
		web.click("//a[@id='submit']");
	}

}
