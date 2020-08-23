package com.testing.ShopPage;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testing.webKeyword.WebkeyWord;


public class LoginPage  {
	public WebkeyWord kw;
	public String Url = "http://112.74.191.10:8000/Home/user/login.html";

	@FindBy(xpath = "//input[@id='username']")
	public WebElement user;

	@FindBy(xpath = "//input[@id='password']")
	public WebElement password;

	@FindBy(xpath = "//input[@placeholder='验证码']")
	public WebElement verifyCode;

	@FindBy(xpath = "//a[@name='sbtbutton']")
	public WebElement submitBtn;

	public LoginPage(WebkeyWord keyword) {
		kw=keyword;
		PageFactory.initElements(kw.driver, this);
	}

	public void load() {
		kw.visitWeb(Url);
	}

	public void login() {
		try {
			user.sendKeys("13800138006");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			password.sendKeys("123456");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		verifyCode.sendKeys("1");
		submitBtn.click();
		kw.halt("2");
	}

}
