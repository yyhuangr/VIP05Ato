package chen.test.class1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import chen.test.driverself.FirefDriver;

public class FirefoxDemo {
	/*
	 * 该类是打开火狐浏览器，输入url:www.baidu.com，然后输入cheese 点击 百度一下
	 * 显式等待： 等待页面标题变成以小写cheese开头，如果超过10秒，依然没有符合条件，则报错。
	 */
	public static void main(String[] args) {
		// 1.在当前系统里面设置火狐浏览器的驱动
		System.setProperty("webdriver.gecko.driver", "webDrivers/geckodriver.exe");
		//2.给WebDriver赋值，创建一个火狐浏览器的驱动
		WebDriver driver=new FirefoxDriver();
		
		//尝试使用FirefDriver.java文件里面的创建火狐浏览器驱动实例的方法。（该方法比较慢，因为要加载用户文件）
//		FirefDriver fd=new FirefDriver("","webDrivers/geckodriver.exe");
//		WebDriver driver=fd.getDriver();
		
		
		//3.通过新创建的火狐浏览器驱动打开一个网址（即：url）地址
		driver.get("http://www.baidu.com");
		//4.在新打开的网址里面找到name属性为wd的元素。
		WebElement le=driver.findElement(By.name("wd"));
		//5.在定位到的目标元素里面设置内容
		le.sendKeys("Cheese");
		//提交元素内容
		le.submit();
		//输出打开的url地址的标题
		System.out.println("page title is "+driver.getTitle());
		//使用内部类，显式等待，使用expectedCondition 预料内容查询之后的页码标题 是否为小写的cheese开头
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d ) {
				return d.getTitle().toLowerCase().startsWith("cheese");
			}
		});
		//再次输出打开的url地址的标题
		System.out.println("page title is "+driver.getTitle());
		//关闭浏览器，同时关闭谷歌浏览器驱动的进程
		driver.quit();
	}
}
