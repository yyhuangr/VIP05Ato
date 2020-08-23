package com.testing.class7;

import com.testing.app.KeywordOfApp;

public class QQSayTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KeywordOfApp app=new KeywordOfApp();
		app.StartAppium("12345", "5");
		app.runAPP("5.1.1", "127.0.0.1:62001", "com.tencent.mobileqq", ".activity.SplashActivity", "http://127.0.0.1:12345/wd/hub", "5");
		//点击搜索框
		app.click("//android.widget.EditText[@content-desc=\"搜索\"]");
		//输入搜索内容。
		app.input("//android.widget.RelativeLayout[@content-desc=\"搜索聊天或者联系人\"]/android.widget.EditText", "Roy");
		//点击头像
		app.click("//*[@resource-id='com.tencent.mobileqq:id/dpr']/android.widget.ImageView[@resource-id='com.tencent.mobileqq:id/image']");
		//点击设置
		app.click("//android.widget.ImageView[@content-desc=\"聊天设置\"]");
		app.halt("3");
		app.click("//*[@text='青鸿']");
		app.click("//android.widget.LinearLayout[@content-desc=\"QQ空间\"]");
		app.click("//*[@resource-id='com.tencent.mobileqq:id/c7p']");
		app.quitApp();
	}

}
