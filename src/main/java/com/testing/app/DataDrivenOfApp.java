package com.testing.app;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;
import com.testing.common.AutoLogger;
import com.testing.common.ExcelWriter;

import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class DataDrivenOfApp {
	public AndroidDriver driver;
	public int line=0;
	public ExcelWriter appExcel;
	
	
	public DataDrivenOfApp(ExcelWriter excel) {
		appExcel=excel;
	}
	
	public void setline(int rowNo) {
		line=rowNo;
	}

	//强制等待
	public void wait(String time) {
		int t = 0;
		try {
			t = Integer.parseInt(time);
			Thread.sleep(t);
			appExcel.writeCell(line, 10, "PASS");
		} catch (Exception e) {
			AutoLogger.log.error("强制等待出错！");
			AutoLogger.log.error(e,e.fillInStackTrace());
			appExcel.writeFailCell(line, 10, "FAIL");
		}
	}

	// 脚本执行CMD命令的函数
	public void runCmd(String str) {
		String cmd = "cmd /c start " + str;
		Runtime runtime = Runtime.getRuntime();
		try {
			AutoLogger.log.info("执行cmd命令:"+str);
			runtime.exec(cmd);
			appExcel.writeCell(line, 10, "PASS");
		} catch (Exception e) {
			AutoLogger.log.error("cmd命令执行失败");
			AutoLogger.log.error(e,e.fillInStackTrace());
			appExcel.writeFailCell(line, 10, "FAIL");
		}
	}
	
	//通过cmd启动appium的服务
	public void StartAppium(String port, String time) {
		// 启动appium的服务端
		AutoLogger.log.info("启动appiumserver服务");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd+HH-mm-ss");
		String createdate = sdf.format(date);
		// 拼接文件名，形式为：工作目录路径+方法名+执行时间.png
		String appiumLogFile = "log/" + createdate + "AppiumLog.txt";
		String startAppiumCMD ="appium -a 127.0.0.1 -p " + port + " --log " + appiumLogFile +" --local-timezone";
		try {
			runCmd(startAppiumCMD);
			int t = 1000;
			t = Integer.parseInt(time);
			Thread.sleep(t);
			appExcel.writeCell(line, 10, "PASS");
		} catch (InterruptedException e) {
			AutoLogger.log.error("启动appium服务失败，请检查");
			AutoLogger.log.error(e,e.fillInStackTrace());
			appExcel.writeFailCell(line, 10, "FAIL");
		}
	}
	
	// 启动被测APP
	public void runAPP(String platformVersion, String deviceName, String appPackage,String appActivity,String appiumServerIP,String time) {
			try {
				AutoLogger.log.info("启动待测App");
				AppDriver app = new AppDriver(platformVersion, deviceName, appPackage, appActivity, appiumServerIP, time);
				driver = app.getdriver();
				AutoLogger.log.info("启动待测App成功");
				appExcel.writeCell(line, 10, "PASS");
			} catch (Exception e) {
				AutoLogger.log.error("启动待测App失败");
				AutoLogger.log.error(e, e.fillInStackTrace());
				appExcel.writeFailCell(line, 10, "FAIL");
			}
	}

	public void runBrowser(String platformVersion,String deviceName,  String appiumServerIP,String waitTime) {
		try {
			AutoLogger.log.info("启动安卓浏览器");
			BrowserDriver browser = new BrowserDriver(platformVersion, deviceName, appiumServerIP, waitTime);
			driver = browser.getdriver();
			appExcel.writeCell(line, 10, "PASS");
		} catch (Exception e) {
			AutoLogger.log.error("启动安卓浏览器失败");
			AutoLogger.log.error(e, e.fillInStackTrace());
			appExcel.writeFailCell(line, 10, "FAIL");
		}
	}
	
	
	public void visitH5(String url) {
		try {
			AutoLogger.log.info("安卓浏览器访问"+url);
			driver.get(url);
			appExcel.writeCell(line, 10, "PASS");
		} catch (Exception e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
			appExcel.writeFailCell(line, 10, "FAIL");
		}
	}
	
	public void input(String xpath, String text) {
		try {
			explicityWait(xpath);
			driver.findElement(By.xpath(xpath)).clear();
			driver.findElement(By.xpath(xpath)).sendKeys(text);
			appExcel.writeCell(line, 10, "PASS");
		} catch (Exception e) {
			AutoLogger.log.error("输入"+text+"失败");
			AutoLogger.log.error(e, e.fillInStackTrace());
			appExcel.writeFailCell(line, 10, "FAIL");
		}
	}

	public void click(String xpath) {
		try {
			explicityWait(xpath);
			driver.findElement(By.xpath(xpath)).click();
			appExcel.writeCell(line, 10, "PASS");
		} catch (Exception e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
			appExcel.writeFailCell(line, 10, "FAIL");
		}
	}

	// 调用adb滑动
	public void adbSwipe(int i, int j, int k, int l, int m) {
		try {
			this.runCmd("adb shell input swipe " + i + " " + j + " " + k + " " + l + " " + m);
			appExcel.writeCell(line, 10, "PASS");
		} catch (Exception e) {
			AutoLogger.log.error("通过adb执行滑动失败");
			AutoLogger.log.error(e, e.fillInStackTrace());
			appExcel.writeFailCell(line, 10, "FAIL");
		}
	}

	// 调用adb模拟按键
	public void adbPressKey(String keycode) {
		try {
			int k = Integer.parseInt(keycode);
			String cmd = " adb shell input keyevent " + k;
			runCmd(cmd);
			Thread.sleep(2000);
			appExcel.writeCell(line, 10, "PASS");
		} catch (InterruptedException e) {
			AutoLogger.log.error("通过adb执行按键事件失败");
			AutoLogger.log.error(e, e.fillInStackTrace());
			appExcel.writeFailCell(line, 10, "FAIL");
		}
	}

	public void adbTap(String xAxis, String yAxis) {
		try {
			int x = Integer.parseInt(xAxis);
			int y = Integer.parseInt(yAxis);
			runCmd("adb shell input tap " + x + " " + y);
			appExcel.writeCell(line, 10, "PASS");
		} catch (Exception e) {
			AutoLogger.log.error("通过adb执行点击失败");
			AutoLogger.log.error(e, e.fillInStackTrace());
			appExcel.writeFailCell(line, 10, "FAIL");
		}
	}

	public void quitApp() {
		try {
			driver.closeApp();
			appExcel.writeFailCell(line, 10, "FAIL");
		} catch (Exception e) {
			AutoLogger.log.error("关闭app失败");
			AutoLogger.log.error(e, e.fillInStackTrace());
			
		}
	}

	public void killAppium() {
		try {
			runCmd("taskkill /F /IM node.exe");
			appExcel.writeCell(line, 10, "PASS");
		} catch (Exception e) {
			AutoLogger.log.error("关闭appiumserver服务失败");
			AutoLogger.log.error(e, e.fillInStackTrace());
			appExcel.writeFailCell(line, 10, "FAIL");
		}
	}

	// 断言
	public void assertSame(String xpath, String paramRes) {
		try {
			explicityWait(xpath);
			String result = driver.findElement(By.xpath(xpath)).getText();
			System.out.println(result);
			if (result.equals(paramRes)) {
				AutoLogger.log.info("测试用例执行成功");
				appExcel.writeCell(line, 10, "PASS");
			} else {
				AutoLogger.log.info("测试用例执行失败");
				appExcel.writeFailCell(line, 10, "FAIL");
			}
		} catch (Exception e) {
			AutoLogger.log.error("执行断言时报错");
			AutoLogger.log.error(e, e.fillInStackTrace());
			appExcel.writeFailCell(line, 10, "FAIL");
		}
	}

	// 通过appium的方法进行滑屏
	public void appiumSwipe(String iniX, String iniY, String finX, String finY) {
		try {
			int x = Integer.parseInt(iniX);
			int y = Integer.parseInt(iniY);
			int x1 = Integer.parseInt(finX);
			int y1 = Integer.parseInt(finY);
			TouchAction action = new TouchAction(driver);
			PointOption pressPoint=PointOption.point(x, y);
			PointOption movePoint=PointOption.point(x1, y1);
			action.longPress(pressPoint).moveTo(movePoint).release().perform();
			appExcel.writeCell(line, 10, "PASS");
		} catch (Exception e) {
			AutoLogger.log.error("执行Appium滑动方法失败");
			AutoLogger.log.error(e, e.fillInStackTrace());
			appExcel.writeFailCell(line, 10, "FAIL");
		}
	}

	// 使用appium的方法点击坐标
	public void appiumTap(String x, String y) {
		try {
			int xAxis = Integer.parseInt(x);
			int yAxis = Integer.parseInt(y);
			TouchAction action = new TouchAction(driver);
			PointOption pressPoint=PointOption.point(xAxis, yAxis);
			// action类分解动作，先长按，再移动到指定位置，再松开
			action.tap(pressPoint).release().perform();
			appExcel.writeCell(line, 10, "PASS");
		} catch (NumberFormatException e) {
			AutoLogger.log.error("执行Appium点击坐标方法失败");
			AutoLogger.log.error(e, e.fillInStackTrace());
			appExcel.writeFailCell(line, 10, "FAIL");
		}
	}

	// 使用appium方法长按
	public void appiumHold(String x, String y, String time) {
		try {
			int xAxis = Integer.parseInt(x);
			int yAxis = Integer.parseInt(y);
			int t = Integer.parseInt(time);
			PointOption pressPoint=PointOption.point(xAxis, yAxis);
			Duration last = Duration.ofSeconds(t);
			TouchAction action = new TouchAction(driver);
			action.longPress(pressPoint).waitAction(WaitOptions.waitOptions(last)).perform();
			appExcel.writeCell(line, 10, "PASS");
		} catch (NumberFormatException e) {
			AutoLogger.log.error("执行Appium滑动方法失败");
			AutoLogger.log.error(e, e.fillInStackTrace());
			appExcel.writeFailCell(line, 10, "FAIL");
		}
	}
	
	/**
	 * 实现显式等待的方法，在每次定位元素时，先尝试找元素，给10秒钟的最长等待。
	 */
	public void explicityWait(String xpath) {
		try {
			WebDriverWait eWait = new WebDriverWait(driver, 10);
			eWait.until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver d) {
					return d.findElement(By.xpath(xpath));
				}
			});
		} catch (Exception e) {
			AutoLogger.log.error(e,e.fillInStackTrace());
		}
	}
	
	/**
	 * 实现隐式等待的方法
	 */
	public void implicitlyWait() {
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			appExcel.writeFailCell(line, 10, "PASS");
		} catch (Exception e) {
			AutoLogger.log.error(e,e.fillInStackTrace());
			appExcel.writeFailCell(line, 10, "FAIL");
		}
	}

	public void printContexts() {
		Set<String> contexts=driver.getContextHandles();
		for(String s:contexts) {
			System.out.println(s);
			AutoLogger.log.info("context:"+s);
		}
	}

	public void switchContext(String contextName) {
		try {
			AutoLogger.log.info("切换到"+contextName+"context");
			driver.context(contextName);
		} catch (Exception e) {
			AutoLogger.log.error("切换context失败。");
		}
	}
	
	public void saveScrShot(String method) {
		// 获取当前的执行时间
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd+HH-mm-ss");
		// 当前时间的字符串
		String createdate = sdf.format(date);
		// 拼接文件名，形式为：工作目录路径+方法名+执行时间.png
		String scrName = "SCRshot/" + method + createdate + ".png";
		// 以当前文件名创建文件
		File scrShot = new File(scrName);
		// 将截图保存到临时文件
		File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			Files.copy(tmp, scrShot);
		} catch (IOException e) {
			AutoLogger.log.error(e,e.fillInStackTrace());
			AutoLogger.log.error("截图失败！");
		}
	}

	/**
	 * 双指操作，需要分别指定两个手指的动作起止坐标。
	 */
	public void doubleFinger() {
		//multitouchaction，用于拼接多个touchaction，让他们同时发生。
		try {
			MultiTouchAction multiAction=new MultiTouchAction(driver);
			//创建两个touchaction，分别实现两个手指的动作。
			TouchAction actionone =new TouchAction(driver);
			//创建等待时间的对象。
			Duration last = Duration.ofMillis(300);
			WaitOptions waitOptions=WaitOptions.waitOptions(last);
			//创建第一个手指的移动起止点。x坐标增大，y坐标减小，往右上方划
			PointOption pressPointone=PointOption.point(300, 900);
			PointOption movePointone=PointOption.point(400, 800);
			//滑动操作由长按起点->移动到终点->松开组成。
			actionone.press(pressPointone).waitAction(waitOptions).moveTo(movePointone).waitAction(waitOptions).release();
			//创建第二个手指的动作。
			TouchAction actiontwo =new TouchAction(driver);
			//x坐标减小，y坐标增大，往左下方划
			PointOption pressPointtwo=PointOption.point(500, 600);
			PointOption movePointtwo=PointOption.point(400, 800);
			//滑动操作由长按起点->移动到终点->松开组成。
			actiontwo.press(pressPointtwo).waitAction(waitOptions).moveTo(movePointtwo).waitAction(waitOptions).release();
			//将创建好的两个不同的touchaction，添加到multiaction里，形成一组同步动作，从而完成操作。
			multiAction.add(actionone).add(actiontwo).perform();
			appExcel.writeCell(line, 10, "PASS");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AutoLogger.log.error("执行Appium滑动方法失败");
			AutoLogger.log.error(e, e.fillInStackTrace());
			appExcel.writeFailCell(line, 10, "FAIL");
		}
		
	}

}
