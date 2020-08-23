package com.testing.class6;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;

public class QQAppTest {

	public static void main(String[] args) throws IOException {
		//相当于在windows的运行工具中调用命令
		Runtime.getRuntime().exec("cmd /c start appium -p 12345");
		// TODO Auto-generated method stub
		//把客户端的参数设置设定好
		DesiredCapabilities cap=new DesiredCapabilities();
		//必要参数
		cap.setCapability("deviceName", "127.0.0.1:62001");
		cap.setCapability("platformVersion", "5.1.1");
		cap.setCapability("appPackage", "com.tencent.mobileqq");
		cap.setCapability("appActivity", ".activity.SplashActivity");
		cap.setCapability("platformName", "Android");
		//可选参数
		//不重新签名的设置
		cap.setCapability("noSign", true);
		//不清除app数据
		cap.setCapability("noReset", true);
		//当需要输入中文的时候，把这两个cap一起加上。
		cap.setCapability("unicodeKeyboard", true);
		cap.setCapability("resetKeyboard", true);
		//电脑连接了多个设备的时候，指定设备。
		cap.setCapability("udid", "127.0.0.1:62001");
		
		//启动会话，启动androiddriver完成手机的连接和app的启动操作
		//需要指定服务端的ip和端口用于完成连接。
		AndroidDriver<WebElement> driver=new AndroidDriver<>(new URL("http://127.0.0.1:12345/wd/hub"), cap);
		//隐式等待。
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		MobileElement el1 = (MobileElement) driver.findElementByAccessibilityId("请输入QQ号码或手机或邮箱");
//		el1.sendKeys("2798145476");
//		MobileElement el2 = (MobileElement) driver.findElementByAccessibilityId("密码 安全");
//		el2.sendKeys("roy12345");
//		MobileElement el3 = (MobileElement) driver.findElementByAccessibilityId("登 录");
//		el3.click();
//		WebElement ele=driver.findElement(By.id("com.tencent.mobileqq:id/b_y"));
//		Point p=ele.getLocation();
//		Dimension d=ele.getSize();
//		int x=p.getX();
//		int y=p.getY();
//		int xd=d.getWidth();
//		int yd=d.getHeight();
//		System.out.println(p+""+d+""+x+y+xd+yd);
//		TouchAction act=new TouchAction(driver);
//		PointOption targetp=PointOption.point(x+xd/10, y+yd/2);
//		act.tap(targetp).perform();
	}

}
