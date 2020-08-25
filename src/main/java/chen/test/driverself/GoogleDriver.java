package chen.test.driverself;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
//这是一个 专门生成谷歌浏览器 驱动实例（driver）的类。
public class GoogleDriver { // Chrome浏览器驱动类
	/*
	 * 该类是创建谷歌浏览器的驱动实例。
	 * 给chen.test.webkeyword.java类中的openBrowser（）调用的。
	 */
	WebDriver driver=null;
	
	public GoogleDriver(String driverPath) {
		//设置chrome谷歌浏览器的路径
		System.setProperty("webdriver.chrome.driver", driverPath);
		//谷歌浏览器 参数对象
		ChromeOptions option=new ChromeOptions();
		// 去除Chrome浏览器上的被自动化软件操作警告，目前新版本chrome不支持
//		option.addArguments("disable-infobars");
		//配置用户文件
		option.addArguments("C:\\Users\\Administrator\\AppData\\Local\\Google\\Chrome\\User Data");
		// 也可以将浏览器路径下的User Data目录复制一份放到其它位置进行引用，这样不会和手动打开的浏览器产生冲突。
//		option.addArguments("--user-data-dir=D:\\chromeData\\copyData");
		// 最大化浏览器窗口
				option.addArguments("--start-maximized");
				
		//创建一个谷歌浏览器驱动实例
				try {
					this.driver=new ChromeDriver(option);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("--------log--error：创建driver失败！！");
				}
	}
	public WebDriver getDriver() {
		return this.driver;
	}
}
