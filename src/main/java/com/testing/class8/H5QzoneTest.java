package com.testing.class8;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.testing.common.AutoLogger;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class H5QzoneTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Runtime.getRuntime().exec("cmd /c start appium -p 12345");
		
		AndroidDriver driver = BrowserDriver();
		//其实通过browser方式打开的浏览器，已经是一个web自动化的chrome浏览器了，可以使用selenium的方式进行测试。
		driver.get("https://ui.ptlogin2.qq.com/cgi-bin/login?pt_hide_ad=1&style=9&appid=549000929&pt_no_auth=1&pt_wxtest=1&daid=5&s_url=https%3A%2F%2Fh5.qzone.qq.com%2Fmqzone%2Findex");
		//定位时也默认用web自动化的定位方案，将浏览器中的内容看成一个html来进行定位。
		driver.findElement(By.id("u")).sendKeys("2798145476");
//		driver.findElement(By.xpath("//input[@id='p']")).sendKeys("roy12345");
		String nowContext=driver.getContext();
		Set<String> contexts= driver.getContextHandles();
		AutoLogger.log.info("当前的context是"+nowContext+"所有的context有"+contexts);
		
		//如果要使用appium inspector的xml结构树来进行元素定位，切换到NATIVE_APP
		//appium录制的脚本如果要使用，就进行一下这个切换。
		//切换到NATIVE_APP层。
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.context("NATIVE_APP");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//*[@resource-id='p']")).clear();
		driver.findElement(By.xpath("//*[@resource-id='p']")).sendKeys("2798145476");

		//使用NATIVE_APP中的定位方法完成元素操作。
		driver.findElement(By.xpath("//*[@resource-id='go']")).click();
	}

	public static AndroidDriver<WebElement> BrowserDriver() throws MalformedURLException {
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
		String driverpath="";
		cap.setCapability("chromedriverExecutable", driverpath);

		// 启动会话，启动androiddriver完成手机的连接和app的启动操作
		// 需要指定服务端的ip和端口用于完成连接。
		AndroidDriver driver = new AndroidDriver<>(new URL("http://127.0.0.1:12345/wd/hub"), cap);
		// 隐式等待。
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		return driver;
	}

}
