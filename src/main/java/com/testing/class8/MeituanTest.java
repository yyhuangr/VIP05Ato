package com.testing.class8;

import com.testing.app.KeywordOfApp;

public class MeituanTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KeywordOfApp app=new KeywordOfApp();
		app.StartAppium("12345", "5");
		app.runAPP("5.1.1", "127.0.0.1:62001", "com.tencent.mm", ".ui.LauncherUI", "http://127.0.0.1:12345/wd/hub", "15");
		app.appiumSwipe("400", "700", "400", "1400");
		app.click("//*[@text='智行火车票']");
		app.halt("10");
		app.click("//*[@text='高铁动车']");
//		app.click("//*[@text='机票']");
		//查找文本为立即领取的按钮的弟弟，不管是任何类型的元素
		app.closeAd("//*[@text='立即领取']/following-sibling::*");
		app.click("//*[@text='火车票查询']");
	}

}
