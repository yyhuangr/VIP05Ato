package com.testing.class5;


import com.testing.ShopPage.HomePage;
import com.testing.ShopPage.LoginPage;
import com.testing.adminPage.AddGoodsPage;
import com.testing.adminPage.AdminLoginPage;
import com.testing.common.AutoLogger;
import com.testing.webKeyword.WebkeyWord;

import sun.util.logging.resources.logging;

public class TestPO {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//所有被调用的页面都使用同一个keyword打开的浏览器，通过参数传递的方式传递给页面使用。
		WebkeyWord keyword= new WebkeyWord();
		keyword.openBrowser("chrome");
		AutoLogger.log.info("——————————————————————后台登录用例——————————————");
		AdminLoginPage adminLogin=new AdminLoginPage(keyword);
		adminLogin.load();
		adminLogin.login();
		AutoLogger.log.info("——————————————————————后台添加商品用例——————————————");
		AddGoodsPage addGoods=new AddGoodsPage(keyword);
		addGoods.load();
		addGoods.addGoods();
		AutoLogger.log.info("——————————————————————前台登录用例——————————————");
		LoginPage loginpage=new LoginPage(keyword);
		loginpage.load();
		loginpage.login();
		AutoLogger.log.info("——————————————————————前台购买商品用例——————————————");
		HomePage homepage=new HomePage(keyword);
		homepage.load();
		homepage.joinCart();
		keyword.closeBrowser();
	}

}
