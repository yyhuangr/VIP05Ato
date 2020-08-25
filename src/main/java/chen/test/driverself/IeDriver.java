package chen.test.driverself;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class IeDriver {// IE浏览器驱动类
	/*
	 * 该类是创建IE浏览器的驱动实例。
	 * 给chen.test.webkeyword.java类中的openBrowser（）调用的。
	 */
	public WebDriver driver=null;
	//webdriver连接启动浏览器时，启动的服务。
	public InternetExplorerDriverService service=null;
	
	public IeDriver(String driverPath) {
		
		// 设置 IE 的路径
		System.setProperty("webdriver.ie.driver", driverPath);
		//创建ie的配置参数对象添加相关设置
		DesiredCapabilities ieCapablilities=new DesiredCapabilities().internetExplorer();
		//设置忽略区域安全级别校验
		ieCapablilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		//设置忽略缩放大小校验
		ieCapablilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		//通过Options选项加载配置参数，下面两种设置方式与设置capabilities方式等价。
		InternetExplorerOptions ieOptions=new InternetExplorerOptions(ieCapablilities);
//		ieOptions.introduceFlakinessByIgnoringSecurityDomains();
//		ieOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

	//创建一个IEDriver的服务，用于连接IE浏览器
		try {//使用指定的iedriver文件以及任意空闲端口完成服务的启动
			service=new InternetExplorerDriverService.Builder().usingDriverExecutable(new File(driverPath)).usingAnyFreePort().build();
			service.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("log--error：创建driver失败！！");
		}
		
		// 基于options选项与driver服务 创建一个 IE 的浏览器webDriver实例，完成浏览器启动。
		try {
			this.driver=new InternetExplorerDriver(service,ieOptions);
			// 让浏览器访问空白页面
//			driver.get("about.blank");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("-----------log--error：创建IE的driver失败！！");
		}
	}
	//用于外部调用 ，获取driver实例的方法
	public WebDriver getDriver() {
		return this.driver;
	}
	//由于手动启动了driverserver服务，所以需要 在调用quit()方法关闭浏览器以及驱动进程，还需要关闭service
	public void closeIE() {
		driver.quit();
		service.stop();
	}
	
}
