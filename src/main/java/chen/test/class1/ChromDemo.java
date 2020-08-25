package chen.test.class1;

import java.security.PublicKey;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testing.driverself.GoogleDriver;

public class ChromDemo {
	/*
	 * 该类是打开谷歌浏览器，输入url:www.baidu.com，然后输入cheese 点击 百度一下
	 * 显式等待 等待页面标题变成以小写cheese开头，如果超过10秒，依然没有符合条件，则报错。
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 1.在当前系统里面设置谷歌浏览器的驱动
		System.setProperty("webdriver.chrome.driver", "webDrivers/chromedriver.exe");
		//2.给WebDriver赋值，创建一个谷歌的驱动
		WebDriver driver=new ChromeDriver();
		
		//使用GoogleDriver.java中的方法创建的driver实例
//		GoogleDriver gd=new GoogleDriver("webDrivers/chromedriver.exe");
//		WebDriver driver=gd.getdriver();
		
		//3.通过新创建的谷歌驱动打开一个网址（即：url）地址
		driver.get("http://www.baidu.com");
		//4.在新打开的网址里面找到name属性为的元素。
		WebElement el=driver.findElement(By.name("wd"));
		//5.在定位到的目标元素里面设置内容
		el.sendKeys("Cheese");
		//6.提交元素
		el.submit();
		//获取网页标题，并在控制台输出标题
		System.out.println("This Page Title is "+driver.getTitle());
		//内部类
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>(){
			public Boolean apply(WebDriver d) {
				return d.getTitle().toLowerCase().startsWith("cheese");
			} 
		});
		System.out.println("This Page Title is "+driver.getTitle());
		//关闭浏览器，同时关闭谷歌浏览器驱动的进程
		driver.quit();
	}

}
