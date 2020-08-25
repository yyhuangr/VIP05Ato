package chen.test.class1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import chen.test.driverself.IeDriver;


public class IeDemo {
	/*
	 * 该类是打开IE浏览器，输入url:www.baidu.com，然后输入cheese 点击 百度一下
	 * 显式等待： 等待页面标题变成以小写cheese开头，如果超过10秒，依然没有符合条件，则报错。
	 */
	public static void main(String[] args) {
		System.setProperty("webdriver.ie.driver", "webDrivers/IEDriverServer.exe");
		
//		WebDriver driver=new  InternetExplorerDriver();
		
		//调用IeDriver.java文件中的方法创建 IE浏览器的驱动实例
		IeDriver ie=new IeDriver("webDrivers/IEDriverServer.exe");
		WebDriver driver=ie.getDriver();
		
		driver.get("http://www.baidu.com");
		WebElement le=driver.findElement(By.name("wd"));
		le.sendKeys("Cheese");
		le.submit();
		System.out.println("page tile is "+driver.getTitle());
		
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getTitle().toLowerCase().startsWith("cheese");
			}
		});
		System.out.println("page tile is "+driver.getTitle());
		
		driver.quit();
	}
}
