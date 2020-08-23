package com.testing.app;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class BrowserDriver {
	
	private AndroidDriver driver;
	
	public BrowserDriver(String deviceName,String platformVersion,String driverpath)  {
		
		DesiredCapabilities cap = new DesiredCapabilities();
		// 必要参数
		cap.setCapability("deviceName", "127.0.0.1:62001");
		cap.setCapability("platformVersion", "5.1.1");
		cap.setCapability("browserName", "Browser");
		cap.setCapability("platformName", "Android");
		// 可选参数W
		// 不重新签名的设置
		cap.setCapability("noSign", true);
		// 不清除app数据
		cap.setCapability("noReset", true);
		// 当需要输入中文的时候，把这两个cap一起加上。
		cap.setCapability("unicodeKeyboard", true);
		cap.setCapability("resetKeyboard", true);
		// 电脑连接了多个设备的时候，指定设备。
		cap.setCapability("udid", "127.0.0.1:62001");
		//设定一下chromedriver的路径长度大于约定长度时，加上chromedriverExecutable的参数。
		if(driverpath.length()>6) {
		cap.setCapability("chromedriverExecutable", driverpath);
		}

		// 启动会话，启动androiddriver完成手机的连接和app的启动操作
		// 需要指定服务端的ip和端口用于完成连接。
		try {
			driver = new AndroidDriver<>(new URL("http://127.0.0.1:12345/wd/hub"), cap);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public AndroidDriver getdriver() {
		return driver;
	}
	
	
	

}
