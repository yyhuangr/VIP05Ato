package chen.test.class1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriver.SystemProperty;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testing.driverself.FFDriver;

public class SeleniumGeckDemo  {
	/*
	 * 该类是打开火狐浏览器，输入url:www.baidu.com，然后输入cheese 点击 百度一下
	 * 显式等待： 等待页面标题变成以小写cheese开头，如果超过10秒，依然没有符合条件，则报错。
	 */
    public static void main(String[] args) {
        // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface, 
        // not the implementation.
    	//设置临时的系统变量，让代码能够在系统中找到geckodriver.exe的位置
    	System.setProperty("webdriver.gecko.driver", "webDrivers/geckodriver.exe");
        //通过driver对象实例化，完成浏览器的启动过程和启动端口作为服务器端监听脚本命令的过程。
//        WebDriver driver = new FirefoxDriver();
    	//通过调用封装的FFDriver.java里面的类和方法  来实例化driver
    	  FFDriver ff=new FFDriver("", "webDrivers/geckodriver.exe");
    	  WebDriver  driver=ff.getdriver();
        // And now use this to visit Google
  //get()方法访问url
        driver.get("http://www.baidu.com");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        // Find the text input element by its name
        //基于输入框的name属性值为wd，来查找元素，实例化一个element对象
        WebElement element = driver.findElement(By.name("wd"));

        // Enter something to search for
        element.sendKeys("Cheese!");

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();

        // Check the title of the page
        //getTitle()方法获取页面标题
        System.out.println("Page title is: " + driver.getTitle());
        
        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        //显式等待，等待页面标题变成以cheese开头，如果超过10秒，依然没有符合条件，则报错。
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("cheese!");
            }
        });

        // Should see: "cheese! - Google Search"
        System.out.println("Page title is: " + driver.getTitle());
        
        //Close the browser
        //关闭整个浏览器及driver的进程
        driver.quit();
    }
}