package chen.test.adminPage;

import chen.test.common.AutoLogger;
import chen.test.webkeyword.WebKeyWord;

public class POFactorPageTest {
	public static void main(String[] args) {
		WebKeyWord web=new WebKeyWord();
		//创建一个商城后台登陆page实例类
		AdminLoginPage alp=new AdminLoginPage(web);
		//打开谷歌浏览器
		web.openBrowser("chrome");
		AutoLogger.log.info("打开谷歌浏览器");
		
		//加载、打开已经指定好的url地址
		alp.load();
		AutoLogger.log.info("打开已经设定好的URL地址=");
		//登陆
		alp.login();
		AutoLogger.log.info("登陆后台");
		
		AddGoodsPage ap=new AddGoodsPage(web);
		AutoLogger.log.info("添加商品");
		ap.load();
		ap.addGoods();
		
		
	}
}
